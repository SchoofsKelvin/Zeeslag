package exception;

public class DomainException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5327277827698208920L;
	
	public DomainException(){
		super();
	}
	public DomainException(String message){
		super(message);
	}
	
}
