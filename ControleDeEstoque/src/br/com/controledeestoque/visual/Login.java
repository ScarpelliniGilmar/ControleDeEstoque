package br.com.controledeestoque.visual;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.com.controledeestoque.modelo.Usuario;

/**
 * Tela de Login
 * 
 * @author Gilmar
 * Sistema de Controle de Estoque relação com o Banco de dados SQL Server
 * @version 0.1
 *
 */
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textEmail;
	private JPasswordField textSenha;
	private JLabel lblEmail;
	private JLabel lblSenha;
	private JButton btnCancelar;
	private JButton btnEntrar;

	/**
	 * Inicia o aplicativo
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Criando a tela e definindo seus componentes
	 */
	public Login() {
		inicializar();
		definirEventos();
	}

	public void inicializar() {

		// instanciando componentes
		lblEmail = new JLabel("E-mail:");
		lblSenha = new JLabel("Senha:");
		textEmail = new JTextField();
		textSenha = new JPasswordField();
		btnCancelar = new JButton("Cancelar");
		btnEntrar = new JButton("Entrar");
		contentPane = new JPanel();

		// ajuste de tamanho e definição do layout
		setType(Type.UTILITY);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Login"); // seta o titulo
		setLocationRelativeTo(null); // Tela Centralizada
		setContentPane(contentPane);
		textEmail.setColumns(10);
		contentPane.setLayout(null);

		// define limites de componentes
		setBounds(100, 100, 427, 291);// seta a posição na tela
		lblEmail.setBounds(44, 64, 46, 14);// seta a posição na tela
		lblSenha.setBounds(44, 111, 46, 14);
		textEmail.setBounds(100, 61, 227, 20);
		textSenha.setBounds(100, 108, 227, 20);// seta a posição na tela
		btnCancelar.setBounds(114, 176, 89, 23);// seta a posição na tela
		btnEntrar.setBounds(238, 176, 89, 23);// seta a posição na tela
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		// add componentes
		contentPane.add(lblEmail);
		contentPane.add(lblSenha);
		contentPane.add(textEmail);
		contentPane.add(textSenha);
		contentPane.add(btnCancelar);
		contentPane.add(btnEntrar);

	}

	public void definirEventos() {
		/**
		 * Ao clicar no botão "Entrar", se o login e senha for verdadeiro, abre tela
		 * inicial. Se for falso, imprimir mensagem de erro.
		 */
		btnEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Usuario u = new Usuario();
				boolean r = u.validar(textEmail.getText(), new String(textSenha.getPassword()));
				if (r) {
					// abrir menu
					Menu m = new Menu();
					m.setVisible(true);
					setVisible(false);

				} else
					JOptionPane.showMessageDialog(btnEntrar, "Usuario inválido!");

			}
		});

		/**
		 * Ao clicar no botão "Cancelar", o sistema é finalizado
		 */
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}

}
