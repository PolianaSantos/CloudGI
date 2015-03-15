package br.com.cloudGI.controller;

import br.com.cloudGI.entity.InstanceUser;
import br.com.cloudGI.entity.Profile;
import br.com.cloudGI.tasks.StartIntance;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import br.com.cloudGI.dao.impl.DAO_BD_Derby;

/**
 *
 * @author Poliana Nascimento
 */
@ViewScoped
@ManagedBean(name = "registerInstance")
@SessionScoped
public class RegisterInstance implements Serializable {

    Profile user = (Profile) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Perfil");
    private Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
    private String fault;
    private String service;
    private String image;
    private String flavor;
    private String name;
    private Map<String, String> faults;
    private Map<String, String> services;
    private Map<String, String> images;
    private Map<String, String> flavors;

    @PostConstruct
    public void init() {
        faults = new HashMap<String, String>();
        faults.put("Crash", "Crash");
        faults.put("Bizantinas", "Bizantinas");

        Map<String, String> map = new HashMap<String, String>();
        map.put("Ouro - 4 Instancias - 3 falhas", "3");
        map.put("Prata - 3 Instancias - 2 falhas", "2");
        map.put("Bronze - 2 Instancia - 1 falha", "1");
        data.put("Crash", map);

        map = new HashMap<String, String>();
        map.put("Ouro - 10 Instancias - 3 falhas", "3");
        map.put("Prata - 7 Instancias - 2 falhas", "2");
        map.put("Bronze - 4 Instancias - 1 falha", "1");
        data.put("Bizantinas", map);

        images = new HashMap<String, String>();
        images.put("Cirros-x86", "2");
        images.put("Fedora-x86", "1");

        flavors = new HashMap<String, String>();
        flavors.put("m1.nano", "1");
        flavors.put("m1.micro", "2");

    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Map<String, String> getFaults() {
        return faults;
    }

    public Map<String, String> getServices() {
        return services;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public Map<String, String> getImages() {
        return images;
    }

    public void setImages(Map<String, String> images) {
        this.images = images;
    }

    public Map<String, String> getFlavors() {
        return flavors;
    }

    public void setFlavors(Map<String, String> tamanhos) {
        this.flavors = tamanhos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void onFalhaChange() {
        if (fault != null && !fault.equals("")) {
            services = data.get(fault);
        } else {
            services = new HashMap<String, String>();
        }
    }

    public void displayLocation() {
        FacesMessage msg;
        if (service != null && fault != null && !name.isEmpty() && flavor != null && image != null) {
            DAO_BD_Derby bd = new DAO_BD_Derby();
            InstanceUser is = new InstanceUser();
            is.setId_flavor(Integer.parseInt(flavor));
            if (fault.equalsIgnoreCase("Crash")) {
                is.setId_fault(1);
            } else if (fault.equalsIgnoreCase("Bizantinas")) {
                is.setId_fault(2);
            }

            is.setId_image(Integer.parseInt(image));
            is.setId_user(user.getId());

            is.setId_service(Integer.valueOf(service));
            is.setNome(name);
            boolean result = bd.saveInstanceUser(is);
            if (result) {
                StartIntance initIns = new StartIntance();
                result = initIns.startInstance(user.getId(), is);
                if (result) {
                    msg = new FacesMessage("Instâncias Criadas", "Operação realizada com Sucesso!");
                } else {
                    msg = new FacesMessage("Instâncias não Criadas--");
                }
            } else {
                msg = new FacesMessage("Instâncias não Criadas--");
            }

        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Preenchimento Obrigatório *", "Preencher todos os campos");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
