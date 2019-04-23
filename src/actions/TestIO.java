package actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestIO {
	public static void main (String[] args) throws IOException{
		//InputStream IS = new FileInputStream("D:/R/topic/Network/数据/enhancer_tss_associations.bed.txt");
		InputStream IS = new FileInputStream(new File("D:/R/topic/Network/数据/enhancer_tss_associations.bed.txt"));
		//OutputStream OS = new FileOutputStream(new File("D:/testWorkspace/VideoDB/src/actions","enhancer_tss_associations.bed.txt"));
		OutputStream OS = new FileOutputStream("D:/testWorkspace/VideoDB/src/actions/enhancer_tss_associations.bed.txt");
		byte[] buffer=new byte[1024]; //字节数组
		int len=0;
		while((len=IS.read(buffer))!=-1){ 
			OS.write(buffer,0,len);
		}
		OS.close();
		IS.close();
	}
}
