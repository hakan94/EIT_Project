<?php
    $response = array();
    if(isset($_POST['classID']) && isset($_POST['className'])){
        $classID = $_POST['classID'];
        $className = $_POST['className'];

        require_once _DIR_ . '/DatabaseConnection.php';
        $db = new DB_CONNECT();
        $result = mysql_query("UPDATE Classes SET className = '$classname' WHERE classID = $classID");

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