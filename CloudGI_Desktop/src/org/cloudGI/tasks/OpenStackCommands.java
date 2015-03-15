/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.tasks;

import org.cloudGI.entity.Flavor;
import org.cloudGI.entity.Image;
import org.cloudGI.entity.Instance;
import org.cloudGI.entity.OpenStackInstance;
import org.cloudGI.entity.InstanceUser;
import org.cloudGI.entity.Renewal;
import org.cloudGI.view.Terminal_2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudGI.dao.impl.DAO_BD;

/**
 *
 * @author Poliana Nascimento
 */
public class OpenStackCommands {

    public boolean delete(String nome) {
        boolean delete = true;
        Exec ex = new Exec();
        String command;
        command = ex.command("source devstack/openrc admin admin; nova delete " + nome);
        System.out.println(command);
        if (!command.equalsIgnoreCase("OK")) {
            delete = false;
        }

        return delete;
    }

    public boolean reboot(String name) {
        boolean reboot = true;
        Exec ex = new Exec();
        String command;
        command = ex.command("source devstack/openrc admin admin; nova reboot " + name);
        if (!command.equalsIgnoreCase("OK")) {
            reboot = false;
        }
        return reboot;
    }

    public boolean boot(InstanceUser instUser, String img, Instance ins) {
        boolean result = false;
        String command = "nova boot --flavor " + instUser.getId_flavor() + " --image " + img + " --security-groups default --key-name KeyPair01 " + ins.getName();
        Exec ex = new Exec();
        try {
            ex.command("source devstack/openrc admin admin; " + command);
            result = true;
        } catch (Exception e) {
            result = false;
        }

        return result;
    }

    public void UpdateInstanceStatusIp() {
        List<OpenStackInstance> Isopen = null;
        try {
            TreatResultInstance trreI = new TreatResultInstance();

            Isopen = trreI.treat();
            DAO_BD bd = new DAO_BD();
            for (int i = 0; i < Isopen.size(); i++) {

                bd.updateIPStatus(Isopen.get(i));

            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(OpenStackCommands.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String idImage(int id) throws IOException {
        List<Image> list;
        String img = null;
        TreatResultImge tri = new TreatResultImge();
        list = tri.treat();

        for (int i = 0; i < 2; i++) {
            DAO_BD bd = new DAO_BD();
            list.get(i).setId(i + 1);
            bd.updateImage(list.get(i));
            if (id == list.get(i).getId()) {
                img = list.get(i).getId_open();
            }
        }

        return img;
    }

    public int idFlavor(int id) throws IOException {
        List<Flavor> list;
        int flavor = 0;

        TreatResultFlavor tri = new TreatResultFlavor();
        list = tri.treat();

        for (int i = 0; i < list.size(); i++) {
            DAO_BD bd = new DAO_BD();
            bd.updateFlavor(list.get(i));
            if (list.get(i).getName().equalsIgnoreCase("m1.nano") && id == 1) {
                flavor = list.get(i).getId_open();
            } else if (list.get(i).getName().equalsIgnoreCase("m1.micro") && id == 2) {
                flavor = list.get(i).getId_open();
            }
        }

        return flavor;
    }

    public List<Renewal> renewal(List<Renewal> l, Terminal_2 tm) {
        DAO_BD bd = new DAO_BD();
        List<Integer> list = bd.listIdServerUser();
        List<Instance> instanceList = bd.listByzantineInstance();
        List<String> ls;
        for (Integer list1 : list) {
            ls = new ArrayList<>();
            for (Instance instanceList1 : instanceList) {
                if (list1 == instanceList1.getId_instanceUser()) {
                    ls.add(instanceList1.getName());
                }
            }
            for (Renewal l1 : l) {
                if (list1 == l1.getId_instanceUser()) {
                    reboot(ls.get(l1.getNext()));
                    tm.showTerminal("Instancia reiniciada: " + ls.get(l1.getNext()));
                    if (l1.getNext() + 1 == l1.getSize()) {
                        l1.setProxima(0);
                    } else {
                        l1.setProxima(l1.getNext() + 1);
                    }
                }
            }
        }
        return l;
    }
}
