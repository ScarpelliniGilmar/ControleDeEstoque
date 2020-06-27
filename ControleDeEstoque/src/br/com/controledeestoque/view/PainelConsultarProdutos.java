package br.com.controledeestoque.view;

import java.awt.BorderLayout;
import java.awt.SystemColor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import br.com.controledeestoque.controller.MyModel;

public class PainelConsultarProdutos extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7119292093260042086L;
	private JTextField tfLocalizar;
	private JTable tbProdutos;
	private DefaultTableModel model; // linhas da tabela (JTable)
	private JLabel lblConsultar;
	private JComboBox<Object> comboBox;
	private Design design;
	private JLabel lblTitulo;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelConsultarProdutos() {
		setBackground(SystemColor.inactiveCaptionBorder);
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		UIManager.getDefaults().put("OptionPane.background", SystemColor.inactiveCaptionBorder);
		UIManager.put("Panel.background", SystemColor.inactiveCaptionBorder);
		// instanciando componentes
		tfLocalizar = new JTextField();
		tbProdutos = new JTable();
		lblConsultar = new JLabel("Consultar:");
		comboBox = new JComboBox<Object>();
		design = new Design();
		lblTitulo = new JLabel("Consultar Produtos");

		// ajuste de tamanho e definição do layout
		setLayout(null);
		tbProdutos.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Selecione o tipo de consulta", "C\u00F3digo do Produto", "Nome do Produto" }));

		// define limites de componentes
		design.tfConsultas(tfLocalizar);
		design.lblTituloConsultas(lblConsultar);
		design.comboBoxConsultas(comboBox);
		design.tituloConsultas(lblTitulo);
		design.tabelaConsultas(tbProdutos);

		// add componentes
		add(tfLocalizar);
		add(tbProdutos);
		add(lblConsultar);
		add(comboBox);
		add(lblTitulo);

		carregarTabela();

	}

	private void definirEventos() {
		tfLocalizar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String selected = (String) comboBox.getSelectedItem();
				if (selected == "Selecione o tipo de consulta") {
					JOptionPane.showMessageDialog(null, "Selecione o tipo de consulta");
				} else if (selected == "Código do Produto") {
					selected = "codigo_produto";
				} else {
					selected = "nome_produto";
				}

				String sql = "SELECT codigo_produto as 'Código do Produto', nome_produto as 'Nome do Produto', valor_produto \r\n"
						+ "as 'Valor', quantidade_produto as 'Quantidade' from produtos where " + selected + " like '"
						+ tfLocalizar.getText() + "%'"; // selecionar tudo que comece com xx
				model = MyModel.getModel(Menu.bd, sql);
				tbProdutos.setModel(model);
			}
		});

	}

	/**
	 * Método que envia um paramentro e recebe os dados do BD
	 */
	private void carregarTabela() {
		model = MyModel.getModel(Menu.bd,
				"SELECT codigo_produto as 'Código do Produto', nome_produto as 'Nome do Produto', valor_produto as 'Valor', quantidade_produto as 'Quantidade' from produtos");
		tbProdutos.setModel(model);
		JScrollPane sp = new JScrollPane(tbProdutos); // painel de rolagem
		sp.setBackground(SystemColor.inactiveCaptionBorder);
		sp.setBounds(10, 148, 624, 208); // TAMANHO E POSIÇÃO
		add(sp, BorderLayout.CENTER); // CENTRALIZAR

	}
}
