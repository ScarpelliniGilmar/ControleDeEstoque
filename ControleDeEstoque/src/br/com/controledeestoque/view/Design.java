package br.com.controledeestoque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class Design {

	public void tituloConsultas(JLabel titulo) {
		titulo.setFont(new Font("Goudy Stout", Font.PLAIN, 15));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setBorder(new LineBorder(new Color(0, 0, 0)));
		titulo.setBounds(10, 11, 624, 30);

	}

	public void comboBoxConsultas(JComboBox<?> comboBox) {
		comboBox.setBounds(185, 63, 196, 30);
		comboBox.setBackground(SystemColor.inactiveCaptionBorder);
	}

	public void lblTituloConsultas(JLabel consultar) {
		consultar.setBounds(98, 107, 77, 30);
	}

	public void tabelaConsultas(JTable table) {
		table.setBackground(SystemColor.inactiveCaptionBorder);
		table.setBounds(35, 104, 312, 80);

	}

	public void tfConsultas(JTextField tfLocalizar) {
		tfLocalizar.setColumns(10);
		tfLocalizar.setBounds(185, 110, 196, 25);
	}

	public void menu(JMenu menu) {
		menu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menu.setBackground(SystemColor.inactiveCaptionBorder);

	}

	public void menuItem(JMenuItem menuItem) {
		menuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuItem.setBackground(SystemColor.inactiveCaptionBorder);
	}

}
