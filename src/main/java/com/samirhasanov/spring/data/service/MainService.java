/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.service;

import com.samirhasanov.spring.data.dao.IMessageDao;
import com.samirhasanov.spring.data.dao.IUserDao;
import com.samirhasanov.spring.data.entity.Message;
import com.samirhasanov.spring.data.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Hasanov (Asus)
 */
@Service
@Transactional
public class MainService implements IMainService {
    private final IUserDao userDao;
    private final IMessageDao messageDao;
    
    @Autowired
    public MainService(IUserDao userDao, IMessageDao messageDao) {
        this.userDao = userDao;
        this.messageDao = messageDao;
    }
    
    @Override
    public User saveUser(User item) {
        Long id = this.userDao.save(item);
        item.setId(id);
        
        return item;
    }

    @Override
    @Transactional(readOnly = true)
    public User findUserById(Long id) {
        return this.userDao.findById(id);
    }

    @Override
    public void updateUser(User item) {
        this.userDao.update(item);
    }

    @Override
    public void deleteUser(User item) {
        this.userDao.delete(item);
    }

    @Override
    public Message saveMessage(Message item) {
        Long id = this.messageDao.save(item);
        item.setId(id);
        
        return item;
    }

    @Override
    @Transactional(readOnly = true)
    public Message findMessageById(Long id) {
        return this.messageDao.findById(id);
    }

    @Override
    public void updateMessage(Message item) {
        this.messageDao.update(item);
    }

    @Override
    public void deleteMessage(Message item) {
        this.messageDao.delete(item);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Message> findMessagesByUser(User user) {
        return this.messageDao.findMessagesByUser(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsersByFullname(String fullname) {
        return this.userDao.getUsersByFullname(fullname);
    }
    
}
