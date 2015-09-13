package br.com.nomeapp.strategy;

import static br.com.nomeapp.util.Constantes.CELSIUS;
import static br.com.nomeapp.util.ValidadorUtil.isNotVazio;
import static br.com.nomeapp.vo.ComponentesVO.getAPPLICATION_CONTEXT;

import java.text.SimpleDateFormat;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import br.com.nomeapp.R;
import br.com.nomeapp.model.Weather;
import br.com.nomeapp.vo.ComponentesVO;

public class TelaBuscaPrevisaoFutura implements ExibicaoTelaStrategy {

	@Override
	public void exibirTela(final List<Weather> listaPrevisoes) {

		final int componenteParent = R.id.parent_activity_main;

		final ListView listView = (ListView) ComponentesVO.getComponenenteTelaPorId(componenteParent,
				R.id.listaPrevisoes);

		listView.setAdapter(new BaseAdapter() {

			private LayoutInflater inflater;

			@Override
			public View getView(final int position, View convertView, final ViewGroup parent) {
				// TODO Auto-generated method stub
				if (inflater == null) {
					inflater = LayoutInflater.from(getAPPLICATION_CONTEXT());
				}
				if (convertView == null) {
					convertView = inflater.inflate(R.layout.previsao_row, parent, false);
				}

				final Weather weather = listaPrevisoes.get(position);

				final ImageView image = ((ImageView) convertView.findViewById(R.id.imagem));
				final TextView txtDataPrevisao = ((TextView) convertView.findViewById(R.id.dataPrevisao));
				final TextView txtTempMin = ((TextView) convertView.findViewById(R.id.tempMin));
				final TextView txtTempMax = ((TextView) convertView.findViewById(R.id.tempMax));

				image.setImageBitmap(weather.getImagemBitmap());
				txtDataPrevisao.setText(new SimpleDateFormat("dd/MM/yyyy").format(weather.getDataPrevisao()));
				txtTempMin.setText(String.valueOf(weather.getTemperature().getMinTemp(CELSIUS)));
				txtTempMax.setText(String.valueOf(weather.getTemperature().getMaxTemp(CELSIUS)));

				return convertView;
			}

			@Override
			public int getCount() {

				if (isNotVazio(listaPrevisoes)) {
					return listaPrevisoes.size();
				}
				return 0;
			}

			@Override
			public Object getItem(final int position) {
				return listaPrevisoes.get(position);
			}

			@Override
			public long getItemId(final int position) {
				// TODO Auto-generated method stub
				return 0;
			}
		});
	}
}
