package br.com.controledeestoque.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import br.com.controledeestoque.controller.MyModel;

import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class PainelConsultarProdutos extends JPanel {
	private JTextField tfLocalizar;
	private JTable tbProdutos;
	private DefaultTableModel model; // linhas da tabela (JTable)
	private JLabel lblConsultar;
	private JComboBox comboBox;

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
		UIManager.put ("Panel.background",  SystemColor.inactiveCaptionBorder);
		// instanciando componentes
		tfLocalizar = new JTextField();
		tbProdutos = new JTable();
		tbProdutos.setBackground(SystemColor.inactiveCaptionBorder);
		lblConsultar = new JLabel("Consultar:");
		comboBox = new JComboBox();
		
		
		

		// ajuste de tamanho e definição do layout
		setLayout(null);
		tfLocalizar.setColumns(10);
		tbProdutos.setEnabled(false);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione o tipo de consulta", "C\u00F3digo do Produto", "Nome do Produto"}));
		comboBox.setBackground(SystemColor.inactiveCaptionBorder);

		// define limites de componentes
		tfLocalizar.setBounds(190, 76, 196, 25);
		tbProdutos.setBounds(35, 104, 312, 80);
		lblConsultar.setBounds(103, 73, 77, 30);
		comboBox.setBounds(190, 29, 196, 30);
		
		// add componentes
		add(tfLocalizar);
		add(tbProdutos);
		add(lblConsultar);
		add(comboBox);
		carregarTabela();

	}

	private void definirEventos() {
		tfLocalizar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String selected = (String) comboBox.getSelectedItem();
				if(selected=="Selecione o tipo de consulta") {
					JOptionPane.showMessageDialog(null, "Selecione o tipo de consulta");
				} else if(selected=="Código do Produto"){
						selected ="codigo_produto";
				}else {
					selected ="nome_produto";
				}
				
				String sql = "SELECT codigo_produto as 'Código do Produto', nome_produto as 'Nome do Produto', valor_produto \r\n"
						+ "as 'Valor', quantidade_produto as 'Quantidade' from produtos where "+selected+" like '"
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
		sp.setBounds(10, 117, 630, 271); // TAMANHO E POSIÇÃO
		add(sp, BorderLayout.CENTER); // CENTRALIZAR

	}
}
