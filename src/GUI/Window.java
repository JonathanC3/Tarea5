package GUI;


import GUI.*;
import Domain.ConsumerTail;


import Domain.ProducerShadow;
import Domain.SynchronizedBuffer;
import java.awt.Label;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Window extends Application implements Runnable {

    private Thread thread;
    private Pane pane;
    private Scene scene;
    private Canvas canvas;
    private Image fondo;
    private SynchronizedBuffer sharedLocation;
    private ProducerShadow producerShadow;
    private ConsumerTail consumerTail;
    
    private Image ring, emerald;
    

    private GraphicsContext gc;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Tarea 5");
        initComponents(primaryStage);
        primaryStage.setOnCloseRequest(exit);
        primaryStage.show();
    } // start

    private void initComponents(Stage primaryStage) {
        
            try {
            this.pane = new Pane();
            this.scene = new Scene(this.pane, 1200, 500);
            this.canvas = new javafx.scene.canvas.Canvas(1200, 500);
            this.gc = this.canvas.getGraphicsContext2D();
            this.fondo = new Image(new FileInputStream("src/assets/r6tulurfk2.bmp"));
            this.ring=new Image(new FileInputStream("src/assets/ring.png"));
            this.emerald=new Image(new FileInputStream("src/assets/emeralds.png"));
            this.pane.getChildren().add(this.canvas);
            
            primaryStage.setScene(this.scene);

            sharedLocation = new SynchronizedBuffer();
                
            this.producerShadow = new ProducerShadow(sharedLocation, -60, 415);
            this.consumerTail = new ConsumerTail(sharedLocation, 1200, 415);
            
            
            this.producerShadow.start();
            this.consumerTail.start();
            
           
            

            this.thread = new Thread(this);
            this.thread.start();
            
                
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    } // initComponents

    EventHandler<WindowEvent> exit = new EventHandler<WindowEvent>() {
        @Override
        public void handle(WindowEvent event) {
            System.exit(0);
        }
    };

    @Override
    public void run() {
        long start;
        long elapsed;
        long wait;
        int fps = 30;
        long time = 1000 / fps;
        
        while (true) {
            try {
                start = System.nanoTime();
                elapsed = System.nanoTime() - start;
                wait = time - elapsed / 1000000;
                Thread.sleep(wait);

                draw(this.gc);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Window.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    } // run

    private void draw(GraphicsContext gc) {
        
        gc.clearRect(0, 0, 1200, 500);
        gc.drawImage(this.fondo, 0, 0, 1200, 500);
        
        gc.drawImage(this.producerShadow.getImage(), this.producerShadow.getX(), this.producerShadow.getY(), 90, 90);
        gc.drawImage(this.consumerTail.getImage(), this.consumerTail.getX(), this.consumerTail.getY(), 50, 80);
        if(sharedLocation.paint()==1){
            gc.drawImage(this.emerald, 555, 450, 35, 35);
            
        } 
        gc.drawImage(this.ring, 525, 410, 100, 100);
        
        
        
        //gc.drawImage(this.emeralda, 545, 445, 35, 35);
    
    } // draw
    

} // fin de la clase
