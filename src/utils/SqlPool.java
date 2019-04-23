package utils;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class SqlPool {
	/*
	 * 鐢⊿qlPool.getPool().getConnection()锛岃幏寰椾竴涓狢onnection瀵硅薄锛�
	 * 灏卞彲浠ヨ繘琛屾暟鎹簱鎿嶄綔锛屾渶鍚庡埆蹇樹簡瀵笴onnection瀵硅薄璋冪敤close()鏂规硶锛�
	 * 娉ㄦ剰锛氳繖閲屼笉浼氬叧闂繖涓狢onnection锛岃�屾槸灏嗚繖涓狢onnection鏀惧洖鏁版嵁搴撹繛鎺ユ睜
	 * */
    private static DataSource pool;
    	static {
    		Context env = null;
    		try {
              env = (Context) new InitialContext().lookup("java:comp/env");
              pool = (DataSource)env.lookup("jdbc/SqlPool");	//杩炴帴姹犲悕绉帮紝杩欎釜鍜岄厤缃枃浠剁浉瀵瑰簲锛岃嚜宸卞懡鍚嶄竴鑷村嵆鍙�
              
              if(pool==null) 
                  System.err.println("'DBPool' is an unknown DataSource");
              }catch(NamingException ne) {
                  ne.printStackTrace();
          }
    	}
 
   
    	
    public static DataSource getPool() {
        return pool;
    }
    
    
    public static void closeConnection(Connection conn){
    	try{
    		conn.close();
    	}catch(Exception e){
    		System.out.println("Conenction can't be closed exception at SqlPool!");
    	}
    }
    
    public static Connection getConnection(){
    	try{
    		return pool.getConnection();
    	}catch(Exception e){
    		System.out.println("Can't get Connection from SqlPool");
    		return null;
    	}
    }
    // 鍏抽棴鏁版嵁搴撻摼鎺ワ紝骞舵斁鍥瀟omcat鏁版嵁搴撹繛鎺ユ睜
    public static void putBackConnection(Connection conn){
    	try{
    		conn.close();
    	}catch(Exception e){
    		System.out.println("Conenction can't be closed exception at SqlPool!");
    	}
    }
    
}


