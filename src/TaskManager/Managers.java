package TaskManager;

import java.util.List;

public class Managers {

    static HistoryManager historyManager = new InMemoryHistoryManager();
    static TaskManager taskManager = new InMemoryTaskManager();


    public static TaskManager getDfault() {
        return taskManager;
    }

    public static HistoryManager getDfaultHistory() {
        return historyManager;
    }
}
