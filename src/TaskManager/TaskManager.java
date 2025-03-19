package TaskManager;

import java.util.Collection;
import java.util.List;

public interface TaskManager {
    void addNewTask(Task task);

    void addNewEpic(Epic epic);

    void addNewEpicSubtask(Subtask subtask);

    Task getTaskById(Integer taskId);

    Epic getEpicById(Integer epicId);

    Subtask getSubtaskById(Integer subtaskId);

    Collection<Task> getTasksList();

    Collection<Epic> getEpicsList();

    Collection<Subtask> getSubtasksList();

    Collection<Subtask> getEpicSubtasksList(Integer epicId);

    void clearTasks();

    void clearEpics();

    void clearSubtasks();

    void removeTaskById(Integer taskId);

    void removeEpicById(Integer epicId);

    void removeSubtaskById(Integer subtaskId);

    void updateTask(Task updatedTask);

    void updateEpic(Epic updatedEpic);

    void updateSubtask(Subtask updatedSubtask);

    int getIdCounter();

    List<Task> getHistory();
}
