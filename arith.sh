echo "enter value for a"
read a

echo "enter value for b"
read b

echo "sum of $a+$b ="` expr $a + $b `
echo "diff of $a-$b ="` expr $a - $b `
echo "product  of $a*$b ="` expr $a \* $b ` 
echo "division of $a/$b ="` expr $a / $b `

