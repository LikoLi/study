#!/usr/bin/python

import sqlite3

conn = sqlite3.connect('test.db')

print('Opened database successfully')

c = conn.cursor()

c.execute("INSERT INTO KBL_KEY_RELEASE(KEY_NAME) VALUES ('A')");
c.execute("INSERT INTO KBL_KEY_RELEASE(KEY_NAME) VALUES ('b')");
c.execute("INSERT INTO KBL_KEY_RELEASE(KEY_NAME) VALUES ('c')");

conn.commit()
print("Records created succeddfully")
conn.close()
