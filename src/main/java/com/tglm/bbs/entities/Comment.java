package com.tglm.bbs.entities;

import com.tglm.bbs.util.DateUtil;
import com.tglm.bbs.dto.CommentInfo;
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
public class Comment {
    private String content;
    private Timestamp date;
    private Long commentId;
    private Long formerComment;
    private Long postId;

    public Comment(CommentInfo commentInfo) throws ParseException {

        this.content = commentInfo.getContent();
        this.formerComment = commentInfo.getFormerComment();
        this.postId = commentInfo.getPostId();
        this.date = DateUtil.dateToTimestamp(new Date(System.currentTimeMillis()));
        this.commentId = null;

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
    @Column(name = "date", nullable = false)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Id
    @Column(name = "comment_id", nullable = false)
    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    @Basic
    @Column(name = "former_comment")
    public Long getFormerComment() {
        return formerComment;
    }

    public void setFormerComment(Long formerComment) {
        this.formerComment = formerComment;
    }

    @Basic
    @Column(name = "post_id")
    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment = (Comment) o;
        return commentId.equals(comment.commentId) &&
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
