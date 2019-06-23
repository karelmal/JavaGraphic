import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;


public class Main extends Application{

    private int circleCenterX = 75;

    private int circleCenterY = 120;

    private boolean isRemove = false;

    @Override
    public void start(Stage primaryStage){

        primaryStage.setTitle("Visual List");


        Label messages = new Label("Enter number!");
        messages.setLayoutY(25);
        Canvas canvas = new Canvas(500, 500);
        GraphicsContext circle = canvas.getGraphicsContext2D();

        DataList<Integer> list = new DataList();

        TextField dataSource = new TextField();
        dataSource.setLayoutX(145);
        dataSource.setLayoutY(60);

        Button AddButton = new Button("Add");
        AddButton.setLayoutX(40);
        AddButton.setLayoutY(25);
        AddButton.setOnAction(value ->  {
            try {
                if (!dataSource.getText().isEmpty()){
                    list.Add(Integer.parseInt(dataSource.getText()));
                    circle.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    drawCircle(circle, list);
                } else{
                    messages.setText("Nothing to add!");
                }
            }
            catch (NumberFormatException e){
                messages.setText("This is not a number!");
            }
        });

        Button RemoveButton = new Button("Remove");
        RemoveButton.setLayoutX(360);
        RemoveButton.setLayoutY(25);
        RemoveButton.setOnAction(value ->  {
            if (!list.isNull()){
                list.Remove(Integer.parseInt(dataSource.getText()));
                circle.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                drawCircle(circle, list);
            } else{
                messages.setText("Nothing to Remove!");
            }
        });

        messages.setLayoutX(200);
        Group root = new Group();
        root.getChildren().add(canvas);
        root.getChildren().add(AddButton);
        root.getChildren().add(RemoveButton);
        root.getChildren().add(messages);
        root.getChildren().add(dataSource);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }

    private void drawCircle(GraphicsContext graphicsContext, DataList<Integer> list){
        boolean changeDirection = false;
        if(!list.isNull()){
            graphicsContext.fillText("NULL",circleCenterX - 50,circleCenterY + 24);
        }
        for (int obj : list) {
            graphicsContext.setFill(Paint.valueOf("Black"));
            graphicsContext.fillOval(circleCenterX, circleCenterY,40,40);
            graphicsContext.setFill(Paint.valueOf("White"));
            graphicsContext.fillText(String.valueOf(obj),circleCenterX + 12,circleCenterY + 24);

            if (!changeDirection){
                if (circleCenterX < 400)
                    drawHorisontalArrow(graphicsContext, changeDirection, circleCenterX,circleCenterY,circleCenterX + 50);
                circleCenterX += 50;
            }
            else{
                if (circleCenterX > 25)
                    drawHorisontalArrow(graphicsContext, changeDirection, circleCenterX,circleCenterY,circleCenterX + 50);
                circleCenterX -= 60;
            }
            if (circleCenterX >= 420){
                circleCenterX = 25;
                circleCenterY += 75;
            }

        }

        graphicsContext.fillText("NULL",circleCenterX,circleCenterY + 24);

        circleCenterY = 120;
        circleCenterX = 75;
    }

    private void drawHorisontalArrow(GraphicsContext graphicsContext, boolean direction ,double x1, double y, double x2){
        graphicsContext.setFill(Paint.valueOf("Black"));

        y += 30;

        if (!direction){
            graphicsContext.strokeLine(x1,y,x2,y);
            graphicsContext.strokeLine(x2,y,x2 - 4,y - 4);
            graphicsContext.strokeLine(x2,y,x2 - 4,y + 4);
        } else {
            x1 -= 10;
            x2 -= 10;
            graphicsContext.strokeLine(x1,y,x2,y);
            graphicsContext.strokeLine(x1 ,y,x1 + 4,y - 4);
            graphicsContext.strokeLine(x1 ,y,x1 + 4,y + 4);
        }

        y -= 20;

        if (direction){
            graphicsContext.strokeLine(x2,y,x1,y);
            graphicsContext.strokeLine(x2,y-4,x2 - 4,y);
            graphicsContext.strokeLine(x2,y+4,x2 - 4,y);
        } else {
            x1 -= 10;
            x2 -= 10;
            graphicsContext.strokeLine(x2,y,x1,y);
            graphicsContext.strokeLine(x1 ,y,x1 + 4,y - 4);
            graphicsContext.strokeLine(x1 ,y,x1 + 4,y + 4);
        }

        y += 20;

    }

    public static void main(String[] args) {
        Application.launch();
    }

}