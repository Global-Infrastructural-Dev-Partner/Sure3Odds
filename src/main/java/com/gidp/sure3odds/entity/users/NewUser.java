package com.gidp.sure3odds.entity.users;

import com.gidp.sure3odds.entity.payments.PlanTypes;
import com.gidp.sure3odds.entity.users.UserTypes;
import com.gidp.sure3odds.entity.users.Users;

import java.util.Date;


public class NewUser {

    private UserTypes userTypeID;

    private String email;

    private String phone;

    private String password;

    private String firstname;

    private String lastname;

    private Date datejoined;

    private PlanTypes planTypeID;

    private Date startDate;

    private Date paymentdate;

    private String paymenttype;

    private String platform;

    private String referenceCode;

    public UserTypes getUserTypeID() {
        return userTypeID;
    }

    public void setUserTypeID(UserTypes userTypeID) {
        this.userTypeID = userTypeID;
    }

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

    public Date getDatejoined() {
        return datejoined;
    }

    public void setDatejoined(Date datejoined) {
        this.datejoined = datejoined;
    }

    public PlanTypes getPlanTypeID() {
        return planTypeID;
    }

    public void setPlanTypeID(PlanTypes planTypeID) {
        this.planTypeID = planTypeID;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getPaymentdate() {
        return paymentdate;
    }

    public void setPaymentdate(Date paymentdate) {
        this.paymentdate = paymentdate;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }



}