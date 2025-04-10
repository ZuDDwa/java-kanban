package taskmanager.interfaces;

import taskmanager.tasks.Task;

import java.util.List;

public interface HistoryManager {

    void remove(int id);

    void add(Task task);

    List<Task> getTasks();
}
