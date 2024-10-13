const userList = document.getElementById('user-list');
const createUserBtn = document.getElementById('create-user-btn');
const usernameInput = document.getElementById('username');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');

// Fetch and display users
async function fetchUsers() {
    try {
        const response = await fetch('/api/users');
        if (!response.ok) throw new Error('Failed to fetch users');
        const users = await response.json();
        userList.innerHTML = '';
        users.forEach(user => {
            const li = document.createElement('li');
            li.classList.add('list-group-item');
            li.textContent = user.username; // Display the username
            userList.appendChild(li);
        });
    } catch (error) {
        alert(error.message);
    }
}

// Create a new user
createUserBtn.addEventListener('click', async () => {
    const username = usernameInput.value.trim();
    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    if (!username || !email || !password) {
        return alert("Please fill in all fields!");
    }

    const user = { username, email, password };

    try {
        const response = await fetch('/api/users', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(user)
        });

        if (!response.ok) throw new Error('Error creating user');

        usernameInput.value = '';
        emailInput.value = '';
        passwordInput.value = '';
        fetchUsers(); // Refresh user list
    } catch (error) {
        alert(error.message);
    }
});

// Initial fetch of users
fetchUsers();



