package org.framework.authorize.auth.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.framework.authorize.auth.dao.SysProductLineDao;
import org.framework.authorize.auth.model.SysProductLine;
import org.framework.authorize.auth.services.SysProductLineService;
import org.framework.authorize.base.model.ConstData;
import org.framework.authorize.base.utils.Pager;
import org.springframework.stereotype.Service;
@Service("sysProductLineService")
public class SysProductLineServiceImpl implements SysProductLineService {

	protected static final Log LOG=LogFactory.getLog(SysProductLineServiceImpl.class);
	@Resource(name="sysProductLineDao")
	private SysProductLineDao sysProductLineDao;
	
	@Override
	public SysProductLine getById(String productId) {
		
		return sysProductLineDao.getById(productId);
	}

	@Override
	public List<SysProductLine> getByWhere(String whereSql, String paraKey,
			Object... parameters) {
		
		return sysProductLineDao.getByWhere(whereSql, paraKey, parameters);
	}

	@Override
	public List<SysProductLine> getByWhere(String whereSql, String paraKey,
			Map<String, Object> parameters) {
		
		return sysProductLineDao.getByWhere(whereSql, paraKey, parameters);
	}

	@Override
	public void getByWherePage(Pager<SysProductLine> page, String whereSql,
			String paraKey, Object... parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(PRODUCT_ID) as dataCount FROM sys_product_line "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=sysProductLineDao.selectBySql(countSql, paraKey, parameters);
		if(countResult!=null&&countResult.size()>0){
			Map<String, Object> result=countResult.get(0);
			Object dataCount=result.get("dataCount");
			if(dataCount!=null){
				totalCount=Long.parseLong(dataCount.toString());
			}
		}
		LOG.debug(">>>>totalCount:"+totalCount);
		page.setTotalCount(totalCount);
		page.update();
		if(totalCount>0){
			List<SysProductLine> result=sysProductLineDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);

	}

	@Override
	public void getByWherePage(Pager<SysProductLine> page, String whereSql,
			String paraKey, Map<String, Object> parameters) {
		page.init();
		String where="";
		if(whereSql!=null&&!"".equals(whereSql.trim())){
			where="WHERE "+whereSql.replaceAll("^\\s*(?i)where\\s+", "");
		}
		
		String countSql="SELECT COUNT(PRODUCT_ID) as dataCount FROM sys_product_line "+where;
		
		LOG.debug(">>>>countSql:"+countSql);
		
		long totalCount=0;
		
		List<Map<String, Object>> countResult=sysProductLineDao.selectBySql(countSql, paraKey, parameters);
		if(countResult!=null&&countResult.size()>0){
			Map<String, Object> result=countResult.get(0);
			Object dataCount=result.get("dataCount");
			if(dataCount!=null){
				totalCount=Long.parseLong(dataCount.toString());
			}
		}
		LOG.debug(">>>>totalCount:"+totalCount);
		page.setTotalCount(totalCount);
		page.update();
		if(totalCount>0){
			List<SysProductLine> result=sysProductLineDao.getByWherePage(whereSql, page.getStartIndex(), page.getPageSize(), paraKey, parameters);
			LOG.debug(">>>>result list:"+(result==null?"null":result.size()));
			page.setData(result);
			page.update();
		}
		
		LOG.debug(">>>>page info:"+page);

	}

	@Override
	public void add(SysProductLine sysProductLine) {
		Long leftValue=1L;
		Long rightValue=2L;
		
		if(sysProductLine.getParentId()==null){
			LOG.debug(">>>>create root node>>>>");
			
			String sql="PARENT_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
			
			List<SysProductLine> products=sysProductLineDao.getByWhere(sql,"","");
			if(products!=null&&products.size()>0){
				SysProductLine maxProductLine=products.get(0);
				leftValue=maxProductLine.getRightValue()+1;
				rightValue=maxProductLine.getRightValue()+2;
			}
		}else{
			LOG.debug(">>>>create subnode>>>>");
			SysProductLine parentProductLine=sysProductLineDao.getById(sysProductLine.getParentId());
			if(parentProductLine==null) throw new RuntimeException(">>>>["+sysProductLine.getParentId()+"] node non-existent");
			leftValue=parentProductLine.getRightValue();
			rightValue=leftValue+1;
			
			
			
			String sql="update sys_product_line set RIGHT_VALUE=RIGHT_VALUE+2 where RIGHT_VALUE>=#{par[0]}";
			sysProductLineDao.updateBySql(sql, "par", leftValue);
			sql="update sys_product_line set LEFT_VALUE=LEFT_VALUE+2 where LEFT_VALUE>=#{par[0]}";
			sysProductLineDao.updateBySql(sql, "par", leftValue);
		}
		LOG.debug(">>>>LEFT_VALUE:"+leftValue+",RIGHT_VALUE:"+rightValue);
		sysProductLine.setLeftValue(leftValue);
		sysProductLine.setRightValue(rightValue);
		Date currentDate=new Date();
		sysProductLine.setCreateTime(currentDate);
		sysProductLine.setEditTime(currentDate);
		sysProductLine.setDataState(ConstData.DATA_NORMAL);
		int result=sysProductLineDao.save(sysProductLine);
		LOG.debug(">>>>Add Result Value:"+result);

	}

	@Override
	public void update(SysProductLine sysProductLine) {
		if(sysProductLine.getProductId()==null) throw new RuntimeException(">>>>productId is null");
		SysProductLine dbProductLine=sysProductLineDao.getById(sysProductLine.getProductId());
		if(dbProductLine==null)throw new RuntimeException(">>>>["+sysProductLine.getProductId()+"] update node non-existent");
		dbProductLine.setProductName(sysProductLine.getProductName());
		dbProductLine.setProductInfo(sysProductLine.getProductInfo());
		dbProductLine.setProductProtocol(sysProductLine.getProductProtocol());
		dbProductLine.setProductHostName(sysProductLine.getProductHostName());
		dbProductLine.setProductIp(sysProductLine.getProductIp());
		dbProductLine.setProductPort(sysProductLine.getProductPort());
		dbProductLine.setProductCode(sysProductLine.getProductCode());
		dbProductLine.setEditTime(new Date());
		
		int result=sysProductLineDao.update(dbProductLine);
		LOG.debug(">>>>Update Result Value:"+result);

	}

	@Override
	public void changePoint(String sourceNodeId, String parentId,
			String targetNodeId) {
		long beginTime=System.currentTimeMillis();
		SysProductLine dbSourceProduct=sysProductLineDao.getById(sourceNodeId);
		if(dbSourceProduct==null) return;
		
		
		SysProductLine dbParentProduct=null;
		SysProductLine dbTargetProduct=null;
		if(parentId!=null&&!"".equals(parentId.trim())){
			dbParentProduct=sysProductLineDao.getById(parentId);
			if(dbParentProduct==null) throw new RuntimeException(">>>>["+parentId+"] Parent node non-existent");
			if(dbParentProduct.getLeftValue()>=dbSourceProduct.getLeftValue()
					&&dbParentProduct.getRightValue()<=dbSourceProduct.getRightValue()){
				throw new RuntimeException(">>>>parentId Can't be a child node");
			}
		}else{
			parentId=null;
		}
		if(targetNodeId!=null&&!"".equals(targetNodeId.trim())){
			dbTargetProduct=sysProductLineDao.getById(targetNodeId);
			if(dbTargetProduct==null) throw new RuntimeException(">>>>["+targetNodeId+"] Target node non-existent");
			if(dbTargetProduct.getLeftValue()>=dbSourceProduct.getLeftValue()
					&&dbTargetProduct.getRightValue()<=dbSourceProduct.getRightValue()){
				throw new RuntimeException(">>>>targetNodeId Can't be a child node");
			}
		}else{
			targetNodeId=null;
		}
		
		if(dbTargetProduct!=null
				&&(dbTargetProduct.getParentId()==null&&parentId!=null
				||dbTargetProduct.getParentId()!=null&&!dbTargetProduct.getParentId().equals(parentId))){
			throw new RuntimeException(">>>>targetNodeId parentId Don't match");
		}
		
		Long offset=dbSourceProduct.getRightValue()-dbSourceProduct.getLeftValue()+1;
		
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("leftValue", dbSourceProduct.getLeftValue());
		par.put("rightValue", dbSourceProduct.getRightValue());
		par.put("currentTime", new Date());
		par.put("offset", offset);
		
		String sql=null;
		
		Long startLeftValue=1L;
		if(dbParentProduct==null){
			if(dbTargetProduct==null){
				sql="PARENT_ID IS NULL ORDER BY RIGHT_VALUE DESC LIMIT 0,1";
				
				List<SysProductLine> products=sysProductLineDao.getByWhere(sql, "","" );
				if(products!=null&&products.size()>0){
					SysProductLine maxProduct=products.get(0);
					startLeftValue=maxProduct.getRightValue()+1;
				}
			}else{
				startLeftValue=dbTargetProduct.getLeftValue();
			}
		}else{
			if(dbTargetProduct==null){
				startLeftValue=dbParentProduct.getRightValue();
			}else{
				startLeftValue=dbTargetProduct.getLeftValue();
			}
		}
		
		par.put("startLeftValue", startLeftValue);
		
		sql="update sys_product_line set RIGHT_VALUE=RIGHT_VALUE+#{par.offset} "
				+ "where RIGHT_VALUE>=#{par.startLeftValue}";
		sysProductLineDao.updateBySql(sql, "par", par);

		sql="update sys_product_line set LEFT_VALUE=LEFT_VALUE+#{par.offset} "
				+ "where LEFT_VALUE>=#{par.startLeftValue}";
		sysProductLineDao.updateBySql(sql, "par", par);
		
		dbSourceProduct=sysProductLineDao.getById(sourceNodeId);
		par.put("leftValue", dbSourceProduct.getLeftValue());
		par.put("rightValue", dbSourceProduct.getRightValue());
		
		Long sourceOffset=startLeftValue-dbSourceProduct.getLeftValue();
		
		par.put("sourceOffset", sourceOffset);
		
		sql="update sys_product_line set RIGHT_VALUE=RIGHT_VALUE+#{par.sourceOffset},LEFT_VALUE=LEFT_VALUE+#{par.sourceOffset},EDIT_TIME=#{par.currentTime} "
				+ "where LEFT_VALUE>=#{par.leftValue} and RIGHT_VALUE<=#{par.rightValue}";
		sysProductLineDao.updateBySql(sql, "par", par);
		
		sql="update sys_product_line set LEFT_VALUE=LEFT_VALUE-#{par.offset} where LEFT_VALUE>#{par.rightValue}";
		sysProductLineDao.updateBySql(sql, "par", par);

		sql="update sys_product_line set RIGHT_VALUE=RIGHT_VALUE-#{par.offset} where RIGHT_VALUE>#{par.rightValue}";
		sysProductLineDao.updateBySql(sql, "par", par);
		
		dbSourceProduct=sysProductLineDao.getById(sourceNodeId);
		
		dbSourceProduct.setParentId(parentId);
		
		sysProductLineDao.update(dbSourceProduct);
		
		long endTime=System.currentTimeMillis();
		LOG.debug(">>>>run time:"+(endTime-beginTime));

	}

