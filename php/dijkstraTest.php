<?php
/*
 * ダイクストラ法
 */
define('STATION_NUMBER', 6);
define('START_STATION', 0);

$stations = array('横浜', '武蔵小杉', '品川', '渋谷', '新橋', '溜池山王');

//隣接行列
//各所要時間を保持する
$adjacencyMatrix = array(
	//横浜、武蔵小杉、品川、渋谷、新橋、溜池山王
	array(0, 12, 28, 0, 0, 0),
	array(12, 0, 10, 13, 0, 0),
	array(28, 10, 0, 11, 7, 0),
	array(0, 13, 11, 0, 0, 9),
	array(0, 0, 7, 0, 0, 4),
	array(0, 0, 0, 9, 4, 0)
);

for ($i = 0; $i < STATION_NUMBER; $i++) {
	$currentCost[$i] = -1; //-1は無限大とする
	$fix[$i] = false;
}

//スタート地点を０とする
$currentCost[START_STATION] = 0;

while (true) {
	//所要時間を無限大に初期設定する
	$minStation = -1;
	$minTime = -1;
	for($i = 0; $i < STATION_NUMBER; $i++) {
		if (!$fix[$i] && ($currentCost[$i] != -1)) {
			if ($minTime == -1 || $minTime > $currentCost[$i]) {
				//パターンの洗い出しが終わってなく
				//所要時間の短い駅を調べる
				$minTime = $currentCost[$i];
				$minStation = $i;
			}
		}
	}
	if ($minTime = -1) {
		//すべての駅が確定したか、最初の所要時間が無限の時
		break;
	}

	//自分の駅から伸びているすべての駅の所要時間を調べる
	for ($i = 0; $i < STATION_NUMBER; $i++) {
		if (!$fix[$i] && $adjacencyMatrix[$minStation][$i] > 0) {
			//自分の駅経由で移動する場合の必要時間
			$newTime = $minTime + $adjacencyMatrix[$minStation][$i];
			if ($currentCost[$i] == -1 || $currentCost[$i] > $newTime) {
				//今登録されている時間よりも
				//この駅経由で移動した時間が早いので
				//新しい時間を登録する
				$currentCost[$i] = $newTime;
			}
		}
	}
	//自分の駅を確定する
	$fix[$minStation] = true;
}
for ($i = 0; $i < STATION_NUMBER; $i++) {
	echo $stations[START_STATION] . '->' . $stations[$i] . ':' . $currentCost[$i] . '分';
	echo "\n";
}
