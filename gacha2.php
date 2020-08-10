<?php
$gacha = [
	'0' => [
		'name' => 'a',
		'percent' => 12,
	],
	'1' => [
		'name' => 'b',
		'percent' => 1,
	],
	'2' => [
		'name' => 'c',
		'percent' => 67,
	],
	'3' => [
		'name' => 'd',
		'percent' => 10,
	],
	'4' => [
		'name' => 'e',
		'percent' => 5,
	],
	'5' => [
		'name' => 'f',
		'percent' => 5,
	],
];
/*
 * bad answer
 * $hitKey = rand(0, count($gacha));
 * echo $gacha[$hitKey];
 * unset($gacha[$hitKey]);
 */

$hitRand = rand(0, 100);
echo $hitRand . "\n";
$sumPer = 0;
for ($i = 0; $i < count($gacha); $i++) {
	$sumPer += $gacha[$i]['percent'];
	$hitItem = $gacha[$i];
	if ($hitRand < $sumPer) {
		break;
	}
}
var_dump($hitItem);
//新カード追加
$sumRate = 0;
foreach ($gacha as $v) {
	$sumRate += $v['percent'];
}
$hitRand = rand(0, $sumRate);
echo $hitRand . "\n";
$tmpRate = 0;
for ($i = 0; $i < count($gacha); $i++) {
	$tmpRate += $gacha[$i]['percent'];
	$hitItem = $gacha[$i];
	if ($hitRand < $tmpRate) {
		break;
	}
}
var_dump($hitItem);
