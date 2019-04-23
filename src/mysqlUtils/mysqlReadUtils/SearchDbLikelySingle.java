package mysqlUtils.mysqlReadUtils;


import java.sql.SQLException;


public class SearchDbLikelySingle {
	private String tableName;
	// 模糊搜索，返回特定列的list
	public String likelySearchList(String selectedCol, String keyCol,String searchName) throws SQLException{
		String mysqlStr = selectedCol+" from "+this.tableName+" where "+keyCol+" like '%"+searchName+"%';";
		return mysqlStr;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public SearchDbLikelySingle(String tableName) {
		super();
		this.tableName = tableName;
	}
	public SearchDbLikelySingle() {
		super();
	}
	

}