	@Override
	public void deleteLogic(String productId) {
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return;
		
		
		String sql="update sys_product_line set DATA_STATE=#{par[0]},DELETE_TIME=#{par[1]} "
				+ "where LEFT_VALUE>=#{par[2]} and RIGHT_VALUE<=#{par[3]}";
		sysProductLineDao.updateBySql(sql, "par", ConstData.DATA_DELETE,new Date(),dbProduct.getLeftValue(),dbProduct.getRightValue());

	}

	@Override
	public void deleteLogicRestore(String productId) {
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return;
		
		
		String sql="update sys_product_line set DATA_STATE=#{par[0]},DELETE_TIME=#{par[1]} "
				+ "where LEFT_VALUE>=#{par[2]} and RIGHT_VALUE<=#{par[3]}";
		sysProductLineDao.updateBySql(sql, "par", ConstData.DATA_NORMAL,null,dbProduct.getLeftValue(),dbProduct.getRightValue());

	}

	@Override
	public void deletePhysical(String productId) {
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return;
		
		Long offset=dbProduct.getRightValue()-dbProduct.getLeftValue()+1;
		LOG.debug(">>>>delete offset:"+offset);
		
		String sql="delete from sys_product_line where LEFT_VALUE>=#{par[0]} and RIGHT_VALUE<=#{par[1]}";
		int result=sysProductLineDao.deleteBySql(sql, "par", dbProduct.getLeftValue(),dbProduct.getRightValue());
		LOG.debug(">>>>delete node count:"+result);
		sql="update sys_product_line set LEFT_VALUE=LEFT_VALUE-#{par[0]} where LEFT_VALUE>#{par[1]}";
		sysProductLineDao.updateBySql(sql, "par",offset,dbProduct.getRightValue());
		sql="update sys_product_line set RIGHT_VALUE=RIGHT_VALUE-#{par[0]} where RIGHT_VALUE>#{par[1]}";
		sysProductLineDao.updateBySql(sql, "par",offset,dbProduct.getRightValue());

	}

