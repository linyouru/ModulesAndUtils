package lyr.utils.utils;

import org.apache.commons.net.ftp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.SocketException;
import java.util.Calendar;

/**
 * Ftp工具类
 * @ClassName FtpsUtil
 * @Description TODO
 * @Author LYR
 * @Date 2020/2/3 9:53
 * @Version 1.0
 **/
public class FtpUtil {
    private static Logger log = LoggerFactory.getLogger(FtpUtil.class);

    public static FTPSClient getFTPSClient(String ftpHost, int ftpPort,
                                           String ftpUserName, String ftpPassword) {

        //数据商FTP服务器加密，所以使用FTPS
        FTPSClient ftps = null;
        try {
            ftps = new FTPSClient();
            // 连接FPT服务器,设置IP及端口
            ftps.connect(ftpHost, ftpPort);
            //被动模式
            ftps.enterLocalPassiveMode();
            // 设置用户名和密码
            ftps.login(ftpUserName, ftpPassword);
            ftps.execPBSZ(0);
            ftps.execPROT("P");
            ftps.type(FTP.BINARY_FILE_TYPE);
            // 设置连接超时时间,5000毫秒
            ftps.setConnectTimeout(50000);
            // 设置中文编码集，防止中文乱码
            ftps.setControlEncoding("UTF-8");
            if (!FTPReply.isPositiveCompletion(ftps.getReplyCode())) {
                log.info("未连接到FTP，用户名或密码错误");
                ftps.disconnect();
            } else {
                log.info("FTP连接成功");
            }

        } catch (SocketException e) {
            e.printStackTrace();
            log.info("FTP的IP地址可能错误，请正确配置");
        } catch (IOException e) {
            e.printStackTrace();
            log.info("FTP的端口错误,请正确配置");
        }
        return ftps;
    }


    /**
     * Description: 向FTP服务器上传文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param basePath FTP服务器基础目录
     * @param filePath FTP服务器文件存放路径。文件的路径为basePath+filePath
     * @param filename 上传到FTP服务器上的文件名
     * @param input 输入流
     * @return 成功返回true，否则返回false
     */
    public static boolean uploadFile(String host, int port, String username, String password, String basePath,
                                     String filePath, String filename, InputStream input) {
        boolean result = false;
        FTPClient ftp = getFTPSClient(host,port,username,password);
        try {
            int reply;
            reply = ftp.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftp.disconnect();
                return result;
            }
            //切换到上传目录
            if (!ftp.changeWorkingDirectory(basePath+filePath)) {
                //如果目录不存在创建目录
                String[] dirs = filePath.split("/");
                String tempPath = basePath;
                for (String dir : dirs) {
                    if (null == dir || "".equals(dir)) {
                        continue;
                    }
                    tempPath += "/" + dir;
                    if (!ftp.changeWorkingDirectory(tempPath)) {  //进不去目录，说明该目录不存在
                        if (!ftp.makeDirectory(tempPath)) { //创建目录
                            //如果创建文件目录失败，则返回
                            System.out.println("创建文件目录"+tempPath+"失败");
                            return result;
                        } else {
                            //目录存在，则直接进入该目录
                            ftp.changeWorkingDirectory(tempPath);
                        }
                    }
                }
            }
            //设置上传文件的类型为二进制类型
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            //上传文件
            if (!ftp.storeFile(filename, input)) {
                return result;
            }
            input.close();
            ftp.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try {
                    ftp.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param fileName 要下载的文件名
     * @param localPath 下载后保存到本地的路径
     * @return
     */
    public static boolean downloadFile(String host, int port, String username, String password,
                                       String fileName, String localPath) {
        boolean result = false;
        FTPSClient ftps = getFTPSClient(host,port,username,password);
        try {
            int reply;
            reply = ftps.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftps.disconnect();
                return result;
            }
//            ftps.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftps.listFiles();
            for (FTPFile ff : fs) {
                if (ff.getName().equals(fileName)) {
                    File localFile = new File(localPath + "/" + ff.getName());

                    OutputStream is = new FileOutputStream(localFile);
                    ftps.retrieveFile(ff.getName(), is);
                    is.close();
                }
            }

            ftps.logout();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftps.isConnected()) {
                try {
                    ftps.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return result;
    }

    /**
     * Description: 从FTP服务器下载最新的文件
     * @param host FTP服务器hostname
     * @param port FTP服务器端口
     * @param username FTP登录账号
     * @param password FTP登录密码
     * @param localPath 下载后保存到本地的路径
     * @Author LinYouRu
     * @Date 15:52 2020/2/5
     * @return java.lang.String 返回下载的文件路径
     **/
    public static String downloadNewestFile(String host, int port, String username, String password,String localPath) {
        String path = null;
        FTPSClient ftps = getFTPSClient(host,port,username,password);
        try {
            int reply;
            reply = ftps.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftps.disconnect();
                return null;
            }
//            ftps.changeWorkingDirectory(remotePath);// 转移到FTP服务器目录
            FTPFile[] fs = ftps.listFiles();

            String newestFile = null;
            Calendar ca1 = null;
            for (FTPFile f : fs) {
                Calendar ca2 = f.getTimestamp();
                if(null!=ca1){
                    if(0>ca1.compareTo(ca2)){
                        //ca1比ca2早
                        ca1 = ca2;
                        newestFile = f.getName();
                    }
                }else {
                    ca1=ca2;
                }

            }
            log.info("开始从FTP下载最新数据包。。。");
            File localFile = new File(localPath + File.separator + newestFile);
            OutputStream is = new FileOutputStream(localFile);
            ftps.retrieveFile(newestFile, is);
            is.close();
            path = localPath + File.separator + newestFile;
            log.info("最新数据包下载完毕,存储路径为："+path);
            ftps.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftps.isConnected()) {
                try {
                    ftps.disconnect();
                } catch (IOException ioe) {
                }
            }
        }
        return path;
    }


    public static void main(String[] args) {
        /*//ftp上传文件测试
        try {
            FileInputStream in=new FileInputStream(new File("D:\\Tomcat 5.5\\pictures\\t0176ee418172932841.jpg"));
            boolean flag = uploadFile("192.168.111.128", 21, "用户名", "密码", "/www/images","/2017/11/19", "hello.jpg", in);
            System.out.println(flag);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        //ftp下载文件测试
        /*boolean rjtx = downloadFile("ftp.shuzijz.cn", 21, "runjian", "Rj1115@2018",  "test.txt", "E:\\");
        System.out.println(rjtx);*/

        String rjtx = downloadNewestFile("ftp.shuzijz.cn", 21, "runjian", "Rj1115@2018", "E:\\");
        System.out.println(rjtx);


        /*FtpsUtil ftpUtil = new FtpsUtil();
        ftpUtil.getFTPClient("ftp.shuzijz.cn",21,"runjian","Rj1115@2018");*/
    }
}
