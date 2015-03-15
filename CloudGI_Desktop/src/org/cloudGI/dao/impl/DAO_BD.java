/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.cloudGI.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.cloudGI.dao.IDAO_BD;
import org.cloudGI.entity.Flavor;
import org.cloudGI.entity.Image;
import org.cloudGI.entity.Instance;
import org.cloudGI.entity.InstanceUser;
import org.cloudGI.entity.OpenStackInstance;
import org.cloudGI.entity.Profile;
import org.cloudGI.entity.Renewal;

/**
 *
 * @author Poliana Nascimento
 */
public class DAO_BD implements IDAO_BD {

    private Connection conn;

    @Override
    public Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        this.conn = DriverManager.getConnection("jdbc:derby://localhost:1527/CloudGI", "devstack", "openstack123");
        return conn;
    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean saveProfile(Profile profile) {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        try {
            ps = this.getConn().prepareStatement("insert into Usuario (nome, login, senha, id_perfil) values (?,?,?,?)");
            ps.setString(1, profile.getName());
            ps.setString(2, profile.getLogin());
            ps.setString(3, profile.getPassword());
            ps.setInt(4, profile.getProfile());

            ps.executeUpdate();

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();

            return false;
        }
        return true;

    }

    @Override
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean saveInstanceUser(InstanceUser instance) {
        @SuppressWarnings("UnusedAssignment")
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
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean saveInstance(Instance instance) {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        try {
            try {
                ps = this.getConn().prepareStatement("insert into Instancia (id_usuarioinstancia, NOME) values (?,?)");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(DAO_BD.class.getName()).log(Level.SEVERE, null, ex);
            }
            ps.setInt(1, instance.getId_instanceUser());
            ps.setString(2, instance.getName());

            ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();

            return false;
        }
        return true;

    }

    @Override
    public ArrayList listLogin() {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        @SuppressWarnings("UnusedAssignment")
        String user = null;
        @SuppressWarnings("Convert2Diamond")
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
    public Profile selectProfile(String login) {
        @SuppressWarnings("UnusedAssignment")
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
                user.setPerfil(rs.getInt("id_perfil"));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            return user;
        }
        return user;
    }

    @Override
    public int selectInstanceUser(int idUser, String name) {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        int id_instaciaUser = 0;
        try {

            ps = this.getConn().prepareStatement("Select max (id) from UsuarioInstancia where nome like ? and id_usuario = ?");
            ps.setString(1, name);
            ps.setInt(2, idUser);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id_instaciaUser = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return id_instaciaUser;
        }
        return id_instaciaUser;
    }

    @Override
    public boolean updateProfile(Profile profile) {
        @SuppressWarnings("UnusedAssignment")
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
        @SuppressWarnings("UnusedAssignment")
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
        @SuppressWarnings("UnusedAssignment")
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
        @SuppressWarnings("UnusedAssignment")
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
        @SuppressWarnings("UnusedAssignment")
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
    public List<Instance> selectInstance() {

        @SuppressWarnings("UnusedAssignment")
        Statement stmt = null;
        @SuppressWarnings("UnusedAssignment")
        ResultSet rs = null;
        List<Instance> users = new ArrayList();
        try {

            stmt = this.getConn().createStatement();
            //.prepareStatement("Select * from Instancia");
            rs = stmt.executeQuery("Select i.*, f.id_open flavor, im.id_open imagem from Instancia i, usuarioinstancia e, flavor f, imagem im where i.id_usuarioinstancia= e.id and e.id_imagem=im.id and e.id_flavor=f.id");
            while (rs.next()) {
                Instance instance = new Instance();
                instance.setId(rs.getInt("id"));
                instance.setName(rs.getString("nome"));
                instance.setId_usuarioInstancia(rs.getInt("id_usuarioinstancia"));
                instance.setStatus(rs.getString("status"));
                instance.setIp(rs.getString("ip"));
                instance.setId_flavor(rs.getString("flavor"));
                instance.setId_image(rs.getString("imagem"));
                users.add(instance);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DAO_BD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return users;
    }

    @Override
    public int selectFlavor(int flavor) {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        int id_flavor = 0;
        try {

            ps = this.getConn().prepareStatement("Select id_open from flavor where id = ?");
            ps.setInt(1, flavor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id_flavor = rs.getInt(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return id_flavor;
        }
        return id_flavor;
    }

    @SuppressWarnings("override")
    public String selectImage(int id_imagem) {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        String image = null;
        try {

            ps = this.getConn().prepareStatement("Select id_open from imagem where id = ?");
            ps.setInt(1, id_imagem);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                image = rs.getString(1);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.err.println(ex);
            return image;
        }
        return image;
    }

    @Override
    public List<Instance> listByzantineInstance() {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        @SuppressWarnings("UnusedAssignment")
        Instance user = null;
        List<Instance> list = new ArrayList<>();

        try {

            ps = this.getConn().prepareStatement("select i.nome, i.ip, s.id, i.status "
                    + "from instancia i, usuarioinstancia s "
                    + "where i.id_usuarioinstancia=s.id and s.id_falha=2");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new Instance();
                user.setId_usuarioInstancia(rs.getInt("id"));
                user.setName(rs.getString("nome"));
                user.setIp(rs.getString("ip"));
                user.setStatus(rs.getString("status"));
                list.add(user);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return list;

        }
        return list;
    }

    @Override
    public List listIdServerUser() {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        int user;
        List<Integer> list = new ArrayList<>();

        try {

            ps = this.getConn().prepareStatement("Select id from usuarioinstancia");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = rs.getInt("id");
                list.add(user);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            return list;
        }
        return list;
    }

    @Override
    public List<Renewal> listRenewal() {
        @SuppressWarnings("UnusedAssignment")
        PreparedStatement ps = null;
        Renewal user;
        List<Renewal> list = new ArrayList<>();

        try {

            ps = this.getConn().prepareStatement("select iu.id, ((s.id*3)+1) replicas "
                    + "from usuarioinstancia iu, servico s "
                    + "where iu.id_servico=s.id and iu.id_falha=2");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new Renewal();
                user.setId_instanceUser(rs.getInt("id"));
                user.setSize(rs.getInt("replicas"));
                list.add(user);
            }

        } catch (SQLException | ClassNotFoundException ex) {
            return list;
        }
        return list;
    }

}
