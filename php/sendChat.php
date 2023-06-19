<?php
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    include_once('retrofit_config.php');

    $senderId = $_POST['id'];
    $content = $_POST['content'];
    $roomId = $_POST['roomID'];
    $createdAt = $_POST['createdAt'];

    $roomId = (int) $roomId;

    $query = "INSERT INTO chat (chatID, senderID, roomID, content, createdAT) VALUES (null, '$senderId', $roomId, '$content', '$createdAt')";
    mysqli_query($con, $query);
}
?>