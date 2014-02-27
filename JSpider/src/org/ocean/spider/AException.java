package org.ocean.spider;

public class AException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1932925825934863685L;

	private ExceptionType type;
	
	public AException(ExceptionType type,String msg){
		super(msg);
		this.type = type;
	}
	
	public AException(ExceptionType type,String msg, Throwable ex){
		super(msg ,ex);
		this.type = type;
	}

	public ExceptionType getType() {
		return type;
	}
	
}
