echo "enter limit"
read n
i=0
se=0
so=0
sd=0
while [ $i -lt $n ]
do
read a
sd=`expr $a + $sd`
a1=`expr $a % 2`
if [ $a1 -eq 0 ] 
then
se=`expr $se + $a`
else
so=`expr $so + $a`
fi
i=`expr $i + 1`
done

echo "sum of odd nob=" $so
echo "sum of even nob="$se
echo "sum of all nob=" $sd 

