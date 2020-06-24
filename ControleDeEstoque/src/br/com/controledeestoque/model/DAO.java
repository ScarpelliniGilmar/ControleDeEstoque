package br.com.controledeestoque.model;

import java.sql.SQLException;

import br.com.controledeestoque.controller.BD;

public interface DAO {

	String update();

	String delete();

	String insert();
	
	public static void executarBD(String a) {
		BD bd = new BD();
		bd.getConnection();
		try {
			bd.st = bd.con.prepareStatement(a);// preparei a execução da instrução
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
		} catch (SQLException erro) {

		}

}
}
