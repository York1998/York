package com.jpj.project.common.domain;

import java.io.Serializable;
import java.util.Date;

public class Admin implements Serializable {
    private int id;
    private String name;
    private String password;
    private Date lastLoginTime;

    public int getId() {
        return id;
    }

    public Admin setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Admin setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public Admin setPassword(String password) {
        this.password = password;
        return this;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public Admin setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
        return this;
    }
}
