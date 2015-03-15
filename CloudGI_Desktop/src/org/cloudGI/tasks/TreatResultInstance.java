/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.tasks;

import org.cloudGI.entity.OpenStackInstance;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Poliana Nascimento
 */
public class TreatResultInstance {

    public List<OpenStackInstance> treat() throws IOException, InterruptedException {
        Exec ex = new Exec();
        ex.command("source devstack/openrc admin admin; nova list > instancias.txt");

        ScpFrom sf = new ScpFrom();
        sf.file("instancias.txt");

        List<String> lines = new ArrayList<>();

        String line = "";
        File file = new File("instancias.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        return treatSpace(lines);
    }

    @SuppressWarnings({"CallToPrintStackTrace", "SleepWhileInLoop", "UnusedAssignment"})
    private List<OpenStackInstance> treatSpace(List<String> lines) throws InterruptedException {
        List<OpenStackInstance> lis = new ArrayList<>();
        OpenStackInstance insOpne;
        for (int i = 3; i < lines.size() - 1; i++) {
            String palavra = lines.get(i);
            String aux = "";
            String aux1 = "";
            for (int j = 0; j < palavra.length(); j++) {
                if (j == palavra.length() - 1) {
                    break;
                } else if (String.valueOf(palavra.charAt(j)).equalsIgnoreCase(" ") && String.valueOf(palavra.charAt(j + 1)).equalsIgnoreCase("|")) {
                    aux = aux.replace("|", "");
                    aux = aux.replaceAll(" ", "");
                    aux = aux + ";";
                    aux1 = aux1 + aux;

                    aux = "";
                } else {
                    aux = aux + String.valueOf(palavra.charAt(j));
                }

            }
            String list[] = aux1.split(";");
            insOpne = new OpenStackInstance();
            insOpne.setId(list[0]);
            insOpne.setName(list[1]);
            insOpne.setStatus(list[2]);
            insOpne.setTaskState(list[3]);
            insOpne.setPowerState(list[4]);

            try {
                String ip[] = list[5].split("=");
                insOpne.setNetworks(ip[1]);
                System.out.println(ip[1]);
            } catch (Exception ex) {
                ex.printStackTrace();
                insOpne.setNetworks(null);

            }

            aux1 = "";
            lis.add(insOpne);
        }
        return lis;
    }
}
