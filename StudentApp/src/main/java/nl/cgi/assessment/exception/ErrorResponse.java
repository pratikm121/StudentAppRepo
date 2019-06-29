package nl.cgi.assessment.exception;

public class ErrorResponse {
	
	private String errorMessage;
	private int status;
	private long timestamp;
	
	public ErrorResponse() {		
	}
	
	public ErrorResponse(String errorMessage, int status, long timestamp) {
		super();
		this.errorMessage = errorMessage;
		this.status = status;
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "ErrorResponse [errorMessage=" + errorMessage + ", status=" + status + ", timestamp=" + timestamp + "]";
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
}
