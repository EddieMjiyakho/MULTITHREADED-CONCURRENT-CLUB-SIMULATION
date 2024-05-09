package clubSimulation;
import java.util.concurrent.atomic.*;

public class PeopleCounter {
	private AtomicInteger peopleOutSide; //counter for people arrived but not yet in the building
	private AtomicInteger peopleInside; //counter for patrons inside club
	private AtomicInteger peopleLeft; //counter for patrons who have left the club
	private AtomicInteger maxPeople; //maximum patrons allowed in the club at one time
	
	PeopleCounter(int max) {
		peopleOutSide= new AtomicInteger(0);
		peopleInside=new AtomicInteger(0);
		peopleLeft=new AtomicInteger(0);
		maxPeople=new AtomicInteger(max);
	}
		
	public int getWaiting() {
		return peopleOutSide.get();
	}

	public int getInside() {
		return peopleInside.get();
	}
	
	public int getTotal() {
		return (peopleOutSide.get()+peopleInside.get()+peopleLeft.get());
	}

	public int getLeft() {
		return peopleLeft.get();
	}
	
	public int getMax() {
		return maxPeople.get();
	}
	
	//someone arrived outside
	public void personArrived() {
		peopleOutSide.getAndIncrement();
	}
	
	//someone got inside
	public void personEntered() {
    while (true) {
        int currentOutside = peopleOutSide.get();
        int currentInside = peopleInside.get();
        
        if (peopleOutSide.compareAndSet(currentOutside, currentOutside - 1) &&
            peopleInside.compareAndSet(currentInside, currentInside + 1)) {
            break;
        	}
    	}
	}

	//someone left
	public void personLeft() {
    while (true) {
        int currentInside = peopleInside.get();
        int currentLeft = peopleLeft.get();
        
        if (peopleInside.compareAndSet(currentInside, currentInside - 1) &&
            peopleLeft.compareAndSet(currentLeft, currentLeft + 1)) {
            break;
        	}
    	}
	}
	//too many people inside
	synchronized public boolean overCapacity() {
		if(peopleInside.get()>=maxPeople.get())
			return true;
		return false;
	}
	
	//not used
	synchronized public void resetScore() {
		peopleInside.set(0);
		peopleOutSide.set(0);
		peopleLeft.set(0);
	}
}
