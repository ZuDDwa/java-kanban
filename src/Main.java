import taskmanager.*;


public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();


        taskManager.addNewTask(new Task("Задача 1", "Описание задачи 1"));
        taskManager.addNewTask(new Task("Задача 2", "Описание задачи 2"));
        taskManager.addNewEpic(new Epic("Эпик 1", "Описание эпика 1"));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 1", 3));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 2", "Подзадача эпика 1", 3));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 2", 3));
        taskManager.addNewEpic(new Epic("Эпик 2", "Описание эпика 2"));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 2", 7));

        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getSubtaskById(5));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubtaskById(8));
        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getSubtaskById(4));

        System.out.println(taskManager.getHistory());
        System.out.println(taskManager.getHistory().size());

        System.out.println(taskManager.getSubtaskById(8));
        System.out.println(taskManager.getTaskById(1));
        System.out.println(taskManager.getSubtaskById(4));

        System.out.println(taskManager.getHistory());
        System.out.println(taskManager.getHistory().size());

        System.out.println(taskManager.getSubtaskById(6));
        System.out.println(taskManager.getEpicById(7));

        System.out.println(taskManager.getHistory());
        System.out.println(taskManager.getHistory().size());

        taskManager.removeTaskById(2);

        System.out.println(taskManager.getHistory());
        System.out.println(taskManager.getHistory().size());

        taskManager.removeEpicById(3);

        System.out.println(taskManager.getHistory());
        System.out.println(taskManager.getHistory().size());





    }
}
