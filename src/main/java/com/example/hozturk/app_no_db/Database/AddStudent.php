<?php
    $response = array();
    if (isset($_POST['firstName']) && isset($_POST['lastName']) && isset($_POST['Email'])){
        $className = $_POST['className'];

        require_once _DIR_ . '/DatabaseConnection.php';
        $db = new DB_CONNECT();

        $result = mysql_query("INSERT INTO Classes(className) VALUES ('$className') ");
        if($result){
            $response["success"] = 1;
            $response["message"] = "Class successfully added.";
            echo json_encode($response);
        }else{
            $response["success"] = 0;
            $response["message"] = "Error! Class could not added.";
            echo json_encode($response);
        }
    }else{
        $response["success"] = 0;
        $response["message"] = "Required field is missing";
        echo json_encode($response);
    }
?>