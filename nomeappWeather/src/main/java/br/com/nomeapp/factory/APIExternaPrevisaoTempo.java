package br.com.nomeapp.factory;

import java.util.List;

import br.com.nomeapp.exception.InfraEstruturaException;
import br.com.nomeapp.model.Weather;
import br.com.nomeapp.service.WeatherFiltroParametros;

/**
 * Interface que controla a comunicacao da aplicacao com o servico externo de
 * busca pela internet, deixando a aplicacao independente da API utilizada para
 * busca da previsão do tempo
 */
public interface APIExternaPrevisaoTempo {

	public List<Weather> buscarPrevisaoTempo(WeatherFiltroParametros filtro) throws InfraEstruturaException;

}
