package com.tglm.bbs.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author mlgt
 * @date 2019/9/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Session implements Serializable {


    private String sessionId;
    private Date lastAccessTime;
    private String username;

}
