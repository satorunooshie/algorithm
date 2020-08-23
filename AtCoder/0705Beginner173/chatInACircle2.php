<?php
$N = (int)fgets(STDIN);
$A = array_map(function ($n) {
	return (int)$n;
}, explode(' ', fgets(STDIN)));
rsort($A);
if ($N % 2 === 0) {
	echo array_sum(array_slice($A, 0, $N / 2)) * 2 - $A[0];
	return;
}
echo array_sum(array_slice($A, 0, ($N - 1) / 2)) * 2 - $A[0] + $A[($N - 1) / 2];