	@Override
	public List<SysProductLine> getDirectChild(String productId) {
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return null;
		
		String sql="PARENT_ID=#{par.parentId} and DATA_STATE=#{par.dataState} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("parentId", dbProduct.getProductId());
		par.put("dataState", ConstData.DATA_NORMAL);
		
		List<SysProductLine> result=sysProductLineDao.getByWhere(sql, "par", par);
		
		return result;
	}

	@Override
	public int getDirectChildCount(String productId) {
		int result=0;
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return result;
		
		String sql="SELECT COUNT(PRODUCT_ID) AS cou FROM sys_product_line WHERE  DATA_STATE=#{par.dataState} AND PARENT_ID=#{par.parentId}";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("parentId", dbProduct.getProductId());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<Map<String, Object>> list=sysProductLineDao.selectBySql(sql, "par", par);
		if(list!=null&&list.size()>0){
			Map<String, Object> record=list.get(0);
			Object count=record.get("cou");
			if(count!=null){
				result=Integer.parseInt(count.toString());
			}
			
		}
		LOG.debug(">>>>result:"+result);
		return result;
	}

	@Override
	public List<SysProductLine> getAllChild(String productId) {
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return null;
		
		String sql="LEFT_VALUE>#{par.leftValue} and RIGHT_VALUE<#{par.rightValue} and DATA_STATE=#{par.dataState} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("leftValue", dbProduct.getLeftValue());
		par.put("rightValue", dbProduct.getRightValue());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<SysProductLine> result=sysProductLineDao.getByWhere(sql, "par", par);
		return result;
	}

	@Override
	public int getAllChildCount(String productId) {
		int result=0;
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return result;
		
		String sql="SELECT COUNT(PRODUCT_ID) AS cou FROM sys_product_line WHERE  DATA_STATE=#{par.dataState} "
				+ " AND LEFT_VALUE>#{par.leftValue} AND RIGHT_VALUE<#{par.rightValue}";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("leftValue", dbProduct.getLeftValue());
		par.put("rightValue", dbProduct.getRightValue());
		par.put("dataState", ConstData.DATA_NORMAL);
		List<Map<String, Object>> list=sysProductLineDao.selectBySql(sql, "par", par);
		if(list!=null&&list.size()>0){
			Map<String, Object> record=list.get(0);
			Object count=record.get("cou");
			if(count!=null){
				result=Integer.parseInt(count.toString());
			}
			
		}
		LOG.debug(">>>>result:"+result);
		return result;
	}

	@Override
	public List<SysProductLine> getPath(String productId) {
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return null;
		
		String sql="LEFT_VALUE<=#{par.leftValue} and RIGHT_VALUE>=#{par.rightValue} order by LEFT_VALUE";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("leftValue", dbProduct.getLeftValue());
		par.put("rightValue", dbProduct.getRightValue());
		List<SysProductLine> result=sysProductLineDao.getByWhere(sql, "par", par);
		return result;
	}

	@Override
	public int getLevel(String productId) {
		int result=-1;
		SysProductLine dbProduct=sysProductLineDao.getById(productId);
		if(dbProduct==null) return result;
		
		String sql="SELECT COUNT(PRODUCT_ID) AS cou FROM sys_product_line WHERE "
				+ "LEFT_VALUE<=#{par.leftValue} AND RIGHT_VALUE>=#{par.rightValue}";
		Map<String, Object> par=new HashMap<String, Object>();
		par.put("leftValue", dbProduct.getLeftValue());
		par.put("rightValue", dbProduct.getRightValue());
		List<Map<String, Object>> list=sysProductLineDao.selectBySql(sql, "par", par);
		if(list!=null&&list.size()>0){
			Map<String, Object> record=list.get(0);
			Object count=record.get("cou");
			if(count!=null){
				result=Integer.parseInt(count.toString());
			}
			
		}
		LOG.debug(">>>>result:"+result);
		return result;
	}

}
