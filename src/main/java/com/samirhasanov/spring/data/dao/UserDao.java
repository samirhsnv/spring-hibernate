/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

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
public class UserDao implements IUserDao {
    private final SessionFactory sessionFactory;
    
    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    private Session session() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public Long save(User item) {
        return (Long) session().save(item);
    }

    @Override
    public User findById(Long id) {
        return session().find(User.class, id);
    }

    @Override
    public void update(User item) {
        session().update(item);
    }

    @Override
    public void delete(User item) {
        session().delete(item);
    }

    @Override
    public List<User> getUsersByFullname(String fullname) {
        return session()
                .createQuery("from User t where t.fullname like '%' || :fullname || '%'")
                .setParameter("fullname", fullname)
                .list();
    }
    
}
