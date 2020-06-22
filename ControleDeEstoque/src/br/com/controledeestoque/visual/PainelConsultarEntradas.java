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

public class PainelConsultarEntradas extends JPanel {
	private JTextField tfLocalizar;
	private JTable tbCidades;
	private DefaultTableModel model; // linhas da tabela (JTable)
	private JLabel lblNewLabel;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelConsultarEntradas() {
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		// instanciando componentes
		tfLocalizar = new JTextField();
		tbCidades = new JTable();
		lblNewLabel = new JLabel("Consultar entradas por c\u00F3digo:");

		// ajuste de tamanho e definição do layout
		setLayout(null);
		tfLocalizar.setColumns(10);
		tbCidades.setEnabled(false);

		// define limites de componentes
		tfLocalizar.setBounds(294, 29, 196, 20);
		tbCidades.setBounds(35, 104, 312, 80);
		lblNewLabel.setBounds(29, 24, 196, 30);

		// add componentes
		add(tfLocalizar);
		add(tbCidades);
		add(lblNewLabel);

		carregarTabela();

	}

	private void definirEventos() {
		tfLocalizar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String sql = "SELECT codigo_entradas as 'Código da entrada', codigo_produto as 'Código do Produto', quantidade as 'Quantidade', data as 'Data' from entradas where codigo_entradas like '"
						+ tfLocalizar.getText() + "%'"; // selecionar tudo que comece com "??"
				model = MyModel.getModel(Menu.bd, sql);
				tbCidades.setModel(model);
			}
		});

	}

	/**
	 * Método que envia um paramentro e recebe os dados do BD
	 */
	private void carregarTabela() {
		model = MyModel.getModel(Menu.bd,
				"SELECT codigo_entradas as 'Código da entrada', codigo_produto as 'Código do Produto', quantidade as 'Quantidade', data as 'Data' from entradas");
		tbCidades.setModel(model);
		JScrollPane sp = new JScrollPane(tbCidades); // painel de rolagem
		sp.setBounds(24, 72, 517, 271); // TAMANHO E POSIÇÃO
		add(sp, BorderLayout.CENTER); // CENTRALIZAR

	}
}
