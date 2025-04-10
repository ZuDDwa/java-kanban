package taskmanager;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import taskmanager.interfaces.TaskManager;
import taskmanager.managers.Managers;
import taskmanager.tasks.Epic;
import taskmanager.tasks.Subtask;
import taskmanager.tasks.Task;


import java.util.HashSet;
import java.util.Set;

class InMemoryHistoryManagerTest {

    static TaskManager taskManager = Managers.getDefault();


    static Task task1 = new Task("Задача 1", "Описание задачи 1");
    static Task task2 = new Task("Задача 2", "Описание задачи 2");
    static Epic epic1 = new Epic(1, "Эпик 1", "Описание эпика 1");
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
    void historyManagerShouldSaveTasksEpicsSubtasks() {
        Task task = taskManager.getTaskById(1);
        Epic epic = taskManager.getEpicById(3);
        Subtask subtask = taskManager.getSubtaskById(4);
        assertEquals(task, taskManager.getHistory().get(0));
        assertEquals(epic, taskManager.getHistory().get(1));
        assertEquals(subtask, taskManager.getHistory().get(2));
    }

    @Test
    void historyManagerShouldContainUniqueTasks() {
        Set<Task> uniqueTasks = new HashSet<>();

        uniqueTasks.add(taskManager.getTaskById(1)); // 1
        uniqueTasks.add(taskManager.getEpicById(3)); // 2
        uniqueTasks.add(taskManager.getSubtaskById(4)); // 3
        uniqueTasks.add(taskManager.getTaskById(1)); // 4
        uniqueTasks.add(taskManager.getEpicById(3)); // 5
        uniqueTasks.add(taskManager.getEpicById(3)); // 6
        uniqueTasks.add(taskManager.getSubtaskById(4)); // 7
        uniqueTasks.add(taskManager.getTaskById(1)); // 8
        uniqueTasks.add(taskManager.getEpicById(3)); // 9
        uniqueTasks.add(taskManager.getSubtaskById(4)); // 10
        uniqueTasks.add(taskManager.getTaskById(1)); // 11
        uniqueTasks.add(taskManager.getEpicById(3)); // 12
        uniqueTasks.add(taskManager.getEpicById(3)); // 13

        assertEquals(uniqueTasks.size(), taskManager.getHistory().size());
        assertTrue(taskManager.getHistory().containsAll(uniqueTasks));
    }

    @Test
    void historyManagerShouldAddTaskToTheEnd() {
        Task subtask = taskManager.getSubtaskById(7); // 1
        Epic epic1 = taskManager.getEpicById(6); // 2

        assertEquals(epic1, taskManager.getHistory().get(1));

        Subtask subtask1 = taskManager.getSubtaskById(8);

        assertEquals(subtask1, taskManager.getHistory().get(2));
    }


    @Test
    void tasksShouldBeRemovedFromHistory() {
        Task task = taskManager.getTaskById(1); // 1
        Epic epic = taskManager.getEpicById(3); // 2
        Subtask subtask11 = taskManager.getSubtaskById(4); // 3
        Subtask subtask12 = taskManager.getSubtaskById(5);

        assertTrue(taskManager.getHistory().contains(task));
        assertTrue(taskManager.getHistory().contains(epic));
        assertTrue(taskManager.getHistory().contains(subtask11));
        assertTrue(taskManager.getHistory().contains(subtask11));

        taskManager.removeTaskById(1);
        taskManager.removeSubtaskById(4);
        assertFalse(taskManager.getHistory().contains(task));
        assertFalse(taskManager.getHistory().contains(subtask11));
        taskManager.removeEpicById(3);
        assertFalse(taskManager.getHistory().contains(epic));
        assertFalse(taskManager.getHistory().contains(subtask12));
    }
}