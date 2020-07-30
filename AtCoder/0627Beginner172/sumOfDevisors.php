<?php
$n = trim(fgets(STDIN));
$ans = 0;
for ($i = 1; $i <= $n; $i++) {
	$t = floor($n / $i);
	$ans += $t * ($t + 1) * $i / 2;
}
echo sprintf("%.0f", $ans) . "\n";
