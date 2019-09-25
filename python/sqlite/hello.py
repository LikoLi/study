#!/usr/bin/python

import sqlite3

conn = sqlite3.connect('test.db')

print('Opened database successfully')

c = conn.cursor()
c.execute('''CREATE TABLE IF NOT EXISTS KBL_KEY_RELEASE(
	ID INTEGER PRIMARY KEY AUTOINCREMENT,
	KEY_NAME VARCHAR(30) NOT NULL,
	CREATE_DATE TIMESTAMP default (datetime('now', 'localtime'))
	);''')

print('Table created successfully')
conn.commit()
conn.close()
