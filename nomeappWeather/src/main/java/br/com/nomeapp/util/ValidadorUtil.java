package br.com.nomeapp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;

import android.text.TextUtils;

public final class ValidadorUtil {

	private static final String MENSAGEM_VAZIA = "Mensagem Vazia";

	private static final int[] PESO_BOLETO_BANCARIO = { 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8,
			7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] PESO_CPF = { 11, 10, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] PESO_PIS_NIT = { 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] PESO_CNPJ = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
	private static final int[] PESO_CHAVE_ELETRONICA = { 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8,
			7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

	public static final String VAZIO = "";
	public static final char BRANCO = ' ';
	public static final Integer ZERO = Integer.valueOf(0);

	public static final int DIAS_ATE_QUARTA = 4;
	public static final int DIAS_ATE_QUARTA_DEFAULT = 11;

	private ValidadorUtil() {
		super();
	}

	public static String pegarUltimosDigitos(final Long valor, final int quantidade) {
		return pegarUltimosDigitos(String.valueOf(valor), quantidade);
	}

	public static String pegarUltimosDigitos(final String valor, final int quantidade) {
		if (valor.length() <= quantidade) {
			return valor;
		} else {
			return valor.substring(valor.length() - quantidade);
		}
	}

	public static String pegarPrimeirosDigitos(final String valor, final int quantidade) {
		if (valor.length() <= quantidade) {
			return valor;
		} else {
			return valor.substring(0, quantidade);
		}
	}

	public static boolean isTamanhoMaiorIgual(final String valor, final int min) {
		if (isVazio(valor) || valor.length() < min) {
			return false;
		}
		return true;
	}

	public static boolean isTamanhoMenorQue(final String valor, final int min) {
		if (isVazio(valor) || valor.length() < min) {
			return true;
		}
		return false;
	}

	public static boolean isMenor(final Number valorMenor, final Number valorMaior) {
		if (isVazio(valorMenor) || isVazio(valorMaior)) {
			return false;
		}
		return valorMenor.doubleValue() < valorMaior.doubleValue();
	}

	public static boolean isMaiorIgual(final Number valor1, final Number valor2) {
		if (isNull(valor1) || isNull(valor2)) {
			return false;
		}
		return valor1.doubleValue() >= valor2.doubleValue();
	}

	public static int calcularDigito(final String str, final int[] peso) {
		int soma = 0;
		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	private static int calcularDigitoBoletoBancario(final String str, final int[] peso) {

		int soma = 0;
		int resto = 0;

		for (int indice = str.length() - 1, digito; indice >= 0; indice--) {
			digito = Integer.parseInt(str.substring(indice, indice + 1));
			soma += digito * peso[peso.length - str.length() + indice];
		}

		resto = (soma % 11);

		if (resto >= 2 && resto <= 9) {
			return (11 - resto);
		} else {
			return 1;
		}

	}

	public static boolean isValidBoletoBancario(final String codigoBarra) {
		if (!tamanhoCorreto(codigoBarra, 44)) {
			return false;
		}
		final Integer digito = calcularDigitoBoletoBancario(
				(codigoBarra.substring(0, 4) + codigoBarra.substring(5, 44)), PESO_BOLETO_BANCARIO);

		return codigoBarra.substring(4, 5).equals(digito.toString());
	}

	public static boolean isValidCPF(final String cpf) {
		if (!tamanhoCorreto(cpf, 11)) {
			return false;
		}

		final Integer digito1 = calcularDigito(cpf.substring(0, 9), PESO_CPF);
		final Integer digito2 = calcularDigito(cpf.substring(0, 9) + digito1, PESO_CPF);
		return cpf.equals(cpf.substring(0, 9) + digito1.toString() + digito2.toString());
	}

	public static boolean isValidPisNit(final String pisNit) {

		if (tamanhoCorreto(pisNit, 11)) {
			final Integer digito = calcularDigito(pisNit.substring(0, 10), PESO_PIS_NIT);

			return digito.toString().equals(pisNit.substring(10, 11));
		}

		return false;
	}

	public static boolean isValidCNPJ(final String cnpj) {
		if (!tamanhoCorreto(cnpj, 14)) {
			return false;
		}

		final Integer digito1 = calcularDigito(cnpj.substring(0, 12), PESO_CNPJ);
		final Integer digito2 = calcularDigito(cnpj.substring(0, 12) + digito1, PESO_CNPJ);
		return cnpj.equals(cnpj.substring(0, 12) + digito1.toString() + digito2.toString());
	}

	public static Integer calcularDigitoChaveEletronica(final String chave) {
		return calcularDigito(chave.substring(0, 43), PESO_CHAVE_ELETRONICA);
	}

	public static boolean tamanhoCorreto(final String string, final int tamanho) {
		return (!isNull(string)) && (string.length() == tamanho);
	}

	public static <T> boolean tamanhoCorreto(final T lista[], final int tamanho) {
		return (!isNull(lista)) && (lista.length == tamanho);
	}

	public static boolean tamanhoCorreto(final Collection<?> lista, final int tamanho) {
		return (!isNull(lista)) && (lista.size() == tamanho);
	}

	public static String trim(final String value) {
		if (not(isNull(value))) {
			return value.trim();
		}
		return null;
	}

	public static boolean not(final boolean condicao) {
		return !condicao;
	}

	public static boolean isNull(final Object object) {
		return object == null;
	}

	public static boolean isNotNull(final Object object) {
		return not(isNull(object));
	}

	public static boolean isVazio(final Object obj) {
		if (obj instanceof Collection<?>) {
			return isVazioCollection((Collection<?>) obj);
		}
		if (obj instanceof String) {
			return isVazioString((String) obj);
		}
		if (obj instanceof Number) {
			return isVazioNumber((Number) obj);
		}
		if (obj instanceof Map<?, ?>) {
			return isVazioMap((Map<?, ?>) obj);
		}
		return isNull(obj);
	}

	private static boolean isVazioCollection(final Collection<?> list) {
		return list.isEmpty();
	}

	private static boolean isVazioMap(final Map<?, ?> map) {
		return map.isEmpty();
	}

	private static boolean isVazioString(final String value) {
		return TextUtils.isEmpty(value);
	}

	private static boolean isVazioNumber(final Number number) {
		return number.doubleValue() == 0.0;
	}

	public static boolean isNotVazio(final Object obj) {
		return not(isVazio(obj));
	}

	public static boolean isNotNegativo(final Number object) {
		return not(isNegativo(object));
	}

	public static boolean isEquals(final Object obj1, final Object obj2) {
		if (isSameInstance(obj1, obj2)) {
			return true;
		}
		if (isNull(obj1)) {
			return false;
		}
		if (isNull(obj2)) {
			return false;
		}
		if (!(obj1.getClass().isInstance(obj2))) {
			return false;
		}
		if (!(obj1.equals(obj2))) {
			return false;
		}

		return true;
	}

	public static boolean isNotEquals(final Object obj1, final Object obj2) {
		return not(isEquals(obj1, obj2));
	}

	public static boolean isSameInstance(final Object obj1, final Object obj2) {
		return obj1 == obj2;
	}

	public static void assertNullDominio(final Object obj, final String mensagem) throws Exception {
		if (isNull(obj)) {
			throw new Exception(mensagem);
		}
	}

	public static void assertNotNullDominio(final Object obj, final String mensagem) throws Exception {
		if (isNotNull(obj)) {
			throw new Exception(mensagem);
		}
	}

	public static void assertVazioDominio(final Object obj, final String mensagem) throws Exception {
		if (isVazio(obj)) {
			throw new Exception(mensagem);
		}
	}

	public static void assertNotVazioDominio(final Object obj, final String mensagem) throws Exception {
		if (isNotVazio(obj)) {
			throw new Exception(mensagem);
		}
	}

	public static void assertNegativoDominio(final Number number, final String mensagem) throws Exception {
		assertVazioDominio(number, mensagem);

		if (number.doubleValue() <= 0) {
			throw new Exception(mensagem);
		}
	}

	public static <T> T ifNotNull(final Object object, final T obj) {
		if (not(isNull(object))) {
			return obj;
		}
		return null;
	}

	public static <T> T ifNull(final T object, final T obj) {
		if (isNull(object)) {
			return obj;
		}
		return object;
	}

	public static void assertTrueDominio(final boolean expressao, final String mensagem) throws Exception {
		if (not(expressao)) {
			throw new Exception(mensagem);
		}
	}

	public static void assertFalseDominio(final boolean expressao, final String mensagem) throws Exception {
		if (expressao) {
			throw new Exception(mensagem);
		}
	}

	public static long getSequencial() {
		return Long.parseLong(getSequencialToString());
	}

	public static String getSequencialToString() {
		return pegarUltimosDigitos(String.valueOf(Calendar.getInstance().getTimeInMillis()), 10);
	}

	public static boolean isInteiroPositivo(final String string) {
		if (isVazio(string)) {
			return false;
		} else {
			try {
				return (Long.parseLong(string) >= 0L);
			} catch (final NumberFormatException e) {
				return false;
			}
		}
	}

	public static boolean isNegativo(final Number numero) {
		return isNotVazio(numero) && (numero.doubleValue() < 0);
	}

	public static boolean isPositivo(final Number numero) {
		return isNotVazio(numero) && (numero.doubleValue() > 0);
	}

	public static boolean hasMaisDeUmItem(final Collection<?> collection) {
		return collection.size() > 1;
	}

	public static <T> T getUltimoItem(final Collection<T> collection) {
		final Iterator<T> iterator = collection.iterator();

		T t = iterator.next();

		while (iterator.hasNext()) {
			t = iterator.next();
		}

		return t;
	}

	public static <T> boolean isUltimo(final Collection<T> collection, final T objeto) {
		return isSameInstance(getUltimoItem(collection), objeto);
	}

	public static String converterString(final Object objeto) {
		if (isNull(objeto)) {
			return "";
		}
		return objeto.toString();
	}

	public static String ifVazio(final String msg, final String msgAlternativa) {
		if (isVazio(msg)) {
			return ifVazio(msgAlternativa, MENSAGEM_VAZIA);
		}
		return msg;
	}

	public static boolean hasUmItem(final Collection<?> collection) {
		return collection.size() == 1;
	}

	// Utilizar o m�todo in
	@Deprecated
	public static boolean contem(final String value, final String[] lista) {

		if (isNotVazio(lista)) {
			for (int i = 0; i < lista.length; i++) {
				if (isNotNull(lista[i]) && isEquals(lista[i], value)) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean in(final Object objeto, final Collection<?> list) {

		if (isNull(objeto) || isVazio(list)) {
			return false;
		}

		for (final Object elemento : list) {
			if (isEquals(objeto, elemento)) {
				return true;
			}

		}

		return false;
	}

	public static String removerCaracteres(final String str, final Collection<Integer> posicoes) {

		final StringBuilder builder = new StringBuilder();

		for (int i = 0; i < str.length(); i++) {

			if (not(posicoes.contains(i))) {
				builder.append(str.charAt(i));
			}

		}

		return builder.toString();
	}

	/**
	 * Remove acentuação, caracteres especiais, espaços duplicado, espaços do
	 * inicio e fim da string.
	 */
	public static String normalizar(String str) {

		/** Troca os caracteres acentuados por não acentuados **/
		final String[][] caracteresAcento = { { "Á", "A" }, { "á", "a" }, { "É", "E" }, { "é", "e" }, { "Í", "I" },
				{ "í", "i" }, { "Ó", "O" }, { "ó", "o" }, { "Ú", "U" }, { "ú", "u" }, { "À", "A" }, { "à", "a" },
				{ "È", "E" }, { "è", "e" }, { "Ì", "I" }, { "ì", "i" }, { "Ò", "O" }, { "ò", "o" }, { "Ù", "U" },
				{ "ù", "u" }, { "Â", "A" }, { "â", "a" }, { "Ê", "E" }, { "ê", "e" }, { "Î", "I" }, { "î", "i" },
				{ "Ô", "O" }, { "ô", "o" }, { "Û", "U" }, { "û", "u" }, { "Ä", "A" }, { "ä", "a" }, { "Ë", "E" },
				{ "ë", "e" }, { "Ï", "I" }, { "ï", "i" }, { "Ö", "O" }, { "ö", "o" }, { "Ü", "U" }, { "ü", "u" },
				{ "Ã", "A" }, { "ã", "a" }, { "Õ", "O" }, { "õ", "o" }, { "Ç", "C" }, { "ç", "c" }, };

		for (int i = 0; i < caracteresAcento.length; i++) {
			str = str.replaceAll(caracteresAcento[i][0], caracteresAcento[i][1]);
		}

		/** Troca os caracteres especiais da string por "" **/
		final String[] caracteresEspeciais = { "\\.", ",", "-", ":", "\\(", "\\)", "ª", "\\|", "\\\\", "°" };

		for (int i = 0; i < caracteresEspeciais.length; i++) {
			str = str.replaceAll(caracteresEspeciais[i], "");
		}

		/** Troca os espaços no início por "" **/
		str = str.replaceAll("^\\s+", "");
		/** Troca os espaços no início por "" **/
		str = str.replaceAll("\\s+$", "");
		/** Troca os espaços duplicados, tabulações e etc por " " **/
		str = str.replaceAll("\\s+", " ");

		return str;
	}

	public static Date addDays(final Date date, final int days) {
		final Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days); // minus number would decrement the days
		return cal.getTime();
	}

	public static Date parseToUTCDate(final Date date) {

		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		sdf.setTimeZone(new SimpleTimeZone(SimpleTimeZone.UTC_TIME, "UTC"));
		final String utcTime = sdf.format(date);

		Date dataUtc = null;
		try {
			dataUtc = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).parse(utcTime);
		} catch (final ParseException e) {
			e.printStackTrace();
		}

		return dataUtc;
	}
}