echo "enter name"
read a

len=`echo $a |wc -c`
i=`expr $len - 1`
echo "length of string="$i
echo "reverse of string"
while [ $i -gt 0 ]
do
echo $a | cut -c$i
i=`expr $i - 1`
done


