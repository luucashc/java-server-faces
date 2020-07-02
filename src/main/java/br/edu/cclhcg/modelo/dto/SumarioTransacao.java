package br.edu.cclhcg.modelo.dto;

public class SumarioTransacao {

	private Long qtd;
	
	private Double valor;
	
	private String tipo;

	public SumarioTransacao(Long qtd, Double valor, String tipo) {
		this.qtd = qtd;
		this.valor = valor;
		this.tipo = tipo;
	}

	public Long getQtd() {
		return qtd;
	}

	public void setQtd(Long qtd) {
		this.qtd = qtd;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}
}
