package TaskManager;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private List<Task> taskHistory;
    private int historyCapacity;

    public InMemoryHistoryManager() {
        taskHistory = new ArrayList<>();
        historyCapacity = 10;
    }

    @Override
    public void add(Task task) {
        if (taskHistory.size() == historyCapacity) {
            taskHistory.removeFirst();
            taskHistory.add(task);
        } else {
            taskHistory.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return taskHistory;
    }

}
