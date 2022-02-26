package creational;

/**
 * Factory Method is a creational design pattern that provides an interface for
 * creating objects in a superclass, but allows subclasses to alter the type of
 * objects that will be created.
 *
 * https://youtu.be/EcFVTgRHJLM
 * https://refactoring.guru/design-patterns/factory-method
 */

interface Product {
	void doSomething();
}

class ConcreteProduct1 implements Product {

	@Override
	public void doSomething() {
		System.out.println("Product1 do something");
	}
}

class ConcreteProduct2 implements Product {

	@Override
	public void doSomething() {
		System.out.println("Product2 do something");
	}
}

interface Factory {
	Product makeProduct();
}

class ConcreteFactory1 implements Factory {

	@Override
	public Product makeProduct() {
		return new ConcreteProduct1();
	}
}

class ConcreteFactory2 implements Factory {

	@Override
	public Product makeProduct() {
		return new ConcreteProduct2();
	}
}

public class FactoryMethodDemo {

	public static void main(String[] args) {
		Factory factory = null;
		if (System.getProperty("os.name").equals("Windows 10"))
			factory = new ConcreteFactory1();
		else if (System.getProperty("os.name").equals("Linux"))
			factory = new ConcreteFactory2();
		Product product = factory.makeProduct();
		product.doSomething();
	}

}
