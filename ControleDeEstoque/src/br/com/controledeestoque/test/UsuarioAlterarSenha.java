package br.com.controledeestoque.test;

import br.com.controledeestoque.model.UsuarioDAO;

public class UsuarioAlterarSenha {

	public static void main(String[] args) {
		br.com.controledeestoque.model.UsuarioDAO dao = new UsuarioDAO();
		dao.setEmail("ana@ana");
//		dao.setSenha("123456");
		System.out.println(dao.update());
	}
	}


