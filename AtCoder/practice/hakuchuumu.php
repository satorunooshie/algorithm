<?php
/*
 * 英小文字からなる文字列Sが与えられたとき
 * Tが空文字列である状態から始め
 * 以下の操作を好きな回数繰り返すことでS=Tとなるか検証
 * dream, dreamer, erase, eraser
 * 1<=S<=10^5
 */
$string = trim(fgets(STDIN));
fscanf(STDIN, "%s", $s);
$string = str_replace('eraser', '', $string);
$string = str_replace('erase', '', $string);
$string = str_replace('dreamer', '', $string);
$string = str_replace('dream', '', $string);
if ($string == '') {
	echo "YES";
} else {
	echo "NO";
}
//echo ($string == '') ? 'NO' : 'YES';
