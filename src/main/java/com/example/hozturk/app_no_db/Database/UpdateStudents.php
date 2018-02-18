<?php
    $response = array();
    if(isset($_POST['studentID']) && isset($_POST['firstName'])){
        studentID = $_POST['studentID'];
        firstName = $_POST['firstname'];

        require_once _DIR_ . '/DatabaseConnection.php';
        $db = new DB_CONNECT();
        $result = mysql_query("UPDATE Students SET firstName = '$firstName' WHERE studentID = $studentID");

        if($result){
            $response["success"] = 1;
            $response["message"] = "Class successfully updated!";
            echo json_encode($response);
        }else{
            $response["success"] = 0;
            $response["message"] = "Class could not updated!";
            echo json_encode($response);
        }
    }else{
        $response["success"] = 0;
        $response["message"] = "Field is missing";
        echo json_encode($response);
    }
?>