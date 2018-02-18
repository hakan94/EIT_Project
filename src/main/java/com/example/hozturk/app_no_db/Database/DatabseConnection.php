<?php
    class DB_CONNECT {

        //constructor
        function _construct(){
            $this -> connect(); //connecting to DB
        }

        //destructor
        function _destructor(){
            $this -> close(); //closing DB connection
        }

        function connect(){
            require_once _DIR_ . '/DatabaseConfiguration.php'; // import DB connection variables
            $con = mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die (mysql_error());
            $db = mysql_select_db(DB_DATABASE) or die (mysql_error());

            return $con;
        }

        function close(){
            mysql_close();
        }
    }
?>