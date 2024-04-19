package sg.edu.nus.iss.workshop25.service;

import java.io.ByteArrayInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.workshop25.model.MessageObject;

@Component
public class MessageProcessorService implements MessageListener {

    // @Autowired
    // private MessageSender messageSender;
    
    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        byte[] data= message.getBody();
        // Process data egif it is JSON
        JsonReader reader = Json.createReader(new ByteArrayInputStream(data));
        JsonObject jsonData= reader.readObject();
        System.out.println(jsonData.toString());
        MessageObject msgObj = MessageObject.toMessageObject(jsonData);
        //modify ID and push back
        if(msgObj.id().equals("Chiew")) {
            System.out.println("We got your message. message:" + jsonData.toString());
            MessageObject newMsgObj = new MessageObject("Becky", msgObj.message());
            //call function
            //messageSender.pushMessage(newMsgObj);
        }
    }

}
