package br.com.controledeestoque.testes;

import br.com.controledeestoque.model.UsuarioDAO;

public class UsuarioExcluir {

	public static void main(String[] args) {

		UsuarioDAO dao = new UsuarioDAO();
		dao.setEmail("ana@ana");		
		System.out.println(dao.delete());
	}
	}


