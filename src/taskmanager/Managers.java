package taskmanager;

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
