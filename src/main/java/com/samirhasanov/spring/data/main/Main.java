/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.main;

import com.samirhasanov.spring.data.config.AppConfig;
import com.samirhasanov.spring.data.config.DbConfig;
import com.samirhasanov.spring.data.entity.Message;
import com.samirhasanov.spring.data.entity.User;
import com.samirhasanov.spring.data.service.IMainService;
import java.util.Date;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Hasanov (Asus)
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class, DbConfig.class);
        IMainService service = applicationContext.getBean(IMainService.class);
        
        User murad = new User("Murad Rzayev", 28, new Date());
        User samir = new User("Samir Hasanov", 28, new Date());
        User samir_ = new User("Samir Test", 82, new Date());
        
        
        murad = service.saveUser(murad);
        samir = service.saveUser(samir);
        samir_ = service.saveUser(samir_);
        
        Message message = new Message("Test Samir subject", "Test Samir content Rokki balboa", samir, new Date());
        Message message_ = new Message("Test Samir subject 2", "Test Samir content 2 Rokki balboa", samir, new Date());
        
        List<User> retrievedUsers = service.getUsersByFullname("Sam");
        System.out.println(retrievedUsers);
        
        service.saveMessage(message);
        service.saveMessage(message_);
        
        service.findMessagesByUser(samir).forEach(System.out::println);
    }
}
