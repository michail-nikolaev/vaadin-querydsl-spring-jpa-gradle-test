package org.nkey.test.vaadin.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author m.nikolaev Date: 30.10.12 Time: 22:39
 */
@Entity
public class Blog extends AbstractEntity<Blog> {
    @OneToOne
    private User owner;
    @ManyToMany
    private Set<User> readers = new HashSet<>();
    @OneToMany
    private List<Post> posts = new ArrayList<>();

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<User> getReaders() {
        return readers;
    }

    public void setReaders(Set<User> readers) {
        this.readers = readers;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}
