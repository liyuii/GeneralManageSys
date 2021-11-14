package com.web.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.web.CommunicateModule.entity.tx_msg;

//封装发送的消息内容
public class MessageUtils {
    public static String getMessage(String isSystemMessage,String fromName,Object message){
        try{
            tx_msg resultMessage = new tx_msg();
            resultMessage.setMsgType(isSystemMessage);
            resultMessage.setMsgContent(message);
            if(fromName != null){
                resultMessage.setFromName(fromName);
            }
//            if(toName !=null ){
//                resultMessage.setToName(toName);
//            }
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(resultMessage);
        }catch (JsonProcessingException e){
            e.printStackTrace();
        }
        return null;
    }
}

