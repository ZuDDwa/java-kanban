package TaskManager;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private ArrayList<Integer> subtasksId;

    private Integer idCounter;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasksId = new ArrayList<>();
        idCounter = 1;
    }

    ///  Создание задачи, эпика, подзадачи для конкретного эпика
    public void addNewTask(String title, String description) {
        Task newTask = new Task(title, description, idCounter);
        if (!tasks.containsValue(newTask)) {
            tasks.put(newTask.getId(), newTask);
            idCounter++;
        }
    }

    public void addNewEpic(String title, String description) {
        Epic newEpic = new Epic(title, description, idCounter);
        if (!epics.containsValue(newEpic)) {
            epics.put(newEpic.getId(), newEpic);
            idCounter++;
        }
    }

    public void addEpicSubtask(Integer epicId, String title, String description) {
        Subtask newSubtask = new Subtask(title, description, idCounter, epicId);
        if (!epics.get(epicId).getSubtasksMap().containsValue(newSubtask)) {
            epics.get(epicId).addNewSubtask(newSubtask);
            subtasksId.add(newSubtask.getId());
            idCounter++;
        }
    }

    /// Получение по идентификатору (Задача/Эпик/Подзадача)
    public Task getTaskById(Integer taskId) {
        return tasks.get(taskId);
    }

    public Epic getEpicById(Integer epicId) {
        return epics.get(epicId);
    }

    public Subtask getSubtaskById(Integer subtaskId) {
        Subtask subtaskById = null;
        for (Epic epic : epics.values()) {
            if (epic.getSubtasksMap().containsKey(subtaskId)) {
                subtaskById = epic.getSubtasksMap().get(subtaskId);
            }
        }
        return subtaskById;
    }

    /// Получение списка всех задач (Задача/Эпик/Подзадача)
    public Collection<Task> getTasksList() {
        return tasks.values();
    }

    public Collection<Epic> getEpicsList() {
        return epics.values();
    }

    public Collection<Subtask> getSubtasksList() {
        ArrayList<Subtask> allSubtasks = new ArrayList<>();
        for (Epic epic : epics.values()) {
            allSubtasks.addAll(epic.getSubtasksMap().values());
        }
        return allSubtasks;
    }

    public Collection<Subtask> getEpicSubtasksList(Integer epicId) {
        return epics.get(epicId).getSubtasksMap().values();
    }

    ///  Удаление всех задач (Задача/Эпик/Подзадача)
    public void clearTasks() {
        tasks.clear();
    }

    public void clearEpics() {
        epics.clear();
    }

    public void clearSubtasks(Integer epicId) {
        epics.get(epicId).clearSubtasks();
    }

    /// Удаление по идентификатору (Задача/Эпик/Подзадача)
    public void removeTaskById(Integer taskId) {
        tasks.remove(taskId);
    }

    public void removeEpicById(Integer epicId) {
        epics.remove(epicId);
    }

    public void removeSubtaskById(Integer subtaskId) {
        for (Epic epic : epics.values()) {
            if (epic.getSubtasksMap().containsKey(subtaskId)) {
                epic.removeSubtaskById(subtaskId);
            }
        }
    }

    ///  Обновление (Задача/Эпик/Подзадача)
    public void updateById (Integer id, String title, String description) {

        if (tasks.containsKey(id)) {
            Task updatedTask = new Task(title, description, id);
            tasks.replace(id, tasks.get(id), updatedTask);
        } else if (epics.containsKey(id)) {
            Epic updatedEpic = new Epic(title, description, id);
            epics.replace(id, epics.get(id), updatedEpic);
        } else if (subtasksId.contains(id)){
            for (Epic epic : epics.values()) {
                if (epic.getSubtasksMap().containsKey(id)) {
                    epic.updateSubtaskById(id, title, description);;
                }
            }
        }
    }

    /// Обновление статуса (Задача/Эпик/Подзадача)
    public void setStatusById(Integer id, Status status) {
        if (tasks.containsKey(id)) {
            tasks.get(id).setStatus(status);
        } else if (subtasksId.contains(id)){
            for (Epic epic : epics.values()) {
                if (epic.getSubtasksMap().containsKey(id)) {
                    epic.getSubtasksMap().get(id).setStatus(status);
                }
                if (!epic.getSubtasksStatuses().isEmpty() && !epic.getSubtasksStatuses().contains(Status.NEW)
                        && !epic.getSubtasksStatuses().contains(Status.IN_PROGRESS)) {
                    epic.setStatus(Status.DONE);
                } else if (epic.getSubtasksStatuses().contains(Status.IN_PROGRESS)
                        || epic.getSubtasksStatuses().contains(Status.DONE)) {
                    epic.setStatus(Status.IN_PROGRESS);
                }
            }
        }
    }
}
