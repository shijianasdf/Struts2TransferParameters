package mysqlUtils.mysqlReadUtils;



//主頁顯示的表格
//設置最大顯示50條記錄
public class BrowseAll {
    private String tableName;
	//获得选择的列组成的表
	public String browseAllRows(String selectedCols){
		//获取mysql指令
		String mysqlStr = selectedCols+" from "+this.tableName;
		//String mysqlStr = "select "+selectedCols+" from "+dbName+"."+tableName+" order by updateTime desc limit 50";
		System.out.println(mysqlStr);
		return mysqlStr;
	}
	//获得选择的列组成的表
	public String browse50Rows(String selectedCols){
		//获取mysql指令
		String mysqlStr = selectedCols+" from "+this.tableName+" limit 50";
		System.out.println(mysqlStr);
		return mysqlStr;
	}
	public  BrowseAll(){
		
	}
	
	public BrowseAll(String tableName) {
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