package Core;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.QuadCurve;
import javafx.scene.text.Text;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

/** The Controller class is connected with the faceEmotions.fxml file,
**  it handles slider events and face changes. Also it implements the method model.start()
**  and also sends parameters to the ModelGenerator class. There is a listener toogle, in the init method,
**  Upon certain conditions in the slider, the face changes accordingly.
 **/

public class Controller implements Initializable {
    private static Model model;
    private static Model model1;
    private static Model model2;
    private static Model model3;
    private static Model model4;
    private final int PORT = 1594;
    Text val;
    String one = "100";
    @FXML
    Label portNumber;
    @FXML
    JFXToggleButton toggle;

    @FXML
    JFXSlider agreement;
    @FXML
    JFXSlider disagreement;
    @FXML
    JFXSlider frustate;
    @FXML
    JFXSlider concentrate;
    @FXML
    JFXSlider thinking;
    @FXML
    JFXSlider unsure;

    @FXML
    QuadCurve smile;

    @FXML
    Circle rightEye;

    @FXML
    Circle leftEye;

    @FXML
    Line nose;

    @FXML
    Ellipse face;

    @FXML
    ProgressIndicator aggrementIndicator;

    @FXML
    ProgressIndicator disagreementIndicator;

    @FXML
    ProgressIndicator frustateIndicator;

    @FXML
    ProgressIndicator concentrateIndicator;

    @FXML
    ProgressIndicator thinkingIndicator;

    @FXML
    ProgressIndicator unsureIndicator;

    @FXML
    Label serverRunning;


    @FXML void sliderAgreementDragged(){
        System.out.println((double)(agreement.getValue()));

        aggrementIndicator.progressProperty().setValue(agreement.getValue());

        if(agreement.getValue() ==  1) {
            val = (Text) aggrementIndicator.lookup(".percentage");
            val.setText(one);
            aggrementIndicator.progressProperty().setValue(agreement.getValue());

        }


            if(agreement.getValue() >= 0.7) {
                smile.setControlY(+60.0);
            }
            else if (agreement.getValue() >= 0.3 && agreement.getValue() < 0.7){
                smile.setControlY(+50.0);
            }
            else{
                smile.setControlY(+30.0);
            }
            DataGenerator dataGen = new DataGenerator();
            dataGen.updateParam("agreement", (double)agreement.getValue() * 10 );
        }

