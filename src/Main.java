import TaskManager.*;


public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();

        taskManager.addNewTask(new Task("Задача 1", "Описание задачи 1"));
        taskManager.addNewTask(new Task("Задача 2", "Описание задачи 2"));
        taskManager.addNewEpic(new Epic("Эпик 1", "Описание эпика 1"));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 1", 3));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 2", "Подзадача эпика 1", 3));
        taskManager.addNewEpic(new Epic("Эпик 2", "Описание эпика 2"));
        taskManager.addNewEpicSubtask(new Subtask("Подзадача 1", "Подзадача эпика 2", 6));

//        System.out.println(taskManager.getTasksList());
//        System.out.println(taskManager.getEpicsList());
//        System.out.println(taskManager.getSubtasksList());
//        System.out.println(taskManager.getEpicSubtasksList(3));
//        System.out.println(taskManager.getEpicSubtasksList(6));
//
//
//        System.out.println(taskManager.getTaskById(1).getStatus());
//        System.out.println(taskManager.getTaskById(2).getStatus());
//        System.out.println(taskManager.getEpicById(3).getStatus());
//
//        taskManager.updateSubtask(new Subtask(4, "Новая подзадача 1", "Подзадача эпика 1", Status.NEW, 3));
//        taskManager.updateSubtask(new Subtask(5, "Новая подзадача 2", "Подзадача эпика 1", Status.IN_PROGRESS, 3));
//
//        System.out.println(taskManager.getEpicsList());
//        System.out.println(taskManager.getSubtasksList());
//        System.out.println(taskManager.getEpicById(3).getStatus());
//
//        taskManager.removeSubtaskById(5);
//
//        System.out.println(taskManager.getEpicById(3).getStatus());
//        System.out.println(taskManager.getEpicsList());
//        System.out.println(taskManager.getSubtasksList());
//
//
//        System.out.println(taskManager.getTasksList());
//        System.out.println(taskManager.getEpicsList());
//        System.out.println(taskManager.getSubtasksList());
//        System.out.println(taskManager.getEpicSubtasksList(6));

//        taskManager.removeSubtaskById(7);
//
//        System.out.println(taskManager.getEpicSubtasksList(6));
//
//        taskManager.clearSubtasks();
//        taskManager.clearEpics();
//        taskManager.clearTasks();
//
//        System.out.println(taskManager.getTasksList());
//        System.out.println(taskManager.getEpicsList());
//        System.out.println(taskManager.getSubtasksList());

        System.out.println(historyManager.getHistory());

        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubtaskById(5));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubtaskById(5));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubtaskById(5));
        System.out.println(taskManager.getTaskById(2));
        System.out.println(taskManager.getEpicById(3));
        System.out.println(taskManager.getSubtaskById(5));

        System.out.println(historyManager.getHistory());
        System.out.println(historyManager.getHistory().size());


    }
}
