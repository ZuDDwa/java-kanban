package TaskManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Epic extends Task {

    private HashMap<Integer, Subtask> subtasks;


    public Epic(String title, String description, int id) {
        super(title, description, id);
        subtasks = new HashMap<>();

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
        subtasks.put(newSubtask.getId(), newSubtask);

    }

    public HashMap<Integer, Subtask> getSubtasksMap() {
        return subtasks;
    }

    public void clearSubtasks() {
        subtasks.clear();
    }

    public void removeSubtaskById(Integer subtaskId) {
        subtasks.remove(subtaskId);
    }

    public void updateSubtaskById(Integer id, String title, String description) {
        Subtask updatedSubtask = new Subtask(title, description, id, this.id);
        subtasks.replace(id, subtasks.get(id), updatedSubtask);
    }

    public Collection<Status> getSubtasksStatuses() {
        ArrayList<Status> subtaskStatuses = new ArrayList<>();
        for (Subtask subtask : subtasks.values()) {
            subtaskStatuses.add(subtask.getStatus());
        }
        return subtaskStatuses;
    }
}
