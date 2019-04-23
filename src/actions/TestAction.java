package actions;

import java.util.ArrayList;
import java.util.HashMap;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import beans.USER;
import beans.student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import utils.GsonTools;
import utils.SimpleFactory;

public class TestAction extends ActionSupport{
  /**
	 * 
	 */
  private static final long serialVersionUID = 4863453270281189039L;
  private String name;
  private String age;
  private String age1;
  
  public String execute(){
	  this.age1 = "19";
	  ActionContext.getContext().put("key","ilove");
	  ActionContext.getContext().put("tea","hello");
	  ActionContext.getContext().getSession().put("name", "shijian");//把name(shijian)放在stack context中
	  ActionContext.getContext().getSession().put("age", "12");
	  USER user=new USER("deng","110");
	  ArrayList<ArrayList<String>> aas=new ArrayList<ArrayList<String>> ();
	  ArrayList<String> as=new ArrayList<String>();
	  as.add("hah");
	  as.add("hads");
	  aas.add(as);
	  ActionContext.getContext().put("juzhen", aas);
	  String aastojson=GsonTools.createJsonString(aas);
	  ActionContext.getContext().put("json", aastojson);
	  ActionContext.getContext().put("USER",user );
	  ArrayList<USER> AU=new ArrayList<USER>();
	  AU.add(user);
	  ActionContext.getContext().put("USERA",AU);
	 
	  USER user1=new USER("SHI","120");
	  USER user2=new USER("jian","119");
	  ArrayList<USER> AU1=new ArrayList<USER>();
	  ActionContext.getContext().put("AU1", AU1);
	  AU1.add(user1);
	  AU1.add(user2);
	  JSONArray jsonArray = JSONArray.fromObject(AU1);
	  String jsonString = jsonArray.toString();
	 //System.out.println(jsonString);
	 //System.out.println(jsonArray);
	  ActionContext.getContext().put("resultTable", jsonString);
	  ActionContext.getContext().put("result", jsonArray);
	 
	  HashMap<String,String> map=new HashMap<String,String>();//实例化一个map对象，把该对象转换为json对象{"name":"xijinping","age":"60"}
	  map.put("name", "xijinping");
	  map.put("age", "60");
	  JSONObject jsonmap=JSONObject.fromObject(map);
	  ActionContext.getContext().put("jsonmap", jsonmap);
	  
	  
	  HashMap<String,Object> map1=new HashMap<String,Object>();
	  ArrayList<Integer> shanghaidata=new ArrayList<Integer>();
	  shanghaidata.add(10);
	  shanghaidata.add(100);
	  shanghaidata.add(109);
	  shanghaidata.add(15);
	  shanghaidata.add(87);
	  shanghaidata.add(74);
	  map1.put("name", "shanghai");
	  map1.put("data", shanghaidata);
	  JSONObject jsonmap1=JSONObject.fromObject(map1);
	  ActionContext.getContext().put("jsonmap1", jsonmap1);
	  return Action.SUCCESS;
  }
  public String Search(){
	  this.age = "18";
	  ActionContext.getContext().put("allresult", (ArrayList<student>) new SimpleFactory<student>("student").query("from student s"));
	  return "lala";
	  
  }
  public String test(){
	  return SUCCESS;
  }
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAge() {
	return age;
}
public void setAge(String age) {
	this.age = age;
}
public String getAge1() {
	return age1;
}
public void setAge1(String age1) {
	this.age1 = age1;
}
  
}
