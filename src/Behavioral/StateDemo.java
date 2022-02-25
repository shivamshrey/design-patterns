/**
 * State is a behavioral design pattern that lets an object alter its behavior
 * when its internal state changes. It appears as if the object changed its 
 * class.
 * 
 * The State pattern is closely related to the concept of a Finite-State Machine.
 * 
 * The State pattern suggests that you create new classes for all possible
 * states of an object and extract all state-specific behaviors into these
 * classes.
 * 
 * Instead of implementing all behaviors on its own, the original object,called 
 * context, stores a reference to one of the state objects that represents its 
 * current state, and delegates all the state-related work to that object.
 * 
 * https://youtu.be/N12L5D78MAA
 * https://refactoring.guru/design-patterns/state
 * https://www.baeldung.com/java-state-design-pattern
 */
package Behavioral;

// State
interface PackageState {
	void next(Package pkg);
	void prev(Package pkg);
	void printStatus();
}

// Context
class Package {
	
	private PackageState state = new OrderedState();	// root state
	
	public PackageState getState() {
		return state;
	}

	public void setState(PackageState state) {
		this.state = state;
	}

	public void previousState() {
		state.prev(this);
	}
	
	public void nextState() {
		state.next(this);
	}
	
	public void printStatus() {
		state.printStatus();
	}
}

class OrderedState implements PackageState {

	@Override
	public void next(Package pkg) {
		pkg.setState(new DeliveredState());		
	}

	@Override
	public void prev(Package pkg) {
		System.out.println("The package is in its root state.");
	}

	@Override
	public void printStatus() {
		System.out.println("Package ordered, not delivered to the post office yet.");
	}
	
}

class DeliveredState implements PackageState {

	@Override
	public void next(Package pkg) {
		pkg.setState(new ReceivedState());
	}

	@Override
	public void prev(Package pkg) {
		pkg.setState(new OrderedState());
	}

	@Override
	public void printStatus() {
		System.out.println("Package delivered to post office, not received yet.");
	}
	
}

class ReceivedState implements PackageState {

	@Override
	public void next(Package pkg) {
		System.out.println("This package is already received by a client.");
	}

	@Override
	public void prev(Package pkg) {
		pkg.setState(new DeliveredState());
	}

	@Override
	public void printStatus() {
		System.out.println("Package received by the client.");
	}
	
}

public class StateDemo {
	public static void main(String[] args) {
		Package pkg = new Package();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();

        pkg.nextState();
        pkg.printStatus();
	}
}