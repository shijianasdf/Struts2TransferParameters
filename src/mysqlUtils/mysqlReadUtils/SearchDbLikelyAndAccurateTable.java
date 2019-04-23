package mysqlUtils.mysqlReadUtils;


import java.util.ArrayList;


public class SearchDbLikelyAndAccurateTable {
	private String tableName;
	// 模糊和精确匹配，根据多个条件，返回一个 table
	//
	// 参数说明
	// 1-3.	conn, dbName, tableName 望文生意
	// 4	SelectedCols 返回的列，以逗号空格 ", " 分隔的 String 型
	
	//      用于设定模糊和精确搜索的设定
	// 5.1-6.1	基于 likelyKeyCols 和 likelyKeyValues 搜索结果
	// 			要求此两者为 ArrayList<String> 分别包含检索的列名和对应的值
	
	// 5.2-6.2	基于 AccurareKeyCols 和 AccurareKeyValues 搜索结果
	// 			要求此两者为 ArrayList<String> 分别包含检索的列名和对应的值
	
	// 8-9.	是否在最后添加要求，要求 notEqualCol 不等于 notEqualValue
	// 7.	基于 conditionTypeList 将多个条件连接， ArrayList<String>，这个参数关键，举例说明
	//		例一，如果希望得到 select * from * where (* like A) AND (* like B)
	//			则 conditionTypeList 中输入 ") AND ("
	//		例二，如果希望得到 select * from * where (* like A) AND (* like B OR * like C)
	//			则 conditionTypeList 中输入 ") AND (", " OR "
	//		例三，如果希望得到 select * from * where (* like A) AND (* like B) OR (* like C)
	//			则 conditionTypeList 中输入 ") AND (", ") OR ("
	// 返回 ArrayList<ArrayList<String>>
	public String queryTableALT(String selectedCols, ArrayList<String> likelyKeyCols, ArrayList<String> likelyKeyValues, ArrayList<String> AccurareKeyCols, ArrayList<String> AccurareKeyValues, ArrayList<String> conditionTypeList, String notEqualCol, String notEqualValue){
		// 建立 mysql 指令
		// 如果 keyCols 中只有一个值，那么等同于单条件搜索的结果
		String mysqlStr = "";
		mysqlStr = selectedCols+" from "+this.tableName;
		
		// 判断精确和模糊搜索的条件，构建判断条件string
		// 模糊搜索打头；
		if(likelyKeyCols.size()>0){
			mysqlStr = mysqlStr+" where ("+likelyKeyCols.get(0)+" like '%"+likelyKeyValues.get(0)+"%'";
			int conditionTypeFlag = 0;
			
			// 模糊条件设定
			// 如果 likelyKeyValues 的长度大与一
			// 则循环 likelyKeyValues 产生多条件搜索指令
			if(likelyKeyCols.size()>1 && conditionTypeList.size()>0){
				for(int i=1; i<likelyKeyCols.size(); i++)
					mysqlStr = mysqlStr+conditionTypeList.get(i-1)+likelyKeyCols.get(i)+" like '%"+likelyKeyValues.get(i)+"%'";
				    conditionTypeFlag = conditionTypeFlag + 1;
			}
			
			// 精确条件设定
			// 如果 likelyKeyValues 的长度大与一
			// 则循环 likelyKeyValues 产生多条件搜索指令
			if(AccurareKeyCols.size()>0 && conditionTypeList.size()-likelyKeyCols.size()+1>0){
				for(int i=0; i<AccurareKeyCols.size(); i++)
					mysqlStr = mysqlStr + conditionTypeList.get(conditionTypeFlag + i) + AccurareKeyCols.get(i) + " = '" + AccurareKeyValues.get(i) + "'";
			}
		
		// 不存在模糊搜索事项； 所以精确搜索打头
		}else if(AccurareKeyCols.size()>0 && likelyKeyCols.size()<1){
			mysqlStr = mysqlStr+" where ("+AccurareKeyCols.get(0)+" = '"+AccurareKeyValues.get(0)+"'";
						
			// 精确条件设定
			// 如果 likelyKeyValues 的长度大与一
			// 则循环 likelyKeyValues 产生多条件搜索指令
			if(AccurareKeyCols.size()>1 && conditionTypeList.size()>0){
				for(int i=1; i<AccurareKeyCols.size(); i++)
					mysqlStr = mysqlStr + conditionTypeList.get(i-1) + AccurareKeyCols.get(i) + " = '" + AccurareKeyValues.get(i) + "'";
			}
		
		}else {
			//一般情况下，不管是模糊搜索还是精确搜索必须有一个是非空的；
			System.out.println("at least one of your likelyKeyCols and AccurareKeyCols must isn't null. Or, your conditionTypeList isn't null.");
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

	// 模糊匹配，根据单个条件，返回一个 table
	//
	// 参数说明
	// 1-3.	conn, dbName, tableName 望文生意
	// 4.	selectedCols 返回的列，以逗号空格 ", " 分隔的 String 型
	// 5-6.	基于 keyCol 和 keyValue 搜索结果，String
	// 返回 ArrayList<ArrayList<String>>
	public String queryTableSC(String selectedCols, String keyCol, String keyValue){
		// 建立 mysql 指令
		String mysqlStr = "";
		mysqlStr = selectedCols+" from "+this.tableName+" where "+keyCol+" like '%"+keyValue+"%'";
		return mysqlStr;
	}

	public SearchDbLikelyAndAccurateTable() {
		
	}

	public SearchDbLikelyAndAccurateTable(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
