<?php

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include_once('retrofit_config.php');

    $id = $_POST['id'];
    $roomId = $_POST['roomID'];

    $roomId = (int) $roomId;

    $query = "SELECT memberNum FROM room WHERE roomID = $roomId";

    $queryResult = mysqli_query($con, $query);

    $result = mysqli_fetch_assoc($queryResult);

    $memberNum = $result['memberNum'];
    $memberNum = (int) $memberNum;
    $memberNum += 1;

    $query = "UPDATE room SET memberNum = $memberNum WHERE roomID = $roomId";
    mysqli_query($con, $query);

    $memberNum = (string) $memberNum;

    $updateMemberNum = "memberID".$memberNum;


    $query = "UPDATE roomMember SET $updateMemberNum = '$id' WHERE roomID = $roomId";
    mysqli_query($con, $query);
}

mysqli_close($con);

?>