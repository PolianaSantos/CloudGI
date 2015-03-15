/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudGI.tasks;

import br.com.cloudGI.entity.OpenStackInstance;
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

    private List<OpenStackInstance> treatSpace(List<String> lines) throws InterruptedException {
        List<OpenStackInstance> lis = new ArrayList<>();
        OpenStackInstance insOpne;
        for (int i = 3; i < lines.size() - 1; i++) {
            String word = lines.get(i);
            String aux2 = "";
            String aux3 = "";
            for (int j = 0; j < word.length(); j++) {
                if (j == word.length() - 1) {
                    break;
                } else if (String.valueOf(word.charAt(j)).equalsIgnoreCase(" ") && String.valueOf(word.charAt(j + 1)).equalsIgnoreCase("|")) {
                    aux2 = aux2.replace("|", "");
                    aux2 = aux2.replaceAll(" ", "");
                    aux2 = aux2 + ";";
                    aux3 = aux3 + aux2;

                    aux2 = "";
                } else {
                    aux2 = aux2 + String.valueOf(word.charAt(j));
                }

            }
            String list[] = aux3.split(";");
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
                Thread.sleep(8000);

                insOpne.setNetworks(null);
                System.out.println(insOpne.getNetworks());
            }

            aux3 = "";
            lis.add(insOpne);
        }
        return lis;
    }
}
