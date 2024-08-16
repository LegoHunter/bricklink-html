package com.bricklink.web.model;

import lombok.Data;

@Data
public class AuthenticationResult {
    private User user;
    private String ipaddr;
    private int returnCode;
    private String returnMessage;
    private int errorTicket;
    private int procssingTime;
    private String strRefNo;

    @Data
    public class User {
        private int user_no;
        private String user_id;
        private String user_name;
        private int user_type;
        private int user_bstaus;
        private int user_sstaus;
        private String user_profile_url;
        private int url_after_login;
        private long user_since;
    }
}
