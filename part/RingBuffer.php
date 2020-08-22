<?php
/*
 * リングバッファー
 */
class RingBuffer {
	private $front;
	private $rear;
	private $que;
	private $count;
	private $maxSize;

	public function __construct($size) {
		$this->que = array_fill(0, $size, null);
		$this->front = 0;
		$this->rear = 0;
		$this->count = 0;
		$this->maxSize = $size;
	}

	/*
	 * リングバッファーに値を追加する
	 * @param int $value
	 * @return Bool 追加成功の時true
	 *				配列がいっぱいで追加できないときはfalse
	 */
	public function enque($value) {
		if ($this->count == $this->maxSize) {
			return false;
		}
		$this->que[$this->rear++] = $value;
		if ($this->rear == $this->maxSize) {
			$this->rear = 0;
		}
		$this->count++;
	}

	/*
	 * リングバッファーの値を取得する
	 * @return 値
	 * 空の時はnull返す
	 */
	public function deque() {
		if ($this->maxSize == 0) {
			return null;
		}
		$this->count--;
		$value = $this->que[$this->front++];
		if ($this->front == $this->maxSize) {
			$this->front = 0;
		}
		return $value;
	}

	public function getQue() {
		return $this->que;
	}
}
