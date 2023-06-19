<?php
include_once("retrofit_config.php");

$id = $_GET['id'];

$query = "SELECT roomID1, roomID2, roomID3 FROM tempRecommendationList WHERE userID = '$id'";

$queryResult = mysqli_query($con, $query);

/*$result = array();

while ($row = mysqli_fetch_array($queryResult)) {
    array_push($result, array('roomID1' => $row[0], 'roomID2' => $row[1], 'roomID3' => $row[2]));
}*/

$result = mysqli_fetch_assoc($queryResult);

$resultString = $result['roomID1'] . "," . $result['roomID2'] . "," . $result['roomID3'];
$resultString = (string) $resultString;

header('Content-Type: application/json');
#echo json_encode(array("roomList" => $result));
#echo json_encode($result, JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
$result2 = new stdClass();
$result2->result2 = $resultString;

$query = "DELETE FROM tempRecommendationList WHERE userID = '$id'";
mysqli_query($con, $query);

echo json_encode($result2, JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);
mysqli_close($con);
?>