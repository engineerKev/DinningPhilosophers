/** Creates a philosopher
	 * @param leftyOrRighty used to tell whether the Philosopher is left or right handed
	 * @param tableIamSittingAt used as a link to communicate with table class
	 * @param seat place of philosopher at table
	 * @param me reference for output, used to tell Philsopher appart from the others at table
	 */
public class Philo extends Thread {
	private char picksUpFirstForkWith;
	private Table myTable;
	private int mySeat;
	private int whoIam;
	public Philo (char leftyOrRighty, Table tableIamSittingAt, int seat, int me)
	{
		picksUpFirstForkWith = leftyOrRighty;
		myTable = tableIamSittingAt;
		mySeat = seat;
		whoIam = me;
	}
	/** "main " of thread, lists actions thread will commit to before terminating
	 * 
	 */
	public void run()
	{
		int i = 0;
		while(i < 4)
		{
			think();
			myTable.checkThenAllowToEat(mySeat);
			i += 1;
		}
	}
	/** getter 
	 * @return retrives ID of philsopher
	 */
	public int whoamI()
	{
		return whoIam;
	}
	/** getter, returns philosopher's "handedness"(whether lefty or righty)
	 * @return returns the order to pick up forks
	 */
	public char rightyOrLefty()
	{
		return picksUpFirstForkWith;
	}
	/** tells philosopher how long to think for, if interrupted prints message
	 * 
	 */
	public void think() 
	{
		try{
			Thread.currentThread();
			Thread.sleep((long)(3000*Math.random()));
		}catch (InterruptedException e){
			System.out.println("Interrupted, while thinking!");
		}
	}	/** tells philosopher how long to eat for, if interrupted, prints message
	 * 
	 */
	public void eating()
	{
		try{
			Thread.currentThread();
			Thread.sleep((long)(6000*Math.random()));
		}catch (InterruptedException e){
			System.out.println("Interrupted, while eating!");
		}
	}
	

}
