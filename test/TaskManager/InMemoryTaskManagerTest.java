package TaskManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class InMemoryTaskManagerTest {

    static TaskManager taskManager = new InMemoryTaskManager();


    static Task task1 = new Task("Задача 1", "Описание задачи 1");
    static Task task2 = new Task("Задача 2", "Описание задачи 2");
    static Epic epic1 = new Epic("Эпик 1", "Описание эпика 1");
    static Subtask subtask11 = new Subtask("Подзадача 1", "Подзадача эпика 1", 3);
    static Subtask subtask12 = new Subtask("Подзадача 2", "Подзадача эпика 1", 3);
    static Epic epic2 = new Epic("Эпик 2", "Описание эпика 2");
    static Subtask subtask21 = new Subtask("Подзадача 1", "Подзадача эпика 2", 6);
    static Subtask subtask22 = new Subtask("Подзадача 2", "Подзадача эпика 2", 6);

    @BeforeAll
    static void fillTaskManager() {
        taskManager.addNewTask(task1);
        taskManager.addNewTask(task2);
        taskManager.addNewEpic(epic1);
        taskManager.addNewEpicSubtask(subtask11);
        taskManager.addNewEpicSubtask(subtask12);
        taskManager.addNewEpic(epic2);
        taskManager.addNewEpicSubtask(subtask21);
        taskManager.addNewEpicSubtask(subtask22);
    }

    @Test
    void tasksShouldBeEqualIfIdsAreEqual() {
        Task task1Updated = new Task(2, "Задача 2 (обновленная)", "Описание задачи 2", Status.IN_PROGRESS);
        assertEquals(task2, task1Updated);
    }

    @Test
    void taskManagerShouldUpdateTasks() {
        Task task1Updated = new Task(2, "Задача 2 (обновленная)", "Обновленное описание задачи 2", Status.IN_PROGRESS);
        taskManager.updateTask(task1Updated);
        assertEquals("Задача 2 (обновленная)", taskManager.getTaskById(2).getTitle());
        assertEquals("Обновленное описание задачи 2", taskManager.getTaskById(2).getDescription());
        assertEquals(Status.IN_PROGRESS, taskManager.getTaskById(2).getStatus());
    }


    @Test
    void epicsShouldBeEqualIfIdsAreEqual() {
        Epic epic1Updated = new Epic(3, "Эпик 1 (обновленный)", "Обновленное описание эпика 1");
        assertEquals(epic1, epic1Updated);
    }

    @Test
    void taskManagerShouldUpdateEpics() {
        Epic epic1Updated = new Epic(3, "Эпик 1 (обновленный)", "Обновленное описание эпика 1");
        taskManager.updateEpic(epic1Updated);
        assertEquals("Эпик 1 (обновленный)", taskManager.getEpicById(3).getTitle());
        assertEquals("Обновленное описание эпика 1", taskManager.getEpicById(3).getDescription());
    }

    @Test
    void subtasksShouldBeEqualIfIdsAreEqual() {
        Subtask subtask11Updated = new Subtask(4, "Подзадача 1 (обновленная)", "Подзадача эпика 1", Status.IN_PROGRESS, 3);
        assertEquals(subtask11, subtask11Updated);
    }

    @Test
    void taskManagerShouldUpdateSubtasks() {
        Subtask subtask11Updated = new Subtask(4, "Подзадача 1 (обновленная)", "Подзадача (обновленная) эпика 1", Status.IN_PROGRESS, 3);
        taskManager.updateSubtask(subtask11Updated);
        assertEquals("Подзадача 1 (обновленная)", taskManager.getSubtaskById(4).getTitle());
        assertEquals("Подзадача (обновленная) эпика 1", taskManager.getSubtaskById(4).getDescription());
        assertEquals(Status.IN_PROGRESS, taskManager.getSubtaskById(4).getStatus());
    }

    @Test
    void taskManagerShouldIncreaseIdCounterByOne() {
        TaskManager taskManager = new InMemoryTaskManager();
        int expectedCounter = 1;
        assertEquals(expectedCounter, taskManager.getIdCounter());
        taskManager.addNewTask(task1);
        assertEquals(++expectedCounter, taskManager.getIdCounter());
        taskManager.addNewTask(epic1);
        assertEquals(++expectedCounter, taskManager.getIdCounter());
        taskManager.addNewTask(subtask11);
        assertEquals(++expectedCounter, taskManager.getIdCounter());
    }

    @Test
    void taskManagerShouldAddTasksAndReturnThemById() {
        TaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task("Задача", "Описание задачи");
        taskManager.addNewTask(task);
        Epic epic = new Epic("Эпик", "Описание эпика");
        taskManager.addNewEpic(epic);
        Subtask subtask = new Subtask("Подзадача эпика", "Описание подзадачи эпика", 2);
        taskManager.addNewEpicSubtask(subtask);
        assertEquals(task, taskManager.getTaskById(1));
        assertEquals(epic, taskManager.getEpicById(2));
        assertEquals(subtask, taskManager.getSubtaskById(3));
    }

    @Test
    void tasksWithTheSameIdShouldntConflict() {
        TaskManager taskManager = new InMemoryTaskManager();
        Task task = new Task("Задача", "Описание задачи");
        Task task2 = new Task(1, "Другая задача с тем же id", "Описание задачи", Status.IN_PROGRESS);
        taskManager.addNewTask(task);
        taskManager.addNewTask(task2);
        assertEquals(task, taskManager.getTaskById(1));
        assertEquals(task2, taskManager.getTaskById(2));
        taskManager.updateTask(task2);
        Task task3 = new Task(1, "Другая задача с тем же id", "Описание задачи", Status.IN_PROGRESS);
        assertEquals(task3, taskManager.getTaskById(1));
    }

    @Test
    void epicsWithTheSameIdShouldntConflict() {
        TaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic("Эпик", "Описание эпика");
        Epic epic2 = new Epic(1, "Другой эпик с тем же id", "Описание эпика");
        taskManager.addNewEpic(epic);
        taskManager.addNewEpic(epic2);
        assertEquals(epic, taskManager.getEpicById(1));
        assertEquals(epic2, taskManager.getEpicById(2));
        taskManager.updateEpic(epic2);
        Task epic3 = new Epic(1, "Другая задача с тем же id", "Описание задачи");
        assertEquals(epic3, taskManager.getEpicById(1));
    }

    @Test
    void subtasksWithTheSameIdShouldntConflict() {
        TaskManager taskManager = new InMemoryTaskManager();
        taskManager.addNewEpic(new Epic("Эпик", "Описание эпика"));
        Subtask subtask = new Subtask("Подзадача", "Описание подзадачи", 1);
        Subtask subtask2 = new Subtask(2, "Другая подзадача с тем же id", "Описание подзадачи", Status.IN_PROGRESS, 1);
        taskManager.addNewEpicSubtask(subtask);
        taskManager.addNewEpicSubtask(subtask2);
        assertEquals(subtask, taskManager.getSubtaskById(2));
        assertEquals(subtask2, taskManager.getSubtaskById(3));
        taskManager.updateSubtask(subtask2);
        Task subtask3 = new Subtask(2, "Другая подзадача с тем же id", "Описание подзадачи", Status.IN_PROGRESS, 1);
        assertEquals(subtask3, taskManager.getSubtaskById(2));
    }

    @Test
    void taskManagerShouldUpdateEpicsStatus() {
        TaskManager taskManager = new InMemoryTaskManager();
        Epic epic = new Epic("Тестовый эпик", "Описание эпика");
        taskManager.addNewEpic(epic);
        assertEquals(Status.NEW, epic.getStatus());

        Subtask subtask1 = new Subtask("Подзадача 1", "Подзадача тестового эпика", 1);
        Subtask subtask2 = new Subtask("Подзадача 2", "Подзадача тестового эпика", 1);
        Subtask subtask3 = new Subtask("Подзадача 2", "Подзадача тестового эпика", 1);
        taskManager.addNewEpicSubtask(subtask1);
        taskManager.addNewEpicSubtask(subtask2);
        taskManager.addNewEpicSubtask(subtask3);
        assertEquals(Status.NEW, epic.getStatus());

        Subtask subtask1Updated = new Subtask(2, "Подзадача 1 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.IN_PROGRESS, 1);
        Subtask subtask2Updated = new Subtask(3, "Подзадача 2 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.NEW, 1);
        Subtask subtask3Updated = new Subtask(4, "Подзадача 3 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.DONE, 1);
        taskManager.updateSubtask(subtask1Updated);
        taskManager.updateSubtask(subtask2Updated);
        taskManager.updateSubtask(subtask3Updated);
        assertEquals(Status.IN_PROGRESS, epic.getStatus());

        subtask1Updated = new Subtask(2, "Подзадача 1 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.IN_PROGRESS, 1);
        subtask2Updated = new Subtask(3, "Подзадача 2 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.NEW, 1);
        subtask3Updated = new Subtask(4, "Подзадача 3 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.NEW, 1);
        taskManager.updateSubtask(subtask1Updated);
        taskManager.updateSubtask(subtask2Updated);
        taskManager.updateSubtask(subtask3Updated);
        assertEquals(Status.IN_PROGRESS, epic.getStatus());

        subtask1Updated = new Subtask(2, "Подзадача 1 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.DONE, 1);
        subtask2Updated = new Subtask(3, "Подзадача 2 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.DONE, 1);
        subtask3Updated = new Subtask(4, "Подзадача 3 (обновленная)", "Подзадача (обновленная) тестового эпика", Status.DONE, 1);
        taskManager.updateSubtask(subtask1Updated);
        taskManager.updateSubtask(subtask2Updated);
        taskManager.updateSubtask(subtask3Updated);
        assertEquals(Status.DONE, epic.getStatus());

    }
}