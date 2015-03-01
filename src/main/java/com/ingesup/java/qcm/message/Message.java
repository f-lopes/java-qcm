package com.ingesup.java.qcm.message;

/**
 * Created by lopes_f on 1/8/2015.
 * <florian.lopes@outlook.com>
 */
public final class Message {

    private MessageType messageType;

    private String text;

    public Message(){
    }

    public String getStatus(){
        return this.messageType.name().toLowerCase();
    }

    public MessageType getType(){
        return this.messageType;
    }

    public String getText(){
        return this.text;
    }

    public String toString(){
        return messageType + ": " + text;
    }

	public Message setMessageType(MessageType messageType) {
		this.messageType = messageType;
		return this;
	}

	public Message setText(String text) {
		this.text = text;
		return this;
	}
}
