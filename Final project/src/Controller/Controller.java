/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.imageio.ImageIO;


/**
 * Controller class for the GameOflife package where the fxml objects gets their function or method to perform.
 */
public class Controller implements Initializable {

    @FXML private Canvas mycanvas;
    @FXML private Button start;
    @FXML private Button nextgen;
    @FXML private Button randomgenerator;
    @FXML private Button resetbutton;
    @FXML private Button gameshape;
    @FXML private Button Screenshot;
    @FXML private ChoiceBox choicebox;
    @FXML private Button SelectGrid;
        ObservableList<String> options = FXCollections.observableArrayList("Select Pattern","Exploder","Glider","Tumbler","10 Cell Row");
    @FXML GraphicsContext gc;
    @FXML TextField heightInput;
    @FXML TextField widthInput;
    @FXML Slider Speedslider;
    @FXML Slider Gridslider;
    @FXML ColorPicker backgroundcolor;
    @FXML ColorPicker cellcolor;
    @FXML AnchorPane anchorpane;
    @FXML Label generationlabel;
    @FXML CheckBox musicmute;
    @FXML CheckBox dynamicboard;

    private String url=" ";
    private boolean shape;
    private boolean play=false;
    private boolean grid=true;
    private boolean dynamicboardenable=false;
    private double time=15;
    private double startspeed=1;
    private long generation=0;
    private boolean music=true;
    private int addXdirection=0;
    private int addYdirection=0;
    MediaPlayer mediaplayer;

    Cell staticboardtype= new Cell();
    DynamicCell dynamicboardtype= new DynamicCell();

    BoardType BoardType;

/**
 * Method for starting the animation of the game.
 * Also starts the generation counter.
 *
 */
    AnimationTimer timer = new AnimationTimer(){
        @Override
        public void handle(long now) {
            if(play){

                time -=1;
                if(time<=startspeed){

               BoardType.rungame();
               if(dynamicboardenable==true){
                   DynamicCell.dynamicboard();
               }

                draw();
                time=15;

                generation++;
                String s=Long.toString(generation);
                generationlabel.setText(s);
                }
            }
        }

    };

    /**
     * Method for Setting the boardtype of the game (Static board or dynamic board).
     *
     *
     */
    public void setboardtype(){
     if(dynamicboardenable==true){
         BoardType = new DynamicCell();
     }else{
         BoardType = new Cell();
     }


    }

    /**
     * Method for activating the dynamic board
     * @param event Waits for an ActionEvent to be performed before running this method (Dynamic board CheckBox checked)
     */
    @FXML
    private void toggleboard(ActionEvent event){
    dynamicboardenable=!dynamicboardenable;
    BoardType.resetGrid();

    setboardtype();
    draw();
    }

    /**
     * Method for toggling the boolean value of shape from true to false and false to true.
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Shape button Clicked)
     */
    @FXML
    private void changeshape(ActionEvent event){
    shape=!shape;
            if(shape){gameshape.setText("Circle");}else{gameshape.setText("Square");}
            draw();

    }

    /**
     * Method to start or stop the AnimationTimer method (Starting the game)
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Start button Clicked)
     */
    @FXML
    private void startfunc(ActionEvent event){
     play=!play;
     if(play) {
         timer.start();
         start.setText("Stop");

     }else{
         timer.stop();
         start.setText("Start");}
    }

    /**
     * Method for moving the game 1 generation forward
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Next button Clicked)
     */
    @FXML
    private void nextgenfunction(ActionEvent event){



               BoardType.rungame();


                draw();

                generation++;
                String s=Long.toString(generation);
                generationlabel.setText(s);
    }

    /**
     * Method for Reseting the Canvas. This method removes all active cells in the canvas and also empties the imported rle file.
     * This method also resets the generation count.
     *
     * @param e Waits for an ActionEvent to be performed before running this method (Reset button Clicked)
     */
    @FXML
    private void resetfunc(ActionEvent e){
            Importrle.emptystring();
            BoardType.resetGrid();
            draw();
            resetgenerationcount();
    }

    /**
     * Method for changing the value of the startspeed, which in turn decreases or increases the speed at which the AnimationTimer performs its function.
     *
     * @param e Waits for an MouseEvent to be performed before running this method (Mouse clicked and/or Mouse drag on the Speed slider).
     */
   @FXML
    private void speedchange(MouseEvent e){
        startspeed=Speedslider.getValue();
    }

    /**
     * Method for increasing or decreasing the size of the grid that is drawn in the canvas. Changes the value of griddimension.
     *
     * @param e Waits for an MouseEvent to be performed before running this method (Mouse clicked and/or Mouse drag on the Width and Height slider).
     */
   @FXML
   private void changegriddimension(MouseEvent e){
       Cell.changegriddimension((int) Gridslider.getValue());
       draw();
   }

   /**
    * Method for toggling the boolean value of grid from true to false and false to true. Method responsible for turning the grid on or off.
    *
    * @param event Waits for an ActionEvent to be performed before running this method (Grid button Clicked)
    */
   @FXML
   private void togglegridOnorOff(ActionEvent event){
        grid=!grid;
        if(grid){SelectGrid.setText("Grid On");}else{SelectGrid.setText("Grid Off");}
        draw();
   }

   /**
    * Method for giving the ChoiceBox object its function. This method has a listener that waits for an action(a item chosen) before drawing the pattern connected to chosen item.
    * This method also resets the generation count after each new chosen item.
    */
    private void choiceboxfunction(){
         choicebox.setValue("Select Pattern");
         choicebox.setItems(options);
          choicebox.getSelectionModel().selectedItemProperty().addListener((v, oldValue,newValue)-> {

        if(choicebox.getValue().equals("Exploder")){
             BoardType.resetGrid();
             BoardType.loadnewmove(CellPattern.drawExploder(),addYdirection,addXdirection,dynamicboardenable);

        }
        if(choicebox.getValue().equals("Glider")){
             BoardType.resetGrid();
             BoardType.loadnewmove(CellPattern.drawGlider(),addYdirection,addXdirection,dynamicboardenable);
        }
        if(choicebox.getValue().equals("Tumbler")){
             BoardType.resetGrid();
             BoardType.loadnewmove(CellPattern.drawTumbler(),addYdirection,addXdirection,dynamicboardenable);


        }
        if(choicebox.getValue().equals("10 Cell Row")){
             BoardType.resetGrid();
             BoardType.loadnewmove(CellPattern.draw10cellrow(),addYdirection,addXdirection,dynamicboardenable);

        }
             resetgenerationcount();
             draw();
     });
    }

    /**
     * Method for drawing a random cells in the canvas.
     * This method also resets the generation count.
     *
     * @param e Waits for an ActionEvent to be performed before running this method (Random button Clicked)
     */
    @FXML
    private void randomfunc(ActionEvent e){


            BoardType.randomcell();

             draw();
             resetgenerationcount();
    }

    /**
     * Method for activating or deactivating a cell on the coordinates you clicked on, in the canvas
     *
     * @param event Waits for an MouseEvent to be performed before running this method (Mouse clicked in the canvas).
     */
    @FXML
    private void clickactivatecell(MouseEvent event){


          BoardType.clickactivatecell((int)event.getY(), (int)event.getX(), mycanvas);
        draw();
    }

    /**
     * Method for activating or deactivating a cell on the coordinates you clicked and drag on, in the canvas
     *
     * @param event Waits for an MouseEvent to be performed before running this method (Mouse clicked and Mouse drag in the canvas).
     */
    @FXML
    private void dragactivatecell(MouseEvent event){


        BoardType.dragactivatecell((int)event.getY(), (int)event.getX(), mycanvas);

        draw();
    }

    /**
     * Method for changing the color of the active cell in the canvas.
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Cell Color ColorPicker Clicked)
     */
    @FXML
    private void changecellcolor(ActionEvent event){
        Color cellc = cellcolor.getValue();
        gc.setFill(cellc);
        draw();

    }

    /**
     * Method for changing the background color of the canvas.
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Background Color ColorPicker Clicked)
     */
    @FXML
    private void changebackgroundcolor(ActionEvent event){
         Color backcolor=backgroundcolor.getValue();
         gc.setFill(backcolor);
         gc.fillRect(0,0,mycanvas.getWidth(),mycanvas.getHeight());
         draw();

    }

    /**
     * Method for importing a pattern in the canvas from a RLE file.
     * This method also resets the generation count and removes all the active cell from the canvas before importing the new pattern from the RLE file.
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Import from file button Clicked)
     */
    @FXML
    private void Importpattern(ActionEvent event){
        Importrle.emptystring();
        BoardType.resetGrid();
        Importrle.filechooser();
        BoardType.loadnewmove(Importrle.decoder(),addYdirection,addXdirection,dynamicboardenable);
        draw();
        resetgenerationcount();


    }

     /**
     * Method for importing a pattern in the canvas taken form a RLE in a URL.
     * This method also resets the generation count and removes all the active cell from the canvas before importing the new pattern from the RLE file.
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Import from URL button Clicked)
     */
    @FXML
    private void importpatternURL(ActionEvent event){
        importwindow();
    }

    /**
     * Method for changing the coordinates of the imported pattern.
     *
     * @param e Waits for an KeyEvent to be performed before running this method (W/A/S/D or UP/DOWN/LEFT/RIGHT key pressed).
     */
    @FXML
    private void ChangePositionOfPattern(KeyEvent e){
        if(e.getCode().equals(KeyCode.LEFT)||e.getCode().equals(KeyCode.A)){
            addXdirection-=1;
            BoardType.resetGrid();
            BoardType.loadnewmove(Importrle.decoder(),addYdirection,addXdirection,dynamicboardenable);
            draw();
        }
        if(e.getCode().equals(KeyCode.RIGHT)||e.getCode().equals(KeyCode.D)){
            addXdirection+=1;
            BoardType.resetGrid();
            BoardType.loadnewmove(Importrle.decoder(),addYdirection,addXdirection,dynamicboardenable);
            draw();
        }
        if(e.getCode().equals(KeyCode.UP)||e.getCode().equals(KeyCode.W)){
            addYdirection-=1;
            BoardType.resetGrid();
            BoardType.loadnewmove(Importrle.decoder(),addYdirection,addXdirection,dynamicboardenable);
            draw();
        }
        if(e.getCode().equals(KeyCode.DOWN)||e.getCode().equals(KeyCode.S)){
            addYdirection+=1;
            BoardType.resetGrid();
            BoardType.loadnewmove(Importrle.decoder(),addYdirection,addXdirection,dynamicboardenable);
            draw();
        }

//        System.out.println("X:"+addXdirection+", Y:"+addYdirection);


    }

    /**
     * Method for taking a screenshot of the canvas
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Screenshot button pressed).
     */
    @FXML
    private void screenshotfunction(ActionEvent event){
        WritableImage image = mycanvas.snapshot(new SnapshotParameters(), null);

        FileChooser screenshot = new FileChooser();
        screenshot.setTitle("Save Screenshot");
        screenshot.getExtensionFilters().addAll(
         new FileChooser.ExtensionFilter("PNG Files", "*.png"));
        File file = screenshot.showSaveDialog(new Stage());
        if(file!=null){
            try {
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
             } catch (IOException e) {

         }
        }

    }

    /**
     * Method for muting the background music.
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Mute CheckBox activated)
     */
    @FXML
    private void Mutemusic(ActionEvent event){
       music=!music;
           if(music==false){

             mediaplayer.setVolume(0);}else{

            mediaplayer.setVolume(0.4);
           }
    }

     /**
     * Method for generating a pop-up window for importing pattern via URL.
     *
     * The ActionEvent set on the import button takes the url written in the textfield and converts it into a string.
     *
     */
    private void importwindow(){
        Stage window2 = new Stage();
        window2.initModality(Modality.APPLICATION_MODAL);
        window2.setTitle("Import from URL");
        window2.setMinWidth(500);
        window2.setMinHeight(250);
        Label label2 =new Label();
        TextField urltext=new TextField();
        label2.setText("URL : ");
        Button importbutton=new Button("Import");
        Button closebutton=new Button("Close");
        closebutton.setOnAction(e->window2.close());

/* ActionEvent on the Import button. When activated it will take the text written in the textfield and forwards it to a URL decoder
   that decodes the Contents of the URL into a pattern.
 */
        importbutton.setOnAction(e-> {

            url =urltext.getText();
            ImportRlefromURLandDraw();


            window2.close();

//            System.out.println(url);
        });

        VBox layout = new VBox(10);
        HBox buttonlayout=new HBox(5);
        HBox textlayout = new HBox(5);
        textlayout.getChildren().addAll(label2,urltext);
        buttonlayout.getChildren().addAll(importbutton,closebutton);
        layout.getChildren().addAll(textlayout,buttonlayout);
        buttonlayout.setAlignment(Pos.CENTER);
        textlayout.setAlignment(Pos.CENTER);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout);
        window2.setScene(scene);
        window2.showAndWait();
    }

    /**
     * Method for taking the content of the URL page and converting it into a string value.
     * Thereafter the converted converted content of the URL address will be decoded and then drawn in the canvas.
     * This will also reset the generation count after importing.
     *
     * Lastly this method will also reset the string value of url
     */
    private void ImportRlefromURLandDraw(){
            Importrle.ImportfromURL(url);
            BoardType.loadnewmove(Importrle.decoder(),addYdirection,addXdirection,dynamicboardenable);
            draw();
            resetgenerationcount();
            url=" ";
    }

    /**
     * Method for generating a background color for the canvas.
     */
    private void background(){
         gc=mycanvas.getGraphicsContext2D();
         Color backcolor=backgroundcolor.getValue();
         gc.setFill(backcolor);
         gc.fillRect(0,0,mycanvas.getWidth(),mycanvas.getHeight());
    }

    /**
     * Method for opening a pop-up window that displays the rules of the game.
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Under Help menu, rule button Clicked)
     */
    public void Rulesfunction(ActionEvent event){
        Popupwindow.display("Rules", "Rules:\n"+
                                          "1. Any live cell with fewer than two live neighbours dies, as if caused by underpopulation.\n" +
                                          "2.Any live cell with two or three live neighbours lives on to the next generation.\n" +
                                          "3.Any live cell with more than three live neighbours dies, as if by overpopulation.\n" +
                                          "4.Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.");
    }

    /**
     * Method for properly closing the game.
     *
     * @param event Waits for an ActionEvent to be performed before running this method (Under File menu, close button Clicked)
     */
    public void CloseWindow(ActionEvent event){
      Platform.exit();
      System.exit(0);
    }

    /**
     * Method for drawing the background color, Active cell and then the gird style in the canvas.
     */
    private void draw(){
         background();

         BoardType.drawCell(gc, mycanvas, cellcolor,shape);

         if(grid){Cell.creategrid(gc, mycanvas,shape);}
    }

    /**
     * Method for reset the generation value to 0;
     */
    private void resetgenerationcount(){
            generation=0;
            String s=Long.toString(generation);
            generationlabel.setText(s);
    }

    /**
     * Method for playing background music
     */
    private void playmusic(){
                //code for the background music
                //Copyrightfree music: https://soundcloud.com/freebmusic/beaster-awakening-free-background-music
                URL mp3 = getClass().getResource("GoLbackgroundMusic.mp3");

                Media musicFile =new Media(mp3.toString());

                mediaplayer = new MediaPlayer(musicFile);

                //function for setting the mp3 on a loop
                mediaplayer.setOnEndOfMedia(() -> {
                    mediaplayer.seek(Duration.ZERO);
                });
                mediaplayer.play();
                mediaplayer.setVolume(0.4);

    }

    /**
     * This method initializes the ChoiceBox items and listeners, sets a default color for the cell and background, draws the background, all the active cell and the grid
     * when the game application is started
     *
     * @param url The location used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         choiceboxfunction();
         backgroundcolor.setValue(Color.YELLOW);
         cellcolor.setValue(Color.RED);
         DynamicCell.setdynamicboard(mycanvas.getHeight(), mycanvas.getWidth());
         setboardtype();
         draw();
         playmusic();


        }





}
