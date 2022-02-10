/**
 * Command is a behavioral design pattern that turns a request into a stand-alone object that contains all information
 * about the request. This transformation lets you pass requests as a method arguments, delay or queue a request’s
 * execution, and support undoable operations.
 *
 * https://youtu.be/9qA5kw8dcSU
 * https://refactoring.guru/design-patterns/command
 */

package Behavioral;

interface Command {
    void execute();
}

// Receiver on which commands are invoked by Invoker
class Light {
    public void turnOn() {
        System.out.println("Light turned ON");
    }

    public void turnOff() {
        System.out.println("Light turned OFF");
    }
}

// Concrete command
class LightOnCommand implements Command {

    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

}
// Another concrete command
class LightOffCommand implements Command {

    private Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }

}

// Invoker invokes the commands on a receiver
class RemoteController {
    private Command on;
    private Command off;

    public RemoteController(Command on, Command off) {
        this.on = on;
        this.off = off;
    }

    public void clickOn() {
        on.execute();
    }

    public void clickOff() {
        off.execute();
    }
}

public class CommandDemo {
    public static void main(String[] args) {
        Light light = new Light();
        RemoteController remoteController = new RemoteController(
                new LightOnCommand(light),
                new LightOffCommand(light)
        );
        remoteController.clickOn();
        remoteController.clickOff();
    }
}
