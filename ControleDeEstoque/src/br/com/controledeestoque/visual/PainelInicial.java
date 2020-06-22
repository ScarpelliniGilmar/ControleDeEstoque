package br.com.controledeestoque.visual;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PainelInicial extends JPanel {
	private JLabel lblBemVindo;

	/**
	 * Create the panel.
	 */
	public PainelInicial() {
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		// instanciando componentes
		lblBemVindo = new JLabel("Bem Vindo Ao Sistema");

		// ajuste de tamanho e definição do layout
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindo.setFont(new Font("Tahoma", Font.BOLD, 18));
		setLayout(null);

		// define limites de componentes
		lblBemVindo.setBounds(10, 83,581, 50);

		// add componentes
		add(lblBemVindo);

	}

	private void definirEventos() {

	}
}
