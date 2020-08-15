<?php
$num = intval(fgets(STDIN));
$AC = 0;
$WA = 0;
$TLE = 0;
$RE = 0;
for ($i = 0; $i < $num; $i++) {
	$str = rtrim(fgets(STDIN));
	if ($str == 'AC') {
		$AC += 1;
	} elseif ($str == 'WA') {
		$WA += 1;
	} elseif ($str == 'TLE') {
		$TLE += 1;
	} else {
		$RE += 1;
	}
}
echo 'AC x ' . $AC . "\n";
echo 'WA x ' . $WA . "\n";
echo 'TLE x ' . $TLE . "\n";
echo 'RE x ' . $RE . "\n";

