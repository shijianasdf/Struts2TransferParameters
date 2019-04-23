package mysqlUtils.mysqlReadUtils;


import java.util.ArrayList;

public class SearchDbAccurateSingle{
	private String tableName;
	
	// 精确搜索，匹配的条目的特定列
		// 返回 String
		// 注意，这个用于确定值返回一个值的情况，如果返回的个数大于一，则只有最后面的返回值会输出
		public String queryStr(String selectedCol, String keyCol, String keyValue){
			// 建立 mysql 指令
			String mysqlStr = "";
			mysqlStr = selectedCol+" from "+this.tableName+" where "+keyCol+"='"+keyValue+"';";
			return mysqlStr;
		}
		
		// 精确搜索，返回匹配的条目的一列
		// 返回 String
		public String queryList(String selectedCol, String keyCol, String groupCol, String keyValue){
			// 建立 mysql 指令
			String mysqlStr = "";
			if(groupCol.length()>0)
				mysqlStr = selectedCol+" from "+this.tableName+" where "+keyCol+"='"+keyValue+"' group by "+groupCol;
			else
				mysqlStr = selectedCol+" from "+"."+this.tableName+" where "+keyCol+"='"+keyValue+"';";
			return mysqlStr;
		}
		
		// 精确搜索，返回匹配的条目的一行 ArrayList<String>
		// 注意，这个用于确定值返回一个值的情况，如果返回的个数大于一，则只有最后面的返回值会输出
		// 注意， 要求只匹配一行
		public String queryRowList(String selectedCols, String keyCol, String keyValue){
			// 建立 mysql 指令
			String mysqlStr = "";
			mysqlStr = selectedCols+" from "+this.tableName+" where "+keyCol+"='"+keyValue+"';";
			return mysqlStr;
		}
		
		
		// 精确查找，根据多个特定条件，返回一行 ArrayList<String>
		//
		// 参数说明
		// 1-3.	conn, dbName, tableName 望文生意
		// 4.	selectedCols 返回的列，以逗号空格 ", " 分隔的 String 型
		// 5-6.	基于 keyCols 和 keyValues 搜索结果
		// 		要求此两者为 ArrayList<String> 分别包含检索的列名和对应的值
		// 8-9.	是否在最后添加要求，要求 notEqualCol 不等于 notEqualValue
		// 7.	基于 conditionTypeList 将多个条件连接， ArrayList<String>，这个参数关键，举例说明
		//		例一，如果希望得到 select * from * where (*=A) AND (*=B)
		//		则 conditionTypeList 中输入 ") AND ("
		//		例二，如果希望得到 select * from * where (*=A) AND (*=B OR *=C)
		//		则 conditionTypeList 中输入 ") AND (", " OR "
		//		例三，如果希望得到 select * from * where (*=A) AND (*=B) OR (*=C)
		//		则 conditionTypeList 中输入 ") AND (", ") OR ("
		// 返回 ArrayList<String>
		public String queryListMC(String selectedCols, ArrayList<String> keyCols, ArrayList<String> keyValues, ArrayList<String> conditionTypeList, String notEqualCol, String notEqualValue){
			// 建立 mysql 指令
			// 如果 keyCols 中只有一个值，那么等同于单条件搜索的结果
			String mysqlStr = "";
			mysqlStr = selectedCols+" from "+this.tableName+" where ("+keyCols.get(0)+"='"+keyValues.get(0)+"'";
			// 如果 keyCols 的长度大与一
			// 则循环 keyCols 产生多条件搜索指令
			if(keyCols.size()>1 && conditionTypeList.size()>0){
				for(int i=1; i<keyCols.size(); i++)
					mysqlStr = mysqlStr+conditionTypeList.get(i-1)+keyCols.get(i)+"='"+keyValues.get(i)+"'";
			}
			// 添加mysql语句的尾巴
			// 如果没有限定不等列，则直接补上括号的右半边
			// 否则添加不等列指令，并补上括号的右半边
			if(notEqualCol.length()==0)
				mysqlStr = mysqlStr+")";
			else
				mysqlStr = mysqlStr+") AND ("+notEqualCol+"!='"+notEqualValue+"')";
			// 输出 mysql 指令
			System.out.println(mysqlStr);
			return mysqlStr;
		}
	
	public SearchDbAccurateSingle(){
		
	}
	
	public SearchDbAccurateSingle(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	

	
}
