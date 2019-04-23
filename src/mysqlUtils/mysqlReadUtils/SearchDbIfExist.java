package mysqlUtils.mysqlReadUtils;

import java.sql.Connection;
import java.util.ArrayList;

public class SearchDbIfExist{
	//private SearchDbAccurateSingle sdbas = new SearchDbAccurateSingle();
	//private SearchDbLikelySingle sdbls = new SearchDbLikelySingle();
	
	// 判断搜索的条目是否有返回值

	// 精确搜索版本
	/*public boolean ifExistAccurate(Connection conn, String dbName, String tableName, String selectedCol, String searchName) throws Exception{
		boolean resultStr = false;
		String tempStr = "";
		tempStr = sdbas.queryStr(conn, dbName, tableName, selectedCol, selectedCol, searchName);
		resultStr = !tempStr.isEmpty();
		return resultStr;
	}*/
	// 模糊匹配版本
	/*public boolean ifExistLikely(Connection conn, String dbName, String tableName, String selectedCol, String searchName) throws Exception{
		boolean resultStr = false;
		ArrayList<String> al = new ArrayList<String>();
		al = sdbls.likelySearchList(conn, dbName, tableName, selectedCol, searchName);
		System.out.println("judge set size is : "+al.size());
		if(al.size()>0)
			resultStr = true;
		return resultStr;
	}*/
	
}
