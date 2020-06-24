package br.com.controledeestoque.model;

import java.sql.SQLException;

import br.com.controledeestoque.controller.BD;
import br.com.controledeestoque.controller.Criptografia;

public class UsuarioDAO extends Usuario implements DAO { // acessa o banco

	public BD bd;
	private String sql;

	public UsuarioDAO() {
		bd = new BD();
	}

	/**
	 * Usu�rio fornece email e senha
	 */
	@Override
	public String insert() { // considerando que j� temos os dados na classe Usuario
		sql = "insert into usuarios values (?,?)";
		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setString(1, Criptografia.criptografar(getEmail()));
			bd.st.setString(2, Criptografia.criptografar(getSenha()));
			int n = bd.st.executeUpdate();
			return "Usu�rio inserido com sucesso!";
		} catch (SQLException erro) {

			return "J� existe um usuario com esse email!";
		} finally {
			bd.close();
		}
	}

	/**
	 * Usu�rio fornece email;
	 */
	@Override
	public String update() {
		sql = "update usuarios set senha = ? where email = ?";

		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setString(2, Criptografia.criptografar(getEmail()));
			bd.st.setString(1, Criptografia.criptografar(getSenha()));
			int n = bd.st.executeUpdate();

			if (n == 1)
				return "Senha alterada com sucesso!";
			else
				return "Email n�o encontrado";

		} catch (SQLException erro) {

			return "falha: " + erro;
		} finally {
			bd.close();
		}
	}

	@Override
	public String delete() {
		sql = "delete from usuarios where email = ?";
		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setString(1, Criptografia.criptografar(getEmail()));
			int n = bd.st.executeUpdate();

			if (n == 1)
				return "Usu�rio exluido com sucesso";
			else
				return "Usu�rio n�o encontrado";

		} catch (SQLException erro) {

			return "falha: " + erro;
		} finally {
			bd.close();
		}
	}

	public boolean logar() {
		sql = "select * from usuarios where email = ? and senha = ?";
		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setString(1, Criptografia.criptografar(getEmail()));
			bd.st.setString(2, Criptografia.criptografar(getSenha()));
			bd.rs = bd.st.executeQuery();

			return bd.rs.next(); // verifica se tem um proximo registro;
		} catch (SQLException erro) {

			return false;
		} finally {
			bd.close();
		}
	}

	

}
