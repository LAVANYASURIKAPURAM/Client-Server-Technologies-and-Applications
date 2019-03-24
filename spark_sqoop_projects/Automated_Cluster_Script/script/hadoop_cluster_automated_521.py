#!/usr/bin/env python
# Name: Lavanya Surikapuram (A20392981)
# date: 2/5/2018
# course number: ITMD 521
# descriptions: Automated Cluster Analysis 
# File Name: hadoop_cluster_automated_521.py

import os
import time
import random

# The end path for all the years
path_end = ['/1990/1990.txt', '/1990/1990.txt.bz2', '/1990/1990.txt.gz',\
            '/1991/1991.txt', '/1991/1991.txt.bz2', '/1991/1991.txt.gz',\
            '/1992/1992.txt', '/1992/1992.txt.bz2', '/1992/1992.txt.gz',\
            '/1993/1993.txt', '/1993/1993.txt.bz2', '/1993/1993.txt.gz',\
            '/1994/1994.txt', '/1994/1994.txt.bz2', '/1994/1994.txt.gz',\
            '/1995/1995.txt', '/1995/1995.txt.bz2', '/1995/1995.txt.gz',\
            '/1996/1996.txt', '/1996/1996.txt.bz2', '/1996/1996.txt.gz',\
            '/1998/1998.txt', '/1998/1998.txt.bz2', '/1998/1998.txt.gz',\
            '/1999/1999.txt', '/1999/1999.txt.bz2', '/1999/1999.txt.gz',\
            '/1980/1980.txt', '/1980/1980.txt.bz2', '/1980/1980.txt.gz',\
            '/1981/1981.txt', '/1981/1981.txt.bz2', '/1981/1981.txt.gz',\
            '/1982/1982.txt', '/1982/1982.txt.bz2', '/1982/1982.txt.gz',\
            '/1983/1983.txt', '/1983/1983.txt.bz2', '/1983/1983.txt.gz',\
            '/1984/1984.txt', '/1984/1984.txt.bz2', '/1984/1984.txt.gz',\
            '/1985/1985.txt', '/1985/1985.txt.bz2', '/1985/1985.txt.gz',\
            '/1986/1986.txt', '/1986/1986.txt.bz2', '/1986/1986.txt.gz',\
            '/1987/1987.txt', '/1987/1987.txt.bz2', '/1987/1987.txt.gz',\
            '/1988/1988.txt', '/1988/1988.txt.bz2', '/1988/1988.txt.gz',\
            '/1989/1989.txt', '/1989/1989.txt.bz2', '/1989/1989.txt.gz',\
            '/1970/1970.txt', '/1970/1970.txt.bz2', '/1970/1970.txt.gz',\
            '/1971/1971.txt', '/1971/1971.txt.bz2', '/1971/1971.txt.gz',\
            '/1972/1972.txt', '/1972/1972.txt.bz2', '/1972/1972.txt.gz',\
            '/1975/1975.txt', '/1975/1975.txt.bz2', '/1975/1975.txt.gz',\
            '/1976/1976.txt', '/1976/1976.txt.bz2', '/1976/1976.txt.gz',\
            '/1977/1977.txt', '/1977/1977.txt.bz2', '/1977/1977.txt.gz',\
            '/1978/1978.txt', '/1978/1978.txt.bz2', '/1978/1978.txt.gz',\
            '/1979/1979.txt', '/1979/1979.txt.bz2', '/1979/1979.txt.gz',\
            '/1960/1960.txt', '/1960/1960.txt.bz2', '/1960/1960.txt.gz',\
            '/1961/1961.txt', '/1961/1961.txt.bz2', '/1961/1961.txt.gz',\
            '/1962/1962.txt', '/1962/1962.txt.bz2', '/1962/1962.txt.gz',\
            '/1963/1963.txt', '/1963/1963.txt.bz2', '/1963/1963.txt.gz',\
            '/1964/1964.txt', '/1964/1964.txt.bz2', '/1964/1964.txt.gz',\
            '/1965/1965.txt', '/1965/1965.txt.bz2', '/1965/1965.txt.gz',\
            '/1966/1966.txt', '/1966/1966.txt.bz2', '/1966/1966.txt.gz',\
            '/1967/1967.txt', '/1967/1967.txt.bz2', '/1967/1967.txt.gz',\
            '/1968/1968.txt', '/1968/1968.txt.bz2', '/1968/1968.txt.gz',\
            '/1969/1969.txt', '/1969/1969.txt.bz2', '/1969/1969.txt.gz',\
            '/1950/1950.txt', '/1950/1950.txt.bz2', '/1950/1950.txt.gz',\
            '/1951/1951.txt', '/1951/1951.txt.bz2', '/1951/1951.txt.gz',\
            '/1952/1952.txt', '/1952/1952.txt.bz2', '/1952/1952.txt.gz',\
            '/1953/1953.txt', '/1953/1953.txt.bz2', '/1953/1953.txt.gz',\
            '/1954/1954.txt', '/1954/1954.txt.bz2', '/1954/1954.txt.gz',\
            '/1955/1955.txt', '/1955/1955.txt.bz2', '/1955/1955.txt.gz',\
            '/1956/1956.txt', '/1956/1956.txt.bz2', '/1956/1956.txt.gz',\
            '/1957/1957.txt', '/1957/1957.txt.bz2', '/1957/1957.txt.gz',\
            '/1958/1958.txt', '/1958/1958.txt.bz2', '/1958/1958.txt.gz',\
            '/1959/1959.txt', '/1959/1959.txt.bz2', '/1959/1959.txt.gz']

# This automates 100 Jobs or as many jobs by changing the range in for loop.
# Job is picked in a random fasion. 
# The execution of job is done parallely as the timout condition is mentioned, hence the system starts executing a job then
# exits from a running job, further executes many jobs in parallel.
for x in range(0, 100):
    os.system('timeout 8 hadoop jar mv02.jar MaxTemperature /user/controller/ncdc'+random.choice(path_end)+' /output/surikapuram/utss01'+str(x)+' ')





