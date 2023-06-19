<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include_once("retrofit_config.php");

    $id = $_POST['id'];
    $password = $_POST['password'];

    $num = mysqli_num_rows(mysqli_query($con, "SELECT id FROM user WHERE id='$id'"));

    if ($num > 0) { //if id exists
        $num = mysqli_num_rows(mysqli_query($con, "SELECT id FROM user WHERE id='$id' AND password='$password'"));
        if ($num > 0) { //if password exists
            $result = new StdClass();
            $result->result = "success";
        } else { //if password deosn't exist
            $result = new StdClass();
            $result->result = "비밀번호가 일치하지 않습니다.";
        }
    } else { //if id doesn't exist
        $result = new StdClass();
        $result->result = "존재하지 않는 id입니다.";
    }

    header('Content-Type: application/json');

    echo json_encode($result, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);
}



mysqli_close($con);
?>