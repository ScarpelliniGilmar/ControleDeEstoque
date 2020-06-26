package br.com.controledeestoque.test;

import br.com.controledeestoque.model.UsuarioDAO;

public class UsuarioInserir {

	public static void main(String[] args) {

		UsuarioDAO dao = new UsuarioDAO();
		dao.setEmail("gilmar@gilmar");
		dao.setSenha("123");
		System.out.println(dao.insert());
	}

}
