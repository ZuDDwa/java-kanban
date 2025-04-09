package TaskManager;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {

    transient int size = 0;
    transient Node first;
    transient Node last;

    private final Map<Integer, Node> taskHistory;


    public InMemoryHistoryManager() {
        taskHistory = new HashMap<>();
    }

    @Override
    public void add(Task task) {
        if (taskHistory.containsKey(task.getId())) {
            remove(task.getId());
        }
        linkLast(task);
        taskHistory.put(task.getId(), last);
    }

    @Override
    public List<Task> getTasks() {
        List<Task> taskHistoryList = new ArrayList<>();
        Node taskNode = first;
        while (taskNode != null) {
            taskHistoryList.add(taskNode.item);
            taskNode = taskNode.next;
        }
        return taskHistoryList;
    }


    @Override
    public void remove(int id) {
        removeNode(taskHistory.get(id));
        taskHistory.remove(id);
    }

    private void linkLast(Task task) {
        final Node l = last;
        final Node newNode = new Node(l, task, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private void removeNode(Node node) {

        Node next = node.next;
        Node prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.item = null;
        size--;
    }

    private static class Node {
        Task item;
        Node prev;
        Node next;

        Node(Node prev, Task item, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
}
