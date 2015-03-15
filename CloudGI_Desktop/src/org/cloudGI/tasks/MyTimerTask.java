/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.tasks;

import org.cloudGI.entity.Instance;
import org.cloudGI.view.Terminal;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudGI.dao.impl.DAO_BD;

/**
 *
 * @author Poliana Nascimento
 */
public class MyTimerTask extends TimerTask {

    private final Terminal tm = new Terminal();

    private final PingHost ph = new PingHost();
    private OpenStackCommands cos;

    public MyTimerTask() {
        tm.setVisible(true);
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        List<Instance> li;
        li = new ArrayList<>();
        DAO_BD bd = new DAO_BD();
        cos = new OpenStackCommands();
        cos.UpdateInstanceStatusIp();
        li = bd.selectInstance();
        if (!li.isEmpty()) {
            tm.showTerminal("Start-------------------------------------------------");
            String result;
            for (Instance li1 : li) {
                if (!li1.getIp().equalsIgnoreCase(null) || !li1.getIp().equalsIgnoreCase(" ")) {
                    try {
                        result = ph.command(li1.getIp());
                        tm.showTerminal("Pacotes recebidos: " + result);
                        tm.showTerminal("IP: " + li1.getIp());
                        tm.showTerminal("Nome da Maquina: " + li1.getName());
                        if (result.equalsIgnoreCase("0")) {
                            if (li1.getStatus().equalsIgnoreCase("ERROR")) {
                                try {
                                    tm.showTerminal("Status da instancia: ERROR");
                                    tm.showTerminal("Excluindo instancia");
                                    Exec ex = new Exec();
                                    ex.command("source devstack/openrc admin admin; nova delete " + li1.getName());
                                    String command = "nova boot --flavor " + li1.getId_flavor() + " --image " + li1.getId_image() + " --security-groups default --key-name KeyPair01 " + li1.getName();
                                    ex = new Exec();
                                    tm.showTerminal(command);
                                    tm.showTerminal("Status da instancia: SUSPENDED");
                                    ex.command("source devstack/openrc admin admin; " + command);
                                    Thread.sleep(10000);
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            } else if (li1.getStatus().equalsIgnoreCase("SUSPENDED")) {
                                try {
                                    tm.showTerminal("Status da instancia: SUSPENDED");
                                    Exec ex = new Exec();
                                    tm.showTerminal("Resume instancia");
                                    ex.command("source devstack/openrc admin admin; nova resume " + li1.getName());
                                    Thread.sleep(10000);
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            } else if (li1.getStatus().equalsIgnoreCase("PAUSED")) {
                                try {
                                    tm.showTerminal("Status da instancia: PAUSED");
                                    Exec ex = new Exec();
                                    tm.showTerminal("Despausando instancia");
                                    ex.command("source devstack/openrc admin admin; nova unpause " + li1.getName());
                                    Thread.sleep(10000);
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                                System.out.println(li1.getName());
                            } else if (li1.getStatus().equalsIgnoreCase("SHELVED_OFFLOADED")) {
                                try {

                                    tm.showTerminal("Status da instancia: SHELVED_OFFLOADED");
                                    Exec ex = new Exec();
                                    tm.showTerminal("Desarquivando instancia");
                                    ex.command("source devstack/openrc admin admin; nova unshelve " + li1.getName());

                                    Thread.sleep(10000);
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            } else if (li1.getStatus().equalsIgnoreCase("SHUTOFF")) {
                                try {
                                    tm.showTerminal("Status da instancia: SHUTOFF");
                                    Exec ex = new Exec();
                                    tm.showTerminal("Ligando instancia");
                                    ex.command("source devstack/openrc admin admin; nova start " + li1.getName());
                                    Thread.sleep(10000);
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            } else if (li1.getStatus().equalsIgnoreCase("BUILD")) {
                                try {
                                    tm.showTerminal("Status da instancia: BUILD");

                                    tm.showTerminal("Aguardando instancia iniciar");
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            } else if (li1.getStatus().equalsIgnoreCase("REBOOT")) {
                                try {
                                    tm.showTerminal("Status da instancia: REBOOT");

                                    tm.showTerminal("Aguardando instancia reiniciar");
                                    Thread.sleep(100);
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            } else if (li1.getStatus().equalsIgnoreCase("ACTIVE")) {
                                try {
                                    tm.showTerminal("Status da instancia: ACTIVE");

                                    tm.showTerminal("Aguardando instancia iniciar");
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                            } else {
                                tm.showTerminal("Status da instancia: INDEFINIDO");
                                Exec ex = new Exec();
                                tm.showTerminal("Reiniciando instancia");
                                ex.command("source devstack/openrc admin admin; nova reboot " + li1.getName());
                                try {
                                    Thread.sleep(10000);
                                    result = ph.command(li1.getIp());
                                    if (result.equalsIgnoreCase("0")) {
                                        tm.showTerminal("Instancia não responde");
                                        tm.showTerminal("Excluindo instancia");
                                        ex = new Exec();
                                        ex.command("source devstack/openrc admin admin; nova delete " + li1.getName());
                                        String command = "nova boot --flavor " + li1.getId_flavor() + " --image " + li1.getId_image() + " --security-groups default --key-name KeyPair01 " + li1.getName();
                                        tm.showTerminal(command);
                                        ex = new Exec();
                                        tm.showTerminal("Criando instancia");
                                        ex.command("source devstack/openrc admin admin; " + command);
                                        Thread.sleep(10000);
                                    }
                                } catch (InterruptedException ex1) {
                                    Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                                }

                            }
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    if (li1.getStatus().equalsIgnoreCase("ERROR")) {
                        try {
                            tm.showTerminal("Status da instancia: ERRO");
                            tm.showTerminal("Excluindo instancia");
                            Exec ex = new Exec();
                            ex.command("source devstack/openrc admin admin; nova delete " + li1.getName());
                            String command = "nova boot --flavor " + li1.getId_flavor() + " --image " + li1.getId_image() + " --security-groups default --key-name KeyPair01 " + li1.getName();
                            ex = new Exec();
                            tm.showTerminal(command);
                            tm.showTerminal("Status da instancia: SUSPENDED");
                            ex.command("source devstack/openrc admin admin; " + command);
                            Thread.sleep(10000);
                        } catch (InterruptedException ex1) {
                            Logger.getLogger(MyTimerTask.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }
                }
            }
            tm.showTerminal("End-------------------------------------------------");
            tm.showTerminal("Waiting...\n\n");

        }else{
            tm.showTerminal("No Instances");
        }
    }

    public void stop() {

    }
}
