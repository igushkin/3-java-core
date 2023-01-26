package Manager;

import Task.*;

import java.util.*;
import java.util.stream.Collectors;

public class Manager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private static int id;

    static {
        id = 0;
    }

    public Manager() {
        this.tasks = new HashMap<>();
        this.epics = new HashMap<>();
    }

    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(this.tasks.values());
    }

    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(this.epics.values());
    }

    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(this.epics.values().stream()
                .flatMap(i -> i.getSubtasks().values().stream())
                .collect(Collectors.toList())
        );
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Subtask getSubtaskById(int id) {
        return getAllSubtasks().stream()
                .filter(i -> i.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void createTask(Task task) {
        if (task.getId() == null) {
            task.setId(getNewId());
        }
        if (tasks.containsKey(task.getId())) {
            throw new RuntimeException();
        } else {
            tasks.put(task.getId(), task);
        }
    }

    public void createEpic(Epic epic) {
        if (epic.getId() == null) {
            epic.setId(getNewId());
        }
        if (epics.containsKey(epic.getId())) {
            throw new RuntimeException();
        } else {
            epics.put(epic.getId(), epic);
        }
    }

    public void createSubtask(Subtask subtask) {
        if (subtask.getId() == null) {
            subtask.setId(getNewId());
        }
        if (getSubtaskById(subtask.getId()) != null || subtask.getEpic() == null) {
            throw new RuntimeException();
        } else {
            subtask.getEpic().addSubtask(subtask);
        }
    }

    public void updateTask(Task task) {
        if (!tasks.containsKey(task.getId())) {
            throw new RuntimeException();
        } else {
            tasks.put(task.getId(), task);
        }
    }

    public void updateEpic(Epic updatedEpic) {
        if (!epics.containsKey(updatedEpic.getId())) {
            throw new RuntimeException();
        } else {
            Epic epic = epics.get(updatedEpic.getId());
            epic.setName(updatedEpic.getName());
            epic.setDescription(updatedEpic.getDescription());
        }
    }

    public void updateSubtask(Subtask subtask) {
        if (subtask.getEpic() == null) {
            throw new RuntimeException();
        } else {
            subtask.getEpic().updateSubtask(subtask);
        }
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.deleteAllSubtasks();
        }
    }

    public void deleteTaskById(int id) {
        if (!tasks.containsKey(id)) {
            throw new RuntimeException();
        } else {
            tasks.remove(id);
        }
    }

    public void deleteEpicById(int id) {
        if (!epics.containsKey(id)) {
            throw new RuntimeException();
        } else {
            epics.remove(id);
        }
    }

    public void deleteSubtaskById(int id) {
        Subtask toRemove = getSubtaskById(id);
        Epic epic = toRemove.getEpic();
        epic.deleteSubtaskById(id);
    }

    public HashMap<Integer, Subtask> getSubtasksByEpicId(int id) {
        return epics.get(id).getSubtasks();
    }

    private static int getNewId() {
        return id++;
    }
}
