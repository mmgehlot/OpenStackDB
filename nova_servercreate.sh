#!/bin/bash
. ~osbash/profile.demo

myflavor="m1.tiny"
myimage="cirros"
myname=""
host=""
command=""
status=""
value=1

export logfile=${DL}/instance_$myname.log


if [ $(whoami) != 'root' ]; then
        echo "Must be root to run $0"
        exit 1;
fi
clear

############################################################
############################################################


command=`nova service-list |grep -i "nova-compute" |cut -f7 -d "|" `

if [ $command  = 'down' ]; then
echo "\n"
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+ !!! Nova Compute is down !!!" |tee -a $logfile
echo "+ !!! Please rerun the script once it is up !!!" |tee -a $logfile 
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
exit
fi
echo "\n"
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+ Nova Compute Up" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile

##############################################################
##############################################################
echo "\n"
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+ Calculating Hypervisor Stats..." |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile

ramtotal=`nova hypervisor-stats |grep memory_mb |cut -f3 -d "|" | sed -n 1'p' | tr ',' '\n'`
ramused=`nova hypervisor-stats |grep memory_mb |cut -f3 -d "|" | sed -n 2'p' | tr ',' '\n'`


if [ `expr $ramtotal - $ramused` -lt 512 ]; then
echo "\n"
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+ Image cannot be created,you have less memory" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
exit
fi

echo "\n"
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+ Sufficient Memory,Proceeding...."
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile

################################################################
################################################################

echo "\n"
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+ Enter your image name " |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
read myname

echo "\n"
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+ Creating a new instance with name "$myname...." " |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------+" |tee -a $logfile
echo "\n"
nova boot --flavor $myflavor --image $myimage $myname >> $logfile

if [ $? -eq 0 ]
then
command=`nova list|grep -i $myname |cut -f2 -d "|"`
echo "\n"
echo "+-------------------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------------------+" |tee -a $logfile
echo "+ $myname created  " |tee -a $logfile
echo "+ Instance id: $command  "|tee -a $logfile
echo "+ Please wait for it to boot up.... "|tee -a $logfile
echo "+-------------------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------------------+" |tee -a $logfile
fi

sleep 120

command=`nova list|grep -i $myname |cut -f7 -d "|" |cut -f2 -d "="`
echo "+-------------------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------------------+" |tee -a $logfile
echo "+ $myname booted up  " |tee -a $logfile
echo "+ Log name : $logfile " |tee -a $logfile
echo "+ Please ssh to $command to access "|tee -a $logfile
echo "+-------------------------------------------------------+" |tee -a $logfile
echo "+-------------------------------------------------------+" |tee -a $logfile
