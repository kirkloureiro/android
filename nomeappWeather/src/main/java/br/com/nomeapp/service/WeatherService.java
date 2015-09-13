package br.com.nomeapp.service;

public interface WeatherService {

	public void buscarPrevisaoTempoPorCidade(String cidade);

	public void buscarPrevisaoTempoPorCidade(String cidade, String linguagem);

	public void buscarPrevisaoProximosDias(String cidade, Integer quantidadeDias);
}
