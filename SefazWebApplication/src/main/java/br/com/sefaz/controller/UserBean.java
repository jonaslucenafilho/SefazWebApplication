package br.com.sefaz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.sefaz.dao.DaoGeneric;
import br.com.sefaz.model.User;

@ViewScoped
@ManagedBean(name = "userBean")
public class UserBean {

	private User user = new User();
	private DaoGeneric<User> daoGeneric = new DaoGeneric<User>();
	private List<User> users = new ArrayList<User>();
	
//	public String save() {
//		daoGeneric.save(user);
//		
//		return "";
//	}
	
	public String saveUser() {
		user = daoGeneric.update(user);
		user = new User();
		loadUsers();
		return "";
	}
	
	public String newUser() {
		user = new User();
		return "";
	}
	
	public String deleteUser() {
		daoGeneric.delete(user);
		user = new User();
		loadUsers();
		return "";
	}
	
	@PostConstruct
	public void loadUsers() {
		users = daoGeneric.getListEntity(User.class);
	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DaoGeneric<User> getDaoGeneric() {
		return daoGeneric;
	}

	public void setDaoGeneric(DaoGeneric<User> daoGeneric) {
		this.daoGeneric = daoGeneric;
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	
}

