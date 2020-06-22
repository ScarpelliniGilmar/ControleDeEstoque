package br.com.controledeestoque.controle;

import java.sql.SQLException;
import java.util.ArrayList;

public class ClasseDeComunicacao {

	private static int quantidade;
	private static int estoque;
	private static int codigo_produto;
	private static int codigo;
	private static double valor;
	private static double valor_unitario;
	private static double total;
	private static ArrayList teste = new ArrayList();
	private static Object[] vetor = null;

	public ClasseDeComunicacao() {
	}

	/**
	 * Insere um novo produto no banco de Dados
	 * 
	 * @param descricao  - recebe o nome do produto
	 * @param valor      - recebe o valor deste produto
	 * @param quantidade - recebe a quantidade de produtos
	 */
	public void Produtos(String descricao, String valor, String quantidade) {
		BD bd = new BD();
		bd.getConnection();

		String insert = "insert produtos values('" + descricao + "'," + valor + "," + quantidade + ")";
		ClasseDeComunicacao e = new ClasseDeComunicacao();
		e.executarBD(insert);
	}

	/**
	 * Insere novos dados na Tabela Entradas e Atualiza a tabela Produtos
	 * 
	 * @param descricao  - recebe o nome do produto
	 * @param quantidade - recebe a quantidade
	 */
	public void Entradas(String descricao, String quantidade) {
		BD bd = new BD();
		bd.getConnection();

		String sql = "select codigo_produto from produtos where descricao_produto = '" + descricao + "'";

		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
			codigo = bd.rs.getInt("codigo_produto");

		} catch (SQLException erro) {
			System.out.println("erro: " + erro.toString());
		}

		String inserir = "insert into entradas values(" + codigo + "," + quantidade + ",CURRENT_TIMESTAMP)";
		String atualizar = "update produtos set quantidade = quantidade + " + quantidade + " where codigo_produto ="
				+ codigo + "";
		ClasseDeComunicacao e = new ClasseDeComunicacao();
		e.executarBD(inserir);

		ClasseDeComunicacao f = new ClasseDeComunicacao();
		f.executarBD(atualizar);
	}

	/**
	 * Este m�todo verifica se h� produtos em estoque, se houver os dados da venda
	 * s�o gravados no BD e tamb�m faz a redu��o do estoque
	 * 
	 * @param codigo
	 * @param descricao
	 * @param quantidade2
	 * @param valor
	 * @return - retorna true se houver estoque, false se n�o houver
	 */
	public static boolean Vendas(int codigo, String descricao, int quantidade2, double valor) {
		BD bd = new BD();
		bd.getConnection();

		/**
		 * insert - Instru��es para inserir a entrada na tabela de Entradas update -
		 * Instru��es para atualizar a qauntidade em estoque na tabela Produtos
		 */
		String sql = "select codigo_produto,quantidade,valor_produto from produtos where descricao_produto ='"
				+ descricao + "'";

		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
			codigo_produto = bd.rs.getInt("codigo_produto");
			estoque = bd.rs.getInt("quantidade");
			valor_unitario = bd.rs.getDouble("valor_produto");
		} catch (SQLException erro) {
			System.out.println("erro: " + erro.toString());
		}

		if (estoque >= quantidade2) {
			String inserir = "insert into vendas values(" + codigo + ",'" + descricao + "'," + quantidade2 + ","
					+ valor_unitario + ",CURRENT_TIMESTAMP)";
			String atualizar = "update produtos set quantidade = quantidade - " + quantidade2
					+ " where codigo_produto =" + codigo_produto + "";

			ClasseDeComunicacao e = new ClasseDeComunicacao();
			e.executarBD(inserir);

			ClasseDeComunicacao f = new ClasseDeComunicacao();
			f.executarBD(atualizar);

			return true;
		} else {
			return false;
		}
	}

	/**
	 * Essa M�todos recebe um codigo que busca no banco de dados os itens vendidos
	 * ligados a ele e retorna o valor total
	 * 
	 * @param codigo
	 * @return - retorna o valor total de um codigo de venda
	 */
	public static double Total(int codigo) {
		BD bd = new BD();
		bd.getConnection();

		/**
		 * insert - Instru��es para inserir a entrada na tabela de Entradas update -
		 * Instru��es para atualizar a qauntidade em estoque na tabela Produtos
		 */
		String sql = "select valor,quantidade from vendas where codigo_venda ='" + codigo + "'";
		total = 0;
		try {
			bd.st = bd.con.prepareStatement(sql);
			bd.rs = bd.st.executeQuery();

			while (bd.rs.next()) {
				quantidade = bd.rs.getInt("quantidade");
				valor_unitario = bd.rs.getDouble("valor");
				total = total + valor_unitario * quantidade;

			}

		} catch (SQLException erro) {
			System.out.println("erro: " + erro.toString());
		}
		return total;
	}

	/**
	 * Este m�todo faz a conex�o com o BD e executa o parametro fornecido
	 * 
	 * @param a - parametro para executar no BD
	 */
	public void executarBD(String a) {
		BD bd = new BD();
		bd.getConnection();
		try {
			bd.st = bd.con.prepareStatement(a);// preparei a execu��o da instru��o
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
		} catch (SQLException erro) {

		}
	}

	/**
	 * M�todo que grava todos os produtos em um Array
	 * 
	 * @return - vetor com os dados
	 */
	public static Object[] listar() {
		BD bd = new BD();
		bd.getConnection();
		// executo a a��o
		String sql1 = "select descricao_produto from produtos";
		try {
			teste.clear();
			bd.st = bd.con.prepareStatement(sql1);// preparei a execu��o da instru��o
			bd.rs = bd.st.executeQuery();
			while (bd.rs.next()) {
				String a = bd.rs.getString("descricao_produto");
				teste.add(a);
			}
		} catch (SQLException erro) {
			System.out.println("erro:" + erro.toString());
		}
		vetor = teste.toArray(new String[teste.size()]);

		return vetor;

	}

	/**
	 * M�todo que retorna o ultimo codigo da tabela de venda
	 * 
	 * @return - retorno o codigo da venda
	 */
	public static int listarUltimoCodigo() {
		BD bd = new BD();
		bd.getConnection();
		// executo a a��o
		String sql1 = "select codigo_venda from vendas where codigo_venda = (select max(codigo_venda) from vendas)";
		try {
			teste.clear();
			bd.st = bd.con.prepareStatement(sql1);// preparei a execu��o da instru��o
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
			codigo = Integer.parseInt(bd.rs.getString("codigo_venda"));

		} catch (SQLException erro) {
			System.out.println("erro:" + erro.toString());
		}

		return codigo;

	}

	/**
	 * M�todo que recebe o nome do produto e retorna o valor dele
	 * 
	 * @return - retorno o valor do produto
	 */
	public static double listarValor(String descricao) {
		BD bd = new BD();
		bd.getConnection();
		// executo a a��o
		String sql1 = "select valor_produto from produtos where descricao_produto ='" + descricao + "'";
		try {
			teste.clear();
			bd.st = bd.con.prepareStatement(sql1);// preparei a execu��o da instru��o
			bd.rs = bd.st.executeQuery();
			bd.rs.next();
			valor = Double.parseDouble(bd.rs.getString("valor_produto"));

		} catch (SQLException erro) {
			System.out.println("erro:" + erro.toString());
		}

		return valor;

	}

}
