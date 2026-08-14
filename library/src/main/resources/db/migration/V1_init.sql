CREATE TABLE account (
    id INT IDENTITY(1,1) PRIMARY KEY,

    username NVARCHAR(100) NOT NULL UNIQUE,

    password NVARCHAR(255) NOT NULL,

    fullName NVARCHAR(150) NOT NULL,

    role NVARCHAR(30) NOT NULL,

    adminCode NVARCHAR(50) NULL UNIQUE
);

CREATE TABLE category (
    id INT IDENTITY(1,1) PRIMARY KEY,

    categoryCode NVARCHAR(50) NOT NULL UNIQUE,

    categoryName NVARCHAR(200) NOT NULL,

    createdBy INT NOT NULL,

    CONSTRAINT FK_Category_Account
        FOREIGN KEY(createdBy)
        REFERENCES account(id)
);

CREATE TABLE member (
    id INT IDENTITY(1,1) PRIMARY KEY,

    cardNumber NVARCHAR(50) NOT NULL UNIQUE,

    name NVARCHAR(150) NOT NULL,

    createdBy INT NOT NULL,

    CONSTRAINT FK_Member_Account
        FOREIGN KEY(createdBy)
        REFERENCES account(id)
);
CREATE TABLE book (
    id INT IDENTITY(1,1) PRIMARY KEY,

    bookCode NVARCHAR(50) NOT NULL UNIQUE,

    bookName NVARCHAR(250) NOT NULL,

    price INT NOT NULL,

    categoryId INT NOT NULL,

    createdBy INT NOT NULL,

    CONSTRAINT FK_Book_Category
        FOREIGN KEY(categoryId)
        REFERENCES category(id),

    CONSTRAINT FK_Book_Account
        FOREIGN KEY(createdBy)
        REFERENCES account(id)
);

CREATE TABLE loanSlip (
    id INT IDENTITY(1,1) PRIMARY KEY,

    receiptNumber NVARCHAR(50) NOT NULL UNIQUE,

    accountId INT NOT NULL,

    bookId INT NOT NULL,

    memberId INT NOT NULL,

    states INT NOT NULL,

    borrowDate DATE NOT NULL,

    dueDate DATE NOT NULL,

    CONSTRAINT FK_LoanSlip_Account
        FOREIGN KEY(accountId)
        REFERENCES account(id),

    CONSTRAINT FK_LoanSlip_Book
        FOREIGN KEY(bookId)
        REFERENCES book(id),

    CONSTRAINT FK_LoanSlip_Member
        FOREIGN KEY(memberId)
        REFERENCES member(id)
);