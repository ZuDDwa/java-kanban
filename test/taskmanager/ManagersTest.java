package taskmanager;

import org.junit.jupiter.api.Test;
import taskmanager.interfaces.HistoryManager;
import taskmanager.interfaces.TaskManager;
import taskmanager.managers.Managers;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void ManagersReturnsInitializedTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager);
    }

    @Test
    void ManagersReturnsInitializedHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
    }

}