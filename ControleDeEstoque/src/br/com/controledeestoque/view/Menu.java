package br.com.controledeestoque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.com.controledeestoque.controller.BD;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.SpringLayout;
import java.awt.Rectangle;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class Menu extends JFrame {

	public static BD bd; // Declarando BD - objeto único para todo o sistema
	// Variaveis
	private JPanel TelaInicial;
	private JMenuBar menuBar;
	private JMenu mnArquivo;
	private JMenu mnVenda;
	private JMenu mnCadastrar;
	private JMenu mnConsultar;
	private JMenuItem mntmSair;
	private JMenuItem mntmInicial;
	private JMenuItem mntmRealizarVenda;
	private JMenuItem mntmProduto;
	private JMenuItem mntmEntrada;
	private JMenuItem mntmProdutos;
	private JMenuItem mntmVendas;
	private JMenuItem mntmEntradas;

	// Inicia o menu direto
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Método construtor, carrega todos os componentes do layout e eventos
	 */
	public Menu() {
		setSize(new Dimension(1000, 500));
		setBackground(SystemColor.inactiveCaptionBorder);

		inicializar();
		definirEventos();

	}

	/**
	 * Neste métodos estão todas caracteristicas do Menu
	 */
	public void inicializar() {
		// inicializando conexão com BD
		bd = new BD();
		bd.getConnection();

		// instanciando componentes
		menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.inactiveCaptionBorder);
		mnArquivo = new JMenu("Arquivo");
		mnArquivo.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnVenda = new JMenu("Venda");
		mnVenda.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnCadastrar = new JMenu("Cadastrar");
		mnCadastrar.setBackground(SystemColor.inactiveCaptionBorder);
		mnCadastrar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnConsultar = new JMenu("Consultar");
		mnConsultar.setBackground(SystemColor.inactiveCaptionBorder);
		mnConsultar.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmInicial = new JMenuItem("Tela Inicial");
		mntmInicial.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmInicial.setBackground(SystemColor.inactiveCaptionBorder);
		mntmSair = new JMenuItem("Sair");
		mntmSair.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmSair.setBackground(SystemColor.inactiveCaptionBorder);
		mntmRealizarVenda = new JMenuItem("Realizar Venda");
		mntmRealizarVenda.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmRealizarVenda.setBackground(SystemColor.inactiveCaptionBorder);
		mntmProduto = new JMenuItem("Produto");
		mntmProduto.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmProduto.setBackground(SystemColor.inactiveCaptionBorder);
		mntmEntrada = new JMenuItem("Entrada");
		mntmEntrada.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmEntrada.setBackground(SystemColor.inactiveCaptionBorder);
		mntmProdutos = new JMenuItem("Produtos");
		mntmProdutos.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmProdutos.setBackground(SystemColor.inactiveCaptionBorder);
		mntmVendas = new JMenuItem("Vendas");
		mntmVendas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmVendas.setBackground(SystemColor.inactiveCaptionBorder);
		mntmEntradas = new JMenuItem("Entradas");
		mntmEntradas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmEntradas.setBackground(SystemColor.inactiveCaptionBorder);
		TelaInicial = new JPanel();

		// ajuste de tamanho e definição do layout
		setTitle("Controle de Estoque"); // Titulo da tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		TelaInicial.setBackground(SystemColor.inactiveCaptionBorder);
		setContentPane(TelaInicial);
		setLocationRelativeTo(null); // Tela Centralizada
		setResizable(false);// Impede que a tela seje maximizada
		setType(Type.UTILITY);
		setPreferredSize(new Dimension(900, 400));

		// define limites de componentes
		setBounds(100, 100, 663, 497);
		setForeground(new Color(0, 102, 153));
		TelaInicial.setLayout(new GridLayout(1, 0, 0, 0));
		setLocationRelativeTo(null); // Tela Centralizada
		

		// add componentes
		menuBar.add(mnArquivo);
		menuBar.add(mnVenda);
		menuBar.add(mnCadastrar);
		menuBar.add(mnConsultar);
		mnArquivo.add(mntmInicial);
		mnArquivo.add(mntmSair);
		mnVenda.add(mntmRealizarVenda);
		mnCadastrar.add(mntmProduto);
		mnCadastrar.add(mntmEntrada);
		mnConsultar.add(mntmProdutos);
		mnConsultar.add(mntmVendas);
		mnConsultar.add(mntmEntradas);
		setJMenuBar(menuBar);

	}

	/**
	 * Neste método estão todos os eventos da tela e interações com o usuário
	 */
	public void definirEventos() {

		// Abrir painel inicial
		mntmInicial.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PainelInicial i = new PainelInicial();
				atualizarPainel(i);

			}
		});

		// Fechar o sistema e finalizar conexão com BD
		mntmSair.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				bd.close(); // fecha conexão com BD
				System.exit(0); // fecha sistema
			}
		});

		// Abrir painel de venda
		mntmRealizarVenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelCadastrarVendas p = new PainelCadastrarVendas();
				atualizarPainel(p);
			}
		});

		// Abrir painel de Cadastro de Produtos
		mntmProduto.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelCadastrarProduto p = new PainelCadastrarProduto();
				atualizarPainel(p);
			}
		});

		// Abrir painel de Cadastro de Entradas
		mntmEntrada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelCadastrarEntradas p = new PainelCadastrarEntradas();
				atualizarPainel(p);
			}
		});

		// Abrir painel de Consultas de Produtos
		mntmProdutos.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelConsultarProdutos c = new PainelConsultarProdutos();
				atualizarPainel(c);

			}
		});

		// Abrir painel de Consultas de Vendas
		mntmVendas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelConsultarVendas c = new PainelConsultarVendas();
				atualizarPainel(c);

			}
		});

		// Abrir painel de Consultas de Entradas
		mntmEntradas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				PainelConsultarEntradas c = new PainelConsultarEntradas();
				atualizarPainel(c);
			}
		});

		// Abrir Menu setando Painel Inicial
		PainelInicial i = new PainelInicial();		
		i.setAlignmentY(0.0f);
		i.setAlignmentX(0.0f);
		atualizarPainel(i);
	}

	/**
	 * Este método limpa a tela e acrescenta o painel especificado
	 * 
	 * @param painel - Recebe o painel
	 */
	public void atualizarPainel(JPanel painel) {
		TelaInicial.removeAll();
		TelaInicial.add(painel);
		TelaInicial.updateUI();
	}
}
