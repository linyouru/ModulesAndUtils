/**
 * 
 */
package lyr.utils.utils;

import com.github.junrar.Archive;
import com.github.junrar.VolumeManager;
import com.github.junrar.rarfile.FileHeader;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.compress.compressors.gzip.GzipCompressorInputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *  解压文件工具类
 * 创建人：lyr
 * 创建日期：2018年6月29日下午4:02:56
 * 
 */
public class UncompressUtil {

	private static Logger logger = LoggerFactory.getLogger(UncompressUtil.class);

	/**
	 * 在当前文件夹下解压zip文件，并创建zip文件名的文件夹,返回压缩包下全部文件的路径list
	 * 创建人：lyr
	 * 创建日期：2018年7月4日上午11:12:03
	 * @param zipFile	zip文件
	 * @param descDir	解压路径
	 * @return list	解压出来的全部文件路径
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public static  ArrayList<String> unZip(File zipFile, String descDir) throws Exception, IOException {
		ArrayList<String> list = new ArrayList<String>();
		cyclicUnzip(zipFile, descDir, list);
	    return list;
	}


	/**
	 * 循环解压zip文件
	 * 创建人：lyr
	 * 创建日期：2018年7月11日上午8:49:27
	 * @param zipFile	
	 * @param descDir
	 * @param list	
	 * @throws Exception
	 * @throws IOException
	 */
	private static void cyclicUnzip(File zipFile, String descDir, ArrayList<String> list) throws Exception, IOException {
		boolean flag = false;
	    File pathFile = new File(descDir);
	    if(!pathFile.exists()){
	        pathFile.mkdirs();
	    }
	    ZipFile zip = null;
	    try {
	        //指定编码，否则压缩包里面不能有中文目录
	        zip = new ZipFile(zipFile, Charset.forName("gbk"));
	        for(Enumeration entries = zip.entries(); entries.hasMoreElements();){	//遍历压缩包下的文件
	            ZipEntry entry = (ZipEntry)entries.nextElement();
	            //修改解压出来的的文件夹的名字
	            String zipEntryName = entry.getName();
	            zipEntryName = zip.getName().substring(0, zip.getName().lastIndexOf("."))+"/"+zipEntryName;
	            InputStream in = zip.getInputStream(entry);
	            String outPath = zipEntryName.replace("/", File.separator);
	            //判断路径是否存在,不存在则创建文件路径
	            File file = new File(outPath.substring(0, outPath.lastIndexOf(File.separator)));
	            if(!file.exists()){
	                file.mkdirs();
	            }
	            //判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
	            if(new File(outPath).isFile()||new File(outPath).isDirectory()){
	                continue;
	            }
	            list.add(outPath);	//解压路径list
	            OutputStream out = new FileOutputStream(outPath);
	            byte[] buf1 = new byte[2048];
	            int len;
	            while((len=in.read(buf1))>0){
	                out.write(buf1,0,len);
	            }
	            in.close();
	            out.close();
	            //循环解压
	            File sonZipFile = new File(outPath);
            	String SonDescDir = outPath.substring(0, outPath.lastIndexOf("\\"))+"\\";
	            if(outPath.substring(outPath.lastIndexOf(".")+1).equals("zip")) {
	            	cyclicUnzip(sonZipFile, SonDescDir, list);
	            }else if(outPath.substring(outPath.lastIndexOf(".")+1).equals("rar")) {
	            	cyclicUnrar(sonZipFile, SonDescDir, list);
	            }
	        }
	        flag = true;
	        //必须关闭，否则无法删除该zip文件
	        zip.close();
	    } catch (IOException e) {
	        throw e;
	    }
	}
	
	
	/**
	 * 在当前文件夹下解压rar文件，并创建rar文件名的文件夹,返回压缩包下全部文件的路径list
	 * 创建人：lyr
	 * 创建日期：2018年7月4日上午11:10:59
	 * @param rarFile	rar文件
	 * @param destDir	解压路径
	 * @return list	解压出来的全部文件路径
	 * @throws Exception
	 */
	public static ArrayList<String> unRar(File rarFile,String destDir) throws Exception{ 
		   ArrayList<String> list = new ArrayList<String>();
	       cyclicUnrar(rarFile, destDir, list);
		return list;    
	   }


