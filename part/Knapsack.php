<?php
class Knapsack {
	public $knapsack; //ナップサック
	public $knapsackCount; //ナップサックの容量
	public $products; //商品はサイズと価値がある

	public function __construct($capacity) {
		$this->knapsack = array_fill(0, $capacity + 1, 0);
		$this->knapsackCount = $capacity;
		$this->product = array();
	}

	public function addProduct($size, $value) {
		$this->products[] = array('size' => $size, 'value' => $value);
	}

	public function solveKnapsack() {
		//ナップサックに詰め込んだときの値を初期化
		$napValue = array_fill(0, $this->knapsackCount + 1, 0);
		//ナップサックの容量を表示する
		echo 'ナップサックの大きさ';
		for ($i = 0; $i < $this->knapsackCount + 1; $i++) {
			echo $i . "\n";
		}
		//扱う商品を一つずつ増やしていく
		$productCount = count($this->products);
		for ($i = 0; $i < $productCount; $i++) {
			//napIndexの初期値は商品サイズから
			for ($napIndex = $this->products[$i]['size'];
				$napIndex < $this->knapsackCount + 1;
				$napIndex++)
			{
				$newValue = $napValue[$napIndex - $this->products[$i]['size']] + $this->products[$i]['value'];
				if ($newValue > $napValue[$napIndex]) {
					$napValue[$napIndex] = $newValue;
				}
			}
			//表示
			echo $i + 1 . "番目までの商品を使用\n";
			for ($napIndex = 1; $napIndex < $this->knapsackCount + 1; $napIndex++) {
				echo $napValue[$napIndex] . "\n";
			}
			echo "\n";
		}
	}
}
