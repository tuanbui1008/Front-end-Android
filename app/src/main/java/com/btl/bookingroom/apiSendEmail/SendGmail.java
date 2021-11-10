package com.btl.bookingroom.apiSendEmail;


import com.btl.bookingroom.constants.Constant;

public class SendGmail {
    private static final String GMAIL_ADMIN = "buiminhtuan@gmail.com";
    private static final String PASSWORD_GMAIL = "tuanbmhd2001";

    public void send(String msg, String gmail) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    GMailSender mailSender = new GMailSender(GMAIL_ADMIN, PASSWORD_GMAIL);
                    mailSender.sendMail(Constant.SUBJECT_SEND, msg,
                            GMAIL_ADMIN, gmail);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
