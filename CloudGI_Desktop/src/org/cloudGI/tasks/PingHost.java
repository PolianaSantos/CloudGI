/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.tasks;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.cloudGI.entity.MPInfoUser;
import org.cloudGI.entity.ServerUser;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author Poliana Nascimento
 */
public class PingHost {

    private String list = null;

    public String command(String ip) {
        try {
            JSch jsch = new JSch();

            ServerUser us = new ServerUser();
            Session session = jsch.getSession(us.getUser(), us.getHost(), us.getPort());
            //Session session = jsch.getSession("devstack", "10.103.17.31", 22);
            session.setUserInfo(new MPInfoUser());
            session.connect();

            Channel channel = session.openChannel("exec");

            ((ChannelExec) channel).setCommand("ping -q -c 2 " + ip);

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
            int cont = 0;
            String aux = "";
            list = list.replaceAll(" ", "");
            for (int j = 0; j < list.length(); j++) {
                if (String.valueOf(list.charAt(j)).equalsIgnoreCase(",")) {

                    aux = String.valueOf(list.charAt(j + 1));
                    System.out.println(aux);
                    return aux;
                }

            }
        } catch (JSchException | IOException e) {
            System.out.println(e);
        }

        return list;
    }

}
