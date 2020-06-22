package br.com.controledeestoque.modelo;

public class Usuario {
	
	private String nome,email,senha;

	/**
	 * verifica se o usuario e senha são validos
	 * @param email - o login do usuario
	 * @param senha - 
	 * @return - true se o usuario for valido, ou false caso contrario
	 */
	public boolean validar(String email, String senha) {
		if(email.equalsIgnoreCase("fatec") && senha.equalsIgnoreCase("fatec"))
			return true;
		else
			return false;
	}
	
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
}
