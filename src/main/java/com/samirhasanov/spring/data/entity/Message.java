/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Hasanov (Asus)
 */
@Entity
@Table(name = "messages")
public class Message extends AbstractEntity {
    
    @Column(length = 100)
    private String subject;
    
    @Column(length = 2000)
    private String message;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;
    
    public Message() {
        
    }
    
    public Message(String subject, String message, User owner) {
        this.subject = subject;
        this.message = message;
        this.owner = owner;
    }
    
    public Message(String subject, String message, User owner, Date created) {
        super(created);
        this.subject = subject;
        this.message = message;
        this.owner = owner;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Message{" + "subject=" + subject + ", message=" + message + ", owner=" + owner + '}';
    }
}
