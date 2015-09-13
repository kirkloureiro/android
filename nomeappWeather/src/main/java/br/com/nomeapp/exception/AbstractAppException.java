package br.com.nomeapp.exception;

public abstract class AbstractAppException extends Exception {

	private static final long serialVersionUID = 8673370208477611259L;

	public AbstractAppException() {
		super();
	}

	public AbstractAppException(final String message) {
		super(message);
	}

	public AbstractAppException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public AbstractAppException(final Throwable cause) {
		super(cause);
	}
}
