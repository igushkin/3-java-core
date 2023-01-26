import Manager.*;
import Task.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager();
        addTasks(manager);

        ArrayList<Task> allTasks = manager.getAllTasks();
        ArrayList<Epic> allEpics = manager.getAllEpics();
        ArrayList<Subtask> allSubtasks = manager.getAllSubtasks();

        System.out.println(allTasks);
        System.out.println(allEpics);
        System.out.println(allSubtasks);


        // Test #1
        Task newTask = new Task(0, "Новая задача", "Новое описание", TaskStatus.DONE);
        manager.updateTask(newTask);
        boolean isCorrect = manager.getAllTasks().size() == 2
                && manager.getTaskById(0).getStatus() == newTask.getStatus()
                && manager.getTaskById(0).getName() == newTask.getName()
                && manager.getTaskById(0).getDescription() == newTask.getDescription();

        System.out.println(isCorrect);


        // Test #2
        manager.deleteTaskById(0);
        isCorrect = manager.getAllTasks().size() == 1
                && manager.getAllTasks().get(0).getId() == 1;

        System.out.println(isCorrect);


        // Test #3
        manager.deleteAllTasks();
        isCorrect = manager.getAllTasks().size() == 0;

        System.out.println(isCorrect);


        // Test #4
        Epic newEpic = new Epic(2, "Новый эпик", "Новое описание эпика", TaskStatus.NEW);
        manager.updateEpic(newEpic);
        isCorrect = manager.getEpicById(2).getName() == newEpic.getName()
                && manager.getEpicById(2).getDescription() == newEpic.getDescription();

        System.out.println(isCorrect);


        //Test #5
        manager.deleteSubtaskById(5);
        Epic epic = manager.getEpicById(2);
        isCorrect = manager.getEpicById(2).getStatus() == TaskStatus.NEW
                && manager.getEpicById(2).getSubtasks().size() == 1
                && manager.getEpicById(2).getSubtasks().get(4).getId() == 4;

        System.out.println(isCorrect);

        addTasks(manager);


        //Test #6
        manager.deleteAllSubtasks();
        isCorrect = manager.getAllSubtasks().stream().allMatch(i -> i.getStatus() == TaskStatus.NEW)
                && manager.getAllEpics().stream().allMatch(i -> i.getSubtasks().size() == 0);

        System.out.println(isCorrect);
    }

    public static void addTasks(Manager manager) {
        Task task1 = new Task("Задача 1", "Описание 1", TaskStatus.NEW);
        Task task2 = new Task("Задача 2", "Описание 2", TaskStatus.NEW);

        Epic epic1 = new Epic("Эпик 1", "Описание 1", TaskStatus.NEW);
        Epic epic2 = new Epic("Эпик 2", "Описание 2", TaskStatus.NEW);

        manager.createTask(task1);
        manager.createTask(task2);
        manager.createEpic(epic1);
        manager.createEpic(epic2);

        manager.createSubtask(new Subtask(epic1, "Подзадача 1", "Описание подзадачи 1", TaskStatus.NEW));
        manager.createSubtask(new Subtask(epic1, "Подзадача 2", "Описание подзадачи 2", TaskStatus.NEW));
        manager.createSubtask(new Subtask(epic2, "Подзадача 1", "Описание подзадачи 1", TaskStatus.NEW));
    }
}