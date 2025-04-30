package taskmanager.tasks;

public class Subtask extends Task {

    private Integer epicId;
    final Type type = Type.SUBTASK;

    public Subtask(String title, String description, Integer epicId) {
        super(title, description);
        this.epicId = epicId;
    }

    /// Конструктор для обновления подзадачи
    public Subtask(Integer id, String title, String description, Status status, Integer epicId) {
        super(id, title, description, status);
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return id + "," + type + "," + title + "," + status + "," + description + "," + epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    public Type getType() {
        return type;
    }
}
