<?php
require_once './part/Knapsack.php';
//ナップサックの容量を設定
$knapsack = new Knapsack(32);
//ナップサックの商品を追加
$knapsack->addProduct(2, 2);
$knapsack->addProduct(3, 4);
$knapsack->addProduct(5, 7);
$knapsack->addProduct(6, 10);
$knapsack->addProduct(9, 30);
//ナップサック問題を解く
$knapsack->solveKnapsack();

