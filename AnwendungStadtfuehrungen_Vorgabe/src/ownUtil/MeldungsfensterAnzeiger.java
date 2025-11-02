// === ownUtil/MeldungsfensterAnzeiger.java ===
package ownUtil;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MeldungsfensterAnzeiger {
    private final AlertType typ; private final String titel; private final String text;
    public MeldungsfensterAnzeiger(AlertType typ, String titel, String text){
        this.typ = typ; this.titel = titel; this.text = (text==null||text.isEmpty()) ? "Die Meldung ist nicht vorhanden." : text;
    }
    public void zeigeMeldungsfensterAn(){
        Alert a = new Alert(typ); a.setTitle(titel); a.setContentText(text); a.showAndWait();
    }
}
