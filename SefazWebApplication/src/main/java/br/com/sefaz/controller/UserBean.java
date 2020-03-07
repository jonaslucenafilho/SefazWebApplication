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
import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;


import br.com.sefaz.dao.UserDAO;
import br.com.sefaz.model.User;

@ViewScoped
@ManagedBean(name = "userBean")
public class UserBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private User user = new User();
	private UserDAO<User> userDAO = new UserDAO<User>();
	private List<User> users = new ArrayList<User>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUsers() {
		return users;
	}

	public UserDAO<User> getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO<User> userDAO) {
		this.userDAO = userDAO;
	}

	@PostConstruct
	public void loadUsers() {
		users = userDAO.getListEntity(User.class);
	}

	public String cleanUser() {
		user = new User();
		return "";
	}

	public String saveUser() {
		try {
			userDAO.update(user);
			user = new User();
			loadUsers();
			userDAO.showMsg("Cadastrado com sucesso!");
			return "";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário já existe no sistema", null));
			user = new User();
		}
		return "";
	}

	public String deleteUser() {
		if (user.getPhones().isEmpty()) {
			userDAO.delete(user);
			user = new User();
			loadUsers();
			userDAO.showMsg("Removido com sucesso!");
		} else {
			userDAO.showMsg("Existem telefones associados ao usuário!");
		}
		user = new User();
		return "";
	}

	public String logInto() {
		try {
			User userLogged = userDAO.consultUser(user.getEmail(), user.getPassword());
			if (userLogged != null) {

				FacesContext context = FacesContext.getCurrentInstance();
				ExternalContext externalContext = context.getExternalContext();
				externalContext.getSessionMap().put("loggedUser", userLogged);

				return "userregister.jsf";
			}

		} catch (NoResultException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuário ou Senha Inválidos", null));
		}

		return "";
	}

	public String logOut() {

		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("loggedUser");

		HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();

		httpServletRequest.getSession().invalidate();

		return "index.jsf";
	}
	
}
