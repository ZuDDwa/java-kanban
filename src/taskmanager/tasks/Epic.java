package taskmanager.tasks;

import java.util.HashMap;

public class Epic extends Task {

    private final HashMap<Integer, Status> subtasksStatuses = new HashMap<>();

    final Type type = Type.EPIC;

    public Epic(String title, String description) {
        super(title, description);
    }

    /// Конструктор для обновления эпика
    public Epic(Integer id, String title, String description) {
        this(title, description);
        this.id = id;

    }

    @Override
    public String toString() {
        return  id + "," + type + "," + title + "," + status + "," + description;
    }

    public Type getType() {
        return type;
    }


}
