package br.com.cloudGI.controller;

import br.com.cloudGI.entity.Profile;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.cloudGI.dao.impl.DAO_BD_Derby;

/**
 *
 * @author Poliana Nascimento
 */
@ManagedBean
@ViewScoped
public class ChangePassword implements Serializable {

    private String oldPassword;
    private String currentPassword;
    private String confirmPassword;

    Profile user = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Perfil");
    DAO_BD_Derby bd = new DAO_BD_Derby();

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String ConfirmPassord) {
        this.confirmPassword = ConfirmPassord;
    }

    public void displayLocation() {
        FacesMessage msg;
        if (!oldPassword.isEmpty() && !currentPassword.isEmpty() && !confirmPassword.isEmpty()) {
            Profile pf = bd.selectUser(user.getLogin());
            if (pf.getPassword().equals(oldPassword)) {
                if (currentPassword.equals(confirmPassword)) {
                    bd.updatePassword(currentPassword, user.getId());
                }

            }

            msg = new FacesMessage("Dados Salvos", "Senha atualizada com sucesso!");

        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preenchimento Obrigatório *", "Preencher todos os campos");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
