echo "enter user name"
read a
<<<<<<< HEAD
b=who
if [ $b |grep "$a" ]
then
echo "user valid"
=======
if (who | grep -w $a)
then
echo "valid user"
>>>>>>> linux
else
echo "invalid user"
fi
