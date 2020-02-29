package br.com.sefaz.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import br.com.sefaz.dao.DaoGeneric;
import br.com.sefaz.model.User;
import br.com.sefaz.repository.IDaoUser;
import br.com.sefaz.repository.IDaoUserImpl;

@ViewScoped
@ManagedBean(name = "userBean")
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user = new User();
	private DaoGeneric<User> daoGenericUser = new DaoGeneric<User>();
	private List<User> users = new ArrayList<User>();
//	private DaoUser daoUser = new DaoUser();
	
	private IDaoUser iDaoUser = new IDaoUserImpl();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public DaoGeneric<User> getDaoGeneric() {
		return daoGenericUser;
	}

	public void setDaoGeneric(DaoGeneric<User> daoGeneric) {
		this.daoGenericUser = daoGeneric;
	}

	public List<User> getUsers() {
		return users;
	}

//	public String save() {
//		daoGeneric.save(user);
//		
//		return "";
//	}

	public String saveUser() {
		daoGenericUser.update(user);
		user = new User();
		loadUsers();
		daoGenericUser.showMsg("Cadastrado com sucesso!");
		return "";
	}

	public String newUser() {
		user = new User();
		return "";
	}

	public String deleteUser() {
		if (user.getPhones().isEmpty()) {
			daoGenericUser.delete(user);
			user = new User();
			loadUsers();
			daoGenericUser.showMsg("Removido com sucesso!");
			} else {
				daoGenericUser.showMsg("Existem telefones associados ao usuário!");
			}
		user = new User();
		return "";
	}

	@PostConstruct
	public void loadUsers() {
		users = daoGenericUser.getListEntity(User.class);
	}
	
	public String logInto() {

		User userLogged = iDaoUser.consultUser(user.getEmail(), user.getPassword());

		if (userLogged != null) {

			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("loggedUser", userLogged);

			return "firstpage.jsf";
		}

		return "index.jsf";
	}
	
	public String logOut() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("loggedUser");
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) context.getCurrentInstance().getExternalContext().getRequest();
		
		httpServletRequest.getSession().invalidate();

		return "index.jsf";
	}

}
