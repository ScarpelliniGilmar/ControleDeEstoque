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

public class PainelConsultarEntradas extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6783150129748683941L;
	private JTextField tfLocalizar;
	private JTable tabble;
	private DefaultTableModel model; // linhas da tabela (JTable)
	private JLabel lblConsultar;
	private JComboBox<Object> comboBox;
	private Design design;
	private JLabel lblTitulo;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelConsultarEntradas() {
		setBackground(SystemColor.inactiveCaptionBorder);
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		// alterando cor do JOptionPane
		UIManager.getDefaults().put("OptionPane.background", SystemColor.inactiveCaptionBorder);
		UIManager.put("Panel.background", SystemColor.inactiveCaptionBorder);

		// instanciando componentes
		design = new Design();
		tfLocalizar = new JTextField();
		tabble = new JTable();
		tabble.setBackground(SystemColor.inactiveCaptionBorder);
		lblConsultar = new JLabel("Consultar:");
		lblConsultar.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox = new JComboBox<Object>();
		comboBox.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox.setModel(new DefaultComboBoxModel<Object>(
				new String[] { "Selecione o tipo de consulta", "C\u00F3digo do Produto", "C\u00F3digo da Entrada" }));
		lblTitulo = new JLabel("Consultar Entradas");

		// ajuste de tamanho e definição do layout
		setLayout(null);
		tabble.setEnabled(false);

		// define limites de componentes
		design.tfConsultas(tfLocalizar);
		design.lblTituloConsultas(lblConsultar);
		design.comboBoxConsultas(comboBox);
		design.tituloConsultas(lblTitulo);
		design.tabelaConsultas(tabble);

		// add componentes
		add(tfLocalizar);
		add(tabble);
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
					selected = "codigo_entrada";
				}

				String sql = "SELECT codigo_entrada as 'Código da entrada', codigo_produto as 'Código do Produto', quantidade_entrada as 'Quantidade', data_entrada as 'Data' from entradas where "
						+ selected + " like '" + tfLocalizar.getText() + "%'"; // selecionar tudo que comece com "??"
				model = MyModel.getModel(Menu.bd, sql);
				tabble.setModel(model);
			}
		});

	}

	/**
	 * Método que envia um paramentro e recebe os dados do BD
	 */
	private void carregarTabela() {
		model = MyModel.getModel(Menu.bd,
				"SELECT codigo_entrada as 'Código da entrada', codigo_produto as 'Código do Produto', quantidade_entrada as 'Quantidade', data_entrada as 'Data' from entradas");
		tabble.setModel(model);
		JScrollPane sp = new JScrollPane(tabble); // painel de rolagem
		sp.setBackground(SystemColor.inactiveCaptionBorder);
		sp.setBounds(10, 148, 624, 208); // TAMANHO E POSIÇÃO
		add(sp, BorderLayout.CENTER); // CENTRALIZAR

	}
}
