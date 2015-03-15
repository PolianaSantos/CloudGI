/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cloudGI.dao.impl;

import br.com.cloudGI.dao.IDAO_BD;
import br.com.cloudGI.entity.Flavor;
import br.com.cloudGI.entity.Image;
import br.com.cloudGI.entity.Instance;
import br.com.cloudGI.entity.OpenStackInstance;
import br.com.cloudGI.entity.InstanceUser;
import br.com.cloudGI.entity.InstanceUserList;
import br.com.cloudGI.entity.Profile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Poliana Nascimento
 */
public class DAO_BD_Derby implements IDAO_BD {

    private Connection conn;

    @Override
    public Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CloudGI", "devstack", "openstack123");
        return conn;
    }

    @Override
    public boolean saveProfile(Profile profile) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("insert into Usuario (nome, login, senha, id_perfil, email) values (?,?,?,?,?)");
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getLogin());
            ps.setString(3, profile.getPassword());
            ps.setInt(4, profile.getProfile());
            ps.setString(5, profile.getEmail());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();

            return false;
        }
        return true;

    }

    @Override
    public boolean saveInstanceUser(InstanceUser instance) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("insert into UsuarioInstancia (ID_FLAVOR, NOME, ID_USUARIO, ID_SERVICO, ID_IMAGEM, ID_FALHA) values (?,?,?,?,?,?)");
            ps.setInt(1, instance.getId_flavor());
            ps.setString(2, instance.getName());
            ps.setInt(3, instance.getId_user());
            ps.setInt(4, instance.getId_service());
            ps.setInt(5, instance.getId_image());
            ps.setInt(6, instance.getId_fault());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();

            return false;
        }
        return true;

    }

    @Override
    public boolean saveInstance(Instance instance) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("insert into Instancia (id_usuarioinstancia, NOME) values (?,?)");
            ps.setInt(1, instance.getId_InstanceUser());
            ps.setString(2, instance.getName());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();

            return false;
        }
        return true;

    }

    @Override
    public ArrayList listLogin() {
        PreparedStatement ps = null;
        String user = null;
        ArrayList<String> list = new ArrayList<String>();

        try {

            ps = this.getConn().prepareStatement("Select login from usuario");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = rs.getString("login");
                list.add(user);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            return list;
        }
        return list;
    }

    @Override
    public Map<String, String> listServiceUser(int id) {
        PreparedStatement ps = null;
        String nome = null;
        String id_int = null;
        Map<String, String> map = new HashMap<String, String>();

        try {

            ps = this.getConn().prepareStatement("select u.nome, u.id from usuarioinstancia u where id_usuario = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                nome = rs.getString("nome");
                id_int = rs.getString("id");
                map.put(nome, id_int);

            }

        } catch (SQLException | ClassNotFoundException ex) {
            return map;
        }
        return map;
    }

    @Override
    public List<InstanceUserList> listInstanceUser(int id) {
        PreparedStatement ps = null;
        InstanceUserList user = null;
        List<InstanceUserList> list = new ArrayList<>();

        try {

            ps = this.getConn().prepareStatement("Select ui.id, i.nome, i.status, i.ip, im.descricao imagem, f.descricao flavor, fl.descricao falha, s.descricao serv, ui.nome grupo "
                    + "from instancia i, usuario u, usuarioinstancia ui, imagem im, flavor f, falha fl, servico s "
                    + "where u.id=ui.id_usuario and ui.id=i.id_usuarioinstancia and ui.id_imagem=im.id "
                    + "and f.id=ui.id_flavor and ui.id_falha=fl.id and ui.id_servico=s.id and u.id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new InstanceUserList();
                user.setFlavor(rs.getString("flavor"));
                user.setGroup(rs.getString("grupo"));
                user.setName(rs.getString("nome"));
                user.setIp(rs.getString("ip"));
                user.setFault(rs.getString("falha"));
                user.setService(rs.getString("serv"));
                user.setStatus(rs.getString("status"));
                user.setImage(rs.getString("imagem"));
                user.setId_InstanceUser(rs.getInt("id"));
                list.add(user);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            return list;
        }
        return list;
    }

    @Override
    public Profile selectUser(String login) {
        PreparedStatement ps = null;
        Profile user = null;
        try {

            ps = this.getConn().prepareStatement("Select * from usuario where login like ?");
            ps.setString(1, login);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                user = new Profile();
                user.setLogin(login);
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("senha"));
                user.setName(rs.getString("nome"));
                user.setId(rs.getInt("id"));
                user.setProfile(rs.getInt("id_perfil"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            return user;
        }
        return user;
    }

    @Override
    public int selectInstanceUser(int idUser, String nome) {
        PreparedStatement ps = null;
        int id_instanceUser = 0;
        try {

            ps = this.getConn().prepareStatement("Select max (id) from UsuarioInstancia where nome like ? and id_usuario = ?");
            ps.setString(1, nome);
            ps.setInt(2, idUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id_instanceUser = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return id_instanceUser;
        }
        return id_instanceUser;
    }

    @Override
    public boolean updateProfile(Profile profile) {
        PreparedStatement ps = null;
        System.out.println(profile.getId());
        try {
            ps = this.getConn().prepareStatement("update usuario set nome=?, email=? where id=?");
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getEmail());
            ps.setInt(3, profile.getId());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            return false;
        }
        return true;

    }

    @Override
    public boolean updatePassword(String password, int id) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("update usuario set senha=? where id=?");
            ps.setString(1, password);
            ps.setInt(2, id);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            return false;

        }
        return true;

    }

    @Override
    public boolean updateImage(Image img) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("update imagem set id_open=? where id=?");
            ps.setString(1, img.getId_open());
            ps.setInt(2, img.getId());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return false;
        }
        return true;

    }

    @Override
    public boolean updateFlavor(Flavor flavor) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("update flavor set id_open=? where descricao like ?");
            ps.setInt(1, flavor.getId_open());
            ps.setString(2, flavor.getName());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return false;
        }
        return true;

    }

    @Override
    public boolean updateIPStatus(OpenStackInstance ins) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("update instancia set ip=?, status=? where nome=?");
            ps.setString(1, ins.getNetworks());
            ps.setString(2, ins.getStatus());
            ps.setString(3, ins.getName());
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return false;
        }
        return true;

    }

    @Override
    public boolean deleteService(int id_serv) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("delete from usuarioinstancia where id =?");
            ps.setInt(1, id_serv);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return false;
        }
        return true;

    }

    @Override
    public boolean deleteInstance(int id_serv) {
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("delete from instancia where id_usuarioinstancia =?");
            ps.setInt(1, id_serv);
            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return false;
        }
        return true;

    }
}
