package mysqlUtils.mysqlWriteUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class UpdateDbC {
	private PreparedStatement pstmt=null;
	private int rsInt=0;
	//插入数据表，不记录时间不产生自增id
		public String insertValueWithoutTime(Connection conn,String dbName,String tableName,ArrayList<String> values){
			String str = "";
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
				rsInt = pstmt.executeUpdate();	//运行sql指令	
				str = "success";
			}
			catch(Exception e){
				str = "error";
			}
			return str;
		}
	//插入数据表，同时记录时间，其中更新时间在最后一列
	public String insertValueWithTime(Connection conn,String dbName,String tableName,ArrayList<String> values){
		String str = "";
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
			rsInt = pstmt.executeUpdate();	//运行sql指令	
			str = "success";
		}
		catch(Exception e){
			str = "error";
		}
		return str;
	}
	//插入数据表，同时记录时间，其中更新时间在最后一列
		public String insertValueWithAutoAndCustomId(Connection conn,String dbName,String tableName,ArrayList<String[]> listValues,String id){
			String str = "";
			int listSize = listValues.size();
			int rowSize = listValues.get(0).length;
			for(int x=0;x<rowSize;x++){
				ArrayList<String> values = new ArrayList<String>();
				for(int i=0;i<listSize;i++){
					values.add(listValues.get(i)[x]);
				}
				//获取mysql指令
				String tempStr = "null,'"+id+"','"+values.get(0)+"'";
				System.out.println("tempStr 1st : "+tempStr);
				for(int i=1;i<listSize;i++){
					tempStr = tempStr+",'"+values.get(i)+"'";	
				}
				//运行mysql指令
				try{
					System.out.println("insert into "+dbName+"."+tableName+" values("+tempStr+");");
					pstmt = conn.prepareStatement("insert into "+dbName+"."+tableName+" values("+tempStr+");");
					rsInt = pstmt.executeUpdate();	//运行sql指令	
					str = "success";
				}
				catch(Exception e){
					str = "error";
					break;
				}
			}
			return str;
		}
	//更新现有条目，输入list，同时修改大量字段
	public void updateValueList(Connection conn,String dbName,String tableName,ArrayList<String> updateCols,ArrayList<String> updateValues,String indexCol,String indexValue){
		int size = 0;
		size = updateCols.size();
		for(int i=0;i<size;i++){
			try{
				System.out.println("update "+dbName+"."+tableName+" set "+updateCols.get(i)+"='"+updateValues.get(i)+"' where "+indexCol+"='"+indexValue+"'");
				pstmt = conn.prepareStatement("update "+dbName+"."+tableName+" set "+updateCols.get(i)+"='"+updateValues.get(i)+"' where "+indexCol+"='"+indexValue+"'");
				rsInt = pstmt.executeUpdate();
				System.out.println("update success");	
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("update error");
			}
		}
	}

	//更新现有条目，输入list，同时修改大量字段
	public void updateValueListWithTime(Connection conn,String dbName,String tableName,ArrayList<String> updateCols,ArrayList<String> updateValues,String indexCol,String indexValue){
		int size = 0;
		size = updateCols.size();
		for(int i=0;i<size;i++){
			try{
				System.out.println("update "+dbName+"."+tableName+" set "+updateCols.get(i)+"='"+updateValues.get(i)+"' where "+indexCol+"='"+indexValue+"'");
				pstmt = conn.prepareStatement("update "+dbName+"."+tableName+" set "+updateCols.get(i)+"='"+updateValues.get(i)+"' where "+indexCol+"='"+indexValue+"'");
				rsInt = pstmt.executeUpdate();
				System.out.println("update success");	
			}
			catch(Exception e){
				e.printStackTrace();
				System.out.println("update error");
			}
		}
		//更新时间
		try{
			System.out.println("update "+dbName+"."+tableName+" set updateTime=NOW() where "+indexCol+"='"+indexValue+"'");
			pstmt = conn.prepareStatement("update "+dbName+"."+tableName+" set updateTime=NOW() where "+indexCol+"='"+indexValue+"'");
			rsInt = pstmt.executeUpdate();
			System.out.println("update time success");	
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("update time error");
		}
	}
	//更新现有条目，输入String，单独修改字段
	public void updateValue(Connection conn,String dbName,String tableName,String updateCols,String updateValues,String indexCol,String indexValue){
		try{
			System.out.println("update "+dbName+"."+tableName+" set "+updateCols+"='"+updateValues+"' where "+indexCol+"='"+indexValue+"'");
			pstmt = conn.prepareStatement("update "+dbName+"."+tableName+" set "+updateCols+"='"+updateValues+"' where "+indexCol+"='"+indexValue+"'");
			rsInt = pstmt.executeUpdate();
			System.out.println("update success");	
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("update error");
		}
	}
	//更新数据表，同时记录时间和自增长id，其中自增长id在第一列，更新时间在最后一列
		public String insertValueWithTimeAndAuto(Connection conn,String dbName,String tableName,ArrayList<String> values){
			String str = "";
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
				rsInt = pstmt.executeUpdate();	//运行sql指令	
				str = "success";
			}
			catch(Exception e){
				str = "error";
			}
			return str;
		}
	public String insertValue(Connection conn,String dbName,String tableName,ArrayList<String> values){
		String str = "";
		int listSize = values.size();
		//获取mysql指令
		String tempStr = "'"+values.get(0)+"'";
		System.out.println("tempStr 1st : "+tempStr);
		for(int i=1;i<listSize;i++){
			tempStr = tempStr+",'"+values.get(i)+"'";	
		}
		System.out.println("insert into "+dbName+"."+tableName+" values("+tempStr+");");
		//运行mysql指令
		try{
			pstmt = conn.prepareStatement("insert into "+dbName+"."+tableName+" values("+tempStr+");");
			rsInt = pstmt.executeUpdate();	//运行sql指令	
			str = "success";
		}
		catch(Exception e){
			str = "error";
		}
		return str;
	}
	//鍦ㄦ渶鍚庝竴鍒楄嚜鍔ㄦ坊鍔犲垱寤烘椂闂�
	
	
	public int initClass(Connection conn,String newClassName,String parentName,String classDesc) throws Exception{
		System.out.println("insert into paper_center.class values('"+newClassName+"','"+parentName+"','"+classDesc+"');");
		pstmt = conn.prepareStatement("insert into paper_center.class values('"+newClassName+"','"+parentName+"','"+classDesc+"');");
		rsInt = pstmt.executeUpdate();
		return rsInt;
	}
	public int rmItem(Connection conn,String dbName,String tableName,String indexName,String indexValue) throws Exception{
		System.out.println("delete from "+dbName+"."+tableName+" where "+indexName+"='"+indexValue+"';");
		pstmt = conn.prepareStatement("delete from "+dbName+"."+tableName+" where "+indexName+"='"+indexValue+"';");
		rsInt = pstmt.executeUpdate();
		return rsInt;
	}
}
