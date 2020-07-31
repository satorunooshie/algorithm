<?php
/*
 * お店でN円の商品を買います
 * 1000円札のみを使って支払いを行うとき
 * おつりはいくらになりますか
 * 但し必要最低限の枚数を使うとします
 */
$price = trim(fgets(STDIN));
$money = ceil($price / 1000);
$change = $money * 1000 - $price;
echo $change;
