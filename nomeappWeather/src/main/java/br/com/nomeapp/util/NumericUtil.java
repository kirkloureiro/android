package br.com.nomeapp.util;

import static br.com.nomeapp.util.ValidadorUtil.isNotNull;
import static br.com.nomeapp.util.ValidadorUtil.isNull;
import static br.com.nomeapp.util.ValidadorUtil.isVazio;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumericUtil {

	public static final int SCALE = 6;
	public static final int SCALE_MONETARIA = 2;
	public static final int SCALE_TOTAL = 10;

	public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	public static final RoundingMode ROUNDING_MODE_DOWN = RoundingMode.DOWN;
	public static final BigDecimal CEM = new BigDecimal("100");

	// ESCALA NORMAL
	public static BigDecimal multiplicar(final BigDecimal multiplicando,
			final BigDecimal multiplicador) {
		return multiplicando.multiply(multiplicador).setScale(SCALE,
				ROUNDING_MODE);
	}

	public static BigDecimal dividir(final BigDecimal dividendo,
			final BigDecimal divisor) {
		return dividendo.divide(divisor, SCALE, ROUNDING_MODE);
	}

	public static BigDecimal porcentagem(final BigDecimal base,
			final BigDecimal porcentagem) {
		final BigDecimal valorPorcentagem = porcentagem.divide(CEM, SCALE,
				ROUNDING_MODE);
		return multiplicar(base, valorPorcentagem);
	}

	public static BigDecimal somar(final BigDecimal valor1,
			final BigDecimal valor2) {
		return valor1.add(valor2).setScale(SCALE, ROUNDING_MODE);
	}

	public static BigDecimal zero() {
		return BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);
	}

	public static BigDecimal subtrair(final BigDecimal valor1,
			final BigDecimal valor2) {
		return valor1.subtract(valor2).setScale(SCALE, ROUNDING_MODE);
	}

	// ESCALA TOTAL
	public static BigDecimal multiplicarTotal(final BigDecimal multiplicando,
			final BigDecimal multiplicador) {
		return multiplicando.multiply(multiplicador).setScale(SCALE,
				ROUNDING_MODE);
	}

	public static BigDecimal dividirTotal(final BigDecimal dividendo,
			final BigDecimal divisor) {
		return dividendo.divide(divisor, SCALE, ROUNDING_MODE);
	}

	public static BigDecimal porcentagemTotal(final BigDecimal base,
			final BigDecimal porcentagem) {
		final BigDecimal valorPorcentagem = porcentagem.divide(CEM, SCALE,
				ROUNDING_MODE);
		return multiplicar(base, valorPorcentagem);
	}

	public static BigDecimal somarTotal(final BigDecimal valor1,
			final BigDecimal valor2) {
		return valor1.add(valor2).setScale(SCALE, ROUNDING_MODE);
	}

	public static BigDecimal zeroTotal() {
		return BigDecimal.ZERO.setScale(SCALE, ROUNDING_MODE);
	}

	public static BigDecimal subtrairTotal(final BigDecimal valor1,
			final BigDecimal valor2) {
		return valor1.subtract(valor2).setScale(SCALE, ROUNDING_MODE);
	}

	// ESCALA MONET�RIA
	public static BigDecimal dividirMonetario(final BigDecimal dividando,
			final BigDecimal divisor) {
		return dividando.divide(divisor, SCALE_MONETARIA, ROUNDING_MODE);
	}

	public static BigDecimal multiplicarMonetario(
			final BigDecimal multiplicando, final BigDecimal multiplicador) {
		return multiplicando.multiply(multiplicador).setScale(SCALE_MONETARIA,
				ROUNDING_MODE);
	}

	public static BigDecimal multiplicarMonetarioRoundDown(
			final BigDecimal multiplicando, final BigDecimal multiplicador) {
		return multiplicando.multiply(multiplicador).setScale(SCALE_MONETARIA,
				ROUNDING_MODE_DOWN);
	}

	public static BigDecimal porcentagemMonetario(final BigDecimal base,
			final BigDecimal porcentagem) {
		final BigDecimal valorPorcentagem = porcentagem.divide(CEM,
				SCALE_TOTAL, ROUNDING_MODE);
		return multiplicar(base, valorPorcentagem).setScale(SCALE_MONETARIA,
				ROUNDING_MODE);
	}

	public static BigDecimal somarMonetario(final BigDecimal valor1,
			final BigDecimal valor2) {
		return valor1.add(valor2).setScale(SCALE_MONETARIA, ROUNDING_MODE);
	}

	public static BigDecimal zeroMonetario() {
		return BigDecimal.ZERO.setScale(SCALE_MONETARIA, ROUNDING_MODE);
	}

	public static BigDecimal subtrairMonetario(final BigDecimal valor1,
			final BigDecimal valor2) {
		return valor1.subtract(valor2).setScale(SCALE_MONETARIA, ROUNDING_MODE);
	}

	public static boolean isMaiorQue(final Number maior, final Number menor) {
		return isMaiorQue(maior, menor, null);
	}

	public static boolean isMaiorQue(final Number maior, final Number menor,
			final Integer scale) {
		return isMaiorQue(maior, menor, scale, RoundingMode.FLOOR);
	}

	public static boolean isMaiorQue(final Number maior, final Number menor,
			final Integer scale, final RoundingMode round) {
		if (isNull(maior) || isNull(menor))
			return maior == menor;
		return compare(maior, menor, scale, round) > 0;
	}

	public static boolean isMaiorQueOuIgual(final Number maior,
			final Number menor) {
		return isMaiorQueOuIgual(maior, menor, null);
	}

	public static boolean isMaiorQueOuIgual(final Number maior,
			final Number menor, final Integer scale) {
		return isMaiorQueOuIgual(maior, menor, scale, RoundingMode.FLOOR);
	}

	public static boolean isMaiorQueOuIgual(final Number maior,
			final Number menor, final Integer scale, final RoundingMode round) {
		if (isNull(maior) || isNull(menor))
			return maior == menor;
		return compare(maior, menor, scale, round) > 0
				|| compare(maior, menor, scale, round) == 0;
	}

	public static boolean isMenorQue(final Number menor, final Number maior) {
		return isMenorQue(menor, maior, null);
	}

	public static boolean isMenorQue(final Number menor, final Number maior,
			final Integer scale) {
		return isMenorQue(menor, maior, scale, RoundingMode.FLOOR);
	}

	public static boolean isMenorQue(final Number menor, final Number maior,
			final Integer scale, final RoundingMode round) {
		if (isNull(maior) || isNull(menor))
			return maior == menor;
		return compare(menor, maior, scale, round) < 0;
	}

	public static boolean isMenorQueOuIgual(final Number menor,
			final Number maior) {
		return isMenorQueOuIgual(menor, maior, null);
	}

	public static boolean isMenorQueOuIgual(final Number menor,
			final Number maior, final Integer scale) {
		return isMenorQueOuIgual(menor, maior, scale, RoundingMode.FLOOR);
	}

	public static boolean isMenorQueOuIgual(final Number menor,
			final Number maior, final Integer scale, final RoundingMode round) {
		if (isNull(maior) || isNull(menor))
			return maior == menor;
		return compare(menor, maior, scale, round) < 0
				|| compare(menor, maior, scale, round) == 0;
	}

	public static boolean isEntre(final Number numero, final Number inferior,
			final Number superior) {
		return isEntre(numero, inferior, superior, null);
	}

	public static boolean isEntre(final Number numero, final Number inferior,
			final Number superior, final Integer scale) {
		return isEntre(numero, inferior, superior, scale, RoundingMode.FLOOR);
	}

	public static boolean isEntre(final Number numero, final Number inferior,
			final Number superior, final Integer scale, final RoundingMode round) {
		return isMaiorQue(numero, inferior, scale, round)
				&& isMenorQue(numero, superior, scale, round);
	}

	public static boolean isIgual(final Number numero, final Number numero2) {
		return isIgual(numero, numero2, null);
	}

	public static boolean isIgual(final Number numero, final Number numero2,
			final Integer scale) {
		return isIgual(numero, numero2, scale, RoundingMode.FLOOR);
	}

	public static boolean isIgual(final Number numero, final Number numero2,
			final Integer scale, final RoundingMode round) {
		if (isNull(numero) || isNull(numero2))
			return numero == numero2;

		return compare(numero, numero2, scale, round) == 0;
	}

	public static BigDecimal validaZero(final BigDecimal numero) {
		if (isVazio(numero)) {
			return zero();
		}
		return numero;
	}

	public static BigDecimal validaZeroMonetario(final BigDecimal numero) {
		if (isVazio(numero)) {
			return zeroMonetario();
		}
		return numero;
	}

	private static int compare(final Number numero, final Number numero2,
			final Integer scale, final RoundingMode round) {
		BigDecimal valor = new BigDecimal(numero.doubleValue());
		BigDecimal valor2 = new BigDecimal(numero2.doubleValue());

		if (isNotNull(scale)) {
			valor = valor.setScale(scale, round);
			valor2 = valor2.setScale(scale, round);
		}

		return valor.compareTo(valor2);
	}
}