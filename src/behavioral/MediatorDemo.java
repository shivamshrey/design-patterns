package behavioral;

import java.util.HashMap;
import java.util.Map;

/**
 * Mediator is a behavioral design pattern that lets you reduce chaotic
 * dependencies between objects. The pattern restricts direct communications
 * between the objects and forces them to collaborate only via a mediator
 * object.
 * 
 * https://refactoring.guru/design-patterns/mediator
 * https://howtodoinjava.com/design-patterns/behavioral/mediator-pattern/
 */

// Mediator interface
interface Mediator {
	void sendMessage(String msg, String userId);

	void addUser(User user);
}

// Concrete Mediator
class ChatRoom implements Mediator {

	private Map<String, User> usersMap = new HashMap<>();

	@Override
	public void sendMessage(String msg, String userId) {
		User u = usersMap.get(userId);
		u.receive(msg);
	}

	@Override
	public void addUser(User user) {
		usersMap.put(user.getId(), user);
	}
}

// Abstract Colleague
abstract class User {

	private Mediator mediator;
	private String id;
	private String name;

	public User(Mediator mediator, String id, String name) {
		super();
		this.mediator = mediator;
		this.id = id;
		this.name = name;
	}

	public abstract void send(String msg, String userId);

	public abstract void receive(String msg);

	public Mediator getMediator() {
		return mediator;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}

// Concrete Colleague
class ChatUser extends User {

	public ChatUser(Mediator mediator, String id, String name) {
		super(mediator, id, name);
	}

	@Override
	public void send(String msg, String userId) {
		System.out.println(getName() + " :: Sending Message :: " + msg);
		getMediator().sendMessage(msg, userId);
	}

	@Override
	public void receive(String msg) {
		System.out.println(getName() + " :: Received Message :: " + msg);
	}
}

public class MediatorDemo {

	public static void main(String[] args) {
		Mediator chatroom = new ChatRoom();

		User user1 = new ChatUser(chatroom, "1", "Alex");
		User user2 = new ChatUser(chatroom, "2", "Brian");
		User user3 = new ChatUser(chatroom, "3", "Charles");
		User user4 = new ChatUser(chatroom, "4", "David");

		chatroom.addUser(user1);
		chatroom.addUser(user2);
		chatroom.addUser(user3);
		chatroom.addUser(user4);

		user1.send("Hello Brian", "2");
		user2.send("Hey buddy", "1");
	}

}
