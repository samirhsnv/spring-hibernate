/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

import com.samirhasanov.spring.data.entity.Message;
import com.samirhasanov.spring.data.entity.User;
import java.util.List;

/**
 *
 * @author Hasanov (Asus)
 */
public interface IMessageDao extends IBasicDao<Message> {
    public List<Message> findMessagesByUser(User user);
}
