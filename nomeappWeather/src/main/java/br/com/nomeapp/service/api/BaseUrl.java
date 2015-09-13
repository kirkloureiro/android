package br.com.nomeapp.service.api;

import java.util.List;

import org.json.JSONException;

import br.com.nomeapp.model.Weather;

public class BaseUrl {

	private final LeituraJsonStrategy leituraJsonStrategy;
	private final String urlBase;

	public BaseUrl(LeituraJsonStrategy leituraJsonStrategy, String urlBase) {
		super();
		this.leituraJsonStrategy = leituraJsonStrategy;
		this.urlBase = urlBase;
	}

	public String getUrlBase() {
		return urlBase;
	}

	public List<Weather> lerJsonStrategy(String json) throws JSONException {
		return leituraJsonStrategy.lerJson(json);
	}
}
