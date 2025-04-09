package taskManager;

import java.util.HashMap;

public class Epic extends Task {

    private HashMap<Integer, Status> subtasksStatuses;

    public Epic(String title, String description) {
        super(title, description);
        subtasksStatuses = new HashMap<>();
    }

    /// Конструктор для обновления эпика
    public Epic(Integer id, String title, String description) {
        this(title, description);
        this.id = id;
    }

    @Override
    public String toString() {
        return "TaskManager.Epic{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }

    public void addNewSubtask(Subtask newSubtask) {
        subtasksStatuses.put(newSubtask.getId(), newSubtask.getStatus());
    }

    public HashMap<Integer, Status> getSubtasksStatuses() {
        return subtasksStatuses;
    }

    public void clearSubtasks() {
        subtasksStatuses.clear();
    }

    public void removeSubtaskById(Integer subtaskId) {
        subtasksStatuses.remove(subtaskId);
    }

    public void updateSubtask(Subtask subtask) {
        subtasksStatuses.replace(subtask.getId(), subtask.getStatus());
    }






}
