package org.framework.commons.db.utils;

import java.io.IOException;
import java.io.Reader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisSessionFactory {

	protected static final Log LOG=LogFactory.getLog(MyBatisSessionFactory.class);
    
    private static String CONFIG_FILE_LOCATION = "/mybatis.xml";
	private static final ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
      
    private static SqlSessionFactory sqlSessionFactory;
    private static String configFile = CONFIG_FILE_LOCATION;
    
    
	/*static {
		Reader reader=null;
    	try {
    		reader=Resources.getResourceAsReader(configFile);
    		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();  
    		sqlSessionFactory=builder.build(reader);
		} catch (Exception e) {
			if(LOG.isErrorEnabled()){
				LOG.error("%%%% Error Creating SqlSessionFactory %%%%");
			}
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				if(LOG.isErrorEnabled()){
					LOG.error("%%%% Error Close  Reader%%%%");
				}
				e.printStackTrace();
			}
			
		}
    }*/
    private MyBatisSessionFactory() {
    }
	
	
    public static SqlSession getSqlSession()  {
        SqlSession sqlSession = threadLocal.get();

		if (sqlSession == null ) {
			if (sqlSessionFactory == null) {
				rebuildSqlSessionFactory();
			}
			sqlSession = (sqlSessionFactory != null) ? sqlSessionFactory.openSession()
					: null;
			threadLocal.set(sqlSession);
		}

        return sqlSession;
    }

	
	public static void rebuildSqlSessionFactory() {
		Reader reader=null;
    	try {
    		reader=Resources.getResourceAsReader(configFile);
    		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();  
    		sqlSessionFactory=builder.build(reader);
		} catch (Exception e) {
			if(LOG.isErrorEnabled()){
				LOG.error("%%%% Error Creating SqlSessionFactory %%%%");
			}
			e.printStackTrace();
		}finally{
			try {
				if(reader!=null){
					reader.close();
				}
			} catch (IOException e) {
				if(LOG.isErrorEnabled()){
					LOG.error("%%%% Error Close  Reader%%%%");
				}
				e.printStackTrace();
			}
			
		}
	}

	
    public static void closeSqlSession() {
        SqlSession sqlSession =  threadLocal.get();
        //threadLocal.set(null);
        threadLocal.remove();

        if (sqlSession != null) {
        	sqlSession.close();
        }
    }

	
	public static SqlSessionFactory getSqlSessionFactory() {
		if(sqlSessionFactory==null){
			rebuildSqlSessionFactory();
		}
		return sqlSessionFactory;
	}

	
	public static void setConfigFile(String configFile) {
		MyBatisSessionFactory.configFile = configFile;
		sqlSessionFactory = null;
	}

	
	public static Configuration getConfiguration() {
		if(sqlSessionFactory==null){
			rebuildSqlSessionFactory();
		}
		return sqlSessionFactory!=null?sqlSessionFactory.getConfiguration():null;
	}

}