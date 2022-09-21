package com.learn.springcloud.bo;

import java.io.Serializable;

public class RokectMQMessage implements Serializable {
    private String msgId;
    private String msgContent;

    public RokectMQMessage(String id, String s) {
        msgId =id;
        msgContent = s;
    }


    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }
}
