/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.cloudGI.entity.Flavor;
import org.cloudGI.entity.Image;
import org.cloudGI.entity.Instance;
import org.cloudGI.entity.InstanceUser;
import org.cloudGI.entity.OpenStackInstance;
import org.cloudGI.entity.Profile;
import org.cloudGI.entity.Renewal;

/**
 *
 * @author anderson
 */
public interface IDAO_BD {

    public Connection getConn() throws ClassNotFoundException, SQLException;

    public List<Renewal> listRenewal();

    public List listIdServerUser();

    public List<Instance> listByzantineInstance();

    public String selectImage(int id_imagem);

    public int selectFlavor(int flavor);

    public boolean saveProfile(Profile profile);

    public boolean saveInstanceUser(InstanceUser instance);

    public boolean saveInstance(Instance instance);

    public ArrayList listLogin();

    public Profile selectProfile(String login);

    public int selectInstanceUser(int idUser, String name);

    public boolean updateProfile(Profile profile);

    public boolean updatePassword(String password, int id);

    public boolean updateImage(Image img);

    public boolean updateFlavor(Flavor flavor);

    public boolean updateIPStatus(OpenStackInstance ins);

    public List<Instance> selectInstance();

}
