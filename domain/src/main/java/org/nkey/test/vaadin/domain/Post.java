package org.nkey.test.vaadin.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

/**
 * @author m.nikolaev Date: 30.10.12 Time: 22:39
 */
@Entity
public class Post extends AbstractEntity<Post> {
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
    @OneToMany
    private List<Comment> comments = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
