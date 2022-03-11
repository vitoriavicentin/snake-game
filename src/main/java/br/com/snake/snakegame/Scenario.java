package br.com.snake.snakegame;

import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Scenario {

    private final Scene scene;
    private final Group container = new Group();
    private final Snake snake;
    private final Food food;

    public Scenario(Stage primaryStage, Snake snake, Food food) {
        this.scene = new Scene(container, Config.WIDTH, Config.HEIGHT,Color.web("#81c483"));
        primaryStage.setScene(scene);
        primaryStage.setTitle("Snake Game - By Vitoria Vicentin");
        primaryStage.show();

        this.snake = snake;
        this.food = food;

        container.getChildren().add(snake.getHead());
        container.getChildren().add(food.getFood());
    }

    public void setKeyPressed(EventHandler<? super KeyEvent> action) {
        this.scene.setOnKeyPressed(action);
    }

    public void showGameOver(EventLoop eventLoop) {
        TryAgainButton tryAgainButton = new TryAgainButton(e -> {
            clean();
            add(this.snake.reset());
            add(this.food.getFood());
            eventLoop.starLoop();
        });

        this.container.getChildren().add(tryAgainButton);
    }

    private void clean() {
        this.container.getChildren().remove(0, this.container.getChildren().size());
    }

    public void add(Node node) {
        this.container.getChildren().add(node);
    }

}
