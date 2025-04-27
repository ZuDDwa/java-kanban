package taskmanager.managers;


import taskmanager.interfaces.HistoryManager;
import taskmanager.tasks.Status;
import taskmanager.tasks.Subtask;
import taskmanager.interfaces.TaskManager;
import taskmanager.tasks.Epic;
import taskmanager.tasks.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    protected Map<Integer, Task> tasks;
    protected Map<Integer, Epic> epics;
    protected Map<Integer, Subtask> subtasks;
    protected HistoryManager historyManager;


    private Integer idCounter;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        historyManager = new InMemoryHistoryManager();
        idCounter = 1;
    }

    ///  Создание задачи, эпика, подзадачи для конкретного эпика
    @Override
    public void addNewTask(Task task) {
        setId(task);
        tasks.put(task.getId(), task);
    }

    @Override
    public void addNewEpic(Epic epic) {
        setId(epic);
        epics.put(epic.getId(), epic);
    }

    @Override
    public void addNewEpicSubtask(Subtask subtask) {
        setId(subtask);
        subtasks.put(subtask.getId(), subtask);
        updateEpicStatus(subtask.getEpicId());
    }

    /// Получение по идентификатору (Задача/Эпик/Подзадача)
    @Override
    public Task getTaskById(Integer taskId) {
        historyManager.add(tasks.get(taskId));
        return tasks.get(taskId);
    }

    @Override
    public Epic getEpicById(Integer epicId) {
        historyManager.add(epics.get(epicId));
        return epics.get(epicId);
    }

    @Override
    public Subtask getSubtaskById(Integer subtaskId) {
        historyManager.add(subtasks.get(subtaskId));
        return subtasks.get(subtaskId);
    }

    /// Получение списка всех задач (Задача/Эпик/Подзадача)
    @Override
    public List<Task> getTasksList() {
        return new ArrayList<>(tasks.values());
    }

    @Override
    public List<Epic> getEpicsList() {
        return new ArrayList<>(epics.values());
    }

    @Override
    public List<Subtask> getSubtasksList() {
        return new ArrayList<>(subtasks.values());
    }

    @Override
    public List<Subtask> getEpicSubtasksList(int epicId) {
        List<Subtask> epicSubtasksList = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == epicId) {
                epicSubtasksList.add(subtask);
            }
        }
        return epicSubtasksList;
    }

    ///  Удаление всех задач (Задача/Эпик/Подзадача)
    @Override
    public void clearTasks() {
        for (Integer id : tasks.keySet()) {
            historyManager.remove(id);
        }
        tasks.clear();
    }

    @Override
    public void clearEpics() {
        for (Integer id : epics.keySet()) {
            historyManager.remove(id);
        }
        for (Integer id : subtasks.keySet()) {
            historyManager.remove(id);
        }
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void clearSubtasks() {
        for (Integer id : subtasks.keySet()) {
            historyManager.remove(id);
        }
        for (Integer epicId : epics.keySet()) {
            updateEpicStatus(epicId);
        }
        subtasks.clear();
    }

    /// Удаление по идентификатору (Задача/Эпик/Подзадача)
    @Override
    public void removeTaskById(Integer taskId) {
        tasks.remove(taskId);
        historyManager.remove(taskId);
    }

    @Override
    public void removeEpicById(Integer epicId) {
        for (Subtask subtask : getEpicSubtasksList(epicId)) {
            subtasks.remove(subtask.getId());
            historyManager.remove(subtask.getId());
        }
        epics.remove(epicId);
        historyManager.remove(epicId);
    }

    @Override
    public void removeSubtaskById(Integer subtaskId) {
        Integer epicId = subtasks.get(subtaskId).getEpicId();
        historyManager.remove(subtaskId);
        subtasks.remove(subtaskId);
        updateEpicStatus(epicId);
    }

    ///  Обновление (Задача/Эпик/Подзадача)
    @Override
    public void updateTask(Task updatedTask) {
        tasks.replace(updatedTask.getId(), updatedTask);
    }

    @Override
    public void updateEpic(Epic updatedEpic) {
        epics.replace(updatedEpic.getId(), updatedEpic);
        updateEpicStatus(updatedEpic.getId());
    }

    @Override
    public void updateSubtask(Subtask updatedSubtask) {
        subtasks.replace(updatedSubtask.getId(), updatedSubtask);
        updateEpicStatus(updatedSubtask.getEpicId());
    }

    @Override
    public int getIdCounter() {
        return idCounter;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getTasks();
    }

    /// Обновление статуса эпика
    protected void updateEpicStatus(int epicId) {
        List<Status> subtasksStatuses = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            if (subtask.getEpicId() == epicId) {
                subtasksStatuses.add(subtask.getStatus());
            }
        }
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
    protected void setId(Task task) {
        task.setId(idCounter++);
    }
}
