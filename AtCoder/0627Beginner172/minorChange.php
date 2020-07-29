<?php
/*
 * 文字列S, Tが与えられます
 * 次の操作を繰り返してSをTに変更するとき
 * 操作回数の最小値を求めてください
 * 操作:Sの一文字を選んで別の文字に書き換える
 * S, Tは長さ1以上2*10^5以下
 * S, Tは英小文字のみからなる
 * SとTの長さは等しい
 */
$a = trim(fgets(STDIN));
$b = trim(fgets(STDIN));
//$a = 'cupofcoffee';
//$b = 'cupofhottea';
$count = '';
for ($i = 0; $i < strlen($a); $i++) {
	if ($a[$i] !== $b[$i]) {
		$count++;
	}
}
echo $count;
//32ms
