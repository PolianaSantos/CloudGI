/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudGI.tasks;

import br.com.cloudGI.entity.Flavor;
import br.com.cloudGI.entity.Image;
import br.com.cloudGI.entity.Instance;
import br.com.cloudGI.entity.InstanceUser;
import java.io.IOException;
import br.com.cloudGI.controller.RegisterInstance;
import java.util.List;
import br.com.cloudGI.dao.impl.DAO_BD_Derby;

/**
 *
 * @author Poliana Nascimento
 */
public class OpenStackCommands {

    public boolean delete(String nome) {
        boolean delete = true;
        Exec ex = new Exec();
        String comando;
        comando = ex.command("source devstack/openrc admin admin; nova delete " + nome);
        System.out.println(comando);
        if (!comando.equalsIgnoreCase("OK")) {
            delete = false;
        }

        return delete;
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

    public String idImage(int id) throws IOException {
        List<Image> list;
        String img = null;
        TreatResultImage tri = new TreatResultImage();
        list = tri.treat();

        for (int i = 0; i < 2; i++) {
            DAO_BD_Derby bd = new DAO_BD_Derby();
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
            DAO_BD_Derby bd = new DAO_BD_Derby();
            bd.updateFlavor(list.get(i));
            if (list.get(i).getName().equalsIgnoreCase("m1.nano") && id == 1) {
                flavor = list.get(i).getId_open();
            } else if (list.get(i).getName().equalsIgnoreCase("m1.micro") && id == 2) {
                flavor = list.get(i).getId_open();
            }
        }

        return flavor;
    }
}
