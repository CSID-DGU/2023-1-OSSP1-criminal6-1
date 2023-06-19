<?php
include_once('retrofit_config.php');

$roomId = $_GET['roomID'];
$roomId = (int) $roomId;

$query = "SELECT * FROM chat WHERE roomID = $roomId ORDER BY createdAt";

$queryResult = mysqli_query($con, $query);

$result = array();
$resultString = "";

while ($row = mysqli_fetch_assoc($queryResult)) {
    $resultString = $resultString . $row['senderID'] . "," . $row['content'] . "," . $row['createdAt'];
    $resultString = $resultString . "/";
}

$result2 = new stdClass();
$result2->result2 = $resultString;
echo json_encode($result2, JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
mysqli_close($con);
?>