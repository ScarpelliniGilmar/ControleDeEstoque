package br.com.controledeestoque.testes;

import javax.swing.JOptionPane;

import br.com.controledeestoque.model.UsuarioDAO;
import br.com.controledeestoque.view.Menu;


public class UsuarioLogar {

	public static void main(String[] args) {

		UsuarioDAO dao = new UsuarioDAO();		
		dao.setEmail("gil@gilmar");
//		dao.setSenha("123");
		
		
		if (dao.logar()) {
			Menu m = new Menu();
			m.setVisible(true);

		} else {
			JOptionPane.showMessageDialog(null, "Login Invalido!");
		}

	}
}