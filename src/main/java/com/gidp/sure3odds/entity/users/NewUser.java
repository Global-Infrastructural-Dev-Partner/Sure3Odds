package com.gidp.sure3odds.entity.users;

import com.gidp.sure3odds.entity.payments.PlanTypes;

import javax.persistence.ManyToOne;


public class NewUser {

    private String email;

    private String phone;

    private String password;

    private String firstname;

    private String lastname;

    @ManyToOne
    private UserTypes userTypes;

    private PlanTypes plantype;

    private String referencecode;

    private String platform;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }


    public PlanTypes getPlantype() {
        return plantype;
    }

    public void setPlantype(PlanTypes plantype) {
        this.plantype = plantype;
    }

    public String getReferencecode() {
        return referencecode;
    }

    public void setReferencecode(String referencecode) {
        this.referencecode = referencecode;
    }

    public UserTypes getUserTypes() {
        return userTypes;
    }

    public void setUserTypes(UserTypes userTypes) {
        this.userTypes = userTypes;
    }
}

