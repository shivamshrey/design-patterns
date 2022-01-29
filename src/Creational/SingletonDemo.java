/*
* Singleton Design Pattern
* https://youtu.be/VGLjQuEQgkI
* */

package Creational;

// Eager initialisation
class SingletonEager {
    private static final SingletonEager instance = new SingletonEager();

    private SingletonEager() {
    }

    public static SingletonEager getInstance() {
        return instance;
    }
}

// Lazy initialisation
class SingletonLazy {
    private static SingletonLazy instance;

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}

// Thread-safe method initialisation
// In this both reading and writing are synchronized
class SingletonMethodSynchronized {
    private static SingletonMethodSynchronized instance;

    private SingletonMethodSynchronized() {
    }

    public static synchronized SingletonMethodSynchronized getInstance() {
        if (instance == null) {
            instance = new SingletonMethodSynchronized();
        }
        return instance;
    }
}

// Thread-safe block initialisation
// In this only writing is synchronized
class SingletonSynchronized {
    private static SingletonSynchronized instance;

    private SingletonSynchronized() {
    }

    public static SingletonSynchronized getInstance() {
        if (instance == null) {
            synchronized (SingletonSynchronized.class) {
                if (instance == null) {
                    instance = new SingletonSynchronized();
                }
            }
        }
        return instance;
    }
}

public class SingletonDemo {
    public static void main(String[] args) {
        SingletonEager singletonEager1 = SingletonEager.getInstance();
        SingletonEager singletonEager2 = SingletonEager.getInstance();
        System.out.println(singletonEager1);
        System.out.println(singletonEager2);

        SingletonLazy singletonLazy1 = SingletonLazy.getInstance();
        SingletonLazy singletonLazy2 = SingletonLazy.getInstance();
        System.out.println(singletonLazy1);
        System.out.println(singletonLazy2);

        SingletonMethodSynchronized singletonMethodSynchronized1 = SingletonMethodSynchronized.getInstance();
        SingletonMethodSynchronized singletonMethodSynchronized2 = SingletonMethodSynchronized.getInstance();
        System.out.println(singletonMethodSynchronized1);
        System.out.println(singletonMethodSynchronized2);

        SingletonSynchronized singletonSynchronized1 = SingletonSynchronized.getInstance();
        SingletonSynchronized singletonSynchronized2 = SingletonSynchronized.getInstance();
        System.out.println(singletonSynchronized1);
        System.out.println(singletonSynchronized2);
    }
}
