import static org.junit.Assert.assertEquals;

import org.TwoFish.FileLabel;
import org.TwoFish.Info;
import org.TwoFish.core;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.util.ArrayList;

public class coreTest {

    @Test
    public void initconnectionTest() {
        FTPClient ftp = new FTPClient();
        String host = "192.168.0.104";
        String port = "21";
        String user = "test";
        String password = "123456";
        Info info = new Info();
        info.setHost(host);
        info.setPort(port);
        info.setUser(user);
        info.setPassword(password);
        assertEquals(true, core.connecting(info));
    }
    @Test
    public void closeconnectionTest() {
        FTPClient ftp = new FTPClient();
        String host = "192.168.0.104";
        String port = "21";
        String user = "test";
        String password = "123456";
        Info info = new Info();
        info.setHost(host);
        info.setPort(port);
        info.setUser(user);
        info.setPassword(password);
        core.connecting(info);
        assertEquals(true, core.close());
    }

    @Test
    public void listdirsTest() {
        FTPClient ftp = new FTPClient();
        String host = "192.168.0.104";
        String port = "21";
        String user = "test";
        String password = "123456";
        Info info = new Info();
        info.setHost(host);
        info.setPort(port);
        info.setUser(user);
        info.setPassword(password);
        core.connecting(info);
        ArrayList<FileLabel> list=core.listDirectories("\\");
        if(list!=null) {
            String result="success";
            System.out.println(list.toString());
            assertEquals("success", result);
        }
        core.close();
    }
}
