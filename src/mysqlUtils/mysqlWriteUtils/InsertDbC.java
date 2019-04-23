package mysqlUtils.mysqlWriteUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class InsertDbC {
	private PreparedStatement pstmt=null;
	
	//插入数据表，不记录时间不产生自增id
		public String insertValueWithAuto(Connection conn,String dbName,String tableName,ArrayList<String> values){
			String reStr = "success";
			int listSize = values.size();
			//获取mysql指令
			String tempStr = "'"+values.get(0)+"'";
			System.out.println("tempStr 1st : "+tempStr);
			for(int i=1;i<listSize;i++){
				tempStr = tempStr+",'"+values.get(i)+"'";	
			}
			tempStr = tempStr+",null";//最後一列是mysql的auto_increasement的字段，所以需要在insert中添加一個null輸入
			//运行mysql指令
			try{
				System.out.println("insert into "+dbName+"."+tableName+" values("+tempStr+");");
				pstmt = conn.prepareStatement("insert into "+dbName+"."+tableName+" values("+tempStr+");");
				System.out.println("update "+pstmt.executeUpdate());	
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("insert into "+dbName+"."+tableName+" values("+tempStr+");");
				reStr = "error";
			}
			return reStr;
		}
	//插入数据表，不记录时间不产生自增id
	public String insertValue(Connection conn,String dbName,String tableName,ArrayList<String> values){
		String reStr = "success";
		int listSize = values.size();
		//获取mysql指令
		String tempStr = "'"+values.get(0)+"'";
		System.out.println("tempStr 1st : "+tempStr);
		for(int i=1;i<listSize;i++){
			tempStr = tempStr+",'"+values.get(i)+"'";	
		}
		//运行mysql指令
		try{
			System.out.println("insert into "+dbName+"."+tableName+" values("+tempStr+");");
			pstmt = conn.prepareStatement("insert into "+dbName+"."+tableName+" values("+tempStr+");");
			System.out.println("update "+pstmt.executeUpdate());	
		}
		catch(Exception e){
			e.printStackTrace();
			reStr = "error";
		}
		return reStr;
	}
	//插入数据表，同时记录时间，其中更新时间在最后一列
	public String insertValueWithTime(Connection conn,String dbName,String tableName,ArrayList<String> values){
		String reStr = "success";
		int listSize = values.size();
		//获取mysql指令
		String tempStr = "'"+values.get(0)+"'";
		System.out.println("tempStr 1st : "+tempStr);
		for(int i=1;i<listSize;i++){
			tempStr = tempStr+",'"+values.get(i)+"'";	
		}
		//运行mysql指令
		try{
			System.out.println("insert into "+dbName+"."+tableName+" values("+tempStr+",NOW());");
			pstmt = conn.prepareStatement("insert into "+dbName+"."+tableName+" values("+tempStr+",NOW());");
			System.out.println("update "+pstmt.executeUpdate());	
		}
		catch(Exception e){
			e.printStackTrace();
			reStr = "error";
		}
		return reStr;
	}
	//插入数据表，同时记录自定义的ID和时间，其中更新时间在最后一列
	public String insertValueWithCustomIdAndTime(Connection conn,String dbName,String tableName,ArrayList<String[]> listValues,String id){
		String reStr = "success";
		int listSize = listValues.size();
		int rowSize = listValues.get(0).length;
		for(int x=0;x<rowSize;x++){
			ArrayList<String> values = new ArrayList<String>();
			for(int i=0;i<listSize;i++){
				values.add(listValues.get(i)[x]);
			}
			//获取mysql指令
			String tempStr = "'"+id+"','"+values.get(0)+"'";
			System.out.println("tempStr 1st : "+tempStr);
			for(int i=1;i<listSize;i++){
				tempStr = tempStr+",'"+values.get(i)+"'";	
			}
			//运行mysql指令
			try{
				System.out.println("insert into "+dbName+"."+tableName+" values("+tempStr+");");
				pstmt = conn.prepareStatement("insert into "+dbName+"."+tableName+" values("+tempStr+");");
				System.out.println("update "+pstmt.executeUpdate());	
			}
			catch(Exception e){
				System.out.println("insert into "+dbName+"."+tableName+" values("+tempStr+");");
				e.printStackTrace();
				
				reStr = "error";
				break;
			}
		}
		return reStr;
	}
}
