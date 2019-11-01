package com.bootstrap.test.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.bootstrap.test.dao.PersonDAOImpl;
import com.bootstrap.test.dto.Person;

@Service
public class PersonServiceImpl implements IPersonService   {

   @Inject
   private PersonDAOImpl dao;
   
   @Override
   public Person selectPerson(String pid) throws Exception {
      return dao.selectPerson(pid);
   }

   @Override
   public void insertPerson(Person person) throws Exception {
      dao.insertPerson(person);
   }

   @Override
   public void updateInfo(Person person) throws Exception {
      dao.updateInfo(person);
   }

   @Override
   public void updatePw(Person person) throws Exception {
      dao.updatePw(person);
   }

   @Override
   public void deletePerson(String pid) throws Exception {
      dao.deletePerson(pid);
   }

   
   
   
}