# vagrant plugin install vagrant-vbguest  

<table class="image">
<caption align="bottom">command: vagrant plugin install vagrant-vbguest</caption>
<tr><td><img src=images/vagrant_plugin_install_vagrant_vbguest.png alt=command: vagrant plugin install vagrant-vbguest></td></tr>
</table>

# Local folder inside of xenial64 folder on your host system named: data -Created (ls command)  

<table class="image">
<caption align="bottom">command: ls</caption>
<tr><td><img src=images/ls_vagrant_data_folder.png alt=command: ls></td></tr>
</table>

# synced_folders 

## To check if the synced folder is working - Created a sample file in host machine and checked if the sample file is reflected in virtual machine.

<table class="image">
<caption align="bottom">command: echo "test synced folder" > test-synced_folder.txt</caption>
<tr><td><img src=images/host_system_creating_test_file_synced_folder.png alt=command: echo "test synced folder" > test-synced_folder.txt></td></tr>
</table>

## Checking if the sample file is reflecting in the virtual machine.

<table class="image">
<caption align="bottom">command: ls -al /vagrant_data/</caption>
<tr><td><img src=images/vm_system_synced_folder_output.png alt=command: ls -al /vagrant_data/></td></tr>
</table>

# FREEM -M  

## Updated memory for 2048

<table class="image">
<caption align="bottom">command: free-m for 2048</caption>
<tr><td><img src=images/2048free_m.PNG alt=command: free-m for 2048></td></tr>
</table>

## Updated memory for 4096

<table class="image">
<caption align="bottom">command: free-m for 4096</caption>
<tr><td><img src=images/free-m-4096.png alt=command: free-m for 4096></td></tr>
</table>

# hadoop fs -ls ls/ncdc/  

<table class="image">
<caption align="bottom">command: hadoop fs -ls ls/ncdc/ </caption>
<tr><td><img src=images/ls_ncdc.png alt=command: hadoop fs -ls ls/ncdc/ ></td></tr>
</table>

# hadoop fs -ls ls/ncdc/sample/  

<table class="image">
<caption align="bottom">command: hadoop fs -ls ls/ncdc/sample/ </caption>
<tr><td><img src=images/ls_ncdc_sample.png alt=command: hadoop fs -ls ls/ncdc/sample/ ></td></tr>
</table>

# hadoop fs -cat output/ncdc/sample/1/part-r-00000  

<table class="image">
<caption align="bottom">command: hadoop fs -cat output/ncdc/sample/1/part-r-00000 </caption>
<tr><td><img src=images/output_jar.png alt=command: hadoop fs -cat output/ncdc/sample/1/part-r-00000  ></td></tr>
</table>

