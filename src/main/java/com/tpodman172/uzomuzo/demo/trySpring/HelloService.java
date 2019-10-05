package com.tpodman172.uzomuzo.demo.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

@Service
public class HelloService {
    @Autowired
    private HelloRepository helloRepository;

    public Task findOne(int id){
        Map<String, Object> map = helloRepository.findOne(id);
        Task task = new Task();

        task.setId((Integer) map.get("id"));
        task.setBody((String) map.get("body"));
        task.setTitle((String) map.get("title"));
        task.setCreateAt((Date) map.get("created_at"));
        task.setUpdateAt((Date) map.get("updated_at"));

        return task;
    }
}
