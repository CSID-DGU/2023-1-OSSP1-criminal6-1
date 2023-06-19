<?php

//already validated by front-end

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include_once("retrofit_config.php");

    $id = $_POST['id'];
    $password = $_POST['password'];
    $name = $_POST['name'];


    mysqli_query($con, "INSERT INTO user (id, password, name) VALUES ('$id', '$password', '$name')"); //add user data into DB

    $num = mysqli_num_rows(mysqli_query($con, "SELECT id FROM user WHERE id='$id'")); //check if INSERT succeed or not

    if ($num > 0) {
        $result = new StdClass();
        $result->result = "success";
    } else {
        $result = new StdClass();
        $result->result = "fail";
    }

    header('Content-Type: application/json');

    echo json_encode($result, JSON_PRETTY_PRINT | JSON_UNESCAPED_UNICODE);

    mysqli_close($con);
}
?>
