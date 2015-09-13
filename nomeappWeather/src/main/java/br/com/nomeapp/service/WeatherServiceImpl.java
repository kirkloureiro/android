package br.com.nomeapp.service;

import br.com.nomeapp.strategy.TelaBuscaPrevisaoFutura;
import br.com.nomeapp.strategy.TelaBuscaUnica;

public class WeatherServiceImpl implements WeatherService {

	@Override
	public void buscarPrevisaoTempoPorCidade(final String cidade) {

		final WeatherFiltroParametros filtro = new WeatherFiltroParametros(new TelaBuscaUnica());

		filtro.setCidade(cidade);

		executarThreadBuscaPorFiltro(filtro);
	}

	@Override
	public void buscarPrevisaoTempoPorCidade(final String cidade, final String linguagem) {

		final WeatherFiltroParametros filtro = new WeatherFiltroParametros(new TelaBuscaUnica());

		filtro.setCidade(cidade);
		filtro.setLinguagem(linguagem);

		executarThreadBuscaPorFiltro(filtro);
	}

	@Override
	public void buscarPrevisaoProximosDias(final String cidade, final Integer quantidadeDias) {

		final WeatherFiltroParametros filtro = new WeatherFiltroParametros(new TelaBuscaPrevisaoFutura());

		filtro.setCidade(cidade);
		filtro.setQuantidadeDias(quantidadeDias);

		executarThreadBuscaPorFiltro(filtro);
	}

	private void executarThreadBuscaPorFiltro(final WeatherFiltroParametros filtro) {
		final AsyncWeatherTaskService task = new AsyncWeatherTaskService();
		task.execute(filtro);
	}
}
