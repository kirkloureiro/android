package br.com.nomeapp.util;

import org.easymock.EasyMockSupport;
import org.junit.Before;

public abstract class AbstractClassTest<T> extends EasyMockSupport {

	public abstract T criarObjetoParaTestar();

	@Before
	public abstract void startUp();

}
