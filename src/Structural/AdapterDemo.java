/**
 * Adapter is a structural design pattern that allows objects with incompatible interfaces to collaborate.
 *
 * https://youtu.be/2PKQtcJjYvc
 * https://youtu.be/FsS-jWCPtQI
 */

package Structural;

interface WebDriver {
    void getElement();
    void selectElement();
}

class ChromeDriver implements WebDriver {

    @Override
    public void getElement() {
        System.out.println("Get element from Chrome Driver");
    }

    @Override
    public void selectElement() {
        System.out.println("Select element from Chrome Driver");
    }
}

class IEDriver {

    public void findElement() {
        System.out.println("Find element from IE Driver");
    }

    public void clickElement() {
        System.out.println("Click element from IE Driver");
    }

}

/**
 * Adapter for IEDriver (adaptee)
 */
class WebDriverAdapter implements WebDriver {

    private IEDriver ieDriver;

    public WebDriverAdapter(IEDriver ieDriver) {
        this.ieDriver = ieDriver;
    }

    @Override
    public void getElement() {
        ieDriver.findElement();
    }

    @Override
    public void selectElement() {
        ieDriver.clickElement();
    }

}

public class AdapterDemo {

    public static void main(String[] args) {
        ChromeDriver a = new ChromeDriver();
        a.getElement();
        a.selectElement();

        IEDriver e = new IEDriver();
        e.findElement();
        e.clickElement();

        WebDriver wID = new WebDriverAdapter(e);
        wID.getElement();
        wID.selectElement();
    }
}
