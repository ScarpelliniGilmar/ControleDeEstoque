package br.com.controledeestoque.visual;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import br.com.controledeestoque.controle.MyModel;

public class PainelConsultarVendas extends JPanel {
	private JTextField tfLocalizar;
	private JTable tbVendas;
	private DefaultTableModel model; // linhas da tabela (JTable)
	private JLabel lblConsultarVendas;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelConsultarVendas() {
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		// instanciando componentes
		tfLocalizar = new JTextField();
		tbVendas = new JTable();
		lblConsultarVendas = new JLabel("Consultar venda nome do produto:");

		// ajuste de tamanho e definição do layout
		setLayout(null);
		tfLocalizar.setColumns(10);
		tbVendas.setEnabled(false);

		// define limites de componentes
		tfLocalizar.setBounds(228, 36, 196, 20);
		tbVendas.setBounds(35, 104, 312, 80);
		lblConsultarVendas.setBounds(22, 31, 196, 30);

		// add componentes
		add(tfLocalizar);
		add(tbVendas);
		add(lblConsultarVendas);

		carregarTabela();

	}

	private void definirEventos() {
		tfLocalizar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql = "select codigo_venda as 'Código da venda', descricao_produto as 'Produto', quantidade as 'Quantidade',valor as 'Valor Unitário', data as 'Data'  from vendas where descricao_produto like '"
						+ tfLocalizar.getText() + "%'"; // selecionar tudo que comece com "??"
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
				"select codigo_venda as 'Código da venda', descricao_produto as 'Produto', quantidade as 'Quantidade',valor as 'Valor Unitário', data as 'Data' from vendas");
		tbVendas.setModel(model);
		JScrollPane sp = new JScrollPane(tbVendas); // painel de rolagem
		sp.setBounds(24, 72, 517, 271); // TAMANHO E POSIÇÃO
		add(sp, BorderLayout.CENTER); // CENTRALIZAR

	}
}
