package com.arun.pg.dao;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.arun.pg.model.User;

@Repository(value="userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory session;
	
	@Override
	public User findById(String username) {
		
		User user = session.getCurrentSession().get(User.class, username);
//		System.out.println("DAO username "+username);
		return user;
	}

	@Override
	public void saveUser(User user) {
		session.getCurrentSession().persist(user);
		
	}

	@Override
	public void updateUser(User user) {
		session.getCurrentSession().update(user);
		
	}

	@Override
	public void deleteUserById(String email) {
		System.out.println("going to delete");
		session.getCurrentSession().delete(findById(email));
		
	}

	@Override
	public List<User> findAllUsers() {
		return session.getCurrentSession().createQuery("from User").list();
	}

	@Override
	public void deleteAllUsers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUserExist(User user) {
		// TODO Auto-generated method stub
		return findById(user.getUserName())!=null;
	}
	
}
