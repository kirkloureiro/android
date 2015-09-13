package br.com.nomeapp.service;

import static br.com.nomeapp.util.ValidadorUtil.isNotNull;
import static br.com.nomeapp.util.ValidadorUtil.isNotVazio;

import java.util.Date;

import br.com.nomeapp.strategy.ExibicaoTelaStrategy;

public class WeatherFiltroParametros {

	private ExibicaoTelaStrategy exibicaoTelaStrategy;
	private String cidade;
	private String linguagem;
	private String tipo;
	private Date dataInicio;
	private Date dataFim;
	private Integer quantidadeDias;

	public WeatherFiltroParametros(final ExibicaoTelaStrategy exibicaoTelaStrategy) {
		super();
		this.exibicaoTelaStrategy = exibicaoTelaStrategy;
	}

	public ExibicaoTelaStrategy getExibicaoTelaStrategy() {
		return exibicaoTelaStrategy;
	}

	public void setExibicaoTelaStrategy(final ExibicaoTelaStrategy exibicaoTelaStrategy) {
		this.exibicaoTelaStrategy = exibicaoTelaStrategy;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(final String cidade) {
		this.cidade = cidade;
	}

	public String getLinguagem() {
		return linguagem;
	}

	public void setLinguagem(final String linguagem) {
		this.linguagem = linguagem;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(final String tipo) {
		this.tipo = tipo;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(final Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataFim() {
		return dataFim;
	}

	public void setDataFim(final Date dataFim) {
		this.dataFim = dataFim;
	}

	public Integer getQuantidadeDias() {
		return quantidadeDias;
	}

	public void setQuantidadeDias(final Integer quantidadeDias) {
		this.quantidadeDias = quantidadeDias;
	}

	public boolean hasExibicaoTelaStrategy() {
		return isNotNull(exibicaoTelaStrategy);
	}

	public boolean hasCidade() {
		return isNotVazio(cidade);
	}

	public boolean hasLinguagem() {
		return isNotVazio(linguagem);
	}

	public boolean hasTipo() {
		return isNotVazio(tipo);
	}

	public boolean hasQuantidadeDias() {
		return isNotVazio(quantidadeDias);
	}

	public boolean hasDataInicio() {
		return isNotVazio(dataInicio);
	}

	public boolean hasDataFim() {
		return isNotVazio(dataFim);
	}
}
