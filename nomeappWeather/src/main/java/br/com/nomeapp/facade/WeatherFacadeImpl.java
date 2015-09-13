package br.com.nomeapp.facade;

import br.com.nomeapp.service.WeatherService;
import br.com.nomeapp.service.WeatherServiceImpl;

public class WeatherFacadeImpl implements WeatherFacade {

	@Override
	public void buscarPrevisaoTempoPorCidade(final String cidade) {

		final WeatherService weatherService = new WeatherServiceImpl();

		weatherService.buscarPrevisaoTempoPorCidade(cidade);
	}

	@Override
	public void buscarPrevisaoTempoPorCidade(final String cidade, final String linguagem) {

		final WeatherService weatherService = new WeatherServiceImpl();

		weatherService.buscarPrevisaoTempoPorCidade(cidade, linguagem);
	}

	@Override
	public void buscarPrevisaoProximosDias(final String cidade, final Integer quantidadeDias) {

		final WeatherService weatherService = new WeatherServiceImpl();

		weatherService.buscarPrevisaoProximosDias(cidade, quantidadeDias);
	}
}
