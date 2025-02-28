import TaskManager.TaskManager;
import TaskManager.Status;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        taskManager.addNewTask("Задача 1", "Описание задачи 1");
        taskManager.addNewTask("Задача 2", "Описание задачи 2");
        taskManager.addNewEpic("Эпик 1", "Эпик с двумя подзадачами");
        taskManager.addEpicSubtask(3, "1я подзадача эпика 1", "Описание подзадачи");
        taskManager.addEpicSubtask(3, "2я подзадача эпика 1", "Описание подзадачи");
        taskManager.addNewEpic("Эпик 2", "Эпик с одной подзадачами");
        taskManager.addEpicSubtask(6, "1я подзадача эпика 2", "Описание подзадачи");

        System.out.println(taskManager.getTasksList());
        System.out.println(taskManager.getEpicsList());
        System.out.println(taskManager.getSubtasksList());
        System.out.println(taskManager.getEpicSubtasksList(3));
        System.out.println(taskManager.getEpicSubtasksList(6));

        taskManager.setStatusById(1, Status.IN_PROGRESS);
        taskManager.setStatusById(2, Status.DONE);
        taskManager.setStatusById(4, Status.IN_PROGRESS);
        taskManager.setStatusById(5, Status.NEW);



        System.out.println(taskManager.getTaskById(1).getStatus());
        System.out.println(taskManager.getTaskById(2).getStatus());
        System.out.println(taskManager.getEpicById(3).getStatus());

        taskManager.removeTaskById(1);


        System.out.println(taskManager.getTasksList());
        System.out.println(taskManager.getEpicsList());
        System.out.println(taskManager.getSubtasksList());
        System.out.println(taskManager.getEpicSubtasksList(6));

        taskManager.removeSubtaskById(7);

        System.out.println(taskManager.getEpicSubtasksList(6));

    }
}
