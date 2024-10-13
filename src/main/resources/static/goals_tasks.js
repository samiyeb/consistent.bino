const apiUrl = 'http://localhost:8080/api'; // Update this with your backend API URL
let selectedUserId = null;

document.getElementById('user-select').addEventListener('change', (e) => {
    selectedUserId = e.target.value; // Get selected user ID
    fetchGoalsForUser(selectedUserId); // Fetch goals for the selected user
});

document.getElementById('add-goal-btn').addEventListener('click', () => {
    const title = document.getElementById('goal-title').value;
    const description = document.getElementById('goal-description').value;

    if (title && selectedUserId) {
        addGoal(title, description);
    } else {
        alert('Please select a user and enter a goal title.');
    }
});

async function fetchUsers() {
    const response = await fetch(`${apiUrl}/users`);
    const users = await response.json();
    const userSelect = document.getElementById('user-select');

    users.forEach(user => {
        const option = document.createElement('option');
        option.value = user.id;
        option.textContent = user.username;
        userSelect.appendChild(option);
    });
}

async function fetchGoalsForUser(userId) {
    const response = await fetch(`${apiUrl}/users/${userId}/goals`);
    const goals = await response.json();
    const goalList = document.getElementById('goal-list');
    goalList.innerHTML = ''; // Clear existing goals

    goals.forEach(goal => {
        const goalItem = document.createElement('li');
        goalItem.classList.add('list-group-item');
        goalItem.innerHTML = `
            <div>
                <h5>${goal.title}</h5>
                <p>${goal.description}</p>
                <div>
                    <progress class="progress-bar" value="${goal.progression}" max="100"></progress>
                    <span>${goal.progression}%</span>
                </div>
                <input type="text" placeholder="Task title" class="form-control mt-2 task-input">
                <button class="btn btn-secondary mt-2 add-task-btn">Add Task</button>
                <ul class="list-group mt-3 task-list"></ul>
            </div>
        `;

        const taskList = goalItem.querySelector('.task-list');
        const taskInput = goalItem.querySelector('.task-input');
        const addTaskBtn = goalItem.querySelector('.add-task-btn');

        // Event listener to add task
        addTaskBtn.addEventListener('click', async () => {
            const taskTitle = taskInput.value;
            if (taskTitle) {
                await addTask(goal.id, taskTitle);
                taskInput.value = ''; // Clear the input field
                await fetchTasksForGoal(goal.id, taskList, goalItem);
            }
        });

        fetchTasksForGoal(goal.id, taskList, goalItem); // Fetch tasks for each goal
        goalList.appendChild(goalItem);
    });
}

async function fetchTasksForGoal(goalId, taskList, goalItem) {
    const response = await fetch(`${apiUrl}/goals/${goalId}/tasks`);
    const tasks = await response.json();
    taskList.innerHTML = ''; // Clear existing tasks

    tasks.forEach(task => {
        const taskItem = document.createElement('li');
        taskItem.classList.add('list-group-item');
        taskItem.innerHTML = `
            <div class="form-check">
                <input class="form-check-input task-checkbox" type="checkbox" ${task.completed ? 'checked' : ''}>
                <label class="form-check-label">${task.title}</label>
            </div>
        `;

        const checkbox = taskItem.querySelector('.task-checkbox');
        checkbox.addEventListener('change', async () => {
            task.completed = checkbox.checked;
            await toggleTaskCompletion(task);
            updateProgress(goalId, goalItem); // Update progress bar
        });

        taskList.appendChild(taskItem);
    });

    updateProgress(goalId, goalItem); // Update progress bar when tasks are fetched
}

async function addGoal(title, description) {
    const newGoal = {
        title,
        description,
        progression: 0
    };

    await fetch(`${apiUrl}/users/${selectedUserId}/goals`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newGoal),
    });

    fetchGoalsForUser(selectedUserId);
}

async function addTask(goalId, taskTitle) {
    const newTask = {
        title: taskTitle,
        description: '',
        completed: false,
    };

    await fetch(`${apiUrl}/goals/${goalId}/tasks`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(newTask),
    });
}

async function toggleTaskCompletion(task) {
    await fetch(`${apiUrl}/tasks/${task.id}/complete`, {
        method: 'PATCH',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ completed: task.completed }),
    });
}

function updateProgress(goalId, goalItem) {
    const taskList = goalItem.querySelector('.task-list');
    const totalTasks = taskList.children.length;
    let completedTasks = 0;

    taskList.querySelectorAll('.task-checkbox').forEach(checkbox => {
        if (checkbox.checked) {
            completedTasks++;
        }
    });

    const progress = totalTasks > 0 ? (completedTasks / totalTasks) * 100 : 0;
    const progressBar = goalItem.querySelector('progress');
    progressBar.value = progress;
    goalItem.querySelector('span').textContent = `${progress.toFixed(2)}%`;

    // Optionally, update the backend with the progression value (goal.progression)
}

// Fetch initial users for selection
fetchUsers();






