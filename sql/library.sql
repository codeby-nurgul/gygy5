-- Veritabanı Oluşturma
CREATE DATABASE library_system;

-- Kategori Tablosu
CREATE TABLE category (
    category_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT
);

-- Kitap Tablosu
CREATE TABLE book (
    book_id SERIAL PRIMARY KEY,
    isbn VARCHAR(20) UNIQUE NOT NULL,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    publisher VARCHAR(150),
    year INTEGER,
    copies INTEGER DEFAULT 1
);

-- Kategori Tablosu 
CREATE TABLE book_category (
    book_id INTEGER REFERENCES book(book_id),
    category_id INTEGER REFERENCES category(category_id),
    PRIMARY KEY (book_id, category_id)
);

-- Öğrenci Tablosu
CREATE TABLE student (
    student_id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    surname VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    department VARCHAR(100),
    enrollment_date DATE DEFAULT CURRENT_DATE
);

-- Staff Tablosu
CREATE TABLE staff (
    staff_id SERIAL PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    role VARCHAR(50),
    password_hash VARCHAR(255) NOT NULL
);

-- Borrow Tablosu
CREATE TABLE borrow (
    borrow_id SERIAL PRIMARY KEY,
    book_id INTEGER REFERENCES book(book_id),
    student_id INTEGER REFERENCES student(student_id),
    staff_id INTEGER REFERENCES staff(staff_id),
    borrow_date DATE DEFAULT CURRENT_DATE,
    due_date DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'Borrowed'
);

-- Return Log Tablosu
CREATE TABLE return_log (
    return_id SERIAL PRIMARY KEY,
    borrow_id INTEGER UNIQUE REFERENCES borrow(borrow_id),
    staff_id INTEGER REFERENCES staff(staff_id),
    return_date DATE DEFAULT CURRENT_DATE,
    late_fee DECIMAL(10, 2) DEFAULT 0.00,
    notes TEXT
);

-- Kategoriler
INSERT INTO category (name, description) VALUES ('Bilim Kurgu', 'Gelecek ve teknoloji temalı'), ('Tarih', 'Tarihi olaylar'), ('Yazılım', 'Programlama dilleri'), ('Roman', 'Edebi eserler'), ('Psikoloji', 'İnsan davranışı');

-- Kitaplar
INSERT INTO book (isbn, title, author, publisher, year, copies) VALUES 
('978-01', 'Nutuk', 'Mustafa Kemal Atatürk', 'Yapı Kredi', 1927, 10),
('978-02', 'Suç ve Ceza', 'Dostoyevski', 'Can Yayınları', 1866, 5),
('978-03', 'Temiz Kod', 'Robert C. Martin', 'Pearson', 2008, 3),
('978-04', '1984', 'George Orwell', 'Can Yayınları', 1949, 7),
('978-05', 'Sapiens', 'Yuval Noah Harari', 'Kolektif', 2011, 4);

-- Öğrenciler
INSERT INTO student (name, surname, email, department) VALUES 
('Ahmet', 'Yılmaz', 'ahmet@mail.com', 'Bilgisayar Müh.'),
('Ayşe', 'Demir', 'ayse@mail.com', 'Hukuk'),
('Mehmet', 'Kaya', 'mehmet@mail.com', 'Tıp'),
('Fatma', 'Çelik', 'fatma@mail.com', 'İşletme'),
('Can', 'Öz', 'can@mail.com', 'Mimarlık');

-- Personel
INSERT INTO staff (first_name, last_name, role, password_hash) VALUES 
('Hasan', 'Kütüphaneci', 'Admin', 'hash123'),
('Elif', 'Yönetici', 'Librarian', 'hash456'),
('Murat', 'Görevli', 'Librarian', 'hash789'),
('Selin', 'Asistan', 'Assistant', 'hash101'),
('Deniz', 'Stajyer', 'Intern', 'hash202');

