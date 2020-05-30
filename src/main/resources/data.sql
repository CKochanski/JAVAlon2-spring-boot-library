insert INTO BOOK (id, author, title) values (nextval('book_seq'), 'Kaczanowski', 'Testy');
insert INTO BOOK (id, author, title) values (nextval('book_seq'), 'Rowling', 'Harry Potter');
insert INTO BOOK (id, author, title) values (nextval('book_seq'), 'Tolkien', 'Wladca piercieni');
insert INTO BOOK (id, author, title) values (nextval('book_seq'), 'Dostojewski', 'Zbrodnia i kara');
insert INTO BOOK (id, author, title) values (nextval('book_seq'), 'Joshua Bloch', 'Effective Java');

commit;
