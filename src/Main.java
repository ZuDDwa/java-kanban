
import taskmanager.managers.FileBackedTaskManager;

import taskmanager.tasks.Epic;
import taskmanager.tasks.Subtask;
import taskmanager.tasks.Task;

import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {
        FileBackedTaskManager taskManager = new FileBackedTaskManager(Paths.get("TasksList.csv"));

        taskManager.addNewTask(new Task("Задача 1", "Описание задачи 1"));
        taskManager.addNewTask(new Task("Задача 2", "Описание задачи 2"));
        taskManager.addNewEpic(new Epic("Эпик 1", "Описание эпика 1"));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 1", 3));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 2", "Подзадача эпика 1", 3));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 1", 3));
        taskManager.addNewEpic(new Epic("Эпик 2", "Описание эпика 2"));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 2", 7));



        for (Task task : taskManager.getTasksList()) {
            System.out.println(task);
        }

        for (Subtask subtask : taskManager.getSubtasksList()) {
            System.out.println(subtask);
        }

        System.out.println(taskManager.getEpicById(3));


    }
}
