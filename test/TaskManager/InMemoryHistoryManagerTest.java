package TaskManager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class InMemoryHistoryManagerTest {

    static TaskManager taskManager = Managers.getDefault();



    static Task task1 = new Task( "Задача 1", "Описание задачи 1");
    static Task task2 = new Task( "Задача 2", "Описание задачи 2");
    static Epic epic1 = new Epic(1,"Эпик 1", "Описание эпика 1");
    static Subtask subtask11 = new Subtask("Подзадача 1", "Подзадача эпика 1", 3);
    static Subtask subtask12 = new Subtask("Подзадача 2", "Подзадача эпика 1", 3);
    static Epic epic2 = new Epic("Эпик 2", "Описание эпика 2");
    static Subtask subtask21 = new Subtask(  "Подзадача 1", "Подзадача эпика 2", 6);
    static Subtask subtask22 = new Subtask( "Подзадача 2", "Подзадача эпика 2", 6);

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
    void historyManagerShouldContain10ItemsMax() {
        Task task = taskManager.getTaskById(1); // 1
        Epic epic = taskManager.getEpicById(3); // 2
        Subtask subtask = taskManager.getSubtaskById(4); // 3
        Task task2 = taskManager.getTaskById(1); // 4
        Epic epic2 = taskManager.getEpicById(3); // 5
        Epic epic3 = taskManager.getEpicById(3); // 6
        Subtask subtask2 = taskManager.getSubtaskById(4); // 7
        Task task3 = taskManager.getTaskById(1); // 8
        Epic epic4 = taskManager.getEpicById(3); // 9
        Subtask subtask3 = taskManager.getSubtaskById(4); // 10
        Task task4 = taskManager.getTaskById(1); // 11
        Epic epic5 = taskManager.getEpicById(3); // 12
        Epic epic6 = taskManager.getEpicById(3); // 13

        assertEquals(10, taskManager.getHistory().size());
    }





}