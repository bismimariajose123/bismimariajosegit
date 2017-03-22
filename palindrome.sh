echo "enter name"
read a
reverse=`echo $a | rev`
echo $reverse
if [ $a -eq $reverse ]
then
echo "palindrome"
else
echo "not a palindrome"
fi




