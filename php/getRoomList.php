<?php
include_once('retrofit_config.php');

$id = $_GET['id'];
$query = "SELECT * FROM room WHERE roomID IN 
    (SELECT roomID FROM roomMember WHERE memberID1='$id' UNION
    SELECT roomID FROM roomMember WHERE memberID2='$id' UNION
    SELECT roomID FROM roomMember WHERE memberID3='$id' UNION
    SELECT roomID FROM roomMember WHERE memberID4='$id' UNION
    SELECT roomID FROM roomMember WHERE memberID5='$id' UNION
    SELECT roomID FROM roomMember WHERE memberID6='$id')";

$queryResult = mysqli_query($con, $query);

$result = array();

while ($row = mysqli_fetch_array($queryResult)) {
    array_push($result, array('roomID' => $row[0], 'title' => $row[1]));
}

header('Content-Type: application/json');
echo json_encode(array("roomList" => $result));

mysqli_close($con);
?>