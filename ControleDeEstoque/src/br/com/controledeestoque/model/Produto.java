package br.com.controledeestoque.model;

public class Produto {

	private int codigoProduto, quantidade;
	private String nome;
	private double valor;

	public int getCodigo() {
		return codigoProduto;
	}

	public void setCodigo(int codigo) {
		this.codigoProduto = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
