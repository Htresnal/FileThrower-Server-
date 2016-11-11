//------------------------------
//File server-receiver.
//------------------------------

package FileThrower;

//import NETConn.NETConn;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.camel.main.Main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;

//______________________________________
//__To_send_file,_you'll_need___________
//__to_specify_the_args:________________
//__PORT________________________________

public class MainApp extends Application
{
    public MainApp() {}

    public void start(Stage primaryStage) throws Exception {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("/FTJavaFX.fxml"));
        primaryStage.setTitle("File Thrower(Server)");
        primaryStage.setScene(new Scene(root, 355.0D, 245.0D));
        primaryStage.show();
    }
    public static void main(String[] args) { launch(args); }

}