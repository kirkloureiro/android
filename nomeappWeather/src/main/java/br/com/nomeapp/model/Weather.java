package br.com.nomeapp.model;

import static br.com.nomeapp.util.NumericUtil.isMaiorQue;
import static br.com.nomeapp.util.ValidadorUtil.isNotNull;

import java.math.BigDecimal;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Weather {

	private Location location;
	private CurrentCondition currentCondition;
	private Temperature temperature;
	private Wind wind;
	private Rain rain;
	private Snow snow;
	private Clouds clouds;
	private Date dataPrevisao;

	private byte[] iconData;

	public Weather() {
		super();
		currentCondition = new CurrentCondition();
		temperature = new Temperature();
		wind = new Wind();
		rain = new Rain();
		snow = new Snow();
		clouds = new Clouds();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(final Location location) {
		this.location = location;
	}

	public CurrentCondition getCurrentCondition() {
		return currentCondition;
	}

	public void setCurrentCondition(final CurrentCondition currentCondition) {
		this.currentCondition = currentCondition;
	}

	public Temperature getTemperature() {
		return temperature;
	}

	public void setTemperature(final Temperature temperature) {
		this.temperature = temperature;
	}

	public Wind getWind() {
		return wind;
	}

	public void setWind(final Wind wind) {
		this.wind = wind;
	}

	public Rain getRain() {
		return rain;
	}

	public void setRain(final Rain rain) {
		this.rain = rain;
	}

	public Snow getSnow() {
		return snow;
	}

	public void setSnow(final Snow snow) {
		this.snow = snow;
	}

	public Clouds getClouds() {
		return clouds;
	}

	public void setClouds(final Clouds clouds) {
		this.clouds = clouds;
	}

	public byte[] getIconData() {
		return iconData;
	}

	public void setIconData(final byte[] iconData) {
		this.iconData = iconData;
	}

	public Bitmap getImagemBitmap() {

		if (isNotNull(this.iconData) && isMaiorQue(this.iconData.length, BigDecimal.ZERO)) {

			final Bitmap img = BitmapFactory.decodeByteArray(this.iconData, 0, this.iconData.length);

			return img;

		}
		return null;
	}

	public Date getDataPrevisao() {
		return dataPrevisao;
	}

	public void setDataPrevisao(Date dataPrevisao) {
		this.dataPrevisao = dataPrevisao;
	}
}
