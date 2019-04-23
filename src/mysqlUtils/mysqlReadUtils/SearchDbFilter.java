package mysqlUtils.mysqlReadUtils;

public class SearchDbFilter {

	// 过滤搜索词，防止sql注入
	public String sqlInjectDenfender(String sql){
		String midStr = "";
		midStr = sql.replaceAll("([';])+|(--)+","");
		String str = "";
		str=midStr.trim();
		System.out.println("sql inject denfender pass and str is : "+str);
		return str;
	}
}