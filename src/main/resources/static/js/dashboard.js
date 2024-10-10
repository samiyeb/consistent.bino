document.addEventListener('DOMContentLoaded', () => {
    const userId = sessionStorage.getItem('userId');
    const userName = sessionStorage.getItem('username');
    const goalForm = document.getElementById('goal-form');
    const goalList = document.getElementById('goal-list');

    // Set user name in the header
    document.getElementById('user-name').textContent = userName;

    // Fetch and display goals when the page loads
    function fetchGoals() {
        fetch(`http://localhost:8080/api/users/${userId}/goals`)
            .then(response => response.json())
            .then(goals => {
                goalList.innerHTML = '';
                goals.forEach(goal => createGoalElement(goal));
            });
    }

    // Create a list item for each goal with dropdown for tasks
    function createGoalElement(goal) {
        const goalItem = document.createElement('li');
        goalItem.classList.add('goal-item');
        goalItem.innerHTML = `
            <div class="goal-header">
                <span>${goal.title} - ${goal.description}</span>
                <progress value="${goal.progression}" max="100"></progress>
            </div>
            <div class="task-dropdown">
                <button class="toggle-task-list">Show Tasks</button>
                <ul class="task-list" style="display: none;"></ul>
                <form class="task-form" style="display: none;">
                    <input type="text" class="task-title" placeholder="Task Title" required>
                    <input type="text" class="task-description" placeholder="Task Description" required>
                    <button type="submit">Add Task</button>
                </form>
            </div>
        `;

        goalItem.querySelector('.toggle-task-list').addEventListener('click', () => {
            const taskList = goalItem.querySelector('.task-list');
            const taskForm = goalItem.querySelector('.task-form');
            taskList.style.display = taskList.style.display === 'none' ? 'block' : 'none';
            taskForm.style.display = taskForm.style.display === 'none' ? 'block' : 'none';
            fetchTasks(goal.id, taskList);
        });

        // Handle adding a new task for the goal
        goalItem.querySelector('.task-form').addEventListener('submit', (e) => {
            e.preventDefault();
            const taskTitle = goalItem.querySelector('.task-title').value;
            const taskDescription = goalItem.querySelector('.task-description').value;

            const task = {
                title: taskTitle,
                description: taskDescription,
            };

            // POST request to add a new task
            fetch(`http://localhost:8080/api/goals/${goal.id}/tasks`, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(task),
            })
            .then(() => fetchTasks(goal.id, goalItem.querySelector('.task-list')));
        });

        goalList.appendChild(goalItem);
    }

    // Fetch tasks for a specific goal and display them
    function fetchTasks(goalId, taskList) {
        fetch(`http://localhost:8080/api/goals/${goalId}/tasks`)
            .then(response => response.json())
            .then(tasks => {
                taskList.innerHTML = '';
                tasks.forEach(task => {
                    const taskItem = document.createElement('li');
                    taskItem.innerHTML = `
                        <label>
                            <input type="checkbox" ${task.completed ? 'checked' : ''} data-task-id="${task.id}">
                            ${task.title} - ${task.description}
                        </label>
                    `;
                    taskItem.querySelector('input').addEventListener('change', (e) => {
                        toggleTaskCompletion(e, task.id, goalId);
                    });
                    taskList.appendChild(taskItem);
                });
            });
    }

    // Toggle task completion and update the goal progression
    function toggleTaskCompletion(event, taskId, goalId) {
        const isCompleted = event.target.checked;

        fetch(`http://localhost:8080/api/tasks/${taskId}/complete`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ completed: isCompleted })
        })
        .then(() => fetchGoals());  // Refresh the goal list and progression bars
    }

    // Add a new goal for the user
    goalForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const goal = {
            title: document.getElementById('goal-title').value,
            description: document.getElementById('goal-description').value,
        };

        fetch(`http://localhost:8080/api/users/${userId}/goals`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(goal),
        })
        .then(() => fetchGoals());
    });

    // Initial fetch of goals when the page loads
    fetchGoals();
});
