package br.com.nomeapp.activity;

import static br.com.nomeapp.vo.ComponentesVO.addComponenteTela;
import static br.com.nomeapp.vo.ComponentesVO.setAPPLICATION_CONTEXT;

import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import br.com.nomeapp.R;
import br.com.nomeapp.facade.WeatherFacade;
import br.com.nomeapp.facade.WeatherFacadeImpl;
import br.com.nomeapp.vo.ComponentesVO;

public class MainActivity extends Activity {

	public WeatherFacade weatherFacade;

	public MainActivity() {
		super();
		injetaObjetosNecessarios();
	}

	@Override
	protected void onCreate(final Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		final String city = "sao paulo,BR";

		setAPPLICATION_CONTEXT(getApplicationContext());

		adicionarComponentesTela();

		weatherFacade.buscarPrevisaoTempoPorCidade(city, Locale.getDefault().getLanguage());

		weatherFacade.buscarPrevisaoProximosDias(city, 7);
	}

	@Override
	public boolean onCreateOptionsMenu(final Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void buscarPrevisaoTempo(final View view) {

		final String cidade = ((EditText) ComponentesVO.getComponenenteTelaPorId(R.id.parent_activity_main,
				R.id.campo_digite_cidade)).getText().toString();

		setAPPLICATION_CONTEXT(getApplicationContext());

		weatherFacade.buscarPrevisaoTempoPorCidade(cidade, Locale.getDefault().getLanguage());

		weatherFacade.buscarPrevisaoProximosDias(cidade, 7);
	}

	private void adicionarComponentesTela() {
		addComponenteTela(findViewById(R.id.parent_activity_main));
	}

	private void injetaObjetosNecessarios() {
		setWeatherFacade(new WeatherFacadeImpl());
	}

	public void setWeatherFacade(final WeatherFacade weatherFacade) {
		this.weatherFacade = weatherFacade;
	}
}
