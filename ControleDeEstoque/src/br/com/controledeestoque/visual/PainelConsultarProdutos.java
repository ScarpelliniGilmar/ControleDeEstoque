package br.com.controledeestoque.visual;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import br.com.controledeestoque.controle.MyModel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

public class PainelConsultarProdutos extends JPanel {
	private JTextField tfLocalizar;
	private JTable tbProdutos;
	private DefaultTableModel model; // linhas da tabela (JTable)
	private JLabel lblConsultarProdutos;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelConsultarProdutos() {
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		// instanciando componentes
		tfLocalizar = new JTextField();
		tbProdutos = new JTable();
		lblConsultarProdutos = new JLabel("Consultar produtos por c\u00F3digo:");

		// ajuste de tamanho e definição do layout
		setLayout(null);
		tfLocalizar.setColumns(10);
		tbProdutos.setEnabled(false);

		// define limites de componentes
		tfLocalizar.setBounds(222, 31, 196, 20);
		tbProdutos.setBounds(35, 104, 312, 80);
		lblConsultarProdutos.setBounds(24, 26, 196, 30);

		// add componentes
		add(tfLocalizar);
		add(tbProdutos);
		add(lblConsultarProdutos);

		carregarTabela();

	}

	private void definirEventos() {
		tfLocalizar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql = "SELECT codigo_produto as 'Código do Produto', descricao_produto as 'Nome do Produto', valor_produto \r\n"
						+ "as 'Valor', quantidade as 'Quantidade' from produtos where codigo_produto like '"
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
				"SELECT codigo_produto as 'Código', descricao_produto as 'Nome', valor_produto \r\n"
						+ "as 'Valor', quantidade as 'Quantidade' from produtos");
		tbProdutos.setModel(model);
		JScrollPane sp = new JScrollPane(tbProdutos); // painel de rolagem
		sp.setBounds(24, 72, 517, 271); // TAMANHO E POSIÇÃO
		add(sp, BorderLayout.CENTER); // CENTRALIZAR

	}
}
