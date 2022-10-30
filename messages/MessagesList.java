package com.ass2.f190260_i190468.messages;

public class MessagesList {
    private String name,mobile,lastMessage,profilePic,chatKey;
    private Integer unSeenMessages;

    public MessagesList(String name, String mobile, String lastMessage, String profilePic, int unSeenMessages,String chatKey) {
        this.name = name;
        this.mobile = mobile;
        this.lastMessage = lastMessage;
        this.profilePic = profilePic;
        this.unSeenMessages = unSeenMessages;
        this.chatKey = chatKey;
    }

    public void setchatKey(String chatKey) {
        this.chatKey = chatKey;
    }

    public String getchatKey() {
        return chatKey;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public void setUnSeenMessages(int unSeenMessages) {
        this.unSeenMessages = unSeenMessages;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public int getUnSeenMessages() {
        return unSeenMessages;
    }
}
