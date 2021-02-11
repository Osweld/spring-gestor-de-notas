package com.osweld.dev.email;

public interface JavaMailService {

    public void SendMimeMessageActivation(String to,String id);
    public void SendMimeMessageResetPassword(String to,String id);
}
