package br.com.nomeapp.model;

import static br.com.nomeapp.util.Constantes.CELSIUS;
import static br.com.nomeapp.util.Constantes.FAHRENHEIT;

public class Temperature {

	private float temp;
	private float minTemp;
	private float maxTemp;

	public Temperature() {
		super();
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(final float temp) {
		this.temp = temp;
	}

	public float getMinTemp() {
		return minTemp;
	}

	public void setMinTemp(final float minTemp) {
		this.minTemp = minTemp;
	}

	public float getMaxTemp() {
		return maxTemp;
	}

	public void setMaxTemp(final float maxTemp) {
		this.maxTemp = maxTemp;
	}

	public float getTemp(final String unitType) {
		return convertTemperature(unitType, temp);
	}

	public float getMaxTemp(final String unitType) {
		return convertTemperature(unitType, maxTemp);
	}

	public float getMinTemp(final String unitType) {
		return convertTemperature(unitType, minTemp);
	}

	private float convertTemperature(final String unitType, final float temperatureKelvin) {

		final float celsius = Math.round(temperatureKelvin - 273.15);

		if (unitType.equals(CELSIUS)) {
			return celsius;
		}

		if (unitType.equals(FAHRENHEIT)) {
			final float fahrenheit = Math.round(celsius * 9 / 5 + 32);
			return fahrenheit;
		}

		return temperatureKelvin;
	}
}
