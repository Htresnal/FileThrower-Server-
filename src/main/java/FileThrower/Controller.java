package FileThrower;

import SiegeNetworks.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;

import java.io.File;
import java.io.IOException;
import java.util.Objects;


public class Controller {
    @FXML
    javafx.scene.control.TextField input_Port;

    @FXML
    javafx.scene.control.CheckBox chbx_DoReceive;

    @FXML
    javafx.scene.control.ProgressIndicator progr_Upload;

    @FXML
    javafx.scene.control.Label label_StatusBar;

    @FXML
    javafx.scene.control.Button btn_Browse;

    @FXML
    javafx.scene.control.TextField input_filePath;

    public Controller() throws IOException
    {
        chbx_DoReceive.setSelected(true);
    }

    public void onClickBack(ActionEvent actionEvent) {
        /*
        Platform.runLater ( () ->
           {
           */
       try
       {
           int portAddr=Integer.parseInt(input_Port.getText());
           NETConnection_Files newConnection=new NETConnection_Files(portAddr);
           newConnection.init(portAddr);
           newConnection.recvConnection();
           System.out.println("Accepted connection from : "+newConnection.connectionInfo());
           label_StatusBar.setText("Accepted connection from : "+newConnection.connectionInfo());

           System.out.println("Checking the behaviour of our client...");
           label_StatusBar.setText("Checking the behaviour of our client...");
           String tmpString=newConnection.getSentence();
           if (!Objects.equals(tmpString, new String("Hi!")))
           {
               label_StatusBar.setText("[ERROR] Server speaks some foreign language.");
               throw new IOException("[ERROR] The received data has unexpected content.");
           }

           System.out.println("Well, isn't he a nice guy? Lets answer him!");
           label_StatusBar.setText("Well, isn't he a nice guy? Lets answer him!");
           newConnection.sendSentence("Hi!");

           tmpString=newConnection.getSentence();
           if (!Objects.equals(tmpString, new String("filerecv")))
           {
               label_StatusBar.setText("[ERROR] Server speaks some foreign language.");
               System.out.println(tmpString);
               System.out.println(tmpString.length());
               System.out.println(new String("Hi!"));
               System.out.println(new String("Hi!").length());
               throw new IOException("[ERROR] The received data has unexpected content.");
           }

           System.out.println("He wants to send us a file. Lets accept...");
           label_StatusBar.setText("He wants to send us a file. Lets accept...");
           newConnection.sendSentence("filesend");

           System.out.println("Now, we need him to say us the file name and open state.");
           label_StatusBar.setText("Now, we need him to say us the file name and open state.");

           int st_openOnDownload=chbx_DoReceive.isSelected() ? 1:0;
           newConnection.recvFile(newConnection.getSentence(),st_openOnDownload);

           System.out.println("File sent.");
           label_StatusBar.setText("File sent.");
           newConnection.deInit();
       }
       catch (IOException e)
       {
           e.printStackTrace();
       }
       // }) ;
    }

    public void onClickBrowse(ActionEvent actionEvent)
    {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select Save Folder");
        File selectedDirectory = directoryChooser.showDialog(btn_Browse.getScene().getWindow());
        if (selectedDirectory != null) {
            input_filePath.setText(selectedDirectory.getAbsolutePath());
        }
    }
}
