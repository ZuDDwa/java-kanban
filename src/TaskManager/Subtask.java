package TaskManager;

public class Subtask extends Task {

    private Integer epicId;

    public Subtask(String title, String description, Integer id, Integer epicId) {
        super(title, description, id);
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
