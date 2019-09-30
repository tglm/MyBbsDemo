package com.tglm.bbs.util;

import com.tglm.bbs.dto.CommentInfo;
import com.tglm.bbs.dto.PostInfo;
import com.tglm.bbs.dto.UserInfo;
import com.tglm.bbs.entities.Comment;
import com.tglm.bbs.entities.Post;
import com.tglm.bbs.entities.User;

import java.util.Date;

/**
 * @author mlgt
 * @date 2019/9/14
 */
public class InfoUtil {

    /**
     * @param user User
     * @return UserInfo
     */
    public static UserInfo toUserInfo(User user) {
        return new UserInfo(
                user.getUserId(),
                user.getUsername(),
                user.getAvatar()
        );
    }

    public static CommentInfo toCommentInfo(Comment comment) {
        Date date = comment.getDate();
        return new CommentInfo(
                comment.getContent(),
                date,
                comment.getCommentId(),
                comment.getPostId(),
                comment.getFormerComment()

        );
    }


    public static PostInfo toPostInfo(Post post) {

        return new PostInfo(
                post.getPostId(),
                post.getContent(),
                post.getCreatorId(),
                post.isTopic(),
                post.getFormerPostId(),
                DateUtil.timestampToDate(post.getDateCreate())
        );

    }




}
