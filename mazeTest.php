<?php
/*
 * 深さ優先探索で迷路を解く
 * 問題はS地点からG地点にたどり着くかどうか検証せよ
 * 但し0は進め、Xは進めないとする
 * たどり着いた場合はtrue
 * 失敗した場合はfalseを返せ
 */

require_once './part/Maze.php';
$maze = new Maze('./file/maze.csv');
//S地点に引数をセットする
var_dump($maze->search(0, 2));
