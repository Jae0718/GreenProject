package com.bootstrap.test.dao;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.bootstrap.test.dto.Person;

@Repository
public class PersonDAOImpl implements IPersonDAO{
   @Inject
   private SqlSession sqlSession;

   private static final String Namespace = "com.bootstrap.test.mapper.personMapper";

   @Override
   public Person selectPerson(String pid) throws Exception {
      return (Person)sqlSession.selectOne(Namespace + ".selectPerson", pid);
   }

   @Override
   public void insertPerson(Person person) throws Exception {
      sqlSession.insert(Namespace + ".insertPerson", person);
   }

   @Override
   public void updateInfo(Person person) throws Exception {
      sqlSession.update(Namespace+".updateInfo", person);
   }

   @Override
   public void updatePw(Person person) throws Exception {
      sqlSession.update(Namespace+".updatePw", person);
      
   }

   @Override
   public void deletePerson(String pid) throws Exception {
      sqlSession.delete(Namespace + ".deletePerson", pid);
   }



}