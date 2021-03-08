<?php
/*
 * 幅優先探索法を用いて
 * ７パズルを解く
 * 問題
 * １２３４５６７０のブロックを一度混ぜて
 * １２３４５６７０に並べる最短の経路を出せ
 */
require_once './part/SevenPuzzle.php';
$sevenPuzzle = new SevenPuzzle();
/*
$firstValue = array(1, 2, 3, 4, 5, 6, 0, 7);
$firstValue = array(1, 2, 0, 4, 5, 6, 3, 7);
$firstValue = array(1, 0, 2, 4, 5, 6, 3, 7);
$firstValue = array(0, 1, 2, 4, 5, 6, 3, 7);
$firstValue = array(5, 1, 2, 4, 0, 6, 3, 7);
$firstValue = array(5, 1, 2, 4, 6, 0, 3, 7);
$firstValue = array(5, 1, 2, 4, 6, 3, 7, 0);
 */
$firstValue = array(5, 1, 2, 0, 6, 3, 7, 4);
$sevenPuzzle->saveHistory($firstValue, -1);

$endIndex = $sevenPuzzle->solveSevenPuzzle();
$sevenPuzzle->showAnswer($sevenPuzzle->history[$endIndex], $endIndex);
