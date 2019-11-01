package com.bootstrap.test.service;

import com.bootstrap.test.dto.Person;

public interface IPersonService {
   public Person selectPerson(String pid) throws Exception;
   public void insertPerson(Person person) throws Exception;
   
   public void updateInfo(Person person) throws Exception;
   public void updatePw(Person person) throws Exception;
   
   public void deletePerson(String pid) throws Exception;
}