<?php
/*
 * 整数aが入力されます
 * 値a+a^2+a^3を出力してください
 * 1<=a<=10
 */
fscanf(STDIN, "%d", $a);
$answer = $a + $a * $a + $a * $a * $a;
echo $answer;
//27ms
//echo $a + pow($a, 2) + pow($a, 3);
//20ms
