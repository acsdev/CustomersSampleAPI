package br.eti.sca.customersapi.model.dto;

import com.google.gson.annotations.Expose;

import java.io.Serializable;
import java.sql.Date;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = -813297887;

    @Expose
    private Integer id;

    @Expose
    private Long nrRegister;

    @Expose
    private String  dsUsename;

    @Expose(serialize = false)
    private String  dsPassword;

    @Expose
    private String  dsName;

    @Expose
    private Date    dtBirth;

    @Expose
    private String  dsEmail;

    public CustomerDTO() {}

    public CustomerDTO(CustomerDTO value) {
        this.id = value.id;
        this.nrRegister = value.nrRegister;
        this.dsUsename = value.dsUsename;
        this.dsPassword = value.dsPassword;
        this.dsName = value.dsName;
        this.dtBirth = value.dtBirth;
        this.dsEmail = value.dsEmail;
    }

    public CustomerDTO(
        Integer id,
        Long nrRegister,
        String  dsUsename,
        String  dsPassword,
        String  dsName,
        Date    dtBirth,
        String  dsEmail
    ) {
        this.id = id;
        this.nrRegister = nrRegister;
        this.dsUsename = dsUsename;
        this.dsPassword = dsPassword;
        this.dsName = dsName;
        this.dtBirth = dtBirth;
        this.dsEmail = dsEmail;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getNrRegister() {
        return this.nrRegister;
    }

    public void setNrRegister(Long nrRegister) {
        this.nrRegister = nrRegister;
    }

    public String getDsUsename() {
        return this.dsUsename;
    }

    public void setDsUsename(String dsUsename) {
        this.dsUsename = dsUsename;
    }

    public String getDsPassword() {
        return this.dsPassword;
    }

    public void setDsPassword(String dsPassword) {
        this.dsPassword = dsPassword;
    }

    public String getDsName() {
        return this.dsName;
    }

    public void setDsName(String dsName) {
        this.dsName = dsName;
    }

    public Date getDtBirth() {
        return this.dtBirth;
    }

    public void setDtBirth(Date dtBirth) {
        this.dtBirth = dtBirth;
    }

    public String getDsEmail() {
        return this.dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("CustomerDTO (");

        sb.append(id);
        sb.append(", ").append(nrRegister);
        sb.append(", ").append(dsUsename);
        sb.append(", ").append(dsPassword);
        sb.append(", ").append(dsName);
        sb.append(", ").append(dtBirth);
        sb.append(", ").append(dsEmail);

        sb.append(")");
        return sb.toString();
    }
}
