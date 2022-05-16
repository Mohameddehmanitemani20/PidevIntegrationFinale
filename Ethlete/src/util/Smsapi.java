/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author MSI
 */
public class Smsapi {
    public static final String ACCOUNT_SID = "AC215414247f26d9a12eaae32bfa5b07fa";
    public static final String AUTH_TOKEN = "e08afeca8078877fd224934efb9b83cb";

    public static void sendSMS(String num, String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(/*num ili bch yjih il msg */new PhoneNumber("+21624030100"),new PhoneNumber("+19803755136"), msg).create();

        System.out.println(message.getSid());

    }
}
