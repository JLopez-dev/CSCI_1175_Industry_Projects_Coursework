/*
 Author: John Lopez.
 Date:12/17/2020.
 */

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;
import javax.swing.*;

public class FlagRisingAnimation extends Application {
	private static final long serialVersionUID = 1L;

	public void start(Stage primaryStage) {
		// Create a pane
		Pane pane = new Pane();

		// Add an image view and add it to pane
		ImageView imageView = new ImageView("image/us.gif");
		pane.getChildren().add(imageView);

		// Create a path transition
		PathTransition pt = new PathTransition(Duration.millis(10000),
				new Line(100, 200, 100, 0), imageView);
		pt.setCycleCount(5);
		pt.play(); // Start animation


		class FlagPanel extends JPanel implements Runnable {
			private int currentFlag = 0;
			private ImageIcon imageIcon;
			private int x;
			private int y;
			private boolean newFlag = true;
			private Image image;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				if (newFlag) {
					y = getHeight();
					newFlag = false;
					imageIcon = new ImageIcon(this.getClass().getResource("flag" + currentFlag + ".gif"));
					image = imageIcon.getImage();
				}
				x = (getWidth() - imageIcon.getIconWidth()) / 2;
				g.drawImage(image, x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight(), this);
				if (y + imageIcon.getIconHeight() <= 0) {
					newFlag = true;
					currentFlag++;
					if (currentFlag > 6) {
						currentFlag = 0;
					}
				}
			}

			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(10);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		Scene scene = new Scene(pane, 250, 200);
		primaryStage.setTitle("FlagRisingAnimation"); // Set the stage title
		primaryStage.setScene(scene); // Place the scene in the stage
		primaryStage.show(); // Display the stage

	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}