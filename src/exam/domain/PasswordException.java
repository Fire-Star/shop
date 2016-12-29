package exam.domain;

public class PasswordException extends Exception{

	public PasswordException() {
		super();
	}

	public PasswordException(String message, Throwable cause) {
		super(message, cause);
	}

	public PasswordException(String message) {
		super(message);
	}

	public PasswordException(Throwable cause) {
		super(cause);
	}
	
}
