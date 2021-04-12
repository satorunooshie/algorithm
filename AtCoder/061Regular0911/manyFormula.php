<?php
fscanf(STDIN, "%s", $num);

$count = strlen($num) - 1;

$sum = 0;
for ($i = 0; $i <= (2 ** $count) - 1; $i++) {
    $new_sum = [];
    $get = "";
    for ($j=0;$j<=$count;$j++) {
        $get .= $num[$j];
        if ($i & (2 ** $j)) {
            $new_sum[] = (float)$get;
            $get = "";
        }
    }
    $new_sum[] = (float)$get;
    $sum += array_sum($new_sum);
}

echo $sum.PHP_EOL;