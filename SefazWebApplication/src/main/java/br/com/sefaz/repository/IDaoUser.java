package br.com.sefaz.repository;

import br.com.sefaz.model.User;

public interface IDaoUser {

	User consultUser(String email, String password);
}
