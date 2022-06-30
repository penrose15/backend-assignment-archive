CREATE TABLE IF NOT EXISTS MEMBER (
    member_id bigint NOT NULL AUTO_INCREMENT,
    name varchar(50) NOT NULL,
    email varchar(100) NOT NULL,
    PRIMARY KEY (member_id));