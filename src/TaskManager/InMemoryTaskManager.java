package TaskManager;


import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private Map<Integer, Task> tasks;
    private Map<Integer, Epic> epics;
    private Map<Integer, Subtask> subtasks;
    private HistoryManager historyManager;


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
        epics.get(subtask.getEpicId()).addNewSubtask(subtask);
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
    public Collection<Task> getTasksList() {
        return tasks.values();
    }

    @Override
    public Collection<Epic> getEpicsList() {
        return epics.values();
    }

    @Override
    public Collection<Subtask> getSubtasksList() {
        return subtasks.values();
    }

    @Override
    public Collection<Subtask> getEpicSubtasksList(Integer epicId) {
        ArrayList<Subtask> epicSubtasksList = new ArrayList<>();
        for (Integer subtaskId : epics.get(epicId).getSubtasksStatuses().keySet()) {
            epicSubtasksList.add(subtasks.get(subtaskId));
        }
        return epicSubtasksList;
    }

    ///  Удаление всех задач (Задача/Эпик/Подзадача)
    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Override
    public void clearEpics() {
        epics.clear();
        subtasks.clear();
    }

    @Override
    public void clearSubtasks() {
        subtasks.clear();
        for (Integer epicId : epics.keySet()) {
            epics.get(epicId).clearSubtasks();
            updateEpicStatus(epicId);
        }
    }

    /// Удаление по идентификатору (Задача/Эпик/Подзадача)
    @Override
    public void removeTaskById(Integer taskId) {
        tasks.remove(taskId);
    }

    @Override
    public void removeEpicById(Integer epicId) {
        for (Integer subtaskId : epics.get(epicId).getSubtasksStatuses().keySet()) {
            subtasks.remove(subtaskId);
        }
        epics.remove(epicId);
    }

    @Override
    public void removeSubtaskById(Integer subtaskId) {
        Integer epicId = subtasks.get(subtaskId).getEpicId();
        epics.get(epicId).removeSubtaskById(subtaskId);
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
    }

    @Override
    public void updateSubtask(Subtask updatedSubtask) {
        subtasks.replace(updatedSubtask.getId(), updatedSubtask);
        epics.get(updatedSubtask.getEpicId()).updateSubtask(updatedSubtask);
        updateEpicStatus(updatedSubtask.getEpicId());
    }

    @Override
    public int getIdCounter() {
        return idCounter;
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
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
