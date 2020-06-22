package br.com.controledeestoque.visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import br.com.controledeestoque.controle.ClasseDeComunicacao;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;

public class PainelCadastrarEntradas extends JPanel {
	private JTextField textQuantidade;
	private JLabel lblTitulo;
	private JLabel lblQuantidade;
	private JComboBox<Object> comboBox;
	private JButton btnArmazenar;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelCadastrarEntradas() {
		inicializar();
		definirEventos();

	}

	public void inicializar() {
		// instanciando componentes
		lblTitulo = new JLabel("Cadastrar Entrada");
		lblQuantidade = new JLabel("Quantidade:");
		comboBox = new JComboBox();
		textQuantidade = new JTextField();
		btnArmazenar = new JButton("Armazenar");
		ClasseDeComunicacao c = new ClasseDeComunicacao();

		// ajuste de tamanho e definição do layout
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setForeground(Color.BLUE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textQuantidade.setColumns(10);
		setLayout(null);

		// define limites de componentes
		lblTitulo.setBounds(10, 41, 430, 48);
		lblQuantidade.setBounds(30, 145, 148, 30);
		textQuantidade.setBounds(150, 148, 200, 25);
		comboBox.setBounds(148, 94, 204, 30);
		btnArmazenar.setBounds(234, 233, 118, 30);

		// add componentes
		add(lblTitulo);
		add(lblQuantidade);
		add(textQuantidade);
		add(comboBox);
		add(btnArmazenar);

		comboBox.setModel(new DefaultComboBoxModel(new String[] { "Selecione um produto" }));
		// Adicionando itens ao ComboBox
		Object[] vetor = c.listar();
		for (int i = 0; i < vetor.length; i++) {
			comboBox.addItem(vetor[i]);
		}

	}

	private void definirEventos() {
		btnArmazenar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				/**
				 * Recebe o produto e a quantidade e adiciona na tabela entrada no BD
				 */
				String descricao = (String) comboBox.getSelectedItem();
				String quantidade = textQuantidade.getText();

				if (descricao == "Selecione um produto" || "".equals(quantidade)) {
					JOptionPane.showMessageDialog(btnArmazenar, "Selecione um produto e adicione a quantidade");
				} else {
					ClasseDeComunicacao c = new ClasseDeComunicacao();
					c.Entradas(descricao, quantidade);
					JOptionPane.showMessageDialog(btnArmazenar, "Armazenado!");
					textQuantidade.setText("");
					comboBox.requestFocusInWindow();
				}

			}
		});
	}
}
