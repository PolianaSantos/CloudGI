package org.cloudGI.tasks;

import org.cloudGI.entity.Flavor;
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
public class TreatResultFlavor {

    public List<Flavor> treat() throws IOException {
        Exec ex = new Exec();
        ex.command("source devstack/openrc admin admin; nova flavor-list > flavor.txt");
        List<String> linhas = new ArrayList<>();
        ScpFrom cs = new ScpFrom();
        cs.file("flavor.txt");

        @SuppressWarnings("UnusedAssignment")
        String line = "";
        File file = new File("flavor.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            linhas.add(line);
        }

        return treatSpace(linhas);
    }

    @SuppressWarnings("UnusedAssignment")
    private List<Flavor> treatSpace(List<String> lines) {
        List<Flavor> flavors = new ArrayList<>();
        for (int i = 3; i < lines.size() - 1; i++) {
            String word = lines.get(i);
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
            Flavor flavor = new Flavor();
            flavor.setId_open(Integer.parseInt(list[0]));
            flavor.setName(list[1]);
            flavors.add(flavor);

            System.out.println(list[0]);
            aux1 = "";

        }
        return flavors;
    }
}
