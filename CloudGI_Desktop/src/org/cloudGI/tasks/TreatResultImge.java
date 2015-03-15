/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.tasks;

import org.cloudGI.entity.Image;
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
public class TreatResultImge {

    public List<Image> treat() throws IOException {
        Exec ex = new Exec();
        ex.command("source devstack/openrc admin admin; nova image-list > imagem.txt");
        List<String> lines = new ArrayList<>();
        ScpFrom cs = new ScpFrom();
        cs.file("imagem.txt");

        @SuppressWarnings("UnusedAssignment")
        String line = "";
        File file = new File("imagem.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        return treatSpace(lines);
    }

    @SuppressWarnings("UnusedAssignment")
    private List<Image> treatSpace(List<String> lines) {
        List<Image> imgs = new ArrayList<>();
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
            Image img = new Image();
            img.setId_open(list[0]);
            img.setName(list[1]);
            img.setStatus(list[2]);
            imgs.add(img);

            System.out.println(list[0]);
            aux1 = "";

        }
        return imgs;
    }
}
