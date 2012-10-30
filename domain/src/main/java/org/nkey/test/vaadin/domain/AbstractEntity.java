package org.nkey.test.vaadin.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

/**
 * @author m.nikolaev Date: 30.10.12 Time: 22:44
 */
@MappedSuperclass
public class AbstractEntity<T extends AbstractEntity> {
    private static final String SEQUENCE_NAME = "id_seq";

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME) @SequenceGenerator(
            name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
