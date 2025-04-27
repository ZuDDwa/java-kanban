package taskmanager;

import org.junit.jupiter.api.Test;
import taskmanager.interfaces.HistoryManager;
import taskmanager.interfaces.TaskManager;
import taskmanager.managers.Managers;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void ManagersReturnsInitializedTaskManager() {
        TaskManager inMemoryTaskManager = Managers.getDefault();
        TaskManager fileBackedTaskManager = Managers.getFileBackedTaskManager();
        assertNotNull(inMemoryTaskManager);
        assertNotNull(fileBackedTaskManager);
    }

    @Test
    void ManagersReturnsInitializedHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
    }


}