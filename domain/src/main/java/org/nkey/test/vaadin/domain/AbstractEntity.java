package org.nkey.test.vaadin.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author m.nikolaev Date: 30.10.12 Time: 22:44
 */
@MappedSuperclass
public class AbstractEntity<T extends AbstractEntity> implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
