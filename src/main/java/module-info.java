module es.medac.josemiguel.gymfit {
    requires javafx.controls;
    requires javafx.fxml;


    opens es.medac.josemiguel.gymfit to javafx.fxml;
    exports es.medac.josemiguel.gymfit;
}