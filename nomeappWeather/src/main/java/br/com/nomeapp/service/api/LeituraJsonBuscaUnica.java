package br.com.nomeapp.service.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import br.com.nomeapp.model.Location;
import br.com.nomeapp.model.Weather;

public class LeituraJsonBuscaUnica implements LeituraJsonStrategy {

	@Override
	public List<Weather> lerJson(String json) throws JSONException {

		final Weather weather = new Weather();

		final JSONObject jObj = new JSONObject(json);

		final Location loc = new Location();

		final JSONObject coordObj = getObject("coord", jObj);
		loc.setLatitude(getFloat("lat", coordObj));
		loc.setLongitude(getFloat("lon", coordObj));

		final JSONObject sysObj = getObject("sys", jObj);
		loc.setCountry(getString("country", sysObj));
		loc.setSunrise(getInt("sunrise", sysObj));
		loc.setSunset(getInt("sunset", sysObj));
		loc.setCity(getString("name", jObj));
		weather.setLocation(loc);

		final JSONArray jArr = jObj.getJSONArray("weather");

		final JSONObject JSONWeather = jArr.getJSONObject(0);
		weather.getCurrentCondition().setWeatherId(getInt("id", JSONWeather));
		weather.getCurrentCondition().setDescr(getString("description", JSONWeather));
		weather.getCurrentCondition().setCondition(getString("main", JSONWeather));
		weather.getCurrentCondition().setIcon(getString("icon", JSONWeather));

		final JSONObject mainObj = getObject("main", jObj);
		weather.getCurrentCondition().setHumidity(getInt("humidity", mainObj));
		weather.getCurrentCondition().setPressure(getInt("pressure", mainObj));
		weather.getTemperature().setMaxTemp(getFloat("temp_max", mainObj));
		weather.getTemperature().setMinTemp(getFloat("temp_min", mainObj));
		weather.getTemperature().setTemp(getFloat("temp", mainObj));

		final JSONObject wObj = getObject("wind", jObj);
		weather.getWind().setSpeed(getFloat("speed", wObj));
		weather.getWind().setDeg(getFloat("deg", wObj));

		final JSONObject cObj = getObject("clouds", jObj);
		weather.getClouds().setPerc(getInt("all", cObj));

		List<Weather> listaWeathers = new ArrayList<Weather>();

		listaWeathers.add(weather);

		return listaWeathers;
	}

	private static JSONObject getObject(final String tagName, final JSONObject jObj) throws JSONException {
		final JSONObject subObj = jObj.getJSONObject(tagName);
		return subObj;
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
