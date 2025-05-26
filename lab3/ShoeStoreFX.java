import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ShoeStoreFX extends Application {
    private VBox selectedBox = null;
    private ImageView leftImageView; // ImageView trong leftBox để hiển thị ảnh

    @Override
    public void start(Stage primaryStage) {
        // Main layout
        HBox mainBox = new HBox(0);

        // Thanh hiển thị bên trái
        VBox leftBox = new VBox(10);
        leftBox.setStyle("-fx-background-color: rgb(255, 255, 255); -fx-padding: 10px; ");
        leftBox.setMinWidth(300);

        // Thêm ImageView vào leftBox để hiển thị ảnh
        leftImageView = new ImageView();
        leftImageView.setFitHeight(200); // Kích thước ảnh trong leftBox
        leftImageView.setFitWidth(250);
        Label leftnamLabel = new Label();
        leftnamLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; ");
        Label leftPriceLabel = new Label();
        leftPriceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 18px; ");
        Label leftBrandLabel = new Label();
        Label leftDescriptionLabel = new Label();
        leftDescriptionLabel.setWrapText(true);
        leftBox.getChildren().addAll(leftImageView, leftnamLabel, leftPriceLabel,leftBrandLabel, leftDescriptionLabel);

        // Thiết lập GridPane cho các sản phẩm
        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color: rgb(255, 255, 255);");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new javafx.geometry.Insets(15, 10, 10, 10));

        // Dữ liệu sản phẩm
        String[] names = {"4DFWD PULSE SHOES", "FORUM MID SHOES", "SUPERNOVA SHOES", "Adidas", "Adidas", "4DFWD PULSE SHOES", "4DFWD PULSE SHOES", "FORUM MID SHOES"};
        String[] prices = {"$160.00", "$100.00", "$150.00", "$160.00", "$120.00", "$160.00", "$160.00", "$100.00"};
        String[] images = {"img1.png", "img2.png", "img3.png", "img4.png", "img5.png", "img6.png", "img1.png", "img2.png"};

        // Thêm các sản phẩm vào lưới
        for (int i = 0; i < 8; i++) {
            VBox box = new VBox(5);
            box.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-border-width: 1px; -fx-padding: 10px; -fx-background-color: rgb(211, 211, 211);");

            ImageView imageView = new ImageView(new Image(images[i]));
            imageView.setFitHeight(130);
            imageView.setFitWidth(130);

            Label nameLabel = new Label(names[i]);
            nameLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            Label description1Label = new Label("This product is excluded from all promotional discounts and offers");
            Label description2Label = new Label("NMD City Stock 2");
            Label priceLabel = new Label(prices[i]);
            priceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");
            Label brandLabel = new Label("Adidas");
            HBox box1 = new HBox(90);
            box1.getChildren().addAll(brandLabel, priceLabel);
            Label descriptionLabel;
            if (i == 2 || i == 3 || i == 4) {
                descriptionLabel = description2Label;
            } else {
                descriptionLabel = description1Label;
            }
            box.getChildren().addAll(nameLabel, descriptionLabel, imageView, box1);
            grid.add(box, i % 4, i / 4); // Sắp xếp 4 cột

            box.setOnMouseClicked(event -> {
                if (selectedBox != null) {
                    selectedBox.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-border-width: 1px; -fx-padding: 10px; -fx-background-color: rgb(211, 211, 211);");
                }
                box.setStyle("-fx-background-radius: 15px; -fx-border-radius: 15px; -fx-border-width: 1px; -fx-padding: 10px; -fx-border-color: rgb(30, 0, 255); -fx-background-color: rgb(211, 211, 211);");
                selectedBox = box;

                // Cập nhật ảnh trong leftBox
                leftImageView.setImage(imageView.getImage());
                leftnamLabel.setText(nameLabel.getText());
                leftPriceLabel.setText(priceLabel.getText());
                leftBrandLabel.setText(brandLabel.getText());
                leftDescriptionLabel.setText(descriptionLabel.getText());
            });
        }

        // Thêm leftBox và grid vào mainBox
        mainBox.getChildren().addAll(leftBox, grid);

        // Thiết lập cảnh và hiển thị
        Scene scene = new Scene(mainBox, 1200, 500);
        primaryStage.setTitle("Cửa hàng giày Adidas");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}