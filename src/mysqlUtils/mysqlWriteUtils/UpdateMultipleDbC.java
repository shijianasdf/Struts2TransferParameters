package mysqlUtils.mysqlWriteUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class UpdateMultipleDbC {
	private PreparedStatement pstmt=null;
	
	//更新现有条目，输入list，同时修改大量字段
	public String updateValueList(Connection conn,String dbName,String tableName,ArrayList<String> updateCols,ArrayList<String> updateValues,String indexCol,String indexValue){
		String reStr = "success";
		int size = 0;
		size = updateCols.size();
		for(int i=0;i<size;i++){
			try{
				System.out.println("update "+dbName+"."+tableName+" set "+updateCols.get(i)+"='"+updateValues.get(i)+"' where "+indexCol+"='"+indexValue+"'");
				pstmt = conn.prepareStatement("update "+dbName+"."+tableName+" set "+updateCols.get(i)+"='"+updateValues.get(i)+"' where "+indexCol+"='"+indexValue+"'");
				System.out.println("update "+pstmt.executeUpdate());
			}
			catch(Exception e){
				e.printStackTrace();
				reStr = "error";
				System.out.println("update record error");
				break;
			}
		}
		return reStr;
	}

	//更新现有条目及时间，输入list，同时修改大量字段
	public String updateValueListWithTime(Connection conn,String dbName,String tableName,ArrayList<String> updateCols,ArrayList<String> updateValues,String indexCol,String indexValue){
		String reStr = "success";
		int size = 0;
		size = updateCols.size();
		for(int i=0;i<size;i++){
			try{
				System.out.println("update "+dbName+"."+tableName+" set "+updateCols.get(i)+"='"+updateValues.get(i)+"' where "+indexCol+"='"+indexValue+"'");
				pstmt = conn.prepareStatement("update "+dbName+"."+tableName+" set "+updateCols.get(i)+"='"+updateValues.get(i)+"' where "+indexCol+"='"+indexValue+"'");
				System.out.println("update "+pstmt.executeUpdate());
			}
			catch(Exception e){
				e.printStackTrace();
				reStr = "error";
				System.out.println("update record error");
				break;
			}
		}
		if(reStr.equalsIgnoreCase("success")){
			//更新时间
			try{
				System.out.println("update "+dbName+"."+tableName+" set updateTime=NOW() where "+indexCol+"='"+indexValue+"'");
				pstmt = conn.prepareStatement("update "+dbName+"."+tableName+" set updateTime=NOW() where "+indexCol+"='"+indexValue+"'");
				System.out.println("update time "+pstmt.executeUpdate());
			}
			catch(Exception e){
				e.printStackTrace();
				reStr = "error";
				System.out.println("update time error");
			}
		}
		return reStr;
	}
	
	//更新数据表，同时记录时间和自增长id，其中自增长id在第一列，更新时间在最后一列
	public String insertValueWithTimeAndAuto(Connection conn,String dbName,String tableName,ArrayList<String> values){
		String reStr = "success";
		int listSize = values.size();
		//获取mysql指令
		String tempStr = "'"+values.get(0)+"'";
		System.out.println("tempStr 1st : "+tempStr);
		for(int i=1;i<listSize;i++){
			tempStr = tempStr+",'"+values.get(i)+"'";	
		}
		System.out.println("insert into "+dbName+"."+tableName+" values(null,"+tempStr+",NOW());");
		//运行mysql指令
		try{
			pstmt = conn.prepareStatement("insert into "+dbName+"."+tableName+" values(null,"+tempStr+",NOW());");
			System.out.println("update "+pstmt.executeUpdate());	
		}
		catch(Exception e){
			e.printStackTrace();
			reStr = "error";
		}
		return reStr;
	}
	
}
