<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include_once("retrofit_config.php");

    $id = $_POST['id'];
    $roomID1 = $_POST['roomID1'];
    $roomID2 = $_POST['roomID2'];
    $roomID3 = $_POST['roomID3'];

    $query = "INSERT INTO tempRecommendationList (userID, roomID1, roomID2, roomID3) VALUES ('$id', $roomID1, $roomID2, $roomID3)";
    mysqli_query($con, $query);

    mysqli_close($con);
}
?>