# script named step-2.py that will generate 5000 records
# Author: LAVANYA SURIKAPURAM
import random
import mysql.connector
from mysql.connector import errorcode

part_m_file = open('part-m-00000', 'r')
yourResult = [line.split(',') for line in part_m_file.readlines()]

widget_name = []
price = []
design_date = []
version = []
design_comment = []

for sub_list in yourResult:
    widget_name.append(sub_list[1])
    price.append(sub_list[2])
    design_date.append(sub_list[3])
    version.append(sub_list[4])
    design_comment.append(sub_list[5])


# Obtain connection string information from the portal
config = {
  'host':'localhost',
  'user':'root',
  'password':'itmd521',
  'database':'hadoopguide'
}

# Construct connection string
try:
   conn = mysql.connector.connect(**config)
   print("Connection established")
except mysql.connector.Error as err:
  if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
    print("Something is wrong with the user name or password")
  elif err.errno == errorcode.ER_BAD_DB_ERROR:
    print("Database does not exist")
  else:
    print(err)
else:
  cursor = conn.cursor()


  # Insert 5000 records data into table widgets
  for _ in range(5000):
      cursor.execute("INSERT INTO widgets (widget_name, price, design_date, version, design_comment) VALUES (%s, %s, %s, %s, %s);", \
                     (random.choice(widget_name), random.choice(price),\
                      random.choice(design_date), random.choice(version), random.choice(design_comment)))
      conn.commit()
  
  sql= "update widgets SET design_comment= TRIM(TRAILING '\n' FROM design_comment)"

  cursor.execute(sql)
  # Cleanup
  conn.commit()
  cursor.close()
  conn.close()
  print("Done with 5000 records insertion")
