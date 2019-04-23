package actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import beans.USER;


public class TestSet {
	public static void main(String[] args) {
		Set<String> s1 = new HashSet<String>();
	    Set<String> s2 = new HashSet<String>();
	    List<String> l1 = new ArrayList<String>();
	    List<String> l2 = new ArrayList<String>();
	    Map<String,String> m1 = new HashMap<String,String>();
	    Map<String,String> m2 = new HashMap<String,String>();
	   
	    
	    s1.add("a"); s1.add("b"); s1.add("c");s1.add("c");
	    s2.add("a"); s2.add("b"); s2.add("d");
	    System.out.println(s1);
	    Set<String> s3 = new HashSet<String>(s1);
	    System.out.println(s3);
	    s1.addAll(s2);
	    System.out.println(s1);
	    l1.add("er");
	    s1.addAll(l1);
	    System.out.println(s1);
	    
	    Iterator<String> it = s1.iterator();
	    while(it.hasNext()){
	    	System.out.println(it.next());
	    }
	    
	    
	    s1.retainAll(s2);
	    System.out.println(s1);
	    s1.removeAll(s1);
	    System.out.println(s1);
	    
	    l2.add("dfd");l2.add("dfd");l2.add("dfd");l2.add("dfd");
	    s1.addAll(l2);
	    System.out.println(s1.containsAll(l2));
	    System.out.println(s1);
	    System.out.println(l2);
	    
	    USER u1 = new USER("shijian","123456");
	    USER u2 = new USER("shijian","123456");
	    //List<USER> ll = new ArrayList<USER>();
	    Set<USER> ss = new HashSet<USER>();
	    ss.add(u1);ss.add(u2);
	    System.out.println(ss);  
	    
	    List<String> l3 = new ArrayList<String>(s1); //Setתlist
	    System.out.println(l3);  
	    //s1.clear();
	    //System.out.println(s1);
	    
	    m1.put("shijan", "dfsdg");
	    m1.put("shijan1", "dfsdg1");
	    m1.put("shijan2", "dfsdg2");
	    m1.put("shijan3", "dfsdg3");
	    for(String s : m1.keySet()){
	    	System.out.println(m1.get(s));
	    }
	    m2.putAll(m1);
	    System.out.println(m2);
	}
    
    
    
}
