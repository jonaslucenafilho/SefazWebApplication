package br.com.sefaz.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.sefaz.dao.DaoGeneric;
import br.com.sefaz.model.Phone;
import br.com.sefaz.model.User;

@ViewScoped
@ManagedBean(name = "phoneBean")
public class PhoneBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private User user = new User();
	private DaoGeneric<User> daoUser = new DaoGeneric<User>();
	private DaoGeneric<Phone> daoGenericPhone = new DaoGeneric<Phone>();
	private Phone phone = new Phone();
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setPhone(Phone phone) {
		this.phone = phone;
	}
	
	public Phone getPhone() {
		return phone;
	}
	
	@PostConstruct
	public void init() {
		
		String usercode = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("usercode");
		user = daoUser.search(Long.parseLong(usercode), User.class);
	}
	
	public String savePhone() {
		phone.setUser(user);
		daoGenericPhone.update(phone);
		phone = new Phone();
		user = daoUser.search(user.getId(), User.class);
		daoGenericPhone.showMsg("Cadastrado com sucesso!");
		return "";
	}
	
	public String deletePhone() {
		phone.setUser(user);
		daoGenericPhone.delete(phone);
		phone = new Phone();
		user = daoUser.search(user.getId(), User.class);
		daoGenericPhone.showMsg("Removido com sucesso!");
		return "";
	}
	
}
