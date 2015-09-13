package br.com.nomeapp.service.api;

import static br.com.nomeapp.util.ValidadorUtil.parseToUTCDate;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import br.com.nomeapp.exception.InfraEstruturaException;
import br.com.nomeapp.factory.APIExternaPrevisaoTempo;
import br.com.nomeapp.model.Weather;
import br.com.nomeapp.service.WeatherFiltroParametros;
import br.com.nomeapp.strategy.ExibicaoTelaStrategy;
import br.com.nomeapp.strategy.TelaBuscaPrevisaoFutura;
import br.com.nomeapp.strategy.TelaBuscaUnica;
import br.com.nomeapp.vo.ComponentesVO;

public class OpenWeatherMapAPI implements APIExternaPrevisaoTempo {

	protected static String BASE_URL_BUSCA_UNICA = "http://api.openweathermap.org/data/2.5/weather?{0}";
	protected static String BASE_URL_HISTORICO = "http://api.openweathermap.org/data/2.5/history/city?{0}";
	protected static String BASE_URL_PREVISAO_FUTURA = "http://api.openweathermap.org/data/2.5/forecast/daily?{0}";

	private String getWeatherData(final BaseUrl baseUrl, final String urlQuery) throws InfraEstruturaException {

		final String url = MessageFormat.format(baseUrl.getUrlBase(), urlQuery);

		return getWeatherByUrl(url);
	}

	private String getWeatherByUrl(final String url) throws InfraEstruturaException {

		HttpURLConnection con = null;
		InputStream is = null;
		try {

			con = (HttpURLConnection) (new URL(url)).openConnection();
			con.setRequestMethod("GET");
			con.setDoInput(true);
			con.setDoOutput(true);
			con.connect();

			final StringBuffer buffer = new StringBuffer();
			is = con.getInputStream();
			final BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\r\n");
			}

			is.close();
			con.disconnect();
			return buffer.toString();

		} catch (final MalformedURLException e) {
			throw new InfraEstruturaException("URL Informada Invalida.", e);
		} catch (final Exception e) {
			throw new InfraEstruturaException("Ocorreu um erro ao obter os dados.", e);
		} finally {
			try {
				is.close();
			} catch (final Throwable t) {
			}
			try {
				con.disconnect();
			} catch (final Throwable t) {
			}
		}
	}

	private byte[] getImage(final String code) {

		InputStream is = null;
		try {

			is = ComponentesVO.getAPPLICATION_CONTEXT().getAssets().open(code.concat(".png"));

			final byte[] buffer = new byte[1024];
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while (is.read(buffer) != -1) {
				baos.write(buffer);
			}

			return baos.toByteArray();
		} catch (final Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (final Throwable t) {
			}
		}

		return null;
	}

	private String encodeString(final String valor) throws InfraEstruturaException {
		try {
			return URLEncoder.encode(valor, "UTF-8");
		} catch (final UnsupportedEncodingException e) {
			throw new InfraEstruturaException("Não foi possivel converter o valor '".concat(valor).concat(
					"' para UTF-8"), e);
		}
	}

	@Override
	public List<Weather> buscarPrevisaoTempo(final WeatherFiltroParametros filtro) throws InfraEstruturaException {

		String weatherDataJson;

		final String urlQuery = montarFiltroUrlQuery(filtro);

		final BaseUrl baseUrl = getBaseUrl(filtro);

		weatherDataJson = this.getWeatherData(baseUrl, urlQuery);

		try {

			final List<Weather> listaPrevisoes = baseUrl.lerJsonStrategy(weatherDataJson);

			for (final Weather weather : listaPrevisoes) {

				weather.setIconData(this.getImage(weather.getCurrentCondition().getIcon()));
			}

			return listaPrevisoes;

		} catch (final JSONException e) {
			throw new InfraEstruturaException("Não foi possivel ler Json retornado.", e);
		}
	}

	private String montarFiltroUrlQuery(final WeatherFiltroParametros filtro) throws InfraEstruturaException {

		final StringBuilder sb = new StringBuilder();

		if (filtro.hasCidade()) {
			sb.append("q=").append(encodeString(filtro.getCidade()));
		}

		if (filtro.hasLinguagem()) {
			sb.append("&lang=").append(encodeString(filtro.getLinguagem()));
		}

		if (filtro.hasTipo()) {
			sb.append("&type=").append(encodeString(filtro.getTipo()));
		}

		if (filtro.hasDataInicio()) {
			final Date dataInicio = parseToUTCDate(filtro.getDataInicio());
			final String dataLongToString = String.valueOf(dataInicio.getTime());
			sb.append("&start=").append(dataLongToString);
		}

		if (filtro.hasDataFim()) {
			final Date dataFim = parseToUTCDate(filtro.getDataFim());
			final String dataLongToString = String.valueOf(dataFim.getTime());
			sb.append("&end=").append(dataLongToString);
		}

		if (filtro.hasQuantidadeDias()) {
			sb.append("&cnt=").append(filtro.getQuantidadeDias().toString());
		}

		return sb.toString();
	}

	private BaseUrl getBaseUrl(final WeatherFiltroParametros filtro) {

		if (filtro.hasExibicaoTelaStrategy()) {

			if (filtro.getExibicaoTelaStrategy() instanceof TelaBuscaUnica) {
				return new BaseUrl(new LeituraJsonBuscaUnica(), BASE_URL_BUSCA_UNICA);
			}
			if (filtro.getExibicaoTelaStrategy() instanceof TelaBuscaPrevisaoFutura) {
				return new BaseUrl(new LeituraJsonBuscaPrevisaoFutura(), BASE_URL_PREVISAO_FUTURA);
			}
			// TODO: CRIAR ESTRATEGIAS PARA OUTRAS TELAS
			if (filtro.getExibicaoTelaStrategy() instanceof ExibicaoTelaStrategy) {
				return new BaseUrl(null, BASE_URL_HISTORICO);
			}
		}
		return new BaseUrl(new LeituraJsonBuscaUnica(), BASE_URL_BUSCA_UNICA);
	}
}
