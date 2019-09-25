#!/usr/bin/python

import sqlite3

conn = sqlite3.connect('kbl.db')

print('Opened database successfully')

c = conn.cursor()

cursor = c.execute("SELECT ID,KEY_NAME,CREATE_DATE FROM KBL_KEY_RELEASE");
for row in cursor:
    print "ID = ", row[0]
    print "KEY_NAME = ", row[1]
    print "CREATE_DATE = ", row[2]

conn.close()
