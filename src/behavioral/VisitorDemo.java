package behavioral;

import java.text.DecimalFormat;

/**
 * Visitor is a behavioral design pattern that lets you separate algorithms from
 * the objects on which they operate.
 * 
 * https://youtu.be/pL4mOUDi54o https://refactoring.guru/design-patterns/visitor
 */

interface Visitor {

	// Created to automatically use the right
	// code based on the Object sent
	// Method Overloading

	public double visit(Liquor liquorItem);

	public double visit(Tobacco tobaccoItem);

	public double visit(Necessity necessityItem);

}

// Concrete Visitor Class

class TaxVisitor implements Visitor {

	// This formats the item prices to 2 decimal places

	DecimalFormat df = new DecimalFormat("#.##");

	// This is created so that each item is sent to the
	// right version of visit() which is required by the
	// Visitor interface and defined below

	public TaxVisitor() {
	}

	// Calculates total price based on this being taxed
	// as a liquor item

	public double visit(Liquor liquorItem) {
		System.out.println("Liquor Item: Price with Tax");
		return Double.parseDouble(df.format((liquorItem.getPrice() * .18) + liquorItem.getPrice()));
	}

	// Calculates total price based on this being taxed
	// as a tobacco item

	public double visit(Tobacco tobaccoItem) {
		System.out.println("Tobacco Item: Price with Tax");
		return Double.parseDouble(df.format((tobaccoItem.getPrice() * .32) + tobaccoItem.getPrice()));
	}

	// Calculates total price based on this being taxed
	// as a necessity item

	public double visit(Necessity necessityItem) {
		System.out.println("Necessity Item: Price with Tax");
		return Double.parseDouble(df.format(necessityItem.getPrice()));
	}

}

// Concrete Visitor Class

class TaxHolidayVisitor implements Visitor {

	// This formats the item prices to 2 decimal places

	DecimalFormat df = new DecimalFormat("#.##");

	// This is created so that each item is sent to the
	// right version of visit() which is required by the
	// Visitor interface and defined below

	public TaxHolidayVisitor() {
	}

	// Calculates total price based on this being taxed
	// as a liquor item

	public double visit(Liquor liquorItem) {
		System.out.println("Liquor Item: Price with Tax");
		return Double.parseDouble(df.format((liquorItem.getPrice() * .10) + liquorItem.getPrice()));
	}

	// Calculates total price based on this being taxed
	// as a tobacco item

	public double visit(Tobacco tobaccoItem) {
		System.out.println("Tobacco Item: Price with Tax");
		return Double.parseDouble(df.format((tobaccoItem.getPrice() * .30) + tobaccoItem.getPrice()));
	}

	// Calculates total price based on this being taxed
	// as a necessity item

	public double visit(Necessity necessityItem) {
		System.out.println("Necessity Item: Price with Tax");
		return Double.parseDouble(df.format(necessityItem.getPrice()));
	}

}

// Element
interface Visitable {

	// Allows the Visitor to pass the object so
	// the right operations occur on the right
	// type of object.

	// accept() is passed the same visitor object
	// but then the method visit() is called using
	// the visitor object. The right version of visit()
	// is called because of method overloading

	public double accept(Visitor visitor);

}

class Liquor implements Visitable {

	private double price;

	Liquor(double item) {
		price = item;
	}

	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}

}

class Necessity implements Visitable {

	private double price;

	Necessity(double item) {
		price = item;
	}

	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}

}

class Tobacco implements Visitable {

	private double price;

	Tobacco(double item) {
		price = item;
	}

	public double accept(Visitor visitor) {
		return visitor.visit(this);
	}

	public double getPrice() {
		return price;
	}

}

public class VisitorDemo {

	public static void main(String[] args) {
		TaxVisitor taxCalc = new TaxVisitor();
		TaxHolidayVisitor taxHolidayCalc = new TaxHolidayVisitor();

		Necessity milk = new Necessity(3.47);
		Liquor vodka = new Liquor(11.99);
		Tobacco cigars = new Tobacco(19.99);

		System.out.println(milk.accept(taxCalc) + "\n");
		System.out.println(vodka.accept(taxCalc) + "\n");
		System.out.println(cigars.accept(taxCalc) + "\n");

		System.out.println("TAX HOLIDAY PRICES\n");

		System.out.println(milk.accept(taxHolidayCalc) + "\n");
		System.out.println(vodka.accept(taxHolidayCalc) + "\n");
		System.out.println(cigars.accept(taxHolidayCalc) + "\n");
	}

}
