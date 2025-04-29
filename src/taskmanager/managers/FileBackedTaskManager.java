package taskmanager.managers;

import taskmanager.exceptions.ManagerSaveException;
import taskmanager.tasks.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private final Path managerFilePath;
    private final String fileHeader = "id,type,name,status,description,epic";


    public FileBackedTaskManager(Path managerFilePath) throws RuntimeException {
        super();
        this.managerFilePath = managerFilePath;
        if (!Files.exists(managerFilePath)) {
            try {
                Files.createFile(managerFilePath);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        loadFromFile();
    }

    private void loadFromFile() {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(managerFilePath.toFile()))) {
            while (fileReader.ready()) {
                String line = fileReader.readLine();
                if (!(line.isBlank() || line.equals(fileHeader))) {
                    Task task = getTaskFromString(line);
                    if (idCounter < task.getId()) {
                        idCounter = task.getId() + 1;
                    }

                    switch (task.getType()) {
                        case TASK -> tasks.put(task.getId(), task);
                        case EPIC -> {
                            Epic epic = (Epic) task;
                            epics.put(epic.getId(), epic);
                        }
                        case SUBTASK -> {
                            Subtask subtask = (Subtask) task;
                            subtasks.put(subtask.getId(), subtask);
                        }

                        default -> throw new IllegalStateException("Unexpected value: " + task.getType());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Epic epic : epics.values()) {
            updateEpicStatus(epic.getId());
        }

    }

    ///  Создание задачи, эпика, подзадачи для конкретного эпика
    @Override
    public void addNewTask(Task task) {
        super.addNewTask(task);
        save();
    }

    @Override
    public void addNewEpic(Epic epic) {
        super.addNewEpic(epic);
        save();
    }

    @Override
    public void addNewEpicSubtask(Subtask subtask) {
        super.addNewEpicSubtask(subtask);
        save();
    }

    ///  Удаление всех задач (Задача/Эпик/Подзадача)
    @Override
    public void clearTasks() {
        super.clearTasks();
        save();
    }

    @Override
    public void clearEpics() {
        super.clearEpics();
        save();
    }

    @Override
    public void clearSubtasks() {
        super.clearSubtasks();
        save();
    }

    /// Удаление по идентификатору (Задача/Эпик/Подзадача)
    @Override
    public void removeTaskById(Integer taskId) {
       super.removeTaskById(taskId);
       save();
    }

    @Override
    public void removeEpicById(Integer epicId) {
        super.removeEpicById(epicId);
        save();
    }

    @Override
    public void removeSubtaskById(Integer subtaskId) {
        super.removeSubtaskById(subtaskId);
        save();
    }

    ///  Обновление (Задача/Эпик/Подзадача)
    @Override
    public void updateTask(Task updatedTask) {
        super.updateTask(updatedTask);
        save();
    }

    @Override
    public void updateEpic(Epic updatedEpic) {
        super.updateEpic(updatedEpic);
        save();
    }

    @Override
    public void updateSubtask(Subtask updatedSubtask) {
        super.updateSubtask(updatedSubtask);
        save();
    }

    public Task getTaskFromString(String task) {
        String[] taskData = task.split(",");  // id,type,name,status,description,epicId
        Integer id = Integer.parseInt(taskData[0]);
        Type type = Type.valueOf(taskData[1]);
        String title = taskData[2];
        Status status = Status.valueOf(taskData[3]);
        String description = taskData[4];

        return switch (type) {
            case TASK -> new Task(id, title, description, status);
            case EPIC -> new Epic(id, title, description);
            case SUBTASK -> {
                int epicId = Integer.parseInt(taskData[5]);
                yield new Subtask(id, title, description, status, epicId);
            }
            default -> null;
        };
    }


    private void save() throws ManagerSaveException {
        try (Writer fileWriter = new FileWriter(managerFilePath.toFile())) {
            fileWriter.write(fileHeader + "\n\n");

            for (Task task : tasks.values()) fileWriter.write(task.toString() + "\n");
            for (Epic epic : epics.values()) fileWriter.write(epic.toString() + "\n");
            for (Subtask subtask : subtasks.values()) fileWriter.write(subtask.toString() + "\n");
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка сохранения данных");
        }
    }


}
