# 3-java-core

This is part of the Java Developer course from Yandex.
Module - standard Java library and its features.

The repository contains my solution that has been verified from the reviewer to the task described below.

Technical specification
===================

How does a person usually shop? If he needs not one product, but several, then it is very likely that he will first make a list so as not to forget anything. You can do this anywhere: on a piece of paper, in a note-taking application, or, for example, in a message to yourself in a messenger.

And now imagine that this is not a list of products, but of full-fledged cases. And not some simple ones like **"** wash the dishes**"** or **"** call grandma**",** but complex ones — for example, **"** organize a big family holiday**"** or **"** buy an apartment**"**. Each of these tasks can be divided into several stages with its own nuances and deadlines. And if not one person, but a whole team will work on their implementation, then the organization of the process will become even more difficult.

Task Tracker
------------

Both version control systems help the team work with common code, and task trackers allow you to effectively organize joint work on tasks. You have to write a backend for such a tracker. As a result, you should get a program responsible for forming a data model for this page:

![image](https://pictures.s3.yandex.net:443/resources/Untitled_25_1639469823.png)

The user will not see the console of your application. Therefore, you need to make sure that the methods do not just print something to the console, but also return objects of the desired types.
You can add console output for self-checking in the `Main` class, but it should not affect the operation of methods.

Types of tasks
----------

The simplest building block of such a system is the **task** (Eng. _task_). The task has the following properties:

1. **Name**, briefly describing the essence of the task (for example, **"**Moving**"**).
2. ** Description**, which reveals the details.
3. **The unique identification number of the task** by which it can be found.
4. **Status** showing her progress. We will highlight the following stages of the task's life:

1. `NEW` — the task has just been created, but it has not yet been started.
2. `IN_PROGRESS` — the task is being worked on.
3. `DONE' — the task is completed.

Sometimes, to perform some large-scale task, it is better to break it into ** subtasks ** (Eng. _subtask_). A large task, which is divided into subtasks, we will call ** epic ** (Eng. _epic_).

Thus, there can be three types of tasks in our system: regular tasks, epics and subtasks. The following conditions must be met for them:

* For each subtask, it is known within which epic it is performed.
* Every epic knows what subtasks it includes.
* The completion of all epic subtasks is considered the completion of the epic.

### Task ID

Each task type has an identifier. This is an integer that is unique for all types of tasks. We use it to find, update, and delete tasks. When creating a task, the manager assigns a new identifier to it.


Manager
--------

In addition to classes for describing tasks, you need to implement a class for the manager object. It will be launched at the start of the program and manage all tasks. The following functions should be implemented in it:

1. The ability to store tasks of all types. To do this, you need to choose a suitable collection.
2. Methods for each of the task types (Task/Epic/Subtask):
1. Getting a list of all tasks.
2. Delete all tasks.
3. Getting by ID.
4. Creation. The object itself must be passed as a parameter.
5. Update. The new version of the object with the correct identifier is passed as a parameter.
6. Deletion by ID.
3. Additional methods:
1. Getting a list of all subtasks of a certain epic.
4. Statuses are managed according to the following rule:

1. The manager does not choose the status for the task himself. Information about it comes to the manager along with information about the task itself. According to this data, in some cases it will retain the status, in others it will count.
2. For epics:

* if the epic has no subtasks or all of them have the status `NEW`, then the status should be `NEW'.
* if all subtasks have the status `DONE`, then the epic is considered completed — with the status `DONE'.
* in all other cases, the status should be `IN_PROGRESS'.
