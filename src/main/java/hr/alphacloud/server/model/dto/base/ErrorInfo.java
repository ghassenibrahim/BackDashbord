package hr.alphacloud.server.model.dto.base;

public enum ErrorInfo {
    GENERAL_ERROR("generalError", "An error has occurred. Please try again."),
    CONSTRAINT_VIOLATION_EXCEPTION("constraintError", "Constraint exception occurred!"),
    SPENDING_LOCATION_NOT_EXIST("reporting.spendingLocationNotExist", "Spending location does not exist."),
    WAREHOUSE_TYPE_NOT_EXIST("reporting.warehouseTypeNotExist", "Warehouse type does not exist."),
    PREMISES_NOT_EXIST("reporting.premisesNotExist", "Premises does not exist."),
    SECTOR_TYPE_NOT_EXIST("reporting.sectorTypeNotExist", "Sector type does not exist."),
    BUSINESS_TYPE_NOT_EXIST("reporting.businessTypeNotExist", "Business type does not exist."),
    CUSTOMER_NOT_EXIST("reporting.customerNotExist", "Customer does not exist."),
    CUSTOMER_ADVANCE_NOT_EXIST("reporting.customerAdvanceNotExist", "Customer advance does not exist."),
    SUPPLIER_NOT_EXIST("reporting.supplierNotExist", "Supplier does not exist."),
    SUPPLIER_ADVANCE_NOT_EXIST("reporting.supplierAdvanceNotExist", "Supplier advance does not exist."),
    LOAN_RECEIVED_NOT_EXIST("reporting.loanReceivedNotExist", "Received loan does not exist."),
    LOAN_GIVEN_NOT_EXIST("reporting.loanGivenNotExist", "Given loan does not exist."),
    USERNAME_NOT_FOUND("error.usernameNotFound", "Username not found"),
    CSV_FILE_NULL("reporting.csv.null", "The given CSV File is null"),
    IMPORT_SETTINGS_NOT_EXIST("reporting.import_settings.null", "The given import settings are null"),
    CSV_NOT_ENOUGH_COLUMNS("reporting.csv.columns", "The given row has not enough columns"),
    DUPLICATE_EMAIL("error.duplicateEmail", "Email already exists"),
    NO_DATA("reporting.noData", "No data found.");


    private final String messageKey;
    private final String message;

    ErrorInfo(String messageKey, String message) {
        this.messageKey = messageKey;
        this.message = message;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public String getMessage() {
        return this.message;
    }
}

