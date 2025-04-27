package taskmanager.tasks;

import java.util.Objects;

public class Task {
    protected String title;
    protected String description;
    protected int id;
    protected Status status;
    final Type type = Type.TASK;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = Status.NEW;

    }

    /// Конструктор для обновления задачи
    public Task(Integer id, String title, String description, Status status) {
        this(title, description);
        this.id = id;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return  id + "," + type + "," + title + "," + status + "," + description;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public Type getType() {
        return type;
    }
}
