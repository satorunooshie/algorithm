<?php
/*
 * N枚のカードがある
 * i枚目のカードにはa_iという数字が書かれている
 * 二人はこれらのカードを使って交互に
 * 1枚ずつカードを取っていく
 * 二人がすべてのカードを取ったときゲームは終了し
 * 取ったカードの数の合計がその人の得点になる
 * 二人ともが自分の得点を最大化するように最適な戦略を取ったとき
 * その差を求めよ
 * Nは1以上100以下
 * a_i(1<=i<=N)は1以上100以下
 */
$numOfCards = trim(fgets(STDIN));
$array = [];
$array = explode(' ', trim(fgets(STDIN)));
//asortは昇順
//arsortは降順だがキーが維持される
rsort($array);

$sumOfAlice = 0;
$sumOfBob = 0;
for ($i = 1; $i < $numOfCards; $i++) {
	if ($i / 2 !== 1) {
		$sumOfBob += $array[$i];
	} else {
		$sumOfAlice += $array[$i];
	}
}
//echo $sumOfAlice . "\n" . $sumOfBob;
echo $sumOfAlice - $sumOfBob;
//WA

/*
 * $x = fgets(STDIN);
 * $num = array_map('intval', explode(' ', trim(fgets(STDIN))));
 * rsort($num);
 * $c = 0;
 * $alice = 0;
 * $bob = 0;
 * foreach ($num as $value) {
 * $c++;
 * if ($c % 2 == 1) {
 * $slice += $value;
 * } else {
 * $bob += $value;
 * }
 * }
 * echo $alice - $bob;
 */
/*
 * fscanf(STDIN, "%s", $n);
 * $cards = explode(' ', trim(fgets(STDIN)));
 * $n = count($cards);
 * $alice = [];
 * $bob = [];
 * sort($cards);
 * for ($i = 0; $i < $n; $i++) {
 * $alice = array_pop($cards);
 * $bob = array_pop($cards);
 * }
 * echo array_sum($alice) - array_sum($bob);
 */
