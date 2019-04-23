package utils;

import java.util.zip.*;
import java.io.*;

public class ZipUtils {
     public static void main(String[] args) {
          if (args.length < 1) {
               System.out.println("请输入参数：javac ZipTest 需压缩的文件名及路径 保存的文件名及路径。(e.g. c:/test.txt)");
          } else {
               ZipCompressor compressor = new ZipCompressor();
               boolean isSuccessful= compressor.zipCompress(args, args[args.length - 1]);
               if (isSuccessful) {
                    System.out.println("文件压缩成功。");
               } else {
                    System.out.println("文件压缩失败。");
               }
          }
     }
}

class ZipCompressor {
     public ZipCompressor() {}

     /**@param srcFiles 需压缩的文件路径及文件名
     * @param desFile 保存的文件名及路径
     * <a href="http://my.oschina.net/u/556800" class="referer" target="_blank">@return</a>  如果压缩成功返回true
     */
     public boolean zipCompress(String[] srcFiles, String desFile) {
          boolean isSuccessful = false;

          String[] fileNames = new String[srcFiles.length-1];
          for (int i = 0; i < srcFiles.length-1; i++) {
               fileNames[i] = parse(srcFiles[i]);
          }

          try {
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
                    ZipOutputStream zos = new ZipOutputStream(bos);
                    String entryName = null;
                    entryName = fileNames[0];

                    for (int i = 0; i < fileNames.length; i++) {
                         entryName = fileNames[i];

                      // 创建Zip条目
                         ZipEntry entry = new ZipEntry(entryName);
                         zos.putNextEntry(entry);

                         BufferedInputStream bis = new BufferedInputStream(new FileInputStream(srcFiles[i]));

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
          } catch (IOException e) {
          }

          return isSuccessful;
     }


     // 解析文件名
     private String parse(String srcFile) {
          int location = srcFile.lastIndexOf("/");
          String fileName = srcFile.substring(location + 1);
          return fileName;
     }
}