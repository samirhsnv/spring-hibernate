/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.samirhasanov.spring.data.dao;

/**
 *
 * @author Hasanov (Asus)
 */
public interface IBasicDao<T> {
    public Long save(T item);
    public T findById(Long id);
    public void update(T item);
    public void delete(T item);
}
