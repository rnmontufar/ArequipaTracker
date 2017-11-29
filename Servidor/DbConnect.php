<?php

$conn = "";
class DbConnect {

    // constructor
    function __construct() {

        $this->connect();
    }

    // destructor
    function __destruct() {

        $this->close();
    }

    function connect() {
        require_once __DIR__ . '/Config.php';

        global $conn;
        $conn = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME, DB_PORT) or die(mysqli_error());

        return $conn;
    }

    function close() {
        global $conn;
        mysqli_close($conn);
    }
}

?>
