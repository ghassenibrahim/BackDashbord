CREATE TABLE RECEIPT
(
    id                   BIGSERIAL NOT NULL
        CONSTRAINT receipt_pk
            PRIMARY KEY,
    company_id           BIGINT    NOT NULL,
    name                 TEXT,
    last_update          DATE      NOT NULL,
    added_on             TIMESTAMP NOT NULL,
    customer_name        TEXT,
    customer_surname     TEXT,
    customer_address     TEXT,
    customer_oib         TEXT,
    payment_method       TEXT,
    payment_amount       NUMERIC,
    payment_due          DATE
)