package br.com.controledeestoque.model;

import java.sql.SQLException;
import java.util.ArrayList;

import br.com.controledeestoque.controller.BD;

public class VendaDAO extends Produto implements DAO { // acessa o banco

	public BD bd;
	public static Venda v;
	private String sql;

	public VendaDAO() {
		bd = new BD();
		v = new Venda();

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
		sql = "update venda set senha = ? where email = ?";

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

	/**
	 * Este método verifica se há produtos em estoque, se houver os dados da venda
	 * são gravados no BD e também faz a redução do estoque
	 * 
	 * @param codigo
	 * @param descricao
	 * @param quantidade2
	 * @param valor
	 * @return - retorna true se houver estoque, false se não houver
	 */
	public static boolean Vendas(int codigo, String descricao, int quantidade2, double valor) {
		BD bd = new BD();
		bd.getConnection();

		/**
		 * insert - Instruções para inserir a entrada na tabela de Entradas update -
		 * Instruções para atualizar a qauntidade em estoque na tabela Produtos
		 */
		String sql = "select codigo_produto,quantidade_produto,valor_produto from produtos where nome_produto ='"
				+ descricao + "'";

		int codigo_produto = 0, estoque = 0;
		double valor_unitario = 0;

		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
			codigo_produto = bd.rs.getInt("codigo_produto");
			estoque = bd.rs.getInt("quantidade_produto");
			valor_unitario = bd.rs.getDouble("valor_produto");
		} catch (SQLException erro) {
			System.out.println("erro: " + erro.toString());
		}

		if (estoque >= quantidade2) {
			String inserir = "insert into vendas values(" + codigo + ",'" + descricao + "'," + quantidade2 + ","
					+ valor_unitario + ",CURRENT_TIMESTAMP)";
			String atualizar = "update produtos set quantidade_produto = quantidade_produto - " + quantidade2
					+ " where codigo_produto =" + codigo_produto + "";

			DAO.executarBD(inserir);
			DAO.executarBD(atualizar);

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Essa Métodos recebe um codigo que busca no banco de dados os itens vendidos
	 * ligados a ele e retorna o valor total
	 * 
	 * @param codigo
	 * @return - retorna o valor total de um codigo de venda
	 */
	public static double Total(int codigo) {
		BD bd = new BD();
		bd.getConnection();

		/**
		 * insert - Instruções para inserir a entrada na tabela de Entradas update -
		 * Instruções para atualizar a qauntidade em estoque na tabela Produtos
		 */
		String sql = "select valor_produto,quantidade from vendas where codigo_venda ='" + codigo + "'";
		int quantidade;
		double valor_unitario, total = 0;
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();

			while (bd.rs.next()) {
				quantidade = bd.rs.getInt("quantidade");
				valor_unitario = bd.rs.getDouble("valor_produto");
				total = total + valor_unitario * quantidade;

			}

		} catch (SQLException erro) {
			System.out.println("erro: " + erro.toString());
		}
		return total;
	}

	/**
	 * Método que retorna o ultimo codigo da tabela de venda
	 * 
	 * @return - retorno o codigo da venda
	 */
	public static int listarUltimoCodigo() {
		BD bd = new BD();
		bd.getConnection();
		// executo a ação
		String sql1 = "select codigo_venda from vendas where codigo_venda = (select max(codigo_venda) from vendas)";

		try {

			bd.st = bd.con.prepareStatement(sql1);// preparei a execução da instrução
			bd.rs = bd.st.executeQuery();
			bd.rs.next();

			v.setCodigoVenda(Integer.parseInt(bd.rs.getString("codigo_venda")));

		} catch (SQLException erro) {
			System.out.println("erro:" + erro.toString());
		}

		return v.getCodigoVenda();

	}

	public static void main(String[] args) {
		Venda v = new Venda();
		System.out.println(v.getCodigoVenda());

		VendaDAO v1 = new VendaDAO();
		v1.listarUltimoCodigo();

		System.out.println(v.getCodigoVenda());

	}

}
