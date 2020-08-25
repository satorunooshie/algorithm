<?php
/*
 * 大きさN順列を並び替えてできる数列P, Qがある
 * 大きさNの順列はN!通り考えられます
 * このうち、Pが辞書順でa番目に小さくQが辞書順でb番目に小さいとして
 * |a - b|を求めてください
 * 二つの数列X, Yについてある整数kが存在して
 * X_i=Y_i(1<=i<=k)かつX_k<Y_kが成り立つとき
 * XはYより辞書順で小さいと定義されます
 * 2<=N<=8
 * P, Qは大きさNの順列である
 */
while ($line = fgets(STDIN)) {
	$tmp[] = trim($line);
}

$base = [];
for ($i = 0; $i < $tmp[0]; $i++) {
	$base[$i] = $i + 1;
}

$all = pat($base);

$p = preg_replace("/ /", "", $tmp[1]);
$q = preg_replace("/ /", "", $tmp[2]);

$p_index = array_search($p, $all);
$q_index = array_search($q, $all);

echo $p_index - $q_index < 0 ? $q_index - $p_index : $p_index - $q_index;

function pat($a, $s = '') {
	$r = array();
	if (count($a) && is_array($a)) {
		//第一引数に文字の配列を渡されたらループして処理
		foreach ($a as $k => $v) {
			//文字の配列から一文字もらってこちらにくっつけて
			$_s = $s . $v;
			//もらった一文字を除いた文字の配列を作って
			$_a = $a;
			unset($_a[$k]);
			//再帰呼び出し
			$_r = pat($_a, $_s);
			//返り値にする変数に結果を追加
			$r = array_merge($r, $_r);
		}
	} else {
		//第一引数が空っぽの配列だったら再帰の呼び出しはここでストップ
		//返り値はこれだけ
		$r[] = $s;
	}
	return $r;
}
