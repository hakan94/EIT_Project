<?php
    $response = array();
    require_once _DIR_ . '/DatabaseConnection.php';
    $db = new DB_CONNECT();

    $result = mysql_query("SELECT * FROM Classes") or die (mysql_error());

    if (mysql_num_rows($result) > 0){

        $response["class"] = array();

        while ($row = mysql_fetch_array($result)){

            $class = array();
            $class["classID"] = $row["classID"];
            $class["className"] = $row["className"];
            array_push($response["class"], $class);
        }

        $response["success"] = 1;
        echo json_encode($response);

    }else{

        $response["success"] = 0;
        $response["message"] = "No Class(es) found!";
        echo json_encode($response);
    }

?>