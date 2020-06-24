package br.com.controledeestoque.model;

import java.sql.SQLException;

import br.com.controledeestoque.controller.BD;
import br.com.controledeestoque.controller.Criptografia;

public class VendaDAO extends Produto implements DAO { // acessa o banco

	public BD bd;
	private String sql;

	public VendaDAO() {
		bd = new BD();
	}

	/**
	 * Usuário fornece Nome, valor e Quantidade
	 */
	@Override
	public String insert() { 
		sql = "insert into produtos values (?,?,?)";
		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setString(1, getNome());
			bd.st.setDouble(2, getValor());
			bd.st.setInt(3, getQuantidade());
			int n = bd.st.executeUpdate();
			return "Produto inserido com sucesso!";
		} catch (SQLException erro) {

			return "Já existe um produto com esse codigo";
		} finally {
			bd.close();
		}
	}		
	

	/**
	 * Usuário fornece nome do produto;
	 */
	@Override
	public String update() {
		sql = "update usuarios set senha = ? where email = ?";

		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
//			bd.st.setString(2, ));
//			bd.st.setString(1, Criptografia.criptografar(getSenha()));
			int n = bd.st.executeUpdate();

			if (n == 1)
				return "Senha alterada com sucesso!";
			else
				return "Email não encontrado";

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
//			bd.st.setString(1, Criptografia.criptografar(getEmail()));
			int n = bd.st.executeUpdate();

			if (n == 1)
				return "Usuário exluido com sucesso";
			else
				return "Usuário não encontrado";

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
//			bd.st.setString(1, Criptografia.criptografar(getEmail()));
//			bd.st.setString(2, Criptografia.criptografar(getSenha()));
			bd.rs = bd.st.executeQuery();

			return bd.rs.next(); // verifica se tem um proximo registro;
		} catch (SQLException erro) {

			return false;
		} finally {
			bd.close();
		}
	}

	

}
