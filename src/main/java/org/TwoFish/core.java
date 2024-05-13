package org.TwoFish;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.InputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class core {
    private static FTPClient ftp = null;
    public static boolean connecting(Info info){

        ftp = new FTPClient();

        ftp.setControlEncoding("utf-8");
        try {
            //连接ftp服务器
            ftp.connect(info.getHost(), info.getPort());
            //登录ftp服务器
            ftp.login(info.getUser(), info.getPassword());
            //是否成功登录服务器
            int replyCode = ftp.getReplyCode();
            if(!FTPReply.isPositiveCompletion(replyCode)){
                System.out.println("ftp服务器登录成功");
                return true;
            }
        }catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static boolean download(String source, String dest) {
        boolean flag = false;
        try (OutputStream outputStream = Files.newOutputStream(Paths.get(dest))) {
            flag = ftp.retrieveFile(source, outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean upload( String source, String dest) {
        boolean flag = false;
        try (InputStream inputStream = Files.newInputStream(Paths.get(source))) {
            flag = ftp.storeFile(Paths.get(source).getFileName().toString(), inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean close(){
        if(ftp != null && ftp.isConnected()){
            try {
                ftp.logout();
                ftp.disconnect();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static ArrayList<FileLabel> listDirectories(String dirPath) {
        try{
            ftp.changeWorkingDirectory(dirPath);
            // 列出这个目录下的所有文件和文件夹
            FTPFile[] files = ftp.listFiles();
            ArrayList<FileLabel> list = new ArrayList<>();
            for (FTPFile file : files) {
                if(file.isDirectory()){
                    list.add(new FileLabel(file.getName(), "dir"));
                }else if(file.isFile()) {
                    list.add(new FileLabel(file.getName(), "file"));
                }
            }
            return list;
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
