<?php
include_once('retrofit_config.php');

$query = "SELECT * FROM room";

$queryResult = mysqli_query($con, $query);

$result = array();

while ($row = mysqli_fetch_array($queryResult)) {
    array_push($result, array('roomID'=>$row[0], 
    'title'=>$row[1],
    'region1'=>$row[2],
    'region2'=>$row[3],
    'region3'=>$row[4],
    'date'=>$row[5],
    'genre'=>$row[6],
    'difficulty'=>$row[7],
    'fear'=>$row[8],
    'activity'=>$row[9],)
);
}

header('Content-Type: application/json');
echo json_encode(array("roomInfoList" => $result));
mysqli_close($con);
?>