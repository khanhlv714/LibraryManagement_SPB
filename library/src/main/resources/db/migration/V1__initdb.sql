CREATE TABLE account (
    id INT IDENTITY(1,1) PRIMARY KEY,

    username NVARCHAR(100) NOT NULL UNIQUE,
    password NVARCHAR(255) NOT NULL,
    full_name NVARCHAR(150) NOT NULL,
    role NVARCHAR(30) NOT NULL,

    updated_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    delete_at DATETIME2 NULL,
    version BIGINT NOT NULL DEFAULT 0,

    admin_code NVARCHAR(50) NULL UNIQUE
);


CREATE TABLE category (
    id INT IDENTITY(1,1) PRIMARY KEY,

    category_code NVARCHAR(50) NOT NULL UNIQUE,
    category_name NVARCHAR(200) NOT NULL,

    updated_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    delete_at DATETIME2 NULL,
    version BIGINT NOT NULL DEFAULT 0,

    created_by INT NOT NULL,

    CONSTRAINT FK_Category_Account
        FOREIGN KEY (created_by)
        REFERENCES account(id)
);


CREATE TABLE member (
    id INT IDENTITY(1,1) PRIMARY KEY,

    card_number NVARCHAR(50) NOT NULL UNIQUE,
    name NVARCHAR(150) NOT NULL,

    updated_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    delete_at DATETIME2 NULL,
    version BIGINT NOT NULL DEFAULT 0,

    created_by INT NOT NULL,

    CONSTRAINT FK_Member_Account
        FOREIGN KEY (created_by)
        REFERENCES account(id)
);


CREATE TABLE book (
    id INT IDENTITY(1,1) PRIMARY KEY,

    book_code NVARCHAR(50) NOT NULL UNIQUE,
    book_name NVARCHAR(250) NOT NULL,
    price INT NOT NULL,

    updated_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    delete_at DATETIME2 NULL,
    version BIGINT NOT NULL DEFAULT 0,

    category_id INT NOT NULL,
    created_by INT NOT NULL,

    CONSTRAINT FK_Book_Category
        FOREIGN KEY (category_id)
        REFERENCES category(id),

    CONSTRAINT FK_Book_Account
        FOREIGN KEY (created_by)
        REFERENCES account(id)
);


CREATE TABLE loan_slip (
    id INT IDENTITY(1,1) PRIMARY KEY,

    receipt_number NVARCHAR(50) NOT NULL UNIQUE,

    account_id INT NOT NULL,
    book_id INT NOT NULL,
    member_id INT NOT NULL,

    states INT NOT NULL,

    updated_at DATETIME2 NOT NULL DEFAULT GETDATE(),
    delete_at DATETIME2 NULL,
    version BIGINT NOT NULL DEFAULT 0,

    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,

    CONSTRAINT FK_LoanSlip_Account
        FOREIGN KEY (account_id)
        REFERENCES account(id),

    CONSTRAINT FK_LoanSlip_Book
        FOREIGN KEY (book_id)
        REFERENCES book(id),

    CONSTRAINT FK_LoanSlip_Member
        FOREIGN KEY (member_id)
        REFERENCES member(id)
);