package br.com.controledeestoque.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.com.controledeestoque.controller.BD;
import br.com.controledeestoque.controller.MyModel;
import br.com.controledeestoque.model.ProdutoDAO;
import br.com.controledeestoque.model.VendaDAO;

public class PainelCadastrarVendas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5211035452952931179L;
	private JLabel lblTitulo;
	private JTable table;
	private DefaultTableModel model; // linhas da tabela (JTable)
	private JTextField tfQuantidade;
	private int codigo;
	private double subtotal;
	private JLabel lblCodigo;
	public static BD bd;
	public static VendaDAO v;
	public static ProdutoDAO p;
	private JComboBox<Object> comboBox;
	private JButton btnAdicionar;
	private JButton btnFinalizar;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblDescricaoPedido;
	private JScrollPane sp;
	private JLabel lblTotal;
	private JButton btnNovo;
	private JButton btnExcluir;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelCadastrarVendas() {
		setBackground(SystemColor.inactiveCaptionBorder);
		setLocation(new Point(600, 400));
		v = new VendaDAO();
		p = new ProdutoDAO();
		inicializar();
		definirEventos();

	}

	public void inicializar() {
		UIManager.getDefaults().put("OptionPane.background", SystemColor.inactiveCaptionBorder);
		UIManager.put("Panel.background", SystemColor.inactiveCaptionBorder);
		// instanciando componentes
		bd = new BD();
		bd.getConnection();
		lblTitulo = new JLabel("Realizar Venda");

		comboBox = new JComboBox<Object>();
		comboBox.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox.setEnabled(false);
		btnAdicionar = new JButton("Adicionar Produto");
		btnAdicionar.setEnabled(false);
		btnExcluir = new JButton("Excluir Produto");
		btnExcluir.setEnabled(false);
		tfQuantidade = new JTextField();
		tfQuantidade.setBackground(SystemColor.window);
		tfQuantidade.setEnabled(false);
		btnFinalizar = new JButton("Finalizar");
		btnNovo = new JButton("Novo");
		lblNewLabel = new JLabel("Quantidade");
		lblNewLabel_1 = new JLabel("Produto");
		lblCodigo = new JLabel("");
		lblDescricaoPedido = new JLabel("N\u00FAmero do Pedido:");
		table = new JTable();
		table.setBackground(SystemColor.inactiveCaptionBorder);
		sp = new JScrollPane(table); // painel de rolagem
		sp.setBackground(SystemColor.inactiveCaptionBorder);
		sp.setEnabled(false);
		lblTotal = new JLabel("");
		lblTotal.setBackground(Color.WHITE);
		lblTotal.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblTotal.setEnabled(false);

		// ajuste de tamanho e definição do layout
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setForeground(Color.BLUE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		setLayout(null);
		tfQuantidade.setColumns(10);

		// define limites de componentes
		lblTitulo.setBounds(10, 0, 544, 48);
		lblNewLabel_1.setBounds(41, 65, 46, 14);
		lblNewLabel.setBounds(41, 94, 110, 14);
		btnAdicionar.setBounds(430, 59, 160, 55);
		btnFinalizar.setBounds(430, 338, 160, 35);
		lblCodigo.setBounds(178, 28, 46, 14);
		lblDescricaoPedido.setBounds(41, 28, 132, 14);
		tfQuantidade.setBounds(116, 94, 246, 20);
		table.setBounds(45, 125, 523, 132);
		sp.setBounds(10, 135, 580, 146); // TAMANHO E POSIÇÃO
		comboBox.setBounds(116, 61, 246, 22);
		lblTotal.setBounds(466, 292, 124, 35);
		btnNovo.setBounds(30, 338, 160, 35);
		btnExcluir.setBounds(30, 292, 160, 42);

		// add componentes
		add(comboBox);
		add(btnAdicionar);
		add(btnFinalizar);
		add(tfQuantidade);
		add(lblNewLabel);
		add(lblNewLabel_1);
		add(lblCodigo);
		add(lblDescricaoPedido);
		add(sp, BorderLayout.CENTER); // CENTRALIZAR
		add(tfQuantidade);
		add(lblTotal);
		add(btnNovo);
		add(btnExcluir);

	}

	/**
	 * Este método possui todos os eventos de botões
	 */
	private void definirEventos() {

		btnNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfQuantidade.setText("");
				comboBox.setEnabled(true);
				tfQuantidade.setEnabled(true);
				lblTotal.setEnabled(true);
				btnAdicionar.setEnabled(true);
				btnExcluir.setEnabled(true);
				lblCodigo.setText("" + (VendaDAO.listarUltimoCodigo() + 1));
				model = MyModel.getModel(bd,
						"select codigo_venda as 'Código', nome_produto as 'Produto', quantidade as 'Quantidade', valor_produto as 'Valor Unitário' from vendas where codigo_venda ="
								+ lblCodigo.getText());

				table.setModel(model);

				// INICIO DA COMBO BOX
				comboBox.setModel(new DefaultComboBoxModel<Object>(new String[] { "Selecione um produto" }));
				Object[] vetor = ProdutoDAO.listarProdutos();

				for (int i = 0; i < vetor.length; i++) {
					comboBox.addItem(vetor[i]);

				}
			}
		});

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					String descricao = (String) comboBox.getSelectedItem(); // pega o item selecionado
					int quantidade = Integer.parseInt(tfQuantidade.getText()); // pega a quantidade digitada
					double valor = (double) p.listarValor(descricao); // recebe o valor do produto da tabela
					codigo = Integer.parseInt(lblCodigo.getText());

					if (descricao == "Selecione um produto") {
						JOptionPane.showMessageDialog(btnAdicionar, "Selecione um produto");
					} else if ("".equals(quantidade)) {
						JOptionPane.showMessageDialog(btnAdicionar, "Adicione a quantidade");
					} else {

						if (VendaDAO.Vendas(codigo, descricao, quantidade, subtotal) == true) {

							lblTotal.setText("TOTAL R$ " + VendaDAO.Total(codigo));

							JOptionPane.showMessageDialog(btnAdicionar, "Produto Adicionado!");
							tfQuantidade.setText("");
							comboBox.requestFocusInWindow();
						} else {
							JOptionPane.showMessageDialog(btnAdicionar, "Não temos essa quantidade em estoque");
						}

					}

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(btnAdicionar, "Valor invalido!");
				}

				listarTabela();
			}

		});

		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (table.getSelectedRow() >= 1) {

					v.setCodigoVenda(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0)));
					v.setNomeProduto((String) table.getValueAt(table.getSelectedRow(), 1));
					v.setQuantidade(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 2)));
					v.setValor(Double.parseDouble((String) table.getValueAt(table.getSelectedRow(), 3)));
					v.delete();
					listarTabela();
					lblTotal.setText("TOTAL R$ " + VendaDAO.Total(codigo));
					JOptionPane.showMessageDialog(btnExcluir, "Excluido com sucesso");

				} else {
					JOptionPane.showMessageDialog(null, "Favor selecionar uma linha");
				}
			}
		});

		btnFinalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (lblTotal.getText() == "") {

				} else {
					JOptionPane.showMessageDialog(btnFinalizar, "Pedido Salvo!");
					comboBox.requestFocusInWindow();
					tfQuantidade.setEnabled(false);
					comboBox.setEnabled(false);
					btnAdicionar.setEnabled(false);
					table.setEnabled(false);
					lblTotal.setText("");

				}

			}
		});
	}

	/**
	 * Este método seta os dados da tabela venda na JTable
	 */
	public void listarTabela() {

		model = MyModel.getModel(bd,
				"select codigo_venda as 'Código', nome_produto as 'Produto', quantidade as 'Quantidade', valor_produto as 'Valor Unitário' from vendas where codigo_venda ="
						+ lblCodigo.getText());
		table.setModel(model);

	}
}
