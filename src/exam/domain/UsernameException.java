package exam.domain;

public class UsernameException extends Exception{

	public UsernameException() {
		super();
	}

	public UsernameException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameException(String message) {
		super(message);
	}

	public UsernameException(Throwable cause) {
		super(cause);
	}
	
}
