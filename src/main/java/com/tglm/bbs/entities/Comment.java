package com.tglm.bbs.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.util.Objects;

/**
 * @author mlgt
 * @date 2019/9/9
 */
@Entity
public class Comment {
    private String content;
    private Date date;
    private long commentId;
    private Long formerComment;
    private Long postId;

    @Basic
    @Column(name = "content", nullable = false, length = -1)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "comment_id", nullable = false)
    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "former_comment", nullable = true)
    public Long getFormerComment() {
        return formerComment;
    }

    public void setFormerComment(Long formerComment) {
        this.formerComment = formerComment;
    }

    @Basic
    @Column(name = "post_id", nullable = true)
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentId == comment.commentId &&
                Objects.equals(content, comment.content) &&
                Objects.equals(date, comment.date) &&
                Objects.equals(formerComment, comment.formerComment) &&
                Objects.equals(postId, comment.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content, date, commentId, formerComment, postId);
    }
}
