package structural;

/**
 * Proxy is a structural design pattern that lets you provide a substitute or placeholder for another object.
 * A proxy controls access to the original object, allowing you to perform something either before or after
 * the request gets through to the original object.
 *
 * https://refactoring.guru/design-patterns/proxy
 * https://youtu.be/NwaabHqPHeM
 * https://youtu.be/AB0gaAg9jwI
 */

/**
 * Access control (protection proxy). This is when you want only specific
 * clients to be able to use the service object; for instance, when your objects
 * are crucial parts of an operating system and clients are various launched
 * applications (including malicious ones).
 */

interface DatabaseExecutor {
	public void executeDatabase(String query) throws Exception;
}

class DatabaseExecutorImpl implements DatabaseExecutor {

	@Override
	public void executeDatabase(String query) throws Exception {
		System.out.println("Going to execute Query: " + query);
	}

}

class DatabaseExecutorProxy implements DatabaseExecutor {
	boolean ifAdmin;
	DatabaseExecutorImpl dbExecutor;

	public DatabaseExecutorProxy(String name, String password) {
		if (name.equals("Admin") && password.equals("Admin@123")) {
			ifAdmin = true;
		}
		dbExecutor = new DatabaseExecutorImpl();
	}

	@Override
	public void executeDatabase(String query) throws Exception {
		if (ifAdmin) {
			dbExecutor.executeDatabase(query);
		} else {
			if (query.equals("DELETE")) {
				throw new Exception("DELETE not allowed for non-admin user");
			} else {
				dbExecutor.executeDatabase(query);
			}
		}
	}
}

public class ProxyDemo {

	public static void main(String[] args) throws Exception {
		DatabaseExecutor adminExecutor = new DatabaseExecutorProxy("Admin", "Admin@123");
		adminExecutor.executeDatabase("DELETE");

		DatabaseExecutor nonAdminExecutor = new DatabaseExecutorProxy("NonAdmin", "Admin@123");
		nonAdminExecutor.executeDatabase("DELETE");
	}

}