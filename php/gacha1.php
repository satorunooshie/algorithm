<?php
/*
 * ssr 3%
 * sr 12%
 * r 85%
 */
$raritys = [
	'SSR' => 100,
	'SR' => 1200,
	'R' => 8500,
];
//小数第二位まで排出を設定出来るように排出率の値を100倍にした
$cards['SSR'] = ['a', 'b', ];
$cards['SR'] = ['c', 'd', 'e', 'f', 'g', ];
$cards['R'] = ['h', ]; //'i', 'j', 'k', 'l', ];
//まずは単発
$rand = mt_rand(0, 10000);
$probability = 0;
foreach ($raritys as $rarity => $rarity_probability) {
	$probability += $rarity_probability;
	if ($rand <= $probability) {
		$res = array_rand($cards[$rarity], 1);
		break;
	}
}
echo $cards[$rarity][$res];
echo "\n";
//-------------------------------------------
//10連ガチャ
$sr_raritys = [
	'SSR' => 300,
	'SR' => 9700,
];
$rand = mt_rand(0, 10000);
for ($i = 0; $i < 9; $i++) {
	$probability = 0;
	foreach ($raritys as $rarity => $rarity_probability) {
		$probability += $rarity_probability;
		if ($rand <= $probability) {
			$result[] = array_rand($cards[$rarity], 1);
			break;
		}
	}
}
//SR以上確定
$probability = 0;
foreach ($sr_raritys as $rarity => $rarity_probability) {
	$probability += $rarity_probability;
	if ($rand <= $probability) {
		$result[] = array_rand($cards[$rarity], 1);
		break;
	}
}
foreach ($result as $v) {
	echo $v . "\n";
}
