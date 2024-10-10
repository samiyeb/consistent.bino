document.addEventListener('DOMContentLoaded', () => {
    const userForm = document.getElementById('user-form');

    // Handle user login or registration
    userForm.addEventListener('submit', (e) => {
        e.preventDefault();

        const user = {
            username: document.getElementById('username').value,
            email: document.getElementById('email').value,
            password: document.getElementById('password').value,
        };

        // Register or login user
        fetch('http://localhost:8080/api/users', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(user),
        })
        .then(response => response.json())
        .then(data => {
            // Save user info in session storage
            sessionStorage.setItem('userId', data.id);
            sessionStorage.setItem('username', data.username);
            window.location.href = 'dashboard.html';  // Redirect to user dashboard
        });
    });
});
