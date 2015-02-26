package com.panqd.mybatis.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知表
 * @author KingDom
 */
@SuppressWarnings("serial")
public class NotifyLog implements Serializable {

    private Long id;

    private String title;

    private String content;

    private Date sendDate;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSendDate() {
        return this.sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    @Override
    public String toString() {
        return "NotifyLog [id=" + id + ", title=" + title + ", content="
                + content + ", sendDate=" + sendDate + "]";
    }

}
