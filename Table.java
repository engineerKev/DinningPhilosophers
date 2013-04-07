import java.util.ArrayList;
//import java.util.Random;

public class Table {
	private ArrayList<Philo> philosophers;
	private ArrayList<Fork> forksOnTable;

	/** Creates a table for philosophers to sit at
	 * @param seated The number of seats and forks at this table
	 */
	
	public Table(int seated) {
		philosophers = new ArrayList<Philo>(seated);
		forksOnTable = new ArrayList<Fork>(seated);
		
	}
	/** getter to pick out a philosopher from the group in table
	 * @param thisPhilosopher index for philosophers ArrayList
	 */
	public Philo currentPhilosopher(int thisPhilosopher)
	{
		return philosophers.get(thisPhilosopher);
	}
	/** populates philosophers ArrayList
	 * @param newestPhilosopher philsopher to be added to philosophers ArrayList
	 */
	public void addPhilosopher(Philo newestPhilosopher)
	{
		philosophers.add(newestPhilosopher);
	}
	/** populates the ArrayList forksOnTable
	 * @param newFork most recent fork added to Table's forksOnTable ArrayList
	 */
	public void addFork(Fork newFork)
	{
		forksOnTable.add(newFork);
	}
	/** Follow up actions which the philosopher will do after finished eating
	 * @param philosopherWhoJustAte index of philosopher who most recently finished eating
	 */
	public void doneEating(int philosopherWhoJustAte)
	{
		int myName;
		System.out.print("Philosopher(");
		myName = (philosophers.get(philosopherWhoJustAte)).whoamI();
		System.out.print(myName);
		System.out.println(")done eating. Time to think!");
		philosophers.get(philosopherWhoJustAte).think();
	}
	/** Checks to see if the Philosopher can eat, by seeing if their forks are available
	 * @param philosopherWantsToEat index to access philosopher from philosophers ArrayList
	 */
	public void checkThenAllowToEat(int philosopherWantsToEat)
	{
		int myName;
		int totalAtTable = philosophers.size();
		int leftFork;
		int rightFork;
		char philosopherLeftyOrRighty = (philosophers.get(philosopherWantsToEat)).rightyOrLefty();
		if(philosopherLeftyOrRighty == 'R')
		{
			rightFork = philosopherWantsToEat;
			if( !(forksOnTable.get(rightFork).isUsed()) )
			{
				System.out.print("Philosopher(");
				myName = (philosophers.get(philosopherWantsToEat)).whoamI();
				System.out.print(myName);
				System.out.println(")I have my right fork!(");
				System.out.print(rightFork);
				System.out.println(")");
				forksOnTable.get(rightFork).setUse();
				leftFork = (rightFork+1)%totalAtTable;
				if ( !(forksOnTable.get(leftFork).isUsed()) )
				{
					System.out.print("Philosopher(");
					myName = philosophers.get(philosopherWantsToEat).whoamI();
					System.out.print(myName);
					System.out.println(")got my left fork!(");
					System.out.print(leftFork);
					System.out.println(")");
					forksOnTable.get(leftFork).setUse();
					philosophers.get(philosopherWantsToEat).eating();
					forksOnTable.get(leftFork).setUse();
					forksOnTable.get(rightFork).setUse();
					doneEating(philosopherWantsToEat);
				}
				if(forksOnTable.get(rightFork).isUsed())
				{
					forksOnTable.get(rightFork).setUse();
					philosophers.get(philosopherWantsToEat).think();
				}
			}
			
		}
		else 
		{
			leftFork = (philosopherWantsToEat+1)%totalAtTable;
			if( !(forksOnTable.get(leftFork).isUsed()) )
			{
				System.out.print("Philosopher(");
				myName = philosophers.get(philosopherWantsToEat).whoamI();
				System.out.print(myName);
				System.out.print(")got my left fork!(");
				System.out.print(leftFork);
				System.out.println(")");
				forksOnTable.get(leftFork).setUse();
				rightFork = philosopherWantsToEat;
				if( !(forksOnTable.get(rightFork).isUsed()) )
				{
					System.out.print("Philosopher(");
					myName = philosophers.get(philosopherWantsToEat).whoamI();
					System.out.print(myName);
					System.out.println(")I have my right fork!(");
					System.out.print(rightFork);
					System.out.println(")");
					forksOnTable.get(rightFork).setUse();
					philosophers.get(philosopherWantsToEat).eating();
					forksOnTable.get(rightFork).setUse();
					forksOnTable.get(leftFork).setUse();
					doneEating(philosopherWantsToEat);
				}
				if(forksOnTable.get(leftFork).isUsed())
				{
					forksOnTable.get(leftFork).setUse();
					philosophers.get(philosopherWantsToEat).think();
					
				}
			}
		}
	}
	/** 
	 * @param args
	 */
	public static void main(String[] args) {
		char handedness = 'R';
		int totalSeated = 10;
		Table dinningTable = new Table(totalSeated);
		for (int i = 0; i < totalSeated; i++)
		{
			if (handedness == 'R')
			{
				Philo aPhilosopher = new Philo(handedness, dinningTable, i, (i+1));
				dinningTable.addPhilosopher(aPhilosopher);
				handedness = 'L';
			}
			else
			{
				Philo aPhilosopher = new Philo(handedness, dinningTable, i, (i+1));
				dinningTable.addPhilosopher(aPhilosopher);
				handedness = 'R';
			}
			Fork newFork = new Fork();
			dinningTable.addFork(newFork);
		}
		for(int t = 0; t< totalSeated; t++)
		{
			dinningTable.currentPhilosopher(t).start();
		}
		
		
		
	}

}



