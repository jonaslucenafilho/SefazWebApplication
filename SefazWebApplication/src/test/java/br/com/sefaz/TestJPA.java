package br.com.sefaz;

import javax.persistence.Persistence;

public class TestJPA {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("SefazWebApplication");

	}
}
