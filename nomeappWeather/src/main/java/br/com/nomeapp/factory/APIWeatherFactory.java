package br.com.nomeapp.factory;

import br.com.nomeapp.service.api.OpenWeatherMapAPI;

public class APIWeatherFactory {

	/**
	 * Retorna o objeto que tem a implementacao da API a ser utilizada pela
	 * aplicacao
	 * 
	 * @return APIExternaPrevisaoTempo
	 */
	public static APIExternaPrevisaoTempo obterServicoAPIWeather() {
		return new OpenWeatherMapAPI();
	}

}
