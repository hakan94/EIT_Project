<?php
    $response = array();
    if(isset($_POST['classID'])){
        $classID = $_POST['classID'];

        require_once _DIR_ . '/DatabaseConnection.php';
        $db = new DB_CONNECT();
        $result = mysql_query("DELETE FROM Classes WHERE classID = '$classID'");

        if (mysql_affected_rows()>0){
            $response["success"] = 1;
            $response["message"] = "Class successfully deleted!";
            echo json_encode($response);
        }else{
            $response["success"] = 0;
            $response["message"] = "Class could nor deleted!";
            echo json_encode($response);
        }
    }else{
        $response["success"] = 0;
        $response["message"] = "Required field(s) missing!";
        echo json_encode($response);
    }
?>