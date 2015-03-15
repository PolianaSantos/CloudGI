/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudGI.tasks;

import br.com.cloudGI.entity.Instance;
import br.com.cloudGI.entity.OpenStackInstance;
import br.com.cloudGI.entity.InstanceUser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import br.com.cloudGI.dao.impl.DAO_BD_Derby;

/**
 *
 * @author Poliana Nascimento
 */
public class StartIntance {
    
    public boolean startInstance(int idUser, InstanceUser instUser) {
        boolean result = false;
        DAO_BD_Derby bd = new DAO_BD_Derby();
        int id_instUser = bd.selectInstanceUser(idUser, instUser.getName());
        instUser.setId(id_instUser);
        
        if (id_instUser != 0) {
            try {
                OpenStackCommands cos = new OpenStackCommands();
                List<String> name = new ArrayList<>();
                String img = cos.idImage(instUser.getId_image());
                int flavor = cos.idFlavor(instUser.getId_flavor());
                if (instUser.getId_fault() == 1) {
                    int alg = instUser.getId_service() + 1;
                    int cont = 1;
                    while (cont <= alg) {
                        Instance ins = new Instance();
                        
                        ins.setId_InstanceUser(id_instUser);
                        instUser.setId_flavor(flavor);
                        ins.setName(instUser.getName() + "_" + idUser + "_" + id_instUser + "_" + cont);
                        bd.saveInstance(ins);
                        cos.boot(instUser, img, ins);
                        name.add(ins.getName());
                        cont++;
                    }
                    Thread.sleep(10000);
                    TreatResultInstance trreI = new TreatResultInstance();
                    List<OpenStackInstance> Isopen;
                    Isopen = trreI.treat();
                    
                    for (int i = 0; i < Isopen.size(); i++) {
                        for (int j = 0; j < name.size(); j++) {
                            if (name.get(j).equalsIgnoreCase(Isopen.get(i).getName())) {
                                bd.updateIPStatus(Isopen.get(i));
                            }
                            
                        }
                        
                    }
                    
                } else if (instUser.getId_fault() == 2) {
                    int alg = (3 * instUser.getId_service()) + 1;
                    int cont = 1;
                    while (cont <= alg) {
                        Instance ins = new Instance();
                        
                        ins.setId_InstanceUser(id_instUser);
                        instUser.setId_flavor(flavor);
                        ins.setName(instUser.getName() + "_" + idUser + "_" + id_instUser + "_" + cont);
                        bd.saveInstance(ins);
                        cos.boot(instUser, img, ins);
                        name.add(ins.getName());
                        cont++;
                        
                    }
                    Thread.sleep(10000);
                    TreatResultInstance trreI = new TreatResultInstance();
                    List<OpenStackInstance> Isopen;
                    Isopen = trreI.treat();
                    
                    for (int i = 0; i < Isopen.size(); i++) {
                        for (int j = 0; j < name.size(); j++) {
                            if (name.get(j).equalsIgnoreCase(Isopen.get(i).getName())) {
                                bd.updateIPStatus(Isopen.get(i));
                            }
                            
                        }
                        
                    }
                }
                result= true;
            } catch (IOException ex) {
                Logger.getLogger(StartIntance.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } catch (InterruptedException ex) {
                Logger.getLogger(StartIntance.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return result;
    }
}
