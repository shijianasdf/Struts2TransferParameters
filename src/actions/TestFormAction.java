package actions;

import java.util.ArrayList;

import com.opensymphony.xwork2.ActionSupport;

import beans.student;
import mysqlUtils.mysqlReadUtils.SearchDbAccurateTable;
import utils.GsonTools;
import utils.SimpleFactory;

@SuppressWarnings("serial")
public class TestFormAction extends ActionSupport{
	private ArrayList<String> Fruit;
	private ArrayList<String> animals;
	public String TestForm(){
		SearchDbAccurateTable sdt = new SearchDbAccurateTable("student"); //student table
		String selectedCols = "select new beans.student(name,sex)"; //有相应的构造函数student(name,sex)
		/*String selectedCols = " ";*/ //"new beans.student(id,name,sex,score,university)"有相应的构造函数student(id,name,sex,score,university)
		ArrayList<String> keyValues = new ArrayList<String>();
		ArrayList<String> keyCols = new ArrayList<String>();
		ArrayList<String> conditionTypeList = new ArrayList<String>();
		if(Fruit != null){ //checkbox Fruit字段
			System.out.println(GsonTools.createJsonString(Fruit));
			if(Fruit.size() > 1){
				System.out.println(GsonTools.createJsonString(Fruit));
				for(int i=0; i<Fruit.size(); i++){
					keyCols.add("name");
					keyValues.add(Fruit.get(i));
					if(i>0)
						conditionTypeList.add(" OR ");
				}
			}else{
				keyCols.add("name");	
				keyValues.add(Fruit.get(0));
			}
			conditionTypeList.add(") AND (");
		}
		if(animals != null){ //select animals字段
			System.out.println(GsonTools.createJsonString(animals));
			if(animals.size() > 1){
				System.out.println(GsonTools.createJsonString(animals));
				for(int j=0; j<animals.size(); j++){
					keyCols.add("sex");
					keyValues.add(animals.get(j));
					if(j>0)
						conditionTypeList.add(" OR ");
				}
			}else{
				keyCols.add("sex");	
				keyValues.add(animals.get(0));
			}
		}
		//System.out.println(GsonTools.createJsonString(Fruit));
		String mysqlStr2 = sdt.queryTableMC(selectedCols,keyCols,keyValues,conditionTypeList,"","");
		System.out.println(mysqlStr2);//select new beans.student(name,sex) from student where (name='苹果' OR name='桃子' OR name='香蕉' OR name='梨') AND (sex='小猫' OR sex='小狗' OR sex='小猪' OR sex='小马')
		ArrayList<student> as = (ArrayList<student>) new SimpleFactory<student>("student").query(mysqlStr2);
		System.out.println(GsonTools.createJsonString(as));
		return SUCCESS;
	}
	public ArrayList<String> getFruit() {
		return Fruit;
	}
	public void setFruit(ArrayList<String> fruit) {
		Fruit = fruit;
	}
	public ArrayList<String> getAnimals() {
		return animals;
	}
	public void setAnimals(ArrayList<String> animals) {
		this.animals = animals;
	}
    
	
}