	/**
	 * 循环解压rar文件
	 * 创建人：lyr
	 * 创建日期：2018年7月11日上午9:13:46
	 * @param rarFile
	 * @param destDir
	 * @param list
	 * @throws Exception
	 */
	private static void cyclicUnrar(File rarFile, String destDir, ArrayList<String> list) throws Exception {
		Archive archive = null;    
	       FileOutputStream fos = null;    
	       try{    
	    	   archive = new Archive((VolumeManager) rarFile);
	           FileHeader fh = archive.nextFileHeader();    
	           while(fh!=null){    
	               if(!fh.isDirectory()){    
	                   //1 根据不同的操作系统拿到相应的 destDirName 和 destFileName    
	                   String compressFileName = fh.getFileNameW().trim();    
	                   String destFileName = "";    
	                   String destDirName = "";  
	                   String folderName = rarFile.getName().substring(0, rarFile.getName().lastIndexOf("."))+"\\";
	                   //非windows系统    
	                   if(File.separator.equals("/")){    
	                       destFileName = destDir + folderName + compressFileName.replaceAll("\\\\", "/");    
	                       destDirName = destFileName.substring(0, destFileName.lastIndexOf("/"));    
	                   //windows系统     
	                   }else{    
	                       destFileName = destDir + folderName + compressFileName.replaceAll("/", "\\\\");    
	                       destDirName = destFileName.substring(0, destFileName.lastIndexOf("\\"));    
	                   }    
	                   //2创建文件夹    
	                   File dir = new File(destDirName);    
	                   if(!dir.exists()||!dir.isDirectory()){    
	                       dir.mkdirs();    
	                   }    
	                   //3解压缩文件    
	                   File destFile = new File(destFileName);	//文件不存在的时候才解压缩
	                   if(!destFile.exists()) {
	                	   fos = new FileOutputStream(new File(destFileName));    
		                   archive.extractFile(fh, fos);    
		                   fos.close();    
		                   fos = null; 
		                   list.add(destFileName);
	                   }
	                   //循环解压
	                   File sonZipFile = new File(destFileName);
   	            	   String SonDescDir = destFileName.substring(0, destFileName.lastIndexOf("\\"))+"\\";
	                   if(destFileName.substring(destFileName.lastIndexOf(".")+1).equals("zip")) {
	                	   cyclicUnzip(sonZipFile, SonDescDir, list);
            		   }else if(destFileName.substring(destFileName.lastIndexOf(".")+1).equals("rar")) {
            			   cyclicUnrar(sonZipFile, SonDescDir, list); 
            		   }
	               }    
	               fh = archive.nextFileHeader();    
	           }    
	           archive.close();    
	           archive = null;    
	       }catch(Exception e){    
	           throw e;    
	       }finally{    
	           if(fos!=null){    
	               try{fos.close();fos=null;}catch(Exception e){e.printStackTrace();}    
	           }    
	           if(archive!=null){    
	               try{archive.close();archive=null;}catch(Exception e){e.printStackTrace();}    
	           }    
	       }
	}


	/**
	 * 解压.gz文件
	 * @param filePath 文件路径
	 * @Author LinYouRu
	 * @Date 16:22 2020/2/5
	 * @return void
	 **/
	public static void unCompressArchiveGz(String filePath){

		logger.info("开始解压文件"+filePath);
		File file = new File(filePath);

		BufferedOutputStream bos = null;
		GzipCompressorInputStream gcis=null;
		String finalName = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

			String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));

			finalName = file.getParent() + File.separator + fileName;

			bos = new BufferedOutputStream(new FileOutputStream(finalName));

			gcis = new GzipCompressorInputStream(bis);

			byte[] buffer = new byte[1024];
			int read;
			while((read = gcis.read(buffer)) != -1){
				bos.write(buffer, 0, read);
			}
			gcis.close();
			bos.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(gcis!=null){
				try {
					gcis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		unCompressTar(finalName);
		logger.info("解压文件结束");
	}

	/**
	 * 解压.tar文件
	 * @param finalName
	 * @Author LinYouRu
	 * @Date 16:21 2020/2/5
	 * @return void
	 **/
	private static void unCompressTar(String finalName){
		BufferedOutputStream bos = null;
		TarArchiveInputStream tais = null;
		try {
			File file = new File(finalName);
			String parentPath = file.getParent();
			tais = new TarArchiveInputStream(new FileInputStream(file));

			TarArchiveEntry tarArchiveEntry = null;

			while ((tarArchiveEntry = tais.getNextTarEntry()) != null) {
				String name = tarArchiveEntry.getName();
				File tarFile = new File(parentPath, name);
				if (!tarFile.getParentFile().exists()) {
					tarFile.getParentFile().mkdirs();
				}

				bos = new BufferedOutputStream(new FileOutputStream(tarFile));

				int read;
				byte[] buffer = new byte[1024];
				while ((read = tais.read(buffer)) != -1) {
					bos.write(buffer, 0, read);
				}
				bos.close();
			}
			tais.close();
			file.delete();//删除tar文件
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bos!=null){
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(tais!=null){
				try {
					tais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
