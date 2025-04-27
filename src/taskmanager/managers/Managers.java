package taskmanager.managers;

import taskmanager.interfaces.HistoryManager;
import taskmanager.interfaces.TaskManager;

import java.nio.file.Paths;

public class Managers {

    static HistoryManager historyManager = new InMemoryHistoryManager();
    static TaskManager inMemoryTaskManager = new InMemoryTaskManager();
    static TaskManager fileBackedTaskManager = new FileBackedTaskManager(Paths.get("TasksList.csv"));


    public static TaskManager getDefault() {
        return inMemoryTaskManager;
    }

    public static HistoryManager getDefaultHistory() {
        return historyManager;
    }

    public static TaskManager getFileBackedTaskManager() {
        return fileBackedTaskManager;
    }
}
