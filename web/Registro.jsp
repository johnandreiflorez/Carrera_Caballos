<%-- 
    Document   : Resgistro
    Created on : 19/04/2020, 03:05:43 PM
    Author     : Andrei Florez V
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Hipodromo</title>
        <link rel="stylesheet" href="css/estilos.css">
        <script src="js/utils.js" type="text/javascript"></script>
    </head>
    <body>
        <header class="header">
            <div class="logo">
                <a href="index.jsp">
                    <img src="./Imagenes/itm.png" alt="Insitución Tecnológica Metropolitana">
                </a>
            </div>
            <div class="title">
                <h2><span class="bold">Primera Practica</span> JSP POO CSS</h2>
            </div>
        </header>
        <div class="wrap">
            <div class="content-form">
                <form method="get" action="RegistroServlet">
                    <fieldset>
                        <legend>Informacion participantes</legend>
                        <div class="form-group md-4">
                            <label>Nombre</label>
                            <input type="text" name="txtNombre" id="txtNombre" placeholder="Ingrese su nombre"
                                   onkeypress="numberValidation()"
                            >
                        </div>
                        <div class="form-group md-4">
                            <label>Saldo inicial</label>
                            <input type="text" name="txtSaldo" value="$10000" readonly>
                        </div>
                        <div class="btn-group">
                            <input type="submit" name="btnRegistrar" value="Registrar">
                        </div>                    
                    </fieldset>
                </form>
            </div>
        </div>
        
    </body>
</html>
