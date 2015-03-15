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
public class TreatResult {

    public void treat() throws IOException {
        Exec ex = new Exec();
        ex.command("source devstack/openrc admin admin; nova list > instancias.txt");
        List<String> lines = new ArrayList<>();

        String line = "";
        File file = new File("instancias.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        treatSpace(lines);
    }

    @SuppressWarnings("UnusedAssignment")
    private void treatSpace(List<String> line) {

        for (int i = 3; i < line.size() - 1; i++) {
            String word = line.get(i);
            String aux = "";
            String aux1 = "";
            for (int j = 0; j < word.length(); j++) {
                if (j == word.length() - 1) {
                    break;
                } else if (String.valueOf(word.charAt(j)).equalsIgnoreCase(" ") && String.valueOf(word.charAt(j + 1)).equalsIgnoreCase("|")) {
                    aux = aux.replace("|", "");
                    aux = aux.replaceAll(" ", "");
                    aux = aux + ";";
                    aux1 = aux1 + aux;

                    aux = "";
                } else {
                    aux = aux + String.valueOf(word.charAt(j));
                }

            }
            String list[] = aux1.split(";");
            OpenStackInstance insOpne = new OpenStackInstance();
            insOpne.setId(list[0]);
            insOpne.setName(list[1]);
            insOpne.setStatus(list[2]);
            insOpne.setTaskState(list[3]);
            insOpne.setPowerState(list[4]);
            String ip[] = list[5].split("=");
            insOpne.setNetworks(ip[1]);
            System.out.println(ip[1]);
            aux1 = "";

        }
    }
}
