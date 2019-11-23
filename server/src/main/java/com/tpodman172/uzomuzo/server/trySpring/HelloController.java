package com.tpodman172.uzomuzo.server.trySpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("/hello")
    public String getHello(){
        System.out.println("pass");
        return "hello";
    }

    @PostMapping("/hello")
    public String  postRequest(@RequestParam("text1")String str, Model model){
        model.addAttribute("sample", str);

        return "helloResponse";
    }

    @PostMapping("/hello/db")
    public String postDbRequest(@RequestParam("text2")String str, Model model){
        int id = Integer.parseInt(str);

        Task task = helloService.findOne(id);

        model.addAttribute("id", task.getId());
        model.addAttribute("title", task.getTitle());
        model.addAttribute("body", task.getBody());

        return "helloResponseDB";
    }
}
