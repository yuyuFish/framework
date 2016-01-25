package org.framework.authorize.auth.services.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.AmGroupDao;
import org.framework.authorize.auth.model.AmGroup;
import org.framework.authorize.auth.services.AmGroupService;
import org.framework.authorize.base.model.ConstDate;
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
			List<AmGroup> groups=amGroupDao.getByWhere("WHERE  DATA_STATE='"+ConstDate.DATA_NORMAL+"' AND PARENT_GROUP_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1", null,null );
			if(groups!=null&&groups.size()>0){
				AmGroup maxGroup=groups.get(0);
				leftValue=maxGroup.getRightValue()+1;
				rightValue=maxGroup.getRightValue()+2;
			}
		}else{
			AmGroup parentGroup=amGroupDao.getById(amGroup.getParentGroupId());
			leftValue=parentGroup.getRightValue();
			rightValue=leftValue+1;
			//amGroupDao.updateBySql("update am_group set RIGHT_VALUE=RIGHT_VALUE+2 where ", paraName, parameters)
		}

	}

}
