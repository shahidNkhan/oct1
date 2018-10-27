class exceptionsanderrors {

}

class InvalidOptionException extends Exception{
	public InvalidOptionException(String message) {
		super(message);
	}
}

class InvalidInputException extends Exception{
public InvalidInputException(String message) {
	super(message);
}
}

class productnotfound extends Exception{
	public productnotfound(String message) {
		super(message);
	}
}

class categorynotfound extends Exception{
	public categorynotfound(String message) {
		super(message);
	}
}

class Insufficientfunds extends Exception{
	public Insufficientfunds(String message) {
		super(message);
	}
}