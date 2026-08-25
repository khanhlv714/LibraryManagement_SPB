-- ============================================================
-- INIT DATA
-- ============================================================


-- ============================================================
-- 1. CATEGORY
-- ============================================================

INSERT INTO category
(
    category_code,
    category_name,
    updated_at,
    delete_at,
    version,
    created_by
)
VALUES
    ('CAT001', N'Lập trình',
        '2026-08-01 08:00:00', NULL, 0, 2),

    ('CAT002', N'Cơ sở dữ liệu',
        '2026-08-02 09:00:00', NULL, 0, 2),

    ('CAT003', N'Mạng máy tính',
        '2026-08-03 10:00:00', NULL, 0, 3),

    ('CAT004', N'Hệ điều hành',
        '2026-08-04 11:00:00', NULL, 0, 3),

    ('CAT005', N'Trí tuệ nhân tạo',
        '2026-08-05 14:00:00', NULL, 0, 2);


-- ============================================================
-- 2. MEMBER
-- ============================================================

INSERT INTO member
(
    card_number,
    name,
    updated_at,
    delete_at,
    version,
    created_by
)
VALUES
    ('CARD001', N'Nguyễn Văn An',
        '2026-08-01 08:30:00', NULL, 0, 2),

    ('CARD002', N'Trần Thị Bình',
        '2026-08-02 09:30:00', NULL, 0, 2),

    ('CARD003', N'Lê Văn Cường',
        '2026-08-03 10:30:00', NULL, 0, 3),

    ('CARD004', N'Phạm Thị Dung',
        '2026-08-04 11:30:00', NULL, 0, 3),

    ('CARD005', N'Hoàng Văn Em',
        '2026-08-05 13:30:00', NULL, 0, 2),

    ('CARD006', N'Vũ Thị Hoa',
        '2026-08-06 14:30:00', NULL, 0, 3);


-- ============================================================
-- 3. BOOK
-- ============================================================

INSERT INTO book
(
    book_code,
    book_name,
    price,
    updated_at,
    delete_at,
    version,
    category_id,
    created_by
)
VALUES

    -- ========================================================
    -- Category 1: Lập trình
    -- ========================================================

    ('BOOK001', N'Kotlin cơ bản',
        120000, '2026-08-01 08:00:00', NULL, 0, 1, 2),

    ('BOOK002', N'Java Programming',
        150000, '2026-08-01 09:00:00', NULL, 0, 1, 2),

    ('BOOK003', N'Spring Boot thực chiến',
        180000, '2026-08-02 10:00:00', NULL, 0, 1, 3),

    ('BOOK004', N'Clean Code',
        200000, '2026-08-03 11:00:00', NULL, 0, 1, 3),


    -- ========================================================
    -- Category 2: Cơ sở dữ liệu
    -- ========================================================

    ('BOOK005', N'SQL Server cơ bản',
        130000, '2026-08-02 08:00:00', NULL, 0, 2, 2),

    ('BOOK006', N'Database Design',
        170000, '2026-08-03 09:00:00', NULL, 0, 2, 2),

    ('BOOK007', N'Advanced SQL',
        190000, '2026-08-04 10:00:00', NULL, 0, 2, 3),


    -- ========================================================
    -- Category 3: Mạng máy tính
    -- ========================================================

    ('BOOK008', N'Computer Networks',
        210000, '2026-08-03 08:00:00', NULL, 0, 3, 3),

    ('BOOK009', N'Mạng máy tính căn bản',
        140000, '2026-08-04 09:00:00', NULL, 0, 3, 3),


    -- ========================================================
    -- Category 4: Hệ điều hành
    -- ========================================================

    ('BOOK010', N'Operating System',
        220000, '2026-08-04 08:00:00', NULL, 0, 4, 2),

    ('BOOK011', N'Linux Administration',
        250000, '2026-08-05 09:00:00', NULL, 0, 4, 2),


    -- ========================================================
    -- Category 5: Trí tuệ nhân tạo
    -- ========================================================

    ('BOOK012', N'Artificial Intelligence',
        300000, '2026-08-05 08:00:00', NULL, 0, 5, 2),

    ('BOOK013', N'Machine Learning',
        320000, '2026-08-06 09:00:00', NULL, 0, 5, 3),

    ('BOOK014', N'Deep Learning',
        350000, '2026-08-07 10:00:00', NULL, 0, 5, 3),

    ('BOOK015', N'AI with Python',
        280000, '2026-08-08 11:00:00', NULL, 0, 5, 2);


-- ============================================================
-- 4. LOAN SLIP
--
-- states:
-- 0 = đang mượn
-- 1 = đã trả
-- ============================================================

INSERT INTO loan_slip
(
    receipt_number,
    account_id,
    book_id,
    member_id,
    states,
    updated_at,
    delete_at,
    version,
    borrow_date,
    due_date
)
VALUES

    ('LS001',
        2,
        1,
        1,
        0,
        '2026-08-10 08:00:00',
        NULL,
        0,
        '2026-08-10',
        '2026-08-20'),

    ('LS002',
        2,
        2,
        2,
        1,
        '2026-08-05 09:00:00',
        NULL,
        0,
        '2026-08-05',
        '2026-08-15'),

    ('LS003',
        3,
        3,
        3,
        0,
        '2026-08-11 10:00:00',
        NULL,
        0,
        '2026-08-11',
        '2026-08-21'),

    ('LS004',
        3,
        5,
        4,
        1,
        '2026-08-06 11:00:00',
        NULL,
        0,
        '2026-08-06',
        '2026-08-16'),

    ('LS005',
        2,
        6,
        5,
        0,
        '2026-08-12 13:00:00',
        NULL,
        0,
        '2026-08-12',
        '2026-08-22'),

    ('LS006',
        3,
        8,
        6,
        1,
        '2026-08-07 14:00:00',
        NULL,
        0,
        '2026-08-07',
        '2026-08-17'),

    ('LS007',
        2,
        10,
        1,
        0,
        '2026-08-13 08:30:00',
        NULL,
        0,
        '2026-08-13',
        '2026-08-23'),

    ('LS008',
        3,
        12,
        2,
        0,
        '2026-08-14 09:30:00',
        NULL,
        0,
        '2026-08-14',
        '2026-08-24');


-- ============================================================
-- 5. KIỂM TRA CATEGORY + BOOK COUNT
-- ============================================================

SELECT
    c.id,
    c.category_code,
    c.category_name,
    c.created_by,
    c.version,
    COUNT(b.id) AS amount_book
FROM category c
LEFT JOIN book b
    ON b.category_id = c.id
GROUP BY
    c.id,
    c.category_code,
    c.category_name,
    c.created_by,
    c.version
ORDER BY c.id;


-- ============================================================
-- 6. KIỂM TRA BOOK
-- ============================================================

SELECT
    id,
    book_code,
    book_name,
    price,
    category_id,
    created_by,
    version,
    updated_at,
    delete_at
FROM book
ORDER BY id;


-- ============================================================
-- 7. KIỂM TRA MEMBER
-- ============================================================

SELECT
    id,
    card_number,
    name,
    created_by,
    version,
    updated_at,
    delete_at
FROM member
ORDER BY id;


-- ============================================================
-- 8. KIỂM TRA LOAN SLIP
-- ============================================================

SELECT
    ls.id,
    ls.receipt_number,
    ls.account_id,
    ls.book_id,
    ls.member_id,
    ls.states,
    ls.version,
    ls.borrow_date,
    ls.due_date,
    ls.updated_at,
    ls.delete_at
FROM loan_slip ls
ORDER BY ls.id;