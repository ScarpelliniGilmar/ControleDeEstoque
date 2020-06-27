package br.com.controledeestoque.view;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;

/**
 * 
 * @author Gilmar
 *
 */
public class PainelInicial extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel lblBemVindo;

	/**
	 * Create the panel.
	 */
	public PainelInicial() {
		setBorder(new LineBorder(new Color(0, 0, 0)));
		setBackground(SystemColor.inactiveCaptionBorder);
		inicializar();
		definirEventos();
	}

	public void inicializar() {
		setLayout(new BorderLayout(0, 0));
		// instanciando componentes
		lblBemVindo = new JLabel("Bem Vindo Ao Sistema");
		lblBemVindo.setBorder(null);
		lblBemVindo.setBackground(SystemColor.desktop);

		// ajuste de tamanho e definição do layout
		lblBemVindo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBemVindo.setFont(new Font("Goudy Stout", Font.BOLD, 18));

		// add componentes
		add(lblBemVindo);

		JLabel lblNewLabel = new JLabel(
				"Desenvolvido por Graduandos em An\u00E1lise e Desenvolvimento de Sistemas da FATEC-Indaiatuba");
		lblNewLabel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblNewLabel, BorderLayout.SOUTH);

	}

	private void definirEventos() {

	}
}
