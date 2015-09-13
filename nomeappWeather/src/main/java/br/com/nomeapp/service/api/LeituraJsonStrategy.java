package br.com.nomeapp.service.api;

import java.util.List;

import org.json.JSONException;

import br.com.nomeapp.model.Weather;

public interface LeituraJsonStrategy {

	public List<Weather> lerJson(String json) throws JSONException;
}
