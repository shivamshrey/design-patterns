/**
 * Iterator is a behavioral design pattern that lets you traverse elements of a collection without exposing its
 * underlying representation (list, stack, tree, etc.).
 * The main idea of the Iterator pattern is to extract the traversal behavior of a collection into a separate object
 * called an iterator.
 *
 * https://refactoring.guru/design-patterns/iterator
 * https://youtu.be/uNTNEfwYXhI
 */
package Behavioral;

class Topic {
    private String name;

    public Topic(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

interface Iterator<E> {
    boolean hasNext();
    E next();
    E currentItem();
    void reset();   // optional for example
}

// Concrete iterator for Topic
class TopicIterator implements Iterator<Topic> {

    private Topic[] topics;
    private int position;

    public TopicIterator(Topic[] topics) {
        this.topics = topics;
        position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < topics.length;
    }

    @Override
    public Topic next() {
        return topics[position++];
    }

    @Override
    public Topic currentItem() {
        return topics[position];
    }

    @Override
    public void reset() {
        position = 0;
    }
}

// Iterable
interface List<E> {
    Iterator<E> getIterator();
}

// Concrete Iterable
class TopicList implements List<Topic> {

    private Topic[] topics;

    public TopicList(Topic[] topics) {
        this.topics = topics;
    }

    @Override
    public Iterator<Topic> getIterator() {
        return new TopicIterator(topics);
    }
}

public class IteratorDemo {
    public static void main(String[] args) {
        Topic[] topics = new Topic[5];
        for (int i = 0; i < 5; i++) {
            topics[i] = new Topic("topic " + i);
        }

        List<Topic> list = new TopicList(topics);

        Iterator<Topic> iterator = list.getIterator();

        while (iterator.hasNext()) {
            Topic currentTopic = iterator.next();
            System.out.println(currentTopic.getName());
        }
    }
}
