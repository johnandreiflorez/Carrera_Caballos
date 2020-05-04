package Servlets;

import Control.Juego;
import Modelo.Apostador;
import Modelo.Apuesta;
import Modelo.Caballo;
import com.sun.corba.se.spi.presentation.rmi.StubAdapter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andrei Florez V
 */
@WebServlet(name = "RegistroServlet", urlPatterns = {"/RegistroServlet"})
public class RegistroServlet extends HttpServlet {
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    int pos = 0;
    int pos1 = 0;
    Juego objJuego = new Juego();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String mensaje="";
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>" +
                    "<meta http-equiv=Content-Type charset=UTF-8>" +
                    "<title>Hipodromo</title>" +
                    "<link rel='stylesheet' href='css/estilos.css'>" +
                    "<script src='js/utils.js' type='text/javascript'></script>" +
                "</head>");
            out.println("<body>");
            
            /* REGISTRO USUARIO SALDO INICIAL */
            if(pos < 4) {
                Apostador objApostador = new Apostador();
                objApostador.setNombre(request.getParameter("txtNombre"));
                objApostador.setSaldo(10000);
                objApostador.asignarEstado();
                objJuego.addApostadores(objApostador,pos);
                pos++;
                /* FIN DE REGISTRO */
            } else {
                /* TOMA DE APUESTA */
                for(int i = 0 ; i < objJuego.sizeApostadores(); i++){
                    if(objJuego.getJugadores()[i].getSaldo() > 0){
                        Caballo objCaballo = agregarApuesta(Integer.parseInt(request.getParameter("txtId"+i)));
                        Apuesta objApuesta= new Apuesta();
                        objApuesta.setApuesta(Double.parseDouble(request.getParameter("txtApuesta"+i)));
                        objApuesta.setCaballo(objCaballo);
                        objJuego.getJugadores()[i].addApuestas(objApuesta,pos1);
                    }
                }
                /* FIN TOMA DE APUESTA */
                /*GENERA EL GANADOR*/
                objJuego.correr();
                /* CALCULA LA GANACIA O PERDIDA Y LA ASIGNA AL SALDO */
                objJuego.gananciaPerdida(pos1);
                //POS1 == RONDA 
                pos1++;
            }
            out.println(
                "<header class='header'>" +
                    "<div class='logo'>" +
                        "<a href='index.jsp'>" +
                            "<img src='./Imagenes/itm.png' alt='Insitución Tecnológica Metropolitana'>" + 
                        "</a>" +
                    "</div>" +
                    "<div class='title'>" +
                        "<h2><span class='bold'>Primera Practica</span> JSP POO CSS</h2>" +
                    "</div>" +
                "</header>" +
                "<div class='wrap'>" +
                    "<div class='content-form'>" +
                        "<form action='RegistroServlet' method='pop'>" +
                            "<fieldset>" +
                                "<legend>Informacion Persona</legend>" +
                                "<div class='form-group md-4'>" +
                                    "<label>Nombre</label>" +
                                    "<input type='text' name='txtNombre' id='txtNombre' placeholder='Ingrese su nombre'" +
                                        "onkeypress='numberValidation()'>" +
                                "</div>" +
                                "<div class='form-group md-4'>" +
                                    "<label>Saldo inicial</label>" +
                                    "<input type='text' name='txtSaldo' value='$10000' readonly>" +
                                "</div>");

                                /* UNA VEZ REGISTRADOS LO 4 JUGADORES DESHABILITA EL BOTON */
                                String btnDisabled = "";
                                if(pos >= 4) btnDisabled = "disabled";
                                /*  FIN DEL CONTROL DEL BOTON REGISTRAR     */

                                out.println("<div class='btn-group'>" +
                                    "<input type='submit' name='btnRegistrar' value='Registrar' " + btnDisabled + ">" +
                                "</div>" +
                            "</fieldset>" +
                        "</form>" +
                    "</div>" +
                    "<div class='content-players'>");
                        /*      TABLA DE CABALLOS*/
                        String nombreCaballos = "Zeus; Bucéfalos; Millonario; Relámpago";
                        String [] caballos = nombreCaballos.split("; ");
                        out.println("<h2>Listado de Caballos</h2>" +
                        "<ul class='list-items'>");
                            for(int i = 0; i < caballos.length; i++) {
                                out.println("<li><div class='item'>" + caballos[i] + "</div></li>");
                            }
                        out.println("</ul>" +
                        "<h2>Jugadores Registrados: " + objJuego.sizeApostadores() + " </h2>" +
                        "<form action='RegistroServlet' method='get'>" +
                            "<div class='flex-content'>" + 
                                "<table class='players-table' cellspacing='0' cellpadding='0' border='0'>" +
                                    "<thead><tr>" +
                                        "<th>Nombre</th>" +
                                        "<th>Saldo</th>" +
                                    "</tr></thead>" +
                                    "<tbody>");
                                        /*      TABLA DE JUGADORES CON SALDO ACTUAL     */
                                        for(int i = 0 ; i < objJuego.sizeApostadores(); i++){
                                            out.println("<tr>" +
                                                "<td>" + objJuego.getJugadores()[i].getNombre() + "</td>");
                                                if (objJuego.getJugadores()[i].getSaldo() <= 0) {
                                                    out.println("<td>ELIMINADO</td>");
                                                } else {
                                                    out.println("<td>$" + objJuego.getJugadores()[i].getSaldo() + "</td>");
                                                }
                                            out.println("</tr>");
                                        }
                                        out.println(
                                        "</tbody>" +
                                "</table>");
                                /*      TABLA DE APUESTAS     */
                                if(pos >= 4) {
                                    out.println(
                                        "<table class='players-table' cellspacing='0' cellpadding='0' border='0'>" +
                                            "<thead><tr>" +
                                                "<th>Número del Caballo</th>" +
                                                "<th>Valor Apuesta</th>" +
                                            "</tr></thead>" +
                                            "<tbody>");
                                                for(int i =0 ; i<objJuego.sizeApostadores();i++) {
                                                    out.println("<tr><td>");
                                                    if(objJuego.getJugadores()[i].getSaldo()<=0){
                                                        out.println("<input type='text' name='txtId" + i + "' id='txtId' disabled>");
                                                    }else{
                                                        out.println("<input type='text' name='txtId" + i + "' id='txtId' placeholder='Número del Caballo' " +
                                                            "onkeypress='onlyNumber()'>");
                                                    }
                                                    out.println("</td><td>");
                                                    if(objJuego.getJugadores()[i].getSaldo()<=0){
                                                        out.println("<input type='text' name='txtApuesta" + i + "' id='txtApuesta' value='0' disabled>");
                                                    }else{
                                                        out.println("<input type='text' name='txtApuesta" + i + "' id='txtApuesta' placeholder='$0'" +
                                                            " onkeypress='onlyNumber()'>");
                                                    }
                                                    out.println("</td></tr>");
                                                }
                                            out.println("</tbody>" +
                                        "</table>" +
                            "</div>" +
                            "<div class='btn-group'>");
                                
                                for(int i = 0; i < objJuego.sizeApostadores(); i++) {
                                    if(objJuego.getJugadores()[i].getSaldo() <= 0) {
                                        mensaje = "<a href='index.jsp' class='btn-send'>Reiniciar</a>";
                                        i = 5;
                                    } else{
                                        mensaje = "<input type='submit' name='btnApostar' value='Apostar' />";
                                        i = 5;
                                    }
                                    if(mensaje.equals("<a href='index.jsp' class='btn'>Reiniciar</a>")){
                                        objJuego = new Juego();
                                        pos = 0;
                                        pos1 = 0;
                                    }
                                }
                                out.println(mensaje +
                            "</div>" +
                        "</form>" + 
                        "<h2>Resultados</h2>" +
                        "<table class='players-table' cellspacing='0' cellpadding='0' border='0'>" +
                            "<thead><tr>" +
                                "<th>Posición</td>" +
                                "<th>Nombre Caballo</th>" +
                                "<th>Nombre Jugador</th>" +
                                "<th>Valor Apostado</th>" +
                            "</tr></thead>" +
                            "<tbody>");
                            if(pos1 != 0 ){
                                for(int j = 0; j < objJuego.getPosiciones().length; j++) {
                                    for(int i = 0 ; i < objJuego.sizeApostadores(); i++) {
                                        if(objJuego.getPosiciones()[j] == objJuego.getJugadores()[i].getApuestas()[pos1 - 1].getCaballo().getId()) {
                                            out.println("<tr>" +
                                                "<td>" + (j + 1) + "°</td>" +
                                                "<td>" + objJuego.getJugadores()[i].getApuestas()[pos1 - 1].getCaballo().getNombre() + "</td>" +
                                                "<td>" + objJuego.getJugadores()[i].getNombre() + "</td>" +
                                                "<td>$" + objJuego.getJugadores()[i].getApuestas()[pos1 - 1].getApuesta() + "</td>" +
                                            "</tr>");
                                        }
                                    }
                                }
                                if(mensaje.equals("<a href='index.jsp' class='btn-send'>Reiniciar</a>")){
                                    objJuego = new Juego();
                                    pos = 0;
                                    pos1 = 0;
                                }
                            }
                            out.println("</tbody>" +
                        "</table>");
                                }
                    out.println("</div>" +
                "</div>" +
            "</body>" +
            "</html>");
        }
    }
   private Caballo agregarApuesta(int id){
       Caballo caballo = new Caballo();
       caballo.setId(id);
       caballo.nombreCaballo(id);
       return caballo;
   }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
