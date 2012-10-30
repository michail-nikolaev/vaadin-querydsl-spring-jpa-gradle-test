package org.nkey.test.vaadin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author m.nikolaev Date: 30.10.12 Time: 22:39
 */
@Entity
public class User extends AbstractEntity<User> {
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
