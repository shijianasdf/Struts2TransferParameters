package actions;

import java.util.ArrayList;

import mysqlUtils.mysqlReadUtils.SearchDbAccurateSingle;
import mysqlUtils.mysqlReadUtils.SearchDbAccurateTable;

public class test {
	public static void main(String[] args) {
		SearchDbAccurateSingle sdd = new SearchDbAccurateSingle("student");
		String mysqlStr1 = sdd.queryStr("new beans.student(name,score)", "id", "student000001");
		System.out.println(mysqlStr1); //select new beans.student(name,score) from student where id='student000001';
		
		SearchDbAccurateTable sdt = new SearchDbAccurateTable("student"); //student table
		String selectedCols = "new beans.student(name,score)";
		ArrayList<String> keyCols = new ArrayList<String>();
		keyCols.add("name");
		keyCols.add("sex");
		keyCols.add("score");
		ArrayList<String> keyValues = new ArrayList<String>();
		keyValues.add("longzhilin");
		keyValues.add("male");
		keyValues.add("100");
		ArrayList<String> conditionTypeList = new ArrayList<String>();
		conditionTypeList.add(") OR (");
		conditionTypeList.add(") OR (");
		String mysqlStr2 = sdt.queryTableMC(selectedCols,keyCols,keyValues,conditionTypeList,"","");
		System.out.println(mysqlStr2);//select new beans.student(name,score) from student where (name='longzhilin') OR (sex='male') OR (score='100')
		
		String selectedCols1 = "new beans.student(name,score)";
		ArrayList<String> keyCols1 = new ArrayList<String>();
		keyCols1.add("name");
		keyCols1.add("sex");
		keyCols1.add("score");
		ArrayList<String> keyValues1 = new ArrayList<String>();
		keyValues1.add("longzhilin");
		keyValues1.add("male");
		keyValues1.add("100");
		ArrayList<String> conditionTypeList1 = new ArrayList<String>();
		conditionTypeList1.add(") OR (");
		conditionTypeList1.add(" OR ");
		String mysqlStr3 = sdt.queryTableMC(selectedCols1,keyCols1,keyValues1,conditionTypeList1,"","");
		System.out.println(mysqlStr3);//select new beans.student(name,score) from student where (name='longzhilin') OR (sex='male' OR score='100')
	}
}
