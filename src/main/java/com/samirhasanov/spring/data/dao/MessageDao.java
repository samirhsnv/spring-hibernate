/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

import com.samirhasanov.spring.data.entity.Message;
import com.samirhasanov.spring.data.entity.User;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Hasanov (Asus)
 */
@Repository
public class MessageDao implements IMessageDao {
    private final SessionFactory sessionFactory;
    
    @Autowired
    public MessageDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session session() {
        return this.sessionFactory.getCurrentSession();
    }
    
    @Override
    public Long save(Message item) {
        return (Long) session().save(item);
    }

    @Override
    public Message findById(Long id) {
        return session().find(Message.class, id);
    }

    @Override
    public void update(Message item) {
        session().update(item);
    }

    @Override
    public void delete(Message item) {
        session().delete(item);
    }

    @Override
    public List<Message> findMessagesByUser(User user) {
        return session()
                .createQuery("from Message t where t.owner = :owner")
                .setParameter("owner", user)
                .list();
    }
    
}
