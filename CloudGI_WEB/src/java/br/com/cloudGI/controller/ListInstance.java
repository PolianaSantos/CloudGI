package br.com.cloudGI.controller;

import br.com.cloudGI.entity.InstanceUserList;
import br.com.cloudGI.entity.Profile;
import br.com.cloudGI.tasks.OpenStackCommands;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.cloudGI.dao.impl.DAO_BD_Derby;

/**
 *
 * @author Poliana Nascimento
 */
@ManagedBean(name = "instUser")
@ViewScoped
public class ListInstance implements Serializable {

    private List<InstanceUserList> list;
    private Map<String, String> maps;
    private Profile user = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Perfil");
    private DAO_BD_Derby bd;
    private String map;

    @PostConstruct
    public void init() {
        bd = new DAO_BD_Derby();
        list = bd.listInstanceUser(user.getId());
        maps = bd.listServiceUser(user.getId());

    }

    public Map<String, String> getMaps() {
        return maps;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public void setMaps(Map<String, String> map) {
        this.maps = maps;
    }

    public Profile getUser() {
        return user;
    }

    public void setUser(Profile user) {
        this.user = user;
    }

    public List<InstanceUserList> getList() {
        return list;
    }

    public void displayLocation() {
        FacesMessage msg;
        if (!map.isEmpty()) {
            bd = new DAO_BD_Derby();

            boolean result = bd.deleteService(Integer.parseInt(map));
            if (result) {
                OpenStackCommands cos = new OpenStackCommands();
                result = bd.deleteInstance(Integer.parseInt(map));
                for (InstanceUserList list1 : list) {
                    if (list1.getId_InstanceUser() == Integer.parseInt(map)) {
                        cos.delete(list1.getName());
                    }
                }
                if (result) {
                    msg = new FacesMessage("Serviço Excluido", "Operação realizada com Sucesso!");
                    list = bd.listInstanceUser(user.getId());
                    maps = bd.listServiceUser(user.getId());
                } else {
                    msg = new FacesMessage("Não foi possível excluir");
                }
            } else {
                msg = new FacesMessage("Não foi possível excluir");
            }

        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preenchimento Obrigatório *", "Preencher todos os campos");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
