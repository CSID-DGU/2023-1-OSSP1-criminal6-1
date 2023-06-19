<?php
include_once('retrofit_config.php');

$id = $_GET['id'];
$roomID1 = $_GET['roomID1'];
$roomID2 = $_GET['roomID2'];
$roomID3 = $_GET['roomID3'];

$query = "SELECT roomID, title, roomIntro FROM room WHERE roomID = $roomID1 OR roomID = $roomID2 OR roomID = $roomID3";

$queryResult = mysqli_query($con, $query);

$result = array();

while ($row = mysqli_fetch_array($queryResult)) {
    array_push($result, array('id' => $id, 'roomID' => $row[0], 'title' => $row[1], 'roomIntro' => $row[2]));
}

header('Content-Type: application/json');
echo json_encode(array("roomInfoList" => $result));
?>