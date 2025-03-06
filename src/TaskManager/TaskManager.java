package TaskManager;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, Subtask> subtasks;


    private Integer idCounter;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        idCounter = 1;
    }

    ///  Создание задачи, эпика, подзадачи для конкретного эпика
    public void addNewTask(Task task) {
        setId(task);
        tasks.put(task.getId(), task);
    }

    public void addNewEpic(Epic epic) {
        setId(epic);
        epics.put(epic.getId(), epic);
    }

    public void addEpicSubtask(Subtask subtask) {
        setId(subtask);
        subtasks.put(subtask.getId(), subtask);
        epics.get(subtask.getEpicId()).addNewSubtask(subtask);
        updateEpicStatus(subtask.getEpicId());
    }

    /// Получение по идентификатору (Задача/Эпик/Подзадача)
    public Task getTaskById(Integer taskId) {
        return tasks.get(taskId);
    }

    public Epic getEpicById(Integer epicId) {
        return epics.get(epicId);
    }

    public Subtask getSubtaskById(Integer subtaskId) {
        return subtasks.get(subtaskId);
    }

    /// Получение списка всех задач (Задача/Эпик/Подзадача)
    public Collection<Task> getTasksList() {
        return tasks.values();
    }

    public Collection<Epic> getEpicsList() {
        return epics.values();
    }

    public Collection<Subtask> getSubtasksList() {
        return subtasks.values();
    }

    public Collection<Subtask> getEpicSubtasksList(Integer epicId) {
        ArrayList<Subtask> epicSubtasksList = new ArrayList<>();
        for (Integer subtaskId : epics.get(epicId).getSubtasksStatuses().keySet()) {
            epicSubtasksList.add(subtasks.get(subtaskId));
        }
        return epicSubtasksList;
    }

    ///  Удаление всех задач (Задача/Эпик/Подзадача)
    public void clearTasks() {
        tasks.clear();
    }

    public void clearEpics() {
        epics.clear();
        subtasks.clear();
    }

    public void clearSubtasks() {
        subtasks.clear();
        for (Integer epicId : epics.keySet()) {
            epics.get(epicId).clearSubtasks();
            updateEpicStatus(epicId);
        }
    }

    /// Удаление по идентификатору (Задача/Эпик/Подзадача)
    public void removeTaskById(Integer taskId) {
        tasks.remove(taskId);
    }

    public void removeEpicById(Integer epicId) {
        for (Integer subtaskId : epics.get(epicId).getSubtasksStatuses().keySet()) {
            subtasks.remove(subtaskId);
        }
        epics.remove(epicId);
    }

    public void removeSubtaskById(Integer subtaskId) {
        Integer epicId = subtasks.get(subtaskId).getEpicId();
        epics.get(epicId).removeSubtaskById(subtaskId);
        subtasks.remove(subtaskId);
        updateEpicStatus(epicId);
    }

    ///  Обновление (Задача/Эпик/Подзадача)
    public void updateTask(Task updatedTask) {
        tasks.replace(updatedTask.getId(), updatedTask);
    }

    public void updateEpic(Epic updatedEpic) {
        epics.replace(updatedEpic.getId(), updatedEpic);
    }

    public void updateSubtask(Subtask updatedSubtask) {
        subtasks.replace(updatedSubtask.getId(), updatedSubtask);
        epics.get(updatedSubtask.getEpicId()).updateSubtask(updatedSubtask);
        updateEpicStatus(updatedSubtask.getEpicId());
    }

    /// Обновление статуса эпика
    private void updateEpicStatus(Integer epicId) {
        Collection<Status> subtasksStatuses = epics.get(epicId).getSubtasksStatuses().values();

        if (subtasksStatuses.isEmpty() || (!subtasksStatuses.contains(Status.IN_PROGRESS)
                && !subtasksStatuses.contains(Status.DONE))) {
            epics.get(epicId).setStatus(Status.NEW);
        } else if (!subtasksStatuses.contains(Status.IN_PROGRESS) && !subtasksStatuses.contains(Status.NEW)) {
            epics.get(epicId).setStatus(Status.DONE);
        } else {
            epics.get(epicId).setStatus(Status.IN_PROGRESS);
        }
    }

    /// Присвоение id новому заданию
    private void setId(Task task) {
        task.setId(idCounter++);
    }

}
