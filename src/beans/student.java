package beans;

public class student {
   private String id;
   private String name;
   private String sex;
   private String score;
   private String university;
   public student(){
	   
   }
   public student(String name, String sex) {


		this.name = name;
		this.sex = sex;

	}
public student(String id, String name, String sex, String score, String university) {
	this.id = id;
	this.name = name;
	this.sex = sex;
	this.score = score;
	this.university = university;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSex() {
	return sex;
}
public void setSex(String sex) {
	this.sex = sex;
}
public String getScore() {
	return score;
}
public void setScore(String score) {
	this.score = score;
}
public String getUniversity() {
	return university;
}
public void setUniversity(String university) {
	this.university = university;
}
   
 
}