-- 1. Ekleme
INSERT INTO category (name, description) VALUES ('Kişisel Gelişim', 'Bireysel farkındalık kitapları');
-- 2. Güncelleme
UPDATE category SET description = 'Dünya edebiyatı klasikleri' WHERE name = 'Roman';
-- 3. Silme
DELETE FROM category WHERE category_id = 5;
-- 4. Listeleme
SELECT * FROM category ORDER BY name ASC;
-- 5. Arama
SELECT * FROM category WHERE name LIKE '%Bilim%';

-- 1. Ekleme
INSERT INTO book (isbn, title, author, publisher, year, copies) VALUES ('978-06', 'Dune', 'Frank Herbert', 'İthaki', 1965, 8);
-- 2. Güncelleme 
UPDATE book SET copies = copies + 2 WHERE book_id = 3;
-- 3. Silme
DELETE FROM book WHERE isbn = '978-05';
-- 4. Listeleme
SELECT * FROM book ORDER BY copies DESC;
-- 5. Filtreleme
SELECT title, author FROM book WHERE year > 2000;

-- 1. Ekleme
INSERT INTO student (name, surname, email, department) VALUES ('Zeynep', 'Ak', 'zeynep@mail.com', 'Eczacılık');
-- 2. Güncelleme
UPDATE student SET email = 'ahmet.yeni@mail.com' WHERE student_id = 1;
-- 3. Silme
DELETE FROM student WHERE student_id = 4;
-- 4. Sayma
SELECT COUNT(*) FROM student;
-- 5. Listeleme
SELECT name, surname FROM student WHERE department = 'Hukuk';

-- 1. Ekleme
INSERT INTO staff (first_name, last_name, role, password_hash) VALUES ('Kemal', 'Can', 'Güvenlik', 'pass99');
-- 2. Güncelleme
UPDATE staff SET role = 'Senior Librarian' WHERE staff_id = 2;
-- 3. Silme
DELETE FROM staff WHERE staff_id = 5;
-- 4. Listeleme
SELECT first_name, last_name FROM staff WHERE role = 'Librarian';
-- 5. Arama
SELECT * FROM staff WHERE last_name ILIKE 'k%';


-- 1. Ekleme
INSERT INTO book_category (book_id, category_id) VALUES (1, 2), (2, 4), (3, 3), (4, 1), (6, 1);
-- 2. Güncelleme 
UPDATE book_category SET category_id = 2 WHERE book_id = 4;
-- 3. Silme
DELETE FROM book_category WHERE book_id = 1 AND category_id = 2;
-- 4. Kategorileri görme
SELECT * FROM book_category WHERE book_id = 2;
-- 5. Toplam ilişki
SELECT COUNT(*) FROM book_category;

-- 1. Ekleme
INSERT INTO borrow (book_id, student_id, staff_id, due_date) VALUES (1, 1, 1, '2024-06-01'), (2, 2, 2, '2024-06-05'), (3, 3, 1, '2024-05-20'), (4, 1, 3, '2024-06-10'), (6, 2, 2, '2024-06-15');
-- 2. Güncelleme
UPDATE borrow SET status = 'Returned' WHERE borrow_id = 1;
-- 3. Silme
DELETE FROM borrow WHERE borrow_id = 5;
-- 4. Gecikenleri listeleme
SELECT * FROM borrow WHERE due_date < CURRENT_DATE AND status = 'Borrowed';
-- 5. Öğrenci listeleme
SELECT * FROM borrow WHERE student_id = 1;

-- 1. Ekleme
INSERT INTO return_log (borrow_id, staff_id, late_fee, notes) VALUES 
(1, 1, 0.00, 'Zamanında teslim edildi'),
(2, 2, 0.00, 'Hasarsız iade'),
(3, 1, 15.50, 'Gecikmeli iade'),
(4, 2, 5.00, 'Kitap kapağı hafif yıpranmış');
-- 2. Güncelleme
UPDATE return_log SET notes = 'Ceza ödendi' WHERE return_id = 2;
-- 3. Silme
DELETE FROM return_log WHERE return_id = 4;
-- 4. Toplam ceza
SELECT SUM(late_fee) FROM return_log;
-- 5. En yüksek ceza
SELECT MAX(late_fee) FROM return_log;
