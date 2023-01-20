CREATE TABLE addresses
(
    id     BIGINT       NOT NULL,
    number VARCHAR(255) NULL,
    city   VARCHAR(255) NULL,
    CONSTRAINT pk_addresses PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGINT       NOT NULL,
    name       VARCHAR(255) NULL,
    address_id BIGINT       NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES addresses (id);

CREATE TABLE devices
(
    id      BIGINT       NOT NULL,
    name    VARCHAR(255) NULL,
    user_id BIGINT       NULL,
    CONSTRAINT pk_devices PRIMARY KEY (id)
);

CREATE TABLE measurement
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    value            INT                   NOT NULL,
    measurement_unit VARCHAR(255)          NULL,
    date             date                  NULL,
    device_id        BIGINT                NULL,
    CONSTRAINT pk_measurement PRIMARY KEY (id)
);

ALTER TABLE measurement
    ADD CONSTRAINT FK_MEASUREMENT_ON_DEVICE FOREIGN KEY (device_id) REFERENCES devices (id);