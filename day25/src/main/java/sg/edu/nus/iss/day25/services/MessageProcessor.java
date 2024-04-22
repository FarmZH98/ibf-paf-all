package sg.edu.nus.iss.day25.services;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessor {

    @Autowired @Qualifier("myredis")
    private RedisTemplate<String, String> template;

    //For demo purpose, to achieve the processor and subscriber, we dont need threads.
    @Async
    public void start() {
        ExecutorService threadPool = Executors.newFixedThreadPool(2);
        threadPool.submit(new Worker(template, "w0"));
        threadPool.submit(new Worker(template, "w1"));
    }
    
}