    @FXML void sliderDisagreementDragged(){
        System.out.println(disagreement.getValue());
        if(disagreement.getValue() < 1) {
            disagreementIndicator.progressProperty().setValue(disagreement.getValue());
        }
        else{
            Text text =  (Text) disagreementIndicator.lookup(".percentage");
            disagreementIndicator.progressProperty().setValue(disagreement.getValue());
            if(text!=null && text.getText().equals("Done")){
                text.setText(one);
            }
        }
        if(disagreement.getValue() >= 0.7) {
            smile.setControlY(-20.0);
        }
        else if (disagreement.getValue() >= 0.3 && disagreement.getValue() < 0.7){
            smile.setControlY(0.0);
        }
        else{
            smile.setControlY(+30.0);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("disagreement", (double)disagreement.getValue() * 10 );
    }

    @FXML void sliderFrustateDragged(){
        System.out.println(frustate.getValue());
        if(frustate.getValue() < 1) {
            frustateIndicator.progressProperty().setValue(frustate.getValue());
        }
        else{
            Text text =  (Text) frustateIndicator.lookup(".percentage");
            frustateIndicator.progressProperty().setValue(frustate.getValue());
            if(text!=null && text.getText().equals("Done")){
                text.setText(one);
            }
        }
        if(frustate.getValue() >= 0.7) {
            rightEye.setFill(Color.WHITE);
            leftEye.setFill(Color.WHITE);
            smile.setFill(Color.WHITE);
            smile.setControlY(-20.0);
            face.setFill(Color.valueOf("#ff21ca"));

        }
        else if (frustate.getValue() >= 0.3 && frustate.getValue() < 0.7){
            rightEye.setFill(Color.WHITE);
            leftEye.setFill(Color.WHITE);
            smile.setFill(Color.WHITE);
            smile.setControlY(0.0);
            face.setFill(Color.valueOf("#9f21ff"));
        }
        else{
            rightEye.setFill(Color.DODGERBLUE);
            leftEye.setFill(Color.DODGERBLUE);
            smile.setFill(Color.DODGERBLUE);
            smile.setControlY(+30.0);
            face.setFill(Color.DODGERBLUE);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("frustrate", (double)frustate.getValue() * 10);
    }
    @FXML void sliderConcentrateDragged(){
        System.out.println(concentrate.getValue());
        if(concentrate.getValue() < 1) {
            concentrateIndicator.progressProperty().setValue(concentrate.getValue());
        }
        else{
            Text text =  (Text) concentrateIndicator.lookup(".percentage");
            concentrateIndicator.progressProperty().setValue(concentrate.getValue());
            if(text!=null && text.getText().equals("Done")){
                text.setText(one);
            }
        }
        if(concentrate.getValue() >= 0.7) {
            leftEye.setRadius(25.0);
            rightEye.setRadius(25.0);
        }
        else if (concentrate.getValue() >= 0.3 && concentrate.getValue() < 0.7) {
            leftEye.setRadius(20.0);
            rightEye.setRadius(20.0);
        }
        else{
            leftEye.setRadius(16.0);
            rightEye.setRadius(16.0);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("concentrate", (double)concentrate.getValue() * 10 );
    }
    @FXML void sliderThinkingDragged(){
        System.out.println(thinking.getValue());
        if(thinking.getValue() < 1) {
            thinkingIndicator.progressProperty().setValue(thinking.getValue());
        }
        else{
            Text text =  (Text) thinkingIndicator.lookup(".percentage");
            thinkingIndicator.progressProperty().setValue(thinking.getValue());
            if(text!=null && text.getText().equals("Done")){
                text.setText(one);
            }
        }
        if(thinking.getValue() >= 0.7) {
            nose.setEndY(0.0);
        }
        else if (thinking.getValue() >= 0.3 && thinking.getValue() < 0.7) {
            nose.setEndY(-2.0);
        }

        else{
            nose.setEndY(-4.0);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("thinking", (double)thinking.getValue() * 10 );
    }
    @FXML void sliderUnsureDragged(){
        System.out.println(unsure.getValue());
        if(unsure.getValue() < 1) {
            unsureIndicator.progressProperty().setValue(unsure.getValue());
        }
        else{
            Text text =  (Text) unsureIndicator.lookup(".percentage");
            unsureIndicator.progressProperty().setValue(unsure.getValue());
            if(text!=null && text.getText().equals("Done")){
                text.setText(one);
            }
        }
        if(unsure.getValue() >= 0.7) {
            nose.setEndY(0.0);
            smile.setControlY(0.0);
        }
        else if (unsure.getValue() >= 0.3 && unsure.getValue() < 0.7) {
            nose.setEndY(-2.0);
            smile.setControlY(+10.0);
        }

        else{
            nose.setEndY(-4.0);
            smile.setControlY(+30.0);
        }
        DataGenerator dataGen = new DataGenerator();
        dataGen.updateParam("unsure", (double)unsure.getValue() * 10);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        aggrementIndicator.setStyle("-fx-progress-color: #086f18 ;");
        disagreementIndicator.setStyle("-fx-progress-color: #086f18 ;");
        frustateIndicator.setStyle("-fx-progress-color: #086f18 ;");
        concentrateIndicator.setStyle("-fx-progress-color: #086f18 ;");
        unsureIndicator.setStyle("-fx-progress-color: #086f18 ;");
        thinkingIndicator.setStyle("-fx-progress-color: #086f18 ;");

        toggle.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(toggle.isSelected() == true){
                    serverRunning.setVisible(true);
                    model = new Model(new DataGenerator(), new Publisher(PORT));
                    model1 = new Model(new DataGenerator(), new Publisher(PORT+1));
                    model2 = new Model(new DataGenerator(), new Publisher(PORT+2));
                    model3 = new Model(new DataGenerator(), new Publisher(PORT+3));
                    model4 = new Model(new DataGenerator(), new Publisher(PORT+4));
                    model.start();
                    model1.start();
                    model2.start();
                    model3.start();
                    model4.start();
                    toggle.setText("STOP");

                }
                else{
                    //JOptionPane.showMessageDialog(null,"Server Stopped");
                    model.stop();
                    model1.stop();
                    model2.stop();
                    model3.stop();
                    model4.stop();
                    serverRunning.setVisible(false);
                    toggle.setText("RUN");
                }
            }
        });

    }

}
