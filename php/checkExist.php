<?php
    include_once('retrofit_config.php');
    
    $id = $_GET['id'];

    $query = mysqli_query($con, "SELECT id FROM user WHERE id='$id'");

    $num = mysqli_num_rows($query);

    if($num > 0) {
        $result = new StdClass();
        $result->result = "EXISTS";
    }
    else {
        $result = new StdClass();
        $result->result = "AVAILABLE";
    }

    header('Content-Type: application/json');
    
    echo json_encode($result, JSON_PRETTY_PRINT|JSON_UNESCAPED_UNICODE);

    mysqli_close($con);

?>