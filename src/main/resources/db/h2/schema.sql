CREATE TABLE IF NOT EXISTS MESSAGE (
    message_id bigint NOT NULL AUTO_INCREMENT,
    message varchar(100) NOT NULL,
    PRIMARY KEY (message_id)
);

CREATE TABLE IF NOT EXISTS MEMBER (
    member_id bigint NOT NULL AUTO_INCREMENT,
    email varchar(100) NOT NULL,
    name varchar(100) NOT NULL,
    phone varchar(100) NOT NULL,
    PRIMARY KEY (member_id)
);

CREATE TABLE IF NOT EXISTS ORDERS (
    order_id bigint NOT NULL AUTO_INCREMENT,
    member_id bigint NOT NULL,
--    created_at datetime NOT NULL,
    PRIMARY KEY (member_id),
    FOREIGN KEY (member_id) REFERENCES MEMBER(member_id)
);

