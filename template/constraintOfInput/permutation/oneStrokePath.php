<?php
/*
 * 自己ループと二重辺を含まないN頂点M辺の重みなし無向グラフが与えられます
 * i(1<=i<=M)番目の辺は頂点a_iと頂点b_iを結びます
 * ここで自己ループはa_i=b_i(1<=i<=M)となる辺のことを表します
 * また二重辺はa_i=a_jかつb_i=b_j(1<=i<j<=M)となる辺のことを表します
 * 頂点1を始点としてすべての頂点を一度だけ訪れるパスは何通りありますか
 * ただしパスの始点と終点の頂点も訪れたものとみなします
 * 問題文の条件を満たすパスが何通りあるか出力せよ
 * 2<=N<=8
 * 0<=M<=N(N-1)/2
 * 1<=a_i<b_i<=N
 * 与えられるグラフは自己ループと二重辺を含まない
 */
list($n, $m) = explode(" ", trim(fgets(STDIN)));
$x = [];
for ($i = 0; $i < $m; $i++) {
	list($a, $b) = explode(" ", trim(fgets(STDIN)));
	if (!isset($x[$a])) $x[$a] = [];
	if (!isset($x[$b])) $x[$b] = [];
	$x[$a][$b] = true;
	$x[$b][$a] = true;
}
echo dfs($x, [], 1, $n);
function dfs($x, $root, $now, $n) {
	if (isset($root[$now])) {
		return 0;
	}
	if (count($root) == $n - 1) {
		return 1;
	} else {
		$root[$now] = true;
	}
	$p = $x[$now];
	$ret = 0;
	foreach ($p as $q => $v) {
		$ret += dfs($x, $root, $q, $n);
	}
	return $ret;
}
