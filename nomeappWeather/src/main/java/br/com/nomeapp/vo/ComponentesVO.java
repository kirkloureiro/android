package br.com.nomeapp.vo;

import static br.com.nomeapp.util.ValidadorUtil.isNotVazio;
import static br.com.nomeapp.util.ValidadorUtil.isNull;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import br.com.nomeapp.exception.ComponenteTelaNotFoundException;

public class ComponentesVO {

	private static Map<Number, View> LISTA_COMPONENTES_TELA;

	private static Context APPLICATION_CONTEXT;

	public ComponentesVO() {
		super();
	}

	public static View addComponenteTela(final View view) {

		if (isNull(LISTA_COMPONENTES_TELA)) {
			LISTA_COMPONENTES_TELA = new HashMap<Number, View>();
		}

		LISTA_COMPONENTES_TELA.put(view.getId(), view);

		return view;
	}

	private static View getComponenenteTelaPorId(final int idComponenteTela) throws ComponenteTelaNotFoundException {

		if (isNotVazio(LISTA_COMPONENTES_TELA)) {
			return assertNotNullComponenteTela(LISTA_COMPONENTES_TELA.get(idComponenteTela));
		}
		return null;
	}

	public static Map<Number, View> getLISTA_COMPONENTES_TELA() {
		return LISTA_COMPONENTES_TELA;
	}

	public static Context getAPPLICATION_CONTEXT() {
		return APPLICATION_CONTEXT;
	}

	public static void setAPPLICATION_CONTEXT(final Context aPPLICATION_CONTEXT) {
		APPLICATION_CONTEXT = aPPLICATION_CONTEXT;
	}

	public static View assertNotNullComponenteTela(final View view) throws ComponenteTelaNotFoundException {
		if (isNull(view)) {
			throw new ComponenteTelaNotFoundException("Componente nao encontrado!");
		}
		return view;
	}

	public static View getComponenenteTelaPorId(final int idParentComponente, final int idComponenteTela) {

		try {
			return assertNotNullComponenteTela(getComponenenteTelaPorId(idComponenteTela));
		} catch (final ComponenteTelaNotFoundException e) {
			return addComponenteTela(getComponenenteParent(idParentComponente).findViewById(idComponenteTela));
		}
	}

	private static ViewGroup getComponenenteParent(final int idParentComponente) {

		ViewGroup parentMaster = null;
		try {
			parentMaster = (ViewGroup) getComponenenteTelaPorId(idParentComponente);
		} catch (final ComponenteTelaNotFoundException e) {
			Log.e("Error", "Parent não encontrado", e);
		}
		return parentMaster;
	}
}
