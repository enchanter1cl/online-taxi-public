CREATE TABLE passenger_user(
    id bigint NOT NULL PRIMARY KEY,
    mgt_create datetime,
    mgt_modified datetime,
    passenger_phone varchar(16),
    passenger_name varchar(16),
    passenger_gender tinyint,
    status tinyint
) CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci;