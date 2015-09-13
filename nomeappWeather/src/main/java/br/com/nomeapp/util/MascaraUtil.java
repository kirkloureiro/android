package br.com.nomeapp.util;

import static br.com.nomeapp.util.ValidadorUtil.VAZIO;
import static br.com.nomeapp.util.ValidadorUtil.converterString;
import static br.com.nomeapp.util.ValidadorUtil.ifNull;
import static br.com.nomeapp.util.ValidadorUtil.isNotNull;
import static br.com.nomeapp.util.ValidadorUtil.isNull;
import static br.com.nomeapp.util.ValidadorUtil.isVazio;
import static br.com.nomeapp.util.ValidadorUtil.not;

import java.text.DateFormat;
import java.util.Date;

public class MascaraUtil {

	public static final String MASK_CEP = "#####-###";
	public static final String MASK_TELEFONE = "(##) #### - #####";
	public static final String MASK_CNPJ = "##.###.###/####-##";
	public static final String MASK_CPF = "###.###.###-##";

	public static final String YYYY_MM = "yyyyMM";
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	public static final String DD_MM_YYYY_NO_TOKEN = "ddMMyyyy";

	public static final String retirarMascara(final String valor) {
		return ifNull(valor, VAZIO).replaceAll("[^\\d]", VAZIO);
	}

	public static final String truncar(final String str, final int tamanho) {
		if (not(isNull(str)) && str.length() > tamanho) {
			return str.substring(0, tamanho);
		}
		return str;
	}

	public static final String subString(final String str, final int offset,
			final int tamanho) {
		final int end = offset + tamanho;

		if (not(isNull(str)) && str.length() >= offset) {
			if (str.length() > end) {
				return str.substring(offset, end);
			}
			return str.substring(offset);
		}
		return null;
	}

	public static String formatar(final Date date, final DateFormat formater) {
		if (isNotNull(date)) {
			return formater.format(date);
		}
		return null;
	}

	public static String preencherZerosEsquerda(final String numero,
			final int quantidade) {
		return padLeft(numero, '0', quantidade);
	}

	public static String preencherZerosEsquerda(final Number numero,
			final int quantidade) {
		return padLeft(String.valueOf(numero), '0', quantidade);
	}

	public static String padLeft(final Object valor, final char caractere,
			final int quantidade) {
		return padLeft(String.valueOf(valor), caractere, quantidade);
	}

	public static String padLeft(final String valor, final char caractere,
			final int quantidade) {

		final StringBuilder builder = new StringBuilder();
		int diferenca = 0;

		if (isNull(valor)) {
			return padLeft("", caractere, quantidade);
		}

		if (valor.length() < quantidade) {
			diferenca = quantidade - valor.length();

			for (int i = 0; i < diferenca; i++) {
				builder.append(caractere);
			}
		}

		builder.append(valor);

		return builder.toString();
	}

	public static String preencherZerosDireita(final String numero,
			final int quantidade) {
		return padRigth(numero, '0', quantidade);
	}

	public static String padRigth(final String valor, final char caractere,
			final int quantidade) {
		final StringBuffer buffer = new StringBuffer(converterString(valor));

		for (int i = buffer.length(); i < quantidade; i++) {
			buffer.append(caractere);
		}

		return buffer.toString();
	}

	public static String getTelefoneFormatado(final String ddd,
			final String telefone) {
		return ((!isVazio(ddd) ? ddd : VAZIO) + (isNotNull(telefone) ? telefone
				: VAZIO));
	}
}