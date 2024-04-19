package sg.edu.nus.iss.workshop25.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.workshop25.model.MessageObject;

@Service
public class MessageSender {

    @Autowired
    @Qualifier("myredis")
    private RedisTemplate<String, String> template;
    
    public void pushMessage(MessageObject message) {
        final ListOperations<String, String> listOps = template.opsForList();
        listOps.leftPush("myqueue", message.toJson().toString());
    }
}
