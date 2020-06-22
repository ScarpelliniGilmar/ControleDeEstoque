package br.com.controledeestoque.controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class BD {

	// interfaces
	public Connection con = null;
	public PreparedStatement st = null;
	public ResultSet rs = null; // utilizada para fazer uma consulta

	private final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver"; // caminho
	private final String BANCO = "produtos";// NOME DO BANCO DE DADOS
	private final String URL = "jdbc:sqlserver://localhost:1433;databasename=" + BANCO;
	// private final String URL =
	// "jdbc:sqlserver://localhost:1433;databasename="+BANCO+";integratedSecurity=true";

	// o correto é permanecer em um arquivo a parte
	private final String LOGIN = "sa";
	private final String SENHA = "123456";

	/**
	 * Realiza a conexão ao banco de dados
	 * 
	 * @return - true em caso de sucesso, ou false caso contrário
	 */
	public boolean getConnection() {

		try {
			Class.forName(DRIVER); // durante a execução carrega o driver
			con = DriverManager.getConnection(URL, LOGIN, SENHA);
			// con = DriverManager.getConnection(URL);

			System.out.println("Conectou\n");

		} catch (SQLException erro) {
			System.out.println("Falha na conexão ao banco!" + erro.toString());
		} catch (ClassNotFoundException erro) {
			System.out.println("Driver não encontrado!");
		}
		return true;
	}

	/**
	 * Encerra a conexão ( con, st, rs)
	 */
	public void close() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null) {
				con.close();
				System.out.println("\nDesconectou!");
			}

		} catch (SQLException erro) {
			System.out.println("");
		}
	}

}
