package br.com.cloudGI.tasks;

import br.com.cloudGI.entity.Image;
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
public class TreatResultImage {

    public List<Image> treat() throws IOException {
        Exec ex = new Exec();
        ex.command("source devstack/openrc admin admin; nova image-list > imagem.txt");
        List<String> lines = new ArrayList<>();
        ScpFrom cs = new ScpFrom();
        cs.file("imagem.txt");

        String line = "";
        File file = new File("imagem.txt");
        BufferedReader reader = new BufferedReader(new FileReader(file));

        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }

        return treatSpace(lines);
    }

    private List<Image> treatSpace(List<String> lines) {
        List<Image> imgs = new ArrayList<>();
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
            Image img = new Image();
            img.setId_open(list[0]);
            img.setName(list[1]);
            img.setStatus(list[2]);
            imgs.add(img);

            System.out.println(list[0]);
            aux3 = "";

        }
        return imgs;
    }
}
