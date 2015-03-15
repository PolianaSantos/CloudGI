package br.com.cloudGI.entity;

/**
 *
 * @author Poliana Nascimento
 */
public class InstanceUserList {

    private String ip;
    private String name;
    private String Status;
    private String image;
    private String flavor;
    private String group;
    private String service;
    private String fault;
    private int id_InstanceUser;

    public int getId_InstanceUser() {
        return id_InstanceUser;
    }

    public void setId_InstanceUser(int id_InstanceUser) {
        this.id_InstanceUser = id_InstanceUser;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getFault() {
        return fault;
    }

    public void setFault(String fault) {
        this.fault = fault;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
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

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

}
