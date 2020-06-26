package br.com.controledeestoque.model;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.controledeestoque.controller.BD;

public class ProdutoDAO extends Produto implements DAO { // acessa o banco

	public BD bd;
	private String sql;

	public ProdutoDAO() {
		bd = new BD();
	}

	/**
	 * Insere os Nome,valor e quantidade de produtos no banco de dados
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
			bd.st.executeUpdate();
			return "Produto inserido com sucesso!";
		} catch (SQLException erro) {

			return "Não inserido!";
		}
	}

	/**
	 * Atualiza a quantidade de produto a partir do código do produto
	 */
	@Override
	public String update() {
		sql = "update produtos set quantidade_produto = quantidade_produto + ? where codigo_produto = ?";

		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.st.setInt(1, getQuantidade());
			bd.st.setInt(2, getCodigoProduto());
			bd.st.executeUpdate();

			return "Quantidade Alterada com sucesso!";

		} catch (SQLException erro) {

			return "falha: " + erro;
		} finally {
			bd.close();
		}
	}

	@Override
	public String delete() {
		sql = "delete from produtos where nome_produto = ?";
		bd.getConnection(); // conectando ao banco
		try {
			bd.st = bd.con.prepareStatement(sql);
			;
			bd.st.setString(1, getNome());
			int n = bd.st.executeUpdate();

			if (n == 1)
				return "Usuário excluido com sucesso";
			else
				return "Usuário não encontrado";

		} catch (SQLException erro) {

			return "falha: " + erro;
		}
	}

	/**
	 * Método que recebe o nome do produto e retorna o valor dele
	 * 
	 * @return - retorno o valor do produto
	 */
	public double listarValor(String descricao) {
		BD bd = new BD();
		bd.getConnection();
		// executo a ação
		String sql1 = "select valor_produto from produtos where nome_produto ='" + descricao + "'";

		try {
			bd.st = bd.con.prepareStatement(sql1);// preparei a execução da instrução
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
			setValor(Double.parseDouble(bd.rs.getString("valor_produto")));

		} catch (SQLException erro) {
			System.out.println("erro:" + erro.toString());
		}

		return getValor();

	}

	/**
	 * Método que recebe o nome do produto e retorna o valor dele
	 * 
	 * @return - retorno o valor do produto
	 */
	public double listarCodigo() {
		BD bd = new BD();
		bd.getConnection();
		// executo a ação
		String sql1 = "select codigo_produto from produtos where nome_produto ='" + getNome() + "'";

		try {
			bd.st = bd.con.prepareStatement(sql1);// preparei a execução da instrução
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
			setCodigoProduto(Integer.parseInt(bd.rs.getString("codigo_produto")));

		} catch (SQLException erro) {
			System.out.println("erro:" + erro.toString());
		}

		return getCodigoProduto();

	}

	/**
	 * Método que grava todos os produtos em um Array
	 * 
	 * @return - vetor com os dados
	 */
	public static Object[] listarProdutos() {
		BD bd = new BD();
		bd.getConnection();
		// executo a ação
		String sql1 = "select nome_produto from produtos";

		ArrayList<String> lista = new ArrayList<String>();
		Object[] vetor = null;

		try {

			lista.clear();
			bd.st = bd.con.prepareStatement(sql1);// preparei a execução da instrução
			bd.rs = bd.st.executeQuery();
			while (bd.rs.next()) {
				String a = bd.rs.getString("nome_produto");
				lista.add(a);
			}
		} catch (SQLException erro) {
			System.out.println("erro:" + erro.toString());
		}
		vetor = lista.toArray(new String[lista.size()]);

		return vetor;

	}

}
