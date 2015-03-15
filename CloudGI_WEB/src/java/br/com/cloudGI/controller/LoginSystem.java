package br.com.cloudGI.controller;

import br.com.cloudGI.entity.Profile;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import br.com.cloudGI.dao.impl.DAO_BD_Derby;

/**
 *
 * @author Poliana Nascimento
 */
@ManagedBean(name = "loginSystem")
@SessionScoped
public class LoginSystem implements Serializable {

    private String password;
    private String login;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public String displayLocation() {
        String msg = "";
        FacesMessage msgs;
        try {
            if (!password.isEmpty() && !login.isEmpty()) {
                Profile profile;
                DAO_BD_Derby bd = new DAO_BD_Derby();

                profile = bd.selectUser(login);

                if (profile == null) {
                    msg = "Usuario não existe";
                } else {

                    if (profile.getPassword().equals(password)) {
                        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Perfil", profile);
                        return "home";
                    } else {
                        msg = "Senha Incorreta";
                    }
                }

            } else {
                msg = "Preencher todos os campos";
            }

        } catch (Exception e) {
            e.printStackTrace();
            msgs = new FacesMessage("Ocorreu um erro", msg);
            FacesContext.getCurrentInstance().addMessage(null, msgs);
            return prepareLogin();
        }

        msgs = new FacesMessage("Ocorreu um erro", msg);
        FacesContext.getCurrentInstance().addMessage(null, msgs);
        return "login";
    }

    public String prepareLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();

        return "home";
    }

}
