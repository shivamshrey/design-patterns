package behavioral;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer is a behavioral design pattern that lets you define a subscription
 * mechanism to notify multiple objects about any events that happen to the
 * object they're observing.
 *
 * https://refactoring.guru/design-patterns/observer
 * https://youtu.be/_BpmfnqjgzQ
 */

abstract class Observer {
	protected Subject subject;

	public abstract void update();
}

class Subject {
	private List<Observer> observers = new ArrayList<>();
	private int state;

	public void add(Observer o) {
		observers.add(o);
	}

	public int getState() {
		return state;
	}

	public void setState(int value) {
		this.state = value;
		execute();
	}

	private void execute() {
		for (Observer observer : observers) {
			observer.update();
		}
	}
}

class HexObserver extends Observer {
	public HexObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}

	public void update() {
		System.out.print(" " + Integer.toHexString(subject.getState()));
	}
}

class OctObserver extends Observer {
	public OctObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}

	public void update() {
		System.out.print(" " + Integer.toOctalString(subject.getState()));
	}
}

class BinObserver extends Observer {
	public BinObserver(Subject subject) {
		this.subject = subject;
		this.subject.add(this);
	}

	public void update() {
		System.out.print(" " + Integer.toBinaryString(subject.getState()));
	}
}

public class ObserverDemo {
	public static void main(String[] args) {
		Subject sub = new Subject();
		// Client configures the number and type of Observers
		new HexObserver(sub);
		new OctObserver(sub);
		new BinObserver(sub);
		for (int i = 100; i < 105; i++) {
			System.out.format("\nNumber = %d\n", i);
			sub.setState(i);
		}
	}
}