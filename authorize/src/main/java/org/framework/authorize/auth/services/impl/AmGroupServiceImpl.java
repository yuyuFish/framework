package org.framework.authorize.auth.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.AmGroupDao;
import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.auth.services.AmGroupService;
import org.framework.authorize.base.model.ConstData;
import org.springframework.stereotype.Service;
/**
 * 
 * @author ztgoto
 * @version  
 * @date  2016年1月25日 下午4:56:49
 */
@Service("amGroupService")
public class AmGroupServiceImpl implements AmGroupService {

	protected static final Log LOG=LogFactory.getLog(AmGroupServiceImpl.class);
	
	@Resource(name="amGroupDao")
	private AmGroupDao amGroupDao;
	
	@Override
	public void addGroup(AmGroup amGroup) {
		Long leftValue=1L;
		Long rightValue=2L;
		
		if(amGroup.getParentGroupId()==null){
			LOG.debug(">>>>create root node>>>>");
			String productIdWhere=amGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par.productId}";
			String sql=productIdWhere+" AND PARENT_GROUP_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
			Map<String, Object> parameters=new HashMap<String, Object>();
			parameters.put("productId", amGroup.getProductId());
			List<AmGroup> groups=amGroupDao.getByWhere(sql, "par",parameters );
			if(groups!=null&&groups.size()>0){
				AmGroup maxGroup=groups.get(0);
				leftValue=maxGroup.getRightValue()+1;
				rightValue=maxGroup.getRightValue()+2;
			}
		}else{
			LOG.debug(">>>>create subnode>>>>");
			AmGroup parentGroup=amGroupDao.getById(amGroup.getParentGroupId());
			if(parentGroup==null) throw new RuntimeException(">>>>["+amGroup.getParentGroupId()+"] node non-existent");
			leftValue=parentGroup.getRightValue();
			rightValue=leftValue+1;
			
			String productIdWhere=amGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[1]}";
			
			String sql="update am_group set RIGHT_VALUE=RIGHT_VALUE+2 where RIGHT_VALUE>=#{par[0]} and "+productIdWhere;
			amGroupDao.updateBySql(sql, "par", leftValue,amGroup.getProductId());
			sql="update am_group set LEFT_VALUE=LEFT_VALUE+2 where LEFT_VALUE>=#{par[0]} and "+productIdWhere;
			amGroupDao.updateBySql(sql, "par", leftValue,amGroup.getProductId());
		}
		LOG.debug(">>>>LEFT_VALUE:"+leftValue+",RIGHT_VALUE:"+rightValue);
		amGroup.setLeftValue(leftValue);
		amGroup.setRightValue(rightValue);
		Date currentDate=new Date();
		amGroup.setCreateTime(currentDate);
		amGroup.setEditTime(currentDate);
		amGroup.setDataState(ConstData.DATA_NORMAL);
		int result=amGroupDao.save(amGroup);
		LOG.debug(">>>>Add Result Value:"+result);
	}

	@Override
	public void updateGroup(AmGroup amGroup) {
		if(amGroup.getGroupId()==null) throw new RuntimeException(">>>>groupId is null");
		AmGroup dbGroup=amGroupDao.getById(amGroup.getGroupId());
		if(dbGroup==null)throw new RuntimeException(">>>>["+amGroup.getGroupId()+"] update node non-existent");
		dbGroup.setGroupName(amGroup.getGroupName());
		dbGroup.setGroupInfo(amGroup.getGroupInfo());
		dbGroup.setGroupCode(amGroup.getGroupCode());
		dbGroup.setIsInherit(amGroup.getIsInherit());
		dbGroup.setEditTime(new Date());
		int result=amGroupDao.update(amGroup);
		LOG.debug(">>>>Update Result Value:"+result);
	}

	@Override
	public void deleteLoic(String groupId) {
		String sql="update am_group set DATA_STATE=#{par[0]},DELETE_TIME=#{par[1]} where GROUP_ID=#{par[2]}";
		amGroupDao.updateBySql(sql, "par", ConstData.DATA_DELETE,new Date(),groupId);
	}

	@Override
	public void deletePhysical(String groupId) {
		AmGroup dbGroup=amGroupDao.getById(groupId);
		if(dbGroup==null) return;
		Long offset=dbGroup.getRightValue()-dbGroup.getLeftValue()+1;
		LOG.debug(">>>>delete offset:"+offset);
		
		String productIdWhere=dbGroup.getProductId()==null?"PRODUCT_ID IS NULL":"PRODUCT_ID=#{par[2]}";
		
		String sql="delete from am_group where LEFT_VALUE>=#{par[0]} and RIGHT_VALUE<=#{par[1]} and "+productIdWhere;
		int result=amGroupDao.deleteBySql(sql, "par", dbGroup.getLeftValue(),dbGroup.getRightValue(),dbGroup.getProductId());
		LOG.debug(">>>>delete node count:"+result);
		sql="update am_group set LEFT_VALUE=LEFT_VALUE-#{par[0]} where LEFT_VALUE>#{par[1]} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par",offset,dbGroup.getRightValue(),dbGroup.getProductId());
		sql="update am_group set RIGHT_VALUE=RIGHT_VALUE-#{par[0]} where RIGHT_VALUE>#{par[1]} and "+productIdWhere;
		amGroupDao.updateBySql(sql, "par",offset,dbGroup.getRightValue(),dbGroup.getProductId());
		
	}

}
