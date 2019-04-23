package mysqlUtils.mysqlWriteUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateSingleDbC {
	private PreparedStatement pstmt=null;
	//单独更改的
	//更新现有条目，输入String，单独修改字段
	public String updateValue(Connection conn,String dbName,String tableName,String updateCol,String updateValue,String indexCol,String indexValue){
		String reStr = "success";
		try{
			System.out.println("update "+dbName+"."+tableName+" set "+updateCol+"='"+updateValue+"' where "+indexCol+"='"+indexValue+"'");
			pstmt = conn.prepareStatement("update "+dbName+"."+tableName+" set "+updateCol+"='"+updateValue+"' where "+indexCol+"='"+indexValue+"'");
			System.out.println("update "+pstmt.executeUpdate());
		}
		catch(Exception e){
			e.printStackTrace();
			reStr = "error";
		}
		return reStr;
	}
}
