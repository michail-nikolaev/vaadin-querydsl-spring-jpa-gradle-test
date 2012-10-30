package org.nkey.test.vaadin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author m.nikolaev Date: 30.10.12 Time: 22:39
 */
@Entity
public class Comment extends AbstractEntity<Comment>{
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
    @ManyToOne
    private User writer;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getWriter() {
        return writer;
    }

    public void setWriter(User writer) {
        this.writer = writer;
    }
}
