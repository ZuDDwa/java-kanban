package taskmanager.managers;

import taskmanager.interfaces.HistoryManager;
import taskmanager.interfaces.TaskManager;

public class Managers {

    static HistoryManager historyManager = new InMemoryHistoryManager();
    static TaskManager taskManager = new InMemoryTaskManager();


    public static TaskManager getDefault() {
        return taskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return historyManager;
    }
}
