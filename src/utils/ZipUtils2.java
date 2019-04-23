package utils;

import java.util.ArrayList;
import java.util.zip.*;
import java.io.*;

public class ZipUtils2 {
	public boolean zipCompress(ArrayList<String> srcFiles, String desFile) {
		boolean isSuccessful = false;
		String[] fileNames = new String[srcFiles.size()];
		for (int i = 0; i < srcFiles.size(); i++) {
			fileNames[i] = parse(srcFiles.get(i));
		}
		try {
			BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
			ZipOutputStream zos = new ZipOutputStream(bos);
			String entryName = null;
			entryName = fileNames[0];
			for (int i = 0; i < fileNames.length; i++) {
				entryName = fileNames[i];
				// 鍒涘缓Zip鏉＄洰
				ZipEntry entry = new ZipEntry(entryName);
				zos.putNextEntry(entry);
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFiles.get(i)));
				byte[] b = new byte[1024];
				while (bis.read(b, 0, 1024) != -1) {
					zos.write(b, 0, 1024);
				}
				bis.close();
				zos.closeEntry();
			}
			zos.flush();
			zos.close();
			isSuccessful = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isSuccessful;
	}
	// 瑙ｆ瀽鏂囦欢鍚�
	private String parse(String srcFile) {
		int location = srcFile.lastIndexOf("/");
		String fileName = srcFile.substring(location + 1);
		System.out.println(fileName);
		return fileName;
	}
}