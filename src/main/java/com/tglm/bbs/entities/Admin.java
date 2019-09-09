package com.tglm.bbs.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @author mlgt
 * @date 2019/9/9
 */
@Entity
public class Admin {
    private String account;
    private String password;

    @Id
    @Column(name = "account", nullable = false, length = -1)
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Basic
    @Column(name = "password", nullable = false, length = -1)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Admin admin = (Admin) o;
        return Objects.equals(account, admin.account) &&
                Objects.equals(password, admin.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(account, password);
    }
}
