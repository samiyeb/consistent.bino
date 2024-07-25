-- Drop Progress Table
DROP TABLE IF EXISTS progress;

-- Drop Tasks Table
DROP TABLE IF EXISTS tasks;

-- Drop Goals Table
DROP TABLE IF EXISTS goals;

-- Drop Users Table
DROP TABLE IF EXISTS users;




-- Create User Table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Create Goal Table
CREATE TABLE goals (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Create Task Table
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    due_date DATE,
    goal_id INT NOT NULL,
    FOREIGN KEY (goal_id) REFERENCES goals(id) ON DELETE CASCADE
);

-- Create Progress Table
CREATE TABLE progress (
    id SERIAL PRIMARY KEY,
    date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    task_id INT NOT NULL,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE
);
