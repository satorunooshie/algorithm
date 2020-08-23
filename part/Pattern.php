<?php
/*
 * 7パズルに使用するパターン
 * $blockLineの文字列、カンマ区切り0,1,2,3,4,5,6,7
 * $patternFromにパターンを作成したパターン元
 */
class Pattern {
	public $blockLine;
	public $patternFrom;
	public function __construct($blockLine, $patternFrom) {
		$this->blockLine = $blockLine;
		$this->patternFrom = $patternFrom;
	}
}
