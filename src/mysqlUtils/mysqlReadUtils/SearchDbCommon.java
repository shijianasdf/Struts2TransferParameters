package mysqlUtils.mysqlReadUtils;



public class SearchDbCommon {
	private String tableName;
	
	
	// 查看行数
		public String queryRowCount(String tableName){
			String mysqlStr = "select count(*) from "+tableName;	
			return mysqlStr;
		}
		
		// 查看列数
		public String queryColumnCount(String tableName){
			String mysqlStr = "select * from "+tableName+" limit 1";	
			return mysqlStr;
			//另一种计算列数的方法
			//rs=conn.prepareStatement("desc "+dbName+"."+tableName).executeQuery();	//覆盖原来的mysql语句
			//rs.last();
			//columnCount = rs.getRow();
			//System.out.println("my col num: "+rs.getRow());
		}
		
		// 查看列名
		/*public ArrayList<String> queryColName(Connection conn,String dbName,String tableName,int columnCount) throws SQLException{
			ArrayList<String> colName = new ArrayList<String>();
			Statement stmt = conn.createStatement();
			//这是另一种列名方法；推荐使用下方方法
			String sqlStr="select * from "+dbName+"."+tableName+" limit 1";
			rs=stmt.executeQuery(sqlStr);
			ResultSetMetaData data=rs.getMetaData();
			for(int i=1; i<=columnCount; i++)
				colName.add(data.getColumnName(i));	
			System.out.println("colname="+colName.size());
			
			//另一种计算列名的方法
			rs=stmt.executeQuery("desc "+dbName+"."+tableName);
			while(rs.next()){
				colName.add(rs.getString("Field"));
				//System.out.println(rs.getString("Field"));
			}
			System.out.println(colName);
			return colName;
		}*/
	
	public SearchDbCommon(){
		
	}
	public SearchDbCommon(String tableName) {
		super();
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	
}