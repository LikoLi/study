#!/usr/bin/env python
# -*-coding:utf-8 -*-

import sys
import sqlite3

conn = sqlite3.connect('kbl.db', check_same_thread=False)
c = conn.cursor()

def export_all():
    sql = "SELECT KEY_NAME,COUNT(*) FROM KBL_KEY_RELEASE GROUP BY KEY_NAME ORDER BY COUNT(*) DESC"
    cursor = c.execute(sql)
    x = []
    y = []
    for row in cursor:
       x.append(row[0])
       y.append(row[1])
       print("{0}:{1}".format(row[0], row[1]))

    print(x)
    print(y)

if __name__ == "__main__":
#    if (len(sys.argv) == 0):
    export_all()
#    if (len(sys.argv) == 1):
#        export_today()
    conn.close()
    
    
