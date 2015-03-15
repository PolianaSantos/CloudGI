
package br.com.cloudGI.dao;

import br.com.cloudGI.entity.Flavor;
import br.com.cloudGI.entity.Image;
import br.com.cloudGI.entity.Instance;
import br.com.cloudGI.entity.InstanceUser;
import br.com.cloudGI.entity.InstanceUserList;
import br.com.cloudGI.entity.OpenStackInstance;
import br.com.cloudGI.entity.Profile;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anderson
 */
public interface IDAO_BD {

    public Connection getConn() throws ClassNotFoundException, SQLException;

    public boolean saveProfile(Profile profile);

    public boolean saveInstanceUser(InstanceUser instance);

    public boolean saveInstance(Instance instance);

    public ArrayList listLogin();

    public Map<String, String> listServiceUser(int id);

    public List<InstanceUserList> listInstanceUser(int id);

    public Profile selectUser(String login);

    public int selectInstanceUser(int idUser, String nome);

    public boolean updateProfile(Profile profile);

    public boolean updatePassword(String password, int id);

    public boolean updateImage(Image img);

    public boolean updateFlavor(Flavor flavor);

    public boolean updateIPStatus(OpenStackInstance ins);

    public boolean deleteService(int id_serv);

    public boolean deleteInstance(int id_serv);

}
