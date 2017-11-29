<?php
include_once 'DbConnect.php';

$db = new DbConnect();
$conexion = $conn;


function operar() {

	global $conexion;

	if (isset($_POST['dato']) && isset($_POST['id_campo']) && isset($_POST['gps']) && isset($_POST['date']) && isset($_POST['operacion'])) {
		$dato = $_POST['dato'];
		$date = $_POST['date'];
		$gps = $_POST['gps'];
		$id_cam =$_POST['id_campo'];
		$oper = $_POST['operacion'];

		$numero = 0;


		$sql_ = $conexion->query("SELECT * FROM terremoto WHERE datos = '".$dato."';");

		$numero = $sql_->num_rows;


		if ($oper == "c") {

			$sql_ = $conexion->query("INSERT INTO terremoto (datos, gps, date) VALUES ('".$dato."', '".$gps."', '".$date."');");

			if ($sql_) {
				echo "Localizacion Exitosa";
			} else {
				echo "Localizacion Erronea";
			}

		} else if ($oper == "r") {

			if ($numero > 0 ) {
				$resul = mysqli_fetch_array($sql_);

				echo "Nombre campo " .  $resul['campo1'];

			} else {
				echo "No existen valores para este ID";
			}

		} else if ($oper == "u") {

			if ($numero > 0 ) {
				$sql_ = $conexion->query("UPDATE terremoto t SET gps = '".$gps."', date = '".$date."' WHERE datos = '".$dato."';");
				if ($sql_) {
					echo "Localizacion Actualizada";
				} else {
					echo "Localizacion Erronea";
				}
			} else {
				echo "El ID ingresado no existe.$dato";
			}

		} else if ($oper == "d") {

			if ($numero > 0) {

				$sql_ = $conexion->query("DELETE FROM terremoto WHERE id = $id_cam;");

				if ($sql_) {
					echo "La opreacion se realizó correctamente";
				} else {
					echo "La opreacion NO se realizó";
				}

			} else {
				echo "El código ingresado no existe";
			}
		} else {

			echo "No se puede realizar la opreacion indicada";

		}

	} else {
		echo "Ingrese datos";
	}


}

operar();
?>
