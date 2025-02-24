# Ron Chatbot - User Guide

Welcome to **Ron Chatbot**, your personal assistant for managing tasks efficiently! This guide will walk you through the features and commands available in Ron.

## Getting Started
Ron is a simple command-line chatbot that helps you track and manage your tasks. Simply type a command, and Ron will respond accordingly.

## Features

### 1. Add a To-Do Task
- Adds a simple task to your list.
- **Command:** `todo TASK_DESCRIPTION`
- **Example:** `todo Buy groceries`

### 2. Add a Deadline Task
- Adds a task with a deadline.
- **Command:** `deadline TASK_DESCRIPTION /by YYYY-MM-DD HH:MM`
- **Example:** `deadline Submit report /by 2025-03-15 23:59`

### 3. Add an Event Task
- Adds an event with a start and end time.
- **Command:** `event TASK_DESCRIPTION /from YYYY-MM-DD HH:MM /to YYYY-MM-DD HH:MM`
- **Example:** `event Project meeting /from 2025-03-10 14:00 /to 2025-03-10 16:00`

### 4. List All Tasks
- Displays all tasks in the list.
- **Command:** `list`

### 5. Mark a Task as Done
- Marks a task as completed.
- **Command:** `mark TASK_NUMBER`
- **Example:** `mark 2`

### 6. Unmark a Task
- Marks a task as not completed.
- **Command:** `unmark TASK_NUMBER`
- **Example:** `unmark 2`

### 7. Delete a Task
- Removes a task from the list.
- **Command:** `delete TASK_NUMBER`
- **Example:** `delete 3`

### 8. Find a Task
- Searches for tasks containing a keyword.
- **Command:** `find KEYWORD`
- **Example:** `find groceries`

### 9. Exit the Program
- Exits Ron chatbot.
- **Command:** `bye`

### 10. Help List
- Get a list of help commands to run
- **Command:** `help`

## Installation
1. Ensure you have Java 17 or later installed.
2. Download the latest `ron.jar` from the [Releases](https://github.com/{your-username}/ip/releases) page.
3. Open a terminal or command prompt in the directory containing `ron.jar`.
4. Run the following command:
   ```sh
   java -jar ron.jar
   ```

## GitHub Pages User Guide
To view this guide in GitHub Pages format, visit:
[https://salads-source.github.io/ip](https://salads-source.github.io/ip)

---
*Enjoy using Ron Chatbot! If you encounter any issues, feel free to report them on the [GitHub Issues](https://github.com/{your-username}/ip/issues) page.* 🚀
