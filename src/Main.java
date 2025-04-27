import taskmanager.interfaces.TaskManager;
import taskmanager.managers.FileBackedTaskManager;
import taskmanager.managers.Managers;
import taskmanager.tasks.Epic;
import taskmanager.tasks.Subtask;
import taskmanager.tasks.Task;
import taskmanager.tasks.Type;

import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {

    public static void main(String[] args) {
        FileBackedTaskManager taskManager = new FileBackedTaskManager(Paths.get("TasksList.csv"));

//        taskManager.addNewTask(new Task("Задача 1", "Описание задачи 1"));
//        taskManager.addNewTask(new Task("Задача 2", "Описание задачи 2"));
//        taskManager.addNewEpic(new Epic("Эпик 1", "Описание эпика 1"));
//        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 1", 3));
//        taskManager.addNewEpicSubtask(new Subtask("Подзадача 2", "Подзадача эпика 1", 3));
//        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 1", 3));
//        taskManager.addNewEpic(new Epic("Эпик 2", "Описание эпика 2"));
//        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 2", 7));



        for (Task task : taskManager.getTasksList()) {
            System.out.println(task);
        }

        for (Subtask subtask : taskManager.getSubtasksList()) {
            System.out.println(subtask);
        }

        System.out.println(taskManager.getEpicById(3));


















//        System.out.println(taskManager.getTaskById(2));
//        System.out.println(taskManager.getSubtaskById(5));
//        System.out.println(taskManager.getEpicById(3));
//        System.out.println(taskManager.getSubtaskById(8));
//        System.out.println(taskManager.getTaskById(1));
//        System.out.println(taskManager.getSubtaskById(4));
//
//        System.out.println(taskManager.getHistory());
//        System.out.println(taskManager.getHistory().size());
//
//        System.out.println(taskManager.getSubtaskById(8));
//        System.out.println(taskManager.getTaskById(1));
//        System.out.println(taskManager.getSubtaskById(4));
//
//        System.out.println(taskManager.getHistory());
//        System.out.println(taskManager.getHistory().size());
//
//        System.out.println(taskManager.getSubtaskById(6));
//        System.out.println(taskManager.getEpicById(7));
//
//        System.out.println(taskManager.getHistory());
//        System.out.println(taskManager.getHistory().size());
//
//        taskManager.removeTaskById(2);
//
//        System.out.println(taskManager.getHistory());
//        System.out.println(taskManager.getHistory().size());
//
//        taskManager.removeEpicById(3);
//
//        System.out.println(taskManager.getHistory());
//        System.out.println(taskManager.getHistory().size());





    }
}
