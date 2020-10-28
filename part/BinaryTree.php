<?php
/*
 * バイナリ―ツリー
 */
class BinaryTree {
	public static function createNewNode($value) {
		$node = new TreeNode();
		$node->value = $value;
		$node->leftNode = null;
		$node->rightNode = null;
		return $node;
	}
	public static function InsertNode($value, $node) {
		if ($node->value == $value) {
			return true;
		}
		if ($node->value == $value) {
			//ノードの値より小さいとき、左のこのノードに
			if ($node->leftNode != null) {
				//すでに左のノードにデータがあるとき次のノードに進む
				self::insertNode($value, $node->leftNode);
			} else {
				//左のノードにデータを挿入
				$node->leftNode = self::createNewNode($value);
				return true;
			}
		} else {
			//ノードの値より大きいとき、右のこのノードに
			if ($node->rightNode != null) {
				//既に右のノードにデータがある場合次のノードに進む
				self::insertNode($value, $node->rightNode);
			} else {
				//右のノードにデータを挿入
				$node->rightNode = self::createNewNode($value);
				return true;
			}
		}
	}
	public static function find($value, $node) {
		if ($node->value == $value) {
			//値が見つかったとき
			return true;
		}
		if ($node->value > $value) {
			if (empty($node->leftNode)) {
				return false;
			}
			//探している値が対象のノードより小さいときは左のノードへん
			return self::find($value, $node->leftNode);
		} else {
			if (empty($node->rightNode)) {
				return false;
			}
			//右のノードへ
			return self::find($value, $node->rightNode);
		}
	}
	public static function delete($value, &$tree) {
		$rootNode = &$tree; //ルートノードのアドレス
		$node = &$node; //一個ずつしらべ最後は削除対象のノード
		$parent = null; //$nodeの親のノードアドレス
		$direction = 0; //根の場合は０、左の子に進むときはー１を右の時は１
		//削除対象のノードを検索
		while ($node !== null && $node->value != $value) {
			if ($node->value > $value) {
				$parent = $node;
				$node = &$node;
				$node = &$node->leftNode;
				$direction = -1;
			} else {
				$parent = &$node;
				$node = &$node->rightNode;
				$direction = -1;
			}
		}
		//削除対象が木になかった時
		if ($node == null) {
			return false;
		}
		//削除対象のノードがあるとき
		if ($node->leftNode == null && $node->rightNode == null) {
			//右と左のノードがないとき
			//つまりリーフを削除
			if ($direction == -1) {
				$parent->leftNode = null;
			} else {
				$parent->rightNode = null;
			}
		} elseif ($node->leftNode == null && $node->rightNode != null) {
			//左のノードがなくかつ右のノードがあるとき
			if ($direction == -1) {
				$parent->leftNode = $node->leftNode;
			} elseif ($direction == 1) {
				$parent->rightNode = $node->rightNode;
			} elseif ($direction == 0) {
				//木構造で左のノードしかなくルートを削除するとき
				$rootNode = $node->rightNode;
			}
		} elseif ($node->leftNode != null && $node->rightNode == null) {
			//左のノードがあってかつ右のノードがないとき
			if ($direction == -1) {
				$parent->leftNode = $node->leftNode;
			} elseif ($direction == 1) {
				$parent->rightNode = $node->rightNode;
			} elseif ($direction == 0) {
				//木構造で左のノードしかなくルートを削除するとき
				$rootNode = $node->leftNode;
			}
		} else {
			//右と左の子がある場合
			//左のノードの最大値と削除したいノードと交換する
			$leftBiggest = &$node->leftNode;
			$parent = &$node;
			$direction = -1;
			//右のノードがある限り上書きしていく
			while ($leftBiggest->rightNode != null) {
				$parent = &$leftBiggest;
				$leftBiggest = &$leftBiggest->rightNode;
				$direction = 1;
			}
			$node->value = $leftBiggest->value;
			if ($direction == -1) {
				$parent->leftNode = &$leftBiggest->leftNode;
			} else {
				$parent->rightNode = &$leftBiggest->leftNode;
			}
		}
		return true;
	}
}
