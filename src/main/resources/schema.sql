-- Create the User table
CREATE TABLE IF NOT EXISTS user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Create the Image table
CREATE TABLE IF NOT EXISTS image (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    imgurId VARCHAR(255) NOT NULL,
    imageUrl VARCHAR(255) NOT NULL,
    user_id BIGINT, -- Foreign key reference to the user table
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE
);
