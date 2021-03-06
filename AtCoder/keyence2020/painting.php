<?php
/*
 * H行W列のマス目があり
 * 最初全てのマスは白色です
 * あなたはこのますめに何回かペイント操作を施すことにしました
 * 一回のペイント操作では以下の二種類の作業のうちいずれか一つが行えます
 * 行を一つ選びその行に含まれるマスをすべて黒く塗る
 * 列を一つ選びその列に含まれるマスをすべて黒く塗る
 * 黒く塗られているマスの個数がN個以上となるようにするためには
 * 最小で何回のペイント操作が必要ですか
 * なお、制約の項で記述される条件の下で何回かペイント操作を行うことで
 * 黒く塗られているマスの個数が
 * N個以上となるようにできることが保証されます
 * 1<=H<=100
 * 1<=W<=100
 * 1<=N<=H*W
 */
list($h) = ints();
list($w) = ints();
list($n) = ints();
echo (intdiv($n, max($h, $w)) + min(1, $n % max($h, $w)));
function ints() {
	return array_map('intval', explode(' ', trim(fgets(STDIN))));
}
