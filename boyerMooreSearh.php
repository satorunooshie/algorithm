<?php
/*
 * Boyer Moore法
 * 見つけたい文字パターンの末尾から探していく
 * テキストの頭から進める
 * パターンに含まれていない文字の場合はパターンの数文ずらす
 * パターンに含まれている場合は最小限の移動
 */

mb_internal_encoding("utf-8");
//japanese
//全部同じ
var_dump(bMSearch("テストトテスト", "テストトテスト"));
echo "\n";
//最初でマッチ
var_dump(bMSearch("テストトテストトトト", "テストトテスト"));
echo "\n";
//途中でマッチ
var_dump(bMSearch("テストトテストストトテストテテテ", "テストトテスト"));
echo "\n";
//途中でマッチ
var_dump(bMSearch("abbcwsdfajsdfksjテストトテストストトテストテ", "テストトテスト"));
echo "\n";
//最後でマッチ
var_dump(bMSearch("テストトテテストトテスト", "テストトテスト"));
echo "\n";
//最後でマッチ
var_dump(bMSearch("テストトテnトテストトテスト", "テストトテスト"));
echo "\n";
//最後でマッチ
var_dump(bMSearch("テストトテスnトテストトテスト", "テストトテスト"));
echo "\n";
//見つけた文字より短い
var_dump(bMSearch("テスト", "テストトテスト"));
echo "\n";
//一致しない
var_dump(bMSearch("テストステト", "テストトテスト"));
echo "\n";
//一致しない
var_dump(bMSearch("後戻りを考慮せず無限ループではまる", "テストトテスト"));
echo "\n";

function bMSearch($text, $pattern) {
	$tLength = mb_strlen($text);
	$pLength = mb_strlen($pattern);
	$shifTable = array(); //シフトする量のマスターテーブル
	//シフトテーブルの作成
	//末尾の一個前まで実施
	for ($i = 0; $i < $pLength - 1; $i++) {
		//キーはパターンの文字
		//値はシフト量
		$shifTable[mb_substr($pattern, $i, 1, 'UTF-8')] = $pLength - $i - 1;
	}
	//パターンの末尾だけの計算
	//もしすでに文字がある場合は何もしない
	//最初のfor文でやるとシフト量が０になるため
	if (!array_key_exists(mb_substr($pattern, $pLength - 1, 1, 'UTF-8'), $shifTable)) {
		$shifTable[mb_substr($pattern, $pLength - 1, 1, 'UTF-8')] = $pLength;
	}
	//探索する
	//テキストのindexのパターンを末尾と合わせる
	//末尾から探索していく
	$tIndex = $pLength - 1;
	while ($tIndex < $tLength) {
		//パターンの末尾の位置
		$pp = $pLength - 1;
		//文字が一致している間実施
		while (mb_substr($text, $tIndex, 1, 'UTF-8') ==
			mb_substr($pattern, $pp, 1, 'UTF-8')) {
			if ($pp == 0) {
				//success
				return true;
			}
			$tIndex--;
			$pp--;
		}
		//移動する
		if (isset($shifTable[mb_substr($text, $tIndex, 1, 'UTF-8')])) {
			//後戻り対策
			//大きいほうを取る
			$tIndex = $tIndex + MAX($shifTable[mb_substr($text, $tIndex, 1, 'UTF-8')], $pLength - $pp);
		} else {
			//パターン以外の文字列
			$tIndex += $pLength;
		}
	}
	//failed
	return false;
}
