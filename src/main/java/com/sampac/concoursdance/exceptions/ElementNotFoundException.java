package com.sampac.concoursdance.exceptions;

public class ElementNotFoundException extends Exception{
    public ElementNotFoundException() {
        super("L'element recherché n'a pas été trouvé");
    }
}
