package br.com.nomeapp.service.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.nomeapp.model.Weather;

public class LeituraJsonBuscaPrevisaoFutura implements LeituraJsonStrategy {

	@Override
	public List<Weather> lerJson(final String json) throws JSONException {

		final List<Weather> listWeathers = new ArrayList<Weather>();

		final JSONObject jObj = new JSONObject(json);

		final JSONArray jsonArray = jObj.getJSONArray("list");

		for (int i = 0; i < jsonArray.length(); i++) {

			final JSONObject jsonObject = jsonArray.getJSONObject(i);

			final JSONArray jArr = jsonObject.getJSONArray("weather");

			final JSONObject weatherJsonObject = jArr.getJSONObject(0);

			final Weather weather = new Weather();

			weather.setDataPrevisao(new Date(jsonObject.getLong("dt") * 1000L));
			weather.getCurrentCondition().setWeatherId(getInt("id", weatherJsonObject));
			weather.getCurrentCondition().setDescr(getString("description", weatherJsonObject));
			weather.getCurrentCondition().setCondition(getString("main", weatherJsonObject));
			weather.getCurrentCondition().setIcon(getString("icon", weatherJsonObject));

			weather.getCurrentCondition().setHumidity(getInt("humidity", jsonObject));
			weather.getCurrentCondition().setPressure(getInt("pressure", jsonObject));

			final JSONObject jsonTemperatura = jsonObject.getJSONObject("temp");

			weather.getTemperature().setMaxTemp(getFloat("max", jsonTemperatura));
			weather.getTemperature().setMinTemp(getFloat("min", jsonTemperatura));
			weather.getTemperature().setTemp(getFloat("day", jsonTemperatura));

			weather.getWind().setSpeed(getFloat("speed", jsonObject));
			weather.getWind().setDeg(getFloat("deg", jsonObject));

			weather.getClouds().setPerc(getInt("clouds", jsonObject));

			listWeathers.add(weather);
		}

		return listWeathers;
	}

	private static String getString(final String tagName, final JSONObject jObj) throws JSONException {
		return jObj.getString(tagName);
	}

	private static float getFloat(final String tagName, final JSONObject jObj) throws JSONException {
		return (float) jObj.getDouble(tagName);
	}

	private static int getInt(final String tagName, final JSONObject jObj) throws JSONException {
		return jObj.getInt(tagName);
	}
}
