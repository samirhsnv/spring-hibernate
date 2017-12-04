/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Hasanov (Asus)
 */

@Entity
@Table(name = "users")
public class User extends AbstractEntity {
    
    @NotEmpty
    @Column(nullable = false)
    private String fullname;
    
    @NotNull
    @Min(18)
    @Column
    private Integer age;
    
    @OneToMany(mappedBy = "owner")
    private Set<Message> messages = new HashSet<>();
    
    public User() {
        
    }
    
    public User(String fullname, Integer age) {
        this.fullname = fullname;
        this.age = age;
    }
    
    public User(String fullname, Integer age, Date created) {
        super(created);
        this.fullname = fullname;
        this.age = age;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "User{" + "fullname=" + fullname + ", age=" + age + '}';
    }
}
