echo "enter name"
read a
name=$a
rev=""
len=`echo $a |wc -c`
i=`expr $len - 1`

while [ $i -gt 0 ]
do
rev1=`echo $a | cut -c$i`
rev=$rev$rev1
i=`expr $i - 1`
done

if [ $name != $rev ]
then
echo "not palindrome"
else

echo "palindrome"
fi 

