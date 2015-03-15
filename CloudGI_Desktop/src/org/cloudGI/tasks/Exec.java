package org.cloudGI.tasks;

/**
 *
 * @author Poliana Nascimento
 */

import com.jcraft.jsch.*;   
import org.cloudGI.entity.MPInfoUser;
import org.cloudGI.entity.ServerUser;
import java.io.*;

public class Exec {

    private String list = null;

    public String command(String comando) {
        try {
            JSch jsch = new JSch();
            ServerUser us = new ServerUser();
            Session session = jsch.getSession(us.getUser(), us.getHost(), us.getPort());
            session.setUserInfo(new MPInfoUser());
            session.connect();

            Channel channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand(comando);

            channel.setInputStream(null);

            ((ChannelExec) channel).setErrStream(System.err);

            InputStream in = channel.getInputStream();

            channel.connect();

            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) {
                        break;
                    }
                    list = new String(tmp, 0, i);
                    System.out.print(list);

                }
                if (channel.isClosed()) {
                    if (in.available() > 0) {
                        continue;
                    }
                    System.out.println("exit-status: " + channel.getExitStatus());
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            channel.disconnect();
            session.disconnect();
            return "OK";
        } catch (JSchException | IOException e) {
            System.out.println(e);
            return null;
        }

    }
}
