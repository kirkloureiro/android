package br.com.nomeapp.strategy;

import java.util.List;

import br.com.nomeapp.model.Weather;

public interface ExibicaoTelaStrategy {

	public void exibirTela(List<Weather> listaPrevisoes);

}
