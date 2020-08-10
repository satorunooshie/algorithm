<?php
/*
function multipleTime($time, $mult) {
	//時間を時、分、秒に分ける
	$times = explode(':', $time);
	//時を分に変換
	$hour = $times[0] * 60;
	//秒を分に変換
	$second = round($times[2] / 60, 2);
	//分を足し上げる
	$minutes = $hour + $time[1] + $second;
	//掛けたい数をかけて秒に変換する
	$result = $minutes * $mult * 60; //割合を掛け算して秒に変換
	//秒のtimestampをmktimeで算出しdate関数でH:i:sに変換
	return date('H:i:s', mktime(0, 0, $result));
}
$time = "01:00:00";
$mult = 3;
echo multipleTime($time, $mult);
 */
function multipleTimeMoney($time) {
	define('WAGE', 1050);
	$times = explode(':', $time);
	$hour = $times[0];
	$second = round($times[1] / 60, 3);
	$minutes = round($times[2] / 360, 3);
	$result = ($hour + $second + $minutes) * WAGE;
	return floor($result);
}
$time = '01:30:03';
echo multipleTimeMoney($time);
