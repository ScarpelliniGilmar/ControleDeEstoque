package br.com.controledeestoque.view;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import br.com.controledeestoque.controller.MyModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.SystemColor;

public class PainelConsultarVendas extends JPanel {
	private JTextField tfLocalizar;
	private JTable tbVendas;
	private DefaultTableModel model; // linhas da tabela (JTable)
	private JLabel lblConsultar;
	private JComboBox comboBox;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelConsultarVendas() {
		setBackground(SystemColor.inactiveCaptionBorder);
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		UIManager.getDefaults().put("OptionPane.background", SystemColor.inactiveCaptionBorder);
		UIManager.put ("Panel.background",  SystemColor.inactiveCaptionBorder);
		
		// instanciando componentes
		tfLocalizar = new JTextField();
		tbVendas = new JTable();
		tbVendas.setBackground(SystemColor.inactiveCaptionBorder);
		lblConsultar = new JLabel("Consultar:");
		comboBox = new JComboBox();
		comboBox.setBackground(SystemColor.inactiveCaptionBorder);
		comboBox.setModel(new DefaultComboBoxModel(
				new String[] { "Selecione o tipo de consulta", "Nome do Produto", "C\u00F3digo da Venda" }));

		// ajuste de tamanho e definição do layout
		setLayout(null);
		tfLocalizar.setColumns(10);
		tbVendas.setEnabled(false);

		// define limites de componentes
		tfLocalizar.setBounds(190, 76, 196, 25);
		tbVendas.setBounds(35, 104, 312, 80);
		lblConsultar.setBounds(103, 73, 77, 30);
		comboBox.setBounds(190, 29, 196, 30);

		// add componentes
		add(tfLocalizar);
		add(tbVendas);
		add(lblConsultar);
		add(comboBox);

		carregarTabela();

	}

	private void definirEventos() {
		tfLocalizar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String selected = (String) comboBox.getSelectedItem();
				if (selected == "Selecione o tipo de consulta") {
					JOptionPane.showMessageDialog(null, "Selecione o tipo de consulta");
				} else if (selected == "Nome do Produto") {
					selected = "nome_produto";
				} else {
					selected = "codigo_venda";
				}

				String sql = "select codigo_venda as 'Código da venda', nome_produto as 'Produto', quantidade as 'Quantidade',valor_produto as 'Valor Unitário', data_venda as 'Data' from vendas where "
						+ selected + " like '" + tfLocalizar.getText() + "%'"; // selecionar tudo que comece com "??"
				model = MyModel.getModel(Menu.bd, sql);
				tbVendas.setModel(model);
			}
		});

	}

	/**
	 * Método que envia um paramentro e recebe os dados do BD
	 */
	private void carregarTabela() {
		model = MyModel.getModel(Menu.bd,
				"select codigo_venda as 'Código da venda', nome_produto as 'Produto', quantidade as 'Quantidade',valor_produto as 'Valor Unitário', data_venda as 'Data' from vendas");
		tbVendas.setModel(model);
		JScrollPane sp = new JScrollPane(tbVendas); // painel de rolagem
		sp.setBounds(10, 148, 624, 208); // TAMANHO E POSIÇÃO
		add(sp, BorderLayout.CENTER); // CENTRALIZAR

	}
}
