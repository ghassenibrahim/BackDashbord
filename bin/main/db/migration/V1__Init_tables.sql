-- -----------------------------------------------------
-- Table public.company
-- -----------------------------------------------------

create table public.company
(
    id         BIGSERIAL,
    short_name text,
    full_name  text,
    oib        text,
    mbs        text,
    email      text,
    address    text,
    primary key (id),
    unique (oib),
    unique (mbs),
    unique (email)
);

-- -----------------------------------------------------
-- Table public.user_main
-- -----------------------------------------------------

create table public.user_main
(
    id                    BIGSERIAL,
    email                 text,
    password              text,
    enabled               boolean default true,
    jwt_token             text,
    jwt_token_reset_timer TIMESTAMP,
    refresh_token         text,
    phone                 text,
    first_name            text,
    last_name             text,
    role                  char(1),
    position              text,
    address               text,
    company_id            BIGINT,
    active_locale         text,
    designated_user       boolean,
    selected_company_id   BIGINT,
    selected_company_name text,
    primary key (id),
    foreign key (company_id) references public.company (id),
    check (role in ('A', 'C', 'U')),
    unique (email)
);

-- -----------------------------------------------------
-- Table public.import_settings
-- -----------------------------------------------------

CREATE TABLE public.import_settings
(
    id                             BIGSERIAL NOT NULL,
    company_id                     BIGINT    NOT NULL,
    name                           TEXT      NOT NULL,
    skip_first_row_amount          INT       NOT NULL,
    skip_last_row_amount           INT       NOT NULL,
    minimum_accepted_column_length INT       NOT NULL,
    accepted_date_format           TEXT      NOT NULL,
    skip_first_column_amount          INT       NOT NULL,
    CONSTRAINT pk_import_settings PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

COMMENT ON TABLE public.import_settings
    IS 'Import settings table for Reporting application';

-- -----------------------------------------------------
-- Table public.business_type
-- -----------------------------------------------------

CREATE TABLE public.business_type
(
    id         BIGSERIAL,
    company_id BIGINT NOT NULL,
    locale     TEXT   NOT NULL,
    value      TEXT   NOT NULL,
    CONSTRAINT pk_business_type PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

COMMENT ON TABLE public.business_type
    IS 'Business type table for Reporting application';

-- -----------------------------------------------------
-- Table public.spending_location
-- -----------------------------------------------------

CREATE TABLE public.spending_location
(
    id         BIGSERIAL NOT NULL,
    company_id BIGINT    NOT NULL,
    name       TEXT      NOT NULL,
    CONSTRAINT pk_spending_location PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

COMMENT ON TABLE public.spending_location
    IS 'Spending location table for Reporting application';

-- -----------------------------------------------------
-- Table public.premises
-- -----------------------------------------------------

CREATE TABLE public.premises
(
    id         BIGSERIAL NOT NULL,
    company_id BIGINT    NOT NULL,
    name       TEXT      NOT NULL,
    CONSTRAINT pk_premises PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

COMMENT ON TABLE public.premises
    IS 'Premises table for Reporting application';

-- -----------------------------------------------------
-- Table public.sectorType
-- -----------------------------------------------------

CREATE TABLE public.sector_type
(
    id         BIGSERIAL NOT NULL,
    company_id BIGINT    NOT NULL,
    name       TEXT      NOT NULL,
    CONSTRAINT pk_sector_type PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

COMMENT ON TABLE public.sector_type
    IS 'Sector table for Reporting application';

-- -----------------------------------------------------
-- Table public.position_template
-- -----------------------------------------------------

CREATE TABLE public.position_template
(
    id                      BIGSERIAL NOT NULL,
    sheet_type              TEXT      NOT NULL,
    position_properties     JSONB,
    CONSTRAINT pk_position_template PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

-- -----------------------------------------------------
-- Table public.position_template
-- -----------------------------------------------------

CREATE TABLE public.financial_indicator_template
(
    id                              BIGSERIAL NOT NULL,
    liquidity_indicators            JSONB,
    indebtedness_indicators         JSONB,
    activity_indicators             JSONB,
    cost_effectiveness_indicators   JSONB,
    profitability_indicators        JSONB,
    CONSTRAINT pk_financial_indicator_template PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

-- -----------------------------------------------------
-- Table public.position_template
-- -----------------------------------------------------

CREATE TABLE public.positions
(
    id                      BIGSERIAL NOT NULL,
    company_id              BIGSERIAL NOT NULL,
    sheet_type              TEXT      NOT NULL,
    position_properties     JSONB,
    CONSTRAINT pk_positions PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

-- -----------------------------------------------------
-- Table public.account_book
-- -----------------------------------------------------

CREATE TABLE public.account_book
(
    id         BIGSERIAL,
    company_id BIGINT NOT NULL,
    name       TEXT,
    code       BIGINT,
    CONSTRAINT pk_account_book PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

COMMENT ON TABLE public.account_book
    IS 'Account book table for Reporting application';


-- -----------------------------------------------------
-- Table public.additional_settings
-- -----------------------------------------------------

CREATE TABLE public.additional_settings
(
    id         BIGSERIAL,
    company_id BIGINT NOT NULL,
    principal  FLOAT,
    interest   FLOAT,
    rate_pd     FLOAT,
    CONSTRAINT pk_additional_settings PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

COMMENT ON TABLE public.additional_settings
    IS 'Additional settings table for Reporting application';


-- -----------------------------------------------------
-- Table public.warehouse_type
-- -----------------------------------------------------

CREATE TABLE public.warehouse_type
(
    id                       BIGSERIAL,
    company_id               BIGINT NOT NULL,
    locale                   TEXT,
    value                    TEXT   NOT NULL,
    init_state_balance_code  BIGINT NOT NULL,
    final_state_balance_code BIGINT NOT NULL,
    CONSTRAINT pk_warehouse_type PRIMARY KEY (id),
    CONSTRAINT fk_init_state_balance_code FOREIGN KEY (init_state_balance_code)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_final_state_balance_code FOREIGN KEY (final_state_balance_code)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

COMMENT ON TABLE public.warehouse_type
    IS 'Warehouse types table for Reporting application';

-- -----------------------------------------------------
-- Table public.report
-- -----------------------------------------------------
CREATE TABLE public.report
(
    id               BIGSERIAL NOT NULL,
    company_id       BIGINT    NOT NULL,
    name             TEXT,
    added_on         TIMESTAMP NOT NULL,
    last_update      DATE      NOT NULL,
    report_date_from DATE      NOT NULL,
    report_date_to   DATE      NOT NULL,
    report_type_code TEXT      NOT NULL,
    CONSTRAINT pk_report PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    );

COMMENT ON TABLE public.report
    IS 'Report table for Reporting application';

-- -----------------------------------------------------
-- Table public.customer
-- -----------------------------------------------------

CREATE TABLE public.customer
(
    id                           BIGSERIAL NOT NULL,
    report_id                    BIGINT    NOT NULL,
    spending_location_id         BIGINT,
    premises_id                  BIGINT,
    sector_type_id               BIGINT,
    import_settings_id           BIGINT,
    account_book_id              BIGINT,
    import_type                  TEXT,
    customer_supplier_properties JSONB,
    CONSTRAINT pk_customer PRIMARY KEY (id),
    CONSTRAINT fk_customer_report FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table public.supplier
-- -----------------------------------------------------

CREATE TABLE public.supplier
(
    id                           BIGSERIAL NOT NULL,
    report_id                    BIGINT    NOT NULL,
    spending_location_id         BIGINT,
    premises_id                  BIGINT,
    sector_type_id               BIGINT,
    import_settings_id           BIGINT,
    account_book_id              BIGINT,
    import_type                  TEXT,
    customer_supplier_properties JSONB,
    CONSTRAINT pk_supplier PRIMARY KEY (id),
    CONSTRAINT fk_supplier_report FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -- -----------------------------------------------------
-- -- Table public.customer_advance
-- -- -----------------------------------------------------

CREATE TABLE public.customer_advance
(
    id                           BIGSERIAL NOT NULL,
    report_id                    BIGINT    NOT NULL,
    spending_location_id         BIGINT,
    premises_id                  BIGINT,
    sector_type_id               BIGINT,
    import_settings_id           BIGINT,
    account_book_id              BIGINT,
    import_type                  TEXT,
    customer_supplier_properties JSONB,
    CONSTRAINT pk_customer_advance PRIMARY KEY (id),
    CONSTRAINT fk_customer_advance_report FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_advance_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_advance_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_advance_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_advance_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_customer_advance_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -- -----------------------------------------------------
-- -- Table public.supplier_advance
-- -- -----------------------------------------------------

CREATE TABLE public.supplier_advance
(
    id                           BIGSERIAL NOT NULL,
    report_id                    BIGINT    NOT NULL,
    spending_location_id         BIGINT,
    premises_id                  BIGINT,
    sector_type_id               BIGINT,
    import_settings_id           BIGINT,
    account_book_id              BIGINT,
    import_type                  TEXT,
    customer_supplier_properties JSONB,
    CONSTRAINT pk_supplier_advance PRIMARY KEY (id),
    CONSTRAINT fk_supplier_advance_report FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_advance_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_advance_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_advance_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_advance_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supplier_advance_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -- -----------------------------------------------------
-- -- Table public.loan_given
-- -- -----------------------------------------------------

CREATE TABLE public.loan_given
(
    id                           BIGSERIAL NOT NULL,
    report_id                    BIGINT    NOT NULL,
    spending_location_id         BIGINT,
    premises_id                  BIGINT,
    sector_type_id               BIGINT,
    import_settings_id           BIGINT,
    account_book_id              BIGINT,
    import_type                  TEXT,
    customer_supplier_properties JSONB,
    CONSTRAINT pk_loan_given PRIMARY KEY (id),
    CONSTRAINT fk_loan_given_report FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_loan_given_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_loan_given_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_loan_given_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_load_given_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_load_given_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -- -----------------------------------------------------
-- -- Table public.loan_received
-- -- -----------------------------------------------------

CREATE TABLE public.loan_received
(
    id                           BIGSERIAL NOT NULL,
    report_id                    BIGINT    NOT NULL,
    spending_location_id         BIGINT,
    premises_id                  BIGINT,
    sector_type_id               BIGINT,
    import_settings_id           BIGINT,
    account_book_id              BIGINT,
    import_type                  TEXT,
    customer_supplier_properties JSONB,
    CONSTRAINT pk_loan_received PRIMARY KEY (id),
    CONSTRAINT fk_loan_received_report FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_loan_received_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_loan_received_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_loan_received_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_load_received_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_load_received_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table public.brutto_balance
-- -----------------------------------------------------

CREATE TABLE public.brutto_balance
(
    id                        BIGSERIAL NOT NULL,
    report_id                 BIGINT    NOT NULL,
    spending_location_id      BIGINT,
    import_settings_id        BIGINT,
    business_type_id          BIGINT,
    premises_id               BIGINT,
    sector_type_id            BIGINT,
    brutto_balance_properties JSONB,
    CONSTRAINT pk_brutto_balance PRIMARY KEY (id),
    CONSTRAINT fk_brutto_balance_report FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_brutto_balance_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_brutto_balance_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_brutto_balance_business_type FOREIGN KEY (business_type_id)
        REFERENCES public.business_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_brutto_balance_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_brutto_balance_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -----------------------------------------------------
-- Table public.supply_analytics
-- -----------------------------------------------------

CREATE TABLE public.supply_analytics
(
    id                          BIGSERIAL NOT NULL,
    report_id                   BIGINT    NOT NULL,
    spending_location_id        BIGINT,
    business_type_id            BIGINT,
    premises_id                 BIGINT,
    sector_type_id              BIGINT,
    warehouse_type_id           BIGINT,
    import_settings_id          BIGINT,
    import_type                 TEXT,
    account_book_id             BIGINT,
    supply_analytics_properties JSONB,
    CONSTRAINT pk_supply_analytics PRIMARY KEY (id),
    CONSTRAINT fk_supply_analytics_report FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supply_analytics_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supply_analytics_business_type FOREIGN KEY (business_type_id)
        REFERENCES public.business_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supply_analytics_warehouse_type FOREIGN KEY (warehouse_type_id)
        REFERENCES public.warehouse_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supply_analytics_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supply_analytics_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supply_analytics_import_settomgs FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_supply_analytics_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- -- -----------------------------------------------------
-- -- Table public.sales_recap
-- -- -----------------------------------------------------

CREATE TABLE public.sales_recap
(
    id                     BIGSERIAL NOT NULL,
    report_id              BIGINT    NOT NULL,
    spending_location_id   BIGINT,
    business_type_id       BIGINT,
    premises_id            BIGINT,
    sector_type_id         BIGINT,
    account_book_id        BIGINT,
    import_settings_id     BIGINT,
    import_type            TEXT,
    sales_recap_properties JSONB,
    CONSTRAINT pk_sales_recap PRIMARY KEY (id),
    CONSTRAINT fk_sales_recap FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_sales_recap_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_sales_recap_business_type FOREIGN KEY (business_type_id)
        REFERENCES public.business_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_sales_recap_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_sales_recap_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_sales_recap_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_sales_recap_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);


-- -- -----------------------------------------------------
-- -- Table public.sales_recap
-- -- -----------------------------------------------------

CREATE TABLE public.purchase_recap
(
    id                        BIGSERIAL NOT NULL,
    report_id                 BIGINT    NOT NULL,
    spending_location_id      BIGINT,
    business_type_id          BIGINT,
    premises_id               BIGINT,
    sector_type_id            BIGINT,
    account_book_id           BIGINT,
    import_settings_id        BIGINT,
    import_type               TEXT,
    purchase_recap_properties JSONB,
    CONSTRAINT pk_purchase_recap PRIMARY KEY (id),
    CONSTRAINT fk_purchase_recap FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_purchase_recap_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_purchase_recap_business_type FOREIGN KEY (business_type_id)
        REFERENCES public.business_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_purchase_recap_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_purchase_recap_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_purchase_recap_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_purchase_recap_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);

CREATE TABLE plan
(
    id                   BIGSERIAL NOT NULL
        CONSTRAINT plan_pk
            PRIMARY KEY,
    company_id           BIGINT    NOT NULL,
    name                 TEXT,
    added_on             TIMESTAMP NOT NULL,
    last_update          DATE      NOT NULL,
    spending_location_id BIGINT
);

CREATE TABLE plan_period
(
    id                                  BIGSERIAL NOT NULL
        CONSTRAINT plan_period_pk
            PRIMARY KEY,
    plan_id                             BIGSERIAL NOT NULL
        CONSTRAINT plan___fk
            REFERENCES plan,
    operating_incomes                   NUMERIC,
    cost_of_goods_sold                  NUMERIC,
    goods_value_changes                 NUMERIC,
    raw_material_supplies_cost          NUMERIC,
    other_external_costs                NUMERIC,
    net_salaries_wages                  NUMERIC,
    taxes_contributions_from_salaries   NUMERIC,
    wage_contributions                  NUMERIC,
    other_costs                         NUMERIC,
    fixed_assets_except_financial       NUMERIC,
    current_assets_except_financial     NUMERIC,
    provisions_for_pensions             NUMERIC,
    provisions_for_taxes                NUMERIC,
    provisions_for_initiated_litigation NUMERIC,
    provisions_for_natural_resources    NUMERIC,
    provisions_for_warranty_period      NUMERIC,
    provisions_other                    NUMERIC,
    amortization                        NUMERIC,
    financial_revenues                  NUMERIC,
    financial_expenses                  NUMERIC,
    employee_number                     NUMERIC,
    revenue_growth                      NUMERIC,
    revenue_share                       NUMERIC,
    plan_period_type                    TEXT
);


CREATE TABLE public.receivable_maturity
(
    id                        BIGSERIAL NOT NULL,
    report_id                 BIGINT    NOT NULL,
    spending_location_id      BIGINT,
    business_type_id          BIGINT,
    premises_id               BIGINT,
    sector_type_id            BIGINT,
    account_book_id           BIGINT,
    import_settings_id        BIGINT,
    date_from                 DATE,
    date_to                   DATE,
    date_completed            DATE,
    import_type               TEXT,
    receivable_maturity_properties JSONB,
    CONSTRAINT pk_receivable_maturity PRIMARY KEY (id),
    CONSTRAINT fk_receivable_maturity FOREIGN KEY (report_id)
        REFERENCES public.report (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_receivable_maturity_spending_location FOREIGN KEY (spending_location_id)
        REFERENCES public.spending_location (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_receivable_maturity_business_type FOREIGN KEY (business_type_id)
        REFERENCES public.business_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_receivable_maturity_premises FOREIGN KEY (premises_id)
        REFERENCES public.premises (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_receivable_maturity_sector_type FOREIGN KEY (sector_type_id)
        REFERENCES public.sector_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_receivable_maturity_import_settings FOREIGN KEY (import_settings_id)
        REFERENCES public.import_settings (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fk_receivable_maturity_account_book FOREIGN KEY (account_book_id)
        REFERENCES public.account_book (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION

);
