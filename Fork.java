
	/** creates a Fork and its state, whether used or unused
	 * 
	 */
public class Fork {
	private boolean currentlyUsed;
	
	public Fork() 
	{
		currentlyUsed = false; 
	}

	/** Returns whether this fork is being used.
	 * @return current state of fork usage
	 */
	public synchronized boolean isUsed()
	{
		return currentlyUsed;
	}
	
	/** Toggles usage of this fork
	 */
	public synchronized void setUse()
	{	
		currentlyUsed = !currentlyUsed;
	}

}
