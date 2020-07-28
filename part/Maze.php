<?php
/*
 * 迷路クラス
 * 深さ探索法で迷路を解く
 */

class Maze {
	//迷路の配列
	public $maze;
	//探索した地点を記録する配列
	public $reached;
	//ゴールできたらtrue
	public $isGoal;
	//x軸方向の幅
	const MAX_W = 6;
	//y軸方向の幅
	const MAX_H = 4;

	public function __construct($filePath) {
		$this->maze = self::createMazeFromCSV($filePath);
		$this->reached = array();
		$this->isGoal = false;
	}

	//迷路のcsvファイルを読み取って迷路の配列を作成する
	public function createMazeFromCSV($filePath) {
		$maze = array();
		$data = file_get_contents($filePath);
		$lines = explode("\r\n", $data);
		foreach ($lines as $line) {
			$maze[] = explode(", ", $line);
		}
		return $maze;
	}

	public function search($row, $col) {
		//Goalしていたらskip
		if ($this->isGoal) {
			return true;
		}
		//迷路の外側か壁の場合は何もしない
		if ($row < 0 ||
			self::MAX_H <= $row ||
			$col < 0 ||
			self::MAX_W <= $col ||
			$this->maze[$row][$col] == 'X') {
			return false;
		}
		//すでに到達している場合は何もしない
		if (isset($this->reached[$row][$col]) &&
			$this->reached[$row][$col] == true) {
			return false;
		}
		//今いる地点にチェックしたことを記録する
		$this->reached[$row][$col] = true;

		if ($this->maze[$row][$col] == 'G') {
			//exit;
			$this->isGoal = true;
			//var_dump($this->reached);
			return true;
		}

		//右に進む
		self::search($row, $col + 1);
		//左に進む
		self::search($row, $col - 1);
		//上に進む
		self::search($row + 1, $col);
		//下に進む
		self::search($row - 1, $col);

		//最終結果
		if ($this->isGoal) {
			return true;
		} else {
			return false;
		}
	}
}
