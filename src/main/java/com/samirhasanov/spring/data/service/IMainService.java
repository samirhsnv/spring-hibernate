/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.service;

import com.samirhasanov.spring.data.entity.Message;
import com.samirhasanov.spring.data.entity.User;
import java.util.List;

/**
 *
 * @author Hasanov (Asus)
 */
public interface IMainService {
    public User saveUser(User item);
    public User findUserById(Long id);
    public void updateUser(User item);
    public void deleteUser(User item);
    public Message saveMessage(Message item);
    public Message findMessageById(Long id);
    public void updateMessage(Message item);
    public void deleteMessage(Message item);
    public List<Message> findMessagesByUser(User user);
    public List<User> getUsersByFullname(String fullname);
}
