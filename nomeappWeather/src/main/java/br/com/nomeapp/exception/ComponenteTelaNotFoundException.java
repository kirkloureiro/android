package br.com.nomeapp.exception;

public class ComponenteTelaNotFoundException extends AbstractAppException {

	private static final long serialVersionUID = 8208327392836296042L;

	public ComponenteTelaNotFoundException() {
		super();
	}

	public ComponenteTelaNotFoundException(final String message) {
		super(message);
	}

	public ComponenteTelaNotFoundException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public ComponenteTelaNotFoundException(final Throwable cause) {
		super(cause);
	}
}
