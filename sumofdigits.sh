echo "enter a value of any digits"
read a
s=0
b=$a
while [ $a -gt 0 ]
do
r=`expr $a % 10`
s=`expr $s + $r`
a=`expr $a / 10`
done
echo "sum of $b digits is $s"
