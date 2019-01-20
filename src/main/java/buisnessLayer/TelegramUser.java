package buisnessLayer;

import dataAccesLayer.entity.Requests;

import java.util.ArrayList;
import java.util.List;

public class TelegramUser {
//    public TelegramUser(Long userKey) {
//        this.userKey = userKey;
//    }

    boolean loginflag=false;
        boolean logined=false;
        Long userKey=null;
        String login;
        List<Requests> invites=new ArrayList();
        boolean isinvites=false;

    public boolean isLoginflag() {
        return loginflag;
    }

    public void setLoginflag(boolean loginflag) {
        this.loginflag = loginflag;
    }
}
