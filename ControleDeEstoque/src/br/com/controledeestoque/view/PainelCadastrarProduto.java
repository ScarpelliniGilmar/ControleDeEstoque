package br.com.controledeestoque.view;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.com.controledeestoque.model.ProdutoDAO;

public class PainelCadastrarProduto extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1622601495525275875L;
	private JTextField textDescricao;
	private JTextField textValor;
	private JTextField textQuantidade;
	private JLabel lblTitulo;
	private JLabel lblNome;
	private JLabel lblValor;
	private JLabel lblQuantidade;
	private JButton btnCadastrar;
	private Design design;

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public PainelCadastrarProduto() {
		setBackground(SystemColor.inactiveCaptionBorder);
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		// instanciando componentes
		design = new Design();
		lblTitulo = new JLabel("Cadastrar Produto");
		lblNome = new JLabel("Nome: ");
		lblValor = new JLabel("Valor: ");
		lblQuantidade = new JLabel("Quantidade: ");
		textDescricao = new JTextField();
		textValor = new JTextField();
		textQuantidade = new JTextField();
		btnCadastrar = new JButton("Cadastrar");

		// ajuste de tamanho e definição do layout
		design.tituloConsultas(lblTitulo);
		textDescricao.setColumns(10);
		textValor.setColumns(10);
		textQuantidade.setColumns(10);
		setLayout(null);

		// define limites de componentes
		lblNome.setBounds(30, 103, 100, 14);
		lblValor.setBounds(30, 143, 100, 14);
		lblQuantidade.setBounds(30, 187, 100, 14);
		textDescricao.setBounds(150, 100, 200, 25);
		textValor.setBounds(150, 140, 200, 25);
		textQuantidade.setBounds(150, 184, 200, 25);
		btnCadastrar.setBounds(233, 237, 139, 37);

		// add componentes
		add(lblTitulo);
		add(lblNome);
		add(lblValor);
		add(lblQuantidade);
		add(textDescricao);
		add(textValor);
		add(textQuantidade);
		add(btnCadastrar);

	}

	private void definirEventos() {
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String nome = textDescricao.getText();
					String valor = textValor.getText();
					String quantidade = textQuantidade.getText();

					if ("".equals(nome)) {
						JOptionPane.showMessageDialog(btnCadastrar, "Adicione o nome o produto");
					} else if ("".equals(quantidade)) {
						JOptionPane.showMessageDialog(btnCadastrar, "Adicione a quantidade");
					} else if ("".equals(valor)) {
						JOptionPane.showMessageDialog(btnCadastrar, "Adicione o valor");
					} else {

						ProdutoDAO p = new ProdutoDAO();
						p.setNome(nome);
						p.setValor(Double.parseDouble(valor));
						p.setQuantidade(Integer.parseInt(quantidade));
						p.insert();

						JOptionPane.showMessageDialog(btnCadastrar, "Cadastro Concluído!");
						textDescricao.setText("");
						textQuantidade.setText("");
						textValor.setText("");
						textDescricao.requestFocusInWindow();
					}

				} catch (Exception erro) {
					JOptionPane.showMessageDialog(btnCadastrar, "Valor invalido!");
				}
			}
		});

	}
}
