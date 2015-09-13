package br.com.nomeapp.facade;

public interface WeatherFacade {

	public void buscarPrevisaoTempoPorCidade(String cidade);

	public void buscarPrevisaoTempoPorCidade(String cidade, String linguagem);

	public void buscarPrevisaoProximosDias(String cidade, Integer quantidadeDias);
}
