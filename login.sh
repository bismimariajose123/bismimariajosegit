echo "enter user name"
read a
if (who | grep -w $a)
then
echo "valid user"
else
echo "invalid user"
fi
