package tp1.control;

/**
 * Represents the result of a command execution.
 * 
 * @param success      stores if the command was executed successfully.
 * @param draw         stores if the game must be draw.
 * @param errorMessage Generated error message generated during the command
 *                     execution
 */
public record ExecutionResult(boolean success, boolean draw, String errorMessage) {
	/**
	 * @param draw stores if the game must be draw.
	 */
	public ExecutionResult(boolean draw) {
		this(true, draw, null);
	}

	/**
	 * @param errorMessage generated error message.
	 */
	public ExecutionResult(String errorMessage) {
		this(false, false, errorMessage);
	}
}

