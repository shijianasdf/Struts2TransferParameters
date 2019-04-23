package actions;

import java.util.ArrayList;
import utils.ZipUtils2;

public class testZip {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ZipUtils2 zu = new ZipUtils2();
		String desFile = "D:/testWorkspace/TEST/WebContent/testZip/shijian.zip";
		ArrayList<String> srcFiles = new ArrayList<String>();
		srcFiles.add("D:/testWorkspace/TEST/WebContent/testZip/shijian.txt");
		srcFiles.add("D:/testWorkspace/TEST/WebContent/testZip/shijian1.txt");
		srcFiles.add("D:/testWorkspace/TEST/WebContent/testZip/shijian2.txt");
		boolean flag = zu.zipCompress(srcFiles, desFile);
		if(flag){
			System.out.println("成功");
		}
		
	}

}
