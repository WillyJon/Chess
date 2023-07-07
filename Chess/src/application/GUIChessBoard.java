package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GUIChessBoard extends Application {
    private static final int BOARD_SIZE = 8;
    private static final int SQUARE_SIZE = 80;
    private static final int PIECE_SIZE = 60;

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        // Set column and row constraints to enforce size
        for (int i = 0; i < BOARD_SIZE; i++) {
            gridPane.getColumnConstraints().add(new ColumnConstraints(SQUARE_SIZE));
            gridPane.getRowConstraints().add(new RowConstraints(SQUARE_SIZE));
        }

        // Create the chessboard squares
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle square = new Rectangle(SQUARE_SIZE, SQUARE_SIZE, (row + col) % 2 == 0 ? Color.WHITE : Color.GRAY);
                gridPane.add(square, col, row);

                // Add pieces to the board
                if (row == 1) {
                    ImageView pawn = createPieceImageView("C:/Users/W.Sinnott/Desktop/Programming (1)/Java/Chess/white/pawn.png");
                    gridPane.add(pawn, col, row);
                } else if (row == 6) {
                    ImageView pawn = createPieceImageView("C:/Users/W.Sinnott/Desktop/Programming (1)/Java/Chess/black/pawn.png");
                    gridPane.add(pawn, col, row);
                } else if (row == 0) {
                    ImageView piece = getPieceImageViewForPosition(col, "white");
                    gridPane.add(piece, col, row);
                } else if (row == 7) {
                    ImageView piece = getPieceImageViewForPosition(col, "black");
                    gridPane.add(piece, col, row);
                }
            }
        }

        Scene scene = new Scene(gridPane, SQUARE_SIZE * BOARD_SIZE, SQUARE_SIZE * BOARD_SIZE);
        primaryStage.setTitle("Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ImageView getPieceImageViewForPosition(int col, String color) {
        String pieceFilename = "";
        switch (col) {
            case 0:
            case 7:
                pieceFilename = "rook.png";
                break;
            case 1:
            case 6:
                pieceFilename = "knight.png";
                break;
            case 2:
            case 5:
                pieceFilename = "bishop.png";
                break;
            case 3:
                pieceFilename = "queen.png";
                break;
            case 4:
                pieceFilename = "king.png";
                break;
        }

        if (color.equals("black")) {
            pieceFilename = "C:/Users/W.Sinnott/Desktop/Programming (1)/Java/Chess/black/" + pieceFilename;
        } else if (color.equals("white")) {
            pieceFilename = "C:/Users/W.Sinnott/Desktop/Programming (1)/Java/Chess/white/" + pieceFilename;
        }

        return createPieceImageView(pieceFilename);
    }

    private ImageView createPieceImageView(String imagePath) {
        Image pieceImage = new Image(imagePath);
        ImageView pieceImageView = new ImageView(pieceImage);
        pieceImageView.setFitWidth(PIECE_SIZE);
        pieceImageView.setFitHeight(PIECE_SIZE);
        return pieceImageView;
    }

    public static void main(String[] args) {
        launch(args);
    }
}