package br.com.nomeapp.service;

import static br.com.nomeapp.util.ValidadorUtil.isNull;

import java.util.List;

import android.os.AsyncTask;
import android.util.Log;
import br.com.nomeapp.exception.InfraEstruturaException;
import br.com.nomeapp.factory.APIExternaPrevisaoTempo;
import br.com.nomeapp.factory.APIWeatherFactory;
import br.com.nomeapp.model.Weather;
import br.com.nomeapp.strategy.ExibicaoTelaStrategy;

public class AsyncWeatherTaskService extends AsyncTask<Object, Void, List<Weather>> {

	private ExibicaoTelaStrategy exibicaoTelaStrategy;

	public AsyncWeatherTaskService() {
		super();
	}

	@Override
	protected List<Weather> doInBackground(final Object... params) {

		List<Weather> listaPrevisoes = null;

		final APIExternaPrevisaoTempo apiWeather = APIWeatherFactory.obterServicoAPIWeather();

		try {
			validateParams(params);

			final WeatherFiltroParametros filtro = (WeatherFiltroParametros) params[0];

			exibicaoTelaStrategy = filtro.getExibicaoTelaStrategy();

			listaPrevisoes = apiWeather.buscarPrevisaoTempo(filtro);

		} catch (final InfraEstruturaException e) {
			Log.e("Error", e.getMessage());
		}
		return listaPrevisoes;
	}

	@Override
	protected void onPostExecute(final List<Weather> listaPrevisoes) {

		super.onPostExecute(listaPrevisoes);

		if (isNull(exibicaoTelaStrategy)) {
			Log.e("Error", "Não há implementacao de tela a ser exibida");
			return;
		}

		exibicaoTelaStrategy.exibirTela(listaPrevisoes);

	}

	private void validateParams(final Object... params) throws InfraEstruturaException {
		if (isNull(params) || params.length == 0) {
			throw new InfraEstruturaException("O Filtro com parametros para pesquisa está vazio");
		}
	}
}
