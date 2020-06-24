package br.com.controledeestoque.model;

public class Entrada {

	private int codigoEntrada, codigoProduto, quantidadeEntrada;
	private String data;

	public int getCodigoEntrada() {
		return codigoEntrada;
	}

	public void setCodigoEntrada(int codigoEntrada) {
		this.codigoEntrada = codigoEntrada;
	}

	public int getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(int codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public int getQuantidadeEntrada() {
		return quantidadeEntrada;
	}

	public void setQuantidadeEntrada(int quantidadeEntrada) {
		this.quantidadeEntrada = quantidadeEntrada;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
