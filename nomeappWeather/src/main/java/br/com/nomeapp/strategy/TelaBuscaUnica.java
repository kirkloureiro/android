package br.com.nomeapp.strategy;

import static br.com.nomeapp.util.Constantes.CELSIUS;
import static br.com.nomeapp.util.ValidadorUtil.isVazio;

import java.util.List;

import android.widget.ImageView;
import android.widget.TextView;
import br.com.nomeapp.R;
import br.com.nomeapp.model.Weather;
import br.com.nomeapp.vo.ComponentesVO;

public class TelaBuscaUnica implements ExibicaoTelaStrategy {

	@Override
	public void exibirTela(final List<Weather> listaPrevisoes) {

		final int componenteParent = R.id.parent_activity_main;

		if (isVazio(listaPrevisoes)) {
			((TextView) ComponentesVO.getComponenenteTelaPorId(componenteParent, R.id.cityText))
					.setText("Cidade não encontrada!");
			return;
		}

		final Weather weather = listaPrevisoes.get(0);

		((ImageView) ComponentesVO.getComponenenteTelaPorId(componenteParent, R.id.condIcon)).setImageBitmap(weather
				.getImagemBitmap());

		((TextView) ComponentesVO.getComponenenteTelaPorId(componenteParent, R.id.cityText)).setText(weather
				.getLocation().getCity() + "," + weather.getLocation().getCountry());

		((TextView) ComponentesVO.getComponenenteTelaPorId(componenteParent, R.id.condDescr)).setText(weather
				.getCurrentCondition().getCondition() + "(" + weather.getCurrentCondition().getDescr() + ")");

		((TextView) ComponentesVO.getComponenenteTelaPorId(componenteParent, R.id.temp)).setText(""
				+ Math.round((weather.getTemperature().getTemp(CELSIUS))) + "°C");

		((TextView) ComponentesVO.getComponenenteTelaPorId(componenteParent, R.id.hum)).setText(""
				+ weather.getCurrentCondition().getHumidity() + "%");

		((TextView) ComponentesVO.getComponenenteTelaPorId(componenteParent, R.id.press)).setText(""
				+ weather.getCurrentCondition().getPressure() + " hPa");

		((TextView) ComponentesVO.getComponenenteTelaPorId(componenteParent, R.id.windSpeed)).setText("speed: "
				+ weather.getWind().getSpeed() + " mps, direction: " + weather.getWind().getDeg() + "°");

	}
}
