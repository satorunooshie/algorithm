<?php
$input = file_get_contents('php://stdin');
$list = ['AC', 'WA', 'TLE', 'RE'];
foreach  ($list as $row) {
	echo $row . ' x ' . substr_count($input, $row) . "\n";
}
