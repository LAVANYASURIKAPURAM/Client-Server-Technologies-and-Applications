#! /usr/bin/env bash

# Variables
DBUSER=root
DBPASSWD=itmd521

echo -e "\n--- Mkay, installing now... ---\n"

echo -e "\n--- Updating packages list ---\n"
apt-get -qq update

echo -e "\n--- Install base packages ---\n"
apt-get -y install vim curl build-essential python-software-properties git >> /vagrant/vm_build.log 2>&1

echo -e "\n--- Add Node 6.x rather than 4 ---\n"
curl -sL https://deb.nodesource.com/setup_6.x | sudo -E bash - >> /vagrant/vm_build.log 2>&1

echo -e "\n--- Updating packages list ---\n"
apt-get -qq update

# MySQL setup for development purposes ONLY
echo -e "\n--- Install MySQL specific packages and settings ---\n"
debconf-set-selections <<< "mysql-server mysql-server/root_password password $DBPASSWD"
debconf-set-selections <<< "mysql-server mysql-server/root_password_again password $DBPASSWD"
debconf-set-selections <<< "phpmyadmin phpmyadmin/dbconfig-install boolean true"
debconf-set-selections <<< "phpmyadmin phpmyadmin/app-password-confirm password $DBPASSWD"
debconf-set-selections <<< "phpmyadmin phpmyadmin/mysql/admin-pass password $DBPASSWD"
debconf-set-selections <<< "phpmyadmin phpmyadmin/mysql/app-pass password $DBPASSWD"
debconf-set-selections <<< "phpmyadmin phpmyadmin/reconfigure-webserver multiselect none"
apt-get -y install mysql-server phpmyadmin >> /vagrant/vm_build.log 2>&1

echo -e "\n--- Setting up our MySQL user and db ---\n"
mysql -uroot -p$DBPASSWD -e "CREATE DATABASE w_data" >> /vagrant/vm_build.log 2>&1
mysql -uroot -p$DBPASSWD -e "grant all privileges on w_data.* to '$DBUSER'@'localhost' identified by '$DBPASSWD'" > /vagrant/vm_build.log 2>&1
mysql -uroot -p$DBPASSWD -e "use w_data; create table ncdc1 (

usaf_wthr_id VARCHAR(6),

wban_wthr_id INTEGER,

observ_year INTEGER,


latitude VARCHAR(6),

longitude VARCHAR(7),

elevation VARCHAR(5),

wind_dir INTEGER,

sky_ceiling_heigh INTEGER,


visibility_dis INTEGER,


air_temp VARCHAR(5),

due_pt_temp VARCHAR(5),

atmp_pres INTEGER


);

create table ncdc2 (

usaf_wthr_id VARCHAR(6),

wban_wthr_id INTEGER,

observ_year INTEGER,


latitude VARCHAR(6),

longitude VARCHAR(7),

elevation VARCHAR(5),

wind_dir INTEGER,

sky_ceiling_heigh INTEGER,


visibility_dis INTEGER,


air_temp VARCHAR(5),

due_pt_temp VARCHAR(5),

atmp_pres INTEGER


);" >> /vagrant/vm_build.log 2>&1


