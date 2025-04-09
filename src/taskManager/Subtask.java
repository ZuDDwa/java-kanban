package taskManager;

public class Subtask extends Task {

    private Integer epicId;

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
        return "TaskManager.Subtask{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", epicId=" + epicId +
                ", status=" + status +
                '}';
    }

    public Integer getEpicId() {
        return epicId;
    }
}
