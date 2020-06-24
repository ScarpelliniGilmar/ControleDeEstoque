package br.com.controledeestoque.model;

import java.sql.SQLException;

import br.com.controledeestoque.controller.BD;
import br.com.controledeestoque.controller.Criptografia;

public class EntradaDAO extends Entrada implements DAO { // acessa o banco

	public BD bd;
	public ProdutoDAO p;
	private String sql;

	public EntradaDAO() {
		bd = new BD();
		p = new ProdutoDAO();
	}

	/**
	 * Usu�rio fornece Nome, valor e Quantidade
	 */
	@Override
	public String insert() {
		sql = "insert into entradas values (?,?,CURRENT_TIMESTAMP)";
		bd.getConnection(); // conectando ao bancos
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, getCodigoProduto());
			System.out.println("C�digo Produto: "+getCodigoProduto());			
			bd.st.setInt(2, getQuantidadeEntrada());			
			bd.st.executeUpdate();
			return "Inserido com sucesso";
		} catch (SQLException erro) {

			return "Erro";
		} finally {
			
		}
	}

	/**
	 * Usu�rio fornece nome do produto;
	 */
	@Override
	public String update() {
		sql = "update entradas set quantidade_entrada = ? where codigo_produto = ?";

		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, getQuantidadeEntrada());
			bd.st.setInt(2, getCodigoProduto());
			bd.st.executeUpdate();

			return "Alterado com sucesso";

		} catch (SQLException erro) {

			return "falha: " + erro;
		} finally {
			bd.close();
		}
	}

	@Override
	public String delete() {
		sql = "delete from entradas where codigo_entrada = ?";
		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
//			bd.st.setString(1, Criptografia.criptografar(getEmail()));
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

}
