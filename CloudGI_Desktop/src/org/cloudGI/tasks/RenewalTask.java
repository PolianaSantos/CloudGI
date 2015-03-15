/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.tasks;

import org.cloudGI.entity.Renewal;
import org.cloudGI.view.Terminal_2;
import java.util.List;
import java.util.TimerTask;
import org.cloudGI.dao.impl.DAO_BD;

/**
 *
 * @author Poliana Nascimento
 */
public class RenewalTask extends TimerTask {

    private final DAO_BD bd = new DAO_BD();
    private List<Renewal> l;
    Terminal_2 tm = new Terminal_2();

    public RenewalTask() {
        l = bd.listRenewal();
        tm.setVisible(true);
        tm.showTerminal("Renewal");
        for (int i = 0; i < l.size(); i++) {
            l.get(i).setProxima(0);

        }
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        OpenStackCommands cos = new OpenStackCommands();
        tm.showTerminal("Start--------------------------");

        tm.showTerminal("Rejuvenescimento de Instancias");
        l = cos.renewal(l, tm);
        List<Renewal> aux;
        aux = bd.listRenewal();
        int auxiliar = 0;

        for (Renewal aux1 : aux) {
            for (Renewal l1 : l) {
                if (aux1.getId_instanceUser() == l1.getId_instanceUser()) {
                    auxiliar++;
                }
            }
            if (auxiliar == 0) {
                l.add(aux1);
            } else {
                auxiliar = 0;
            }
        }
        tm.showTerminal("End---------------------------- \n\n");

    }

}
