<?php

// already validated by front-end

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include_once("retrofit_config.php");

    $createrUserId = $_POST['createrUserId'];
    $title = $_POST['title'];
    $region1 = $_POST['region1'];
    $region2 = $_POST['region2'];
    $region3 = $_POST['region3'];
    $date = $_POST['date'];
    $genre = $_POST['genre'];
    $difficulty = $_POST['difficulty'];
    $fear = $_POST['fear'];
    $activity = $_POST['activity'];
    $roomIntro = $_POST['roomIntro'];
    #$memberNum = $_POST['memberNum'];

    // room table에 정보 INSERT하면서 새로운 방 만들기
    // roomMember table에 정보 INSERT해서 새로운 roomMember 생성, 방이 처음 만들어졌을 때는 member로 방장만 존재함.

    $createRoomQuery = "INSERT INTO room VALUES(null, '$title', '$region1', '$region2', '$region3', '$date', '$genre', $difficulty, $fear, $activity, '$roomIntro', 1);";
    $createRoomResult = mysqli_query($con, $createRoomQuery);
    if ($createRoomResult) {
        $createdRoomId = mysqli_insert_id($con);
        $insertMemberQuery = "INSERT INTO roomMember VALUES($createdRoomId, '$createrUserId', null, null, null, null, null)";
        $insertMemberResult = mysqli_query($con, $insertMemberQuery);
        if ($insertMemberResult) {
            $result = new StdClass();
            $result->result = "createRoomSuccess";
        }
    } else {
        $result = new StdClass();
        $result->result = "createRoomFail";
    }

    header('Content-Type: application/json');

    echo json_encode($result, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);
}
mysqli_close($con);

?>