package br.com.nomeapp.exception;

public class InfraEstruturaException extends AbstractAppException {

	private static final long serialVersionUID = -1512864061816583533L;

	public InfraEstruturaException() {
		super();
	}

	public InfraEstruturaException(final String message) {
		super(message);
	}

	public InfraEstruturaException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public InfraEstruturaException(final Throwable cause) {
		super(cause);
	}
}
