package br.com.nomeapp.service.api;

import java.util.List;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Test;

import br.com.nomeapp.model.Weather;
import br.com.nomeapp.util.AbstractClassTest;

public class LeituraJsonBuscaUnicaTest extends AbstractClassTest<LeituraJsonBuscaUnica> {

	@Override
	public LeituraJsonBuscaUnica criarObjetoParaTestar() {
		return new LeituraJsonBuscaUnica();
	}

	@Override
	public void startUp() {

	}

	@Test
	public void converterJsonParaWeatherTest() throws Exception {

		String json = "{\"coord\":{\"lon\":-46.64,\"lat\":-23.55},\"sys\":{\"type\":1,\"id\":4575,\"message\":0.0199,\""
				+ "country\":\"BR\",\"sunrise\":1416989475,\"sunset\":1417037807},\"weather\":[{\"id\":500,\"main\":\"Rain\""
				+ ",\"description\":\"light rain\",\"icon\":\"10d\"},{\"id\":300,\"main\":\"Drizzle\",\"description\":\"light"
				+ " intensity drizzle\",\"icon\":\"09d\"}],\"base\":\"cmc stations\",\"main\":{\"temp\":298.34,\"pressure\":1011,"
				+ "\"humidity\":57,\"temp_min\":296.65,\"temp_max\":300.15},\"wind\":{\"speed\":4.6,\"deg\":290,\"gust\":10.3},"
				+ "\"clouds\":{\"all\":75},\"dt\":1417025093,\"id\":3448439,\"name\":\"Sao Paulo\",\"cod\":200}";

		// String teste =
		// "{\"message\":\"Error: Not found city\",\"cod\":\"404\"}";
		List<Weather> lista = null;

		try {

			lista = criarObjetoParaTestar().lerJson(json);
		} catch (JSONException e) {
		}

		Assert.assertNotNull(lista);
	}
}
