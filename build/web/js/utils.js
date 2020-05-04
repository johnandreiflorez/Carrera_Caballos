/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function numberValidation() {
    keycode = event.keyCode ? event.keyCode : event.which;
    if ((event.shiftKey == false && (keycode == 46 || keycode == 8 ||
            keycode == 37 ||keycode == 39 || (keycode >= 48 && keycode <= 57)))) {
        event.preventDefault();
    }
}

function onlyNumber() {
    keycode = event.keyCode ? event.keyCode : event.which;
    if (!(event.shiftKey == false && (keycode == 46 || keycode == 8 ||
            keycode == 37 ||keycode == 39 || (keycode >= 48 && keycode <= 57)))) {
        event.preventDefault();
    }
}



