package com.tglm.bbs.entities;

import com.tglm.bbs.dto.ModifiedPostInfo;
import com.tglm.bbs.dto.NewPostInfo;
import com.tglm.bbs.security.exception.ServiceException;
import com.tglm.bbs.request.ThreadContext;
import com.tglm.bbs.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

/**
 * @author mlgt
 * @date 2019/9/14
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    private Long postId;
    private String content;
    private Long creatorId;
    private boolean topic;
    private Long formerPostId;
    private Timestamp dateCreate;

    public Post(NewPostInfo newPostInfo) throws ParseException, ServiceException {

        this.content = newPostInfo.getContent();
        this.creatorId = ThreadContext.getOperator().getUserId();
        this.topic = newPostInfo.isTopic();
        this.dateCreate= DateUtil.dateToTimestamp(new Date(System.currentTimeMillis()));
        if(newPostInfo.getFormerPostId() != null){
            this.formerPostId = newPostInfo.getFormerPostId();
        }

    }
    public Post(ModifiedPostInfo modifiedPostInfo) throws ServiceException, ParseException {
        this.postId = modifiedPostInfo.getPostId();
        this.content = modifiedPostInfo.getContent();
        this.creatorId = ThreadContext.getOperator().getUserId();
        this.formerPostId = modifiedPostInfo.getFormerPostId();
        this.dateCreate = DateUtil.dateToTimestamp(new Date(System.currentTimeMillis()));
    }

    @Id
    @Column(name = "post_id", nullable = false)
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
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
    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
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
    @Column(name = "former_post_id")
    public Long getFormerPostId() {
        return formerPostId;
    }

    public void setFormerPostId(Long formerPostId) {
        this.formerPostId = formerPostId;
    }

    @Basic
    @Column(name = "date_create")
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
        return postId.equals(post.postId) &&
                creatorId.equals(post.creatorId) &&
                topic == post.topic &&
                Objects.equals(content, post.content) &&
                Objects.equals(formerPostId, post.formerPostId) &&
                Objects.equals(dateCreate, post.dateCreate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, content, creatorId, topic, formerPostId, dateCreate);
    }


}
