package com.tglm.bbs.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author mlgt
 * @date 2019/9/9
 */
@Entity
public class Post {
    private long postId;
    private String content;
    private long creatorId;
    private boolean topic;
    private Long formerPostId;
    private Timestamp dateCreate;
    private User userByCreatorId;

    @Id
    @Column(name = "post_id", nullable = false)
    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "creator_id", nullable = false)
    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    @Basic
    @Column(name = "topic", nullable = false)
    public boolean isTopic() {
        return topic;
    }

    public void setTopic(boolean topic) {
        this.topic = topic;
    }

    @Basic
    @Column(name = "former_post_id", nullable = true)
    public Long getFormerPostId() {
        return formerPostId;
    }

    public void setFormerPostId(Long formerPostId) {
        this.formerPostId = formerPostId;
    }

    @Basic
    @Column(name = "date_create", nullable = true)
    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return postId == post.postId &&
                creatorId == post.creatorId &&
                topic == post.topic &&
                Objects.equals(content, post.content) &&
                Objects.equals(formerPostId, post.formerPostId) &&
                Objects.equals(dateCreate, post.dateCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, content, creatorId, topic, formerPostId, dateCreate);
    }

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id", nullable = false)
    public User getUserByCreatorId() {
        return userByCreatorId;
    }

    public void setUserByCreatorId(User userByCreatorId) {
        this.userByCreatorId = userByCreatorId;
    }
}
