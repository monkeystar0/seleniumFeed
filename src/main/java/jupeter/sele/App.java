package jupeter.sele;

/**
 * Hello world!
 *
 */
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import jupeter.sele.model.BlognonInfo;
import jupeter.sele.task.BlognoneTask;
import jupeter.sele.task.TidproTask;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class App {

    public static void main(String[] args){

        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("/Users/quantumbreak/work/keyStorages/serviceAccountKey.json");
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://jupiter-aa7f0.firebaseio.com/")
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // As an admin, the app has access to read and write all data, regardless of Security Rules
        DatabaseReference ref = FirebaseDatabase.getInstance()
                .getReference("test_access");

        System.setProperty("webdriver.chrome.driver", "/Users/quantumbreak/etc/chromedriver");

        DatabaseReference blognonRef = ref.child("blognoneInfo");
        BlognoneTask blognoneTask = new BlognoneTask();
        Map<String, BlognonInfo> techLists = blognoneTask.processTask();
        blognonRef.setValueAsync(techLists);


        DatabaseReference tidproRef = ref.child("tidpro");
        TidproTask tidproTask = new TidproTask();
        Map<String, BlognonInfo> tidproLists = tidproTask.processTask();
        tidproRef.setValueAsync(tidproLists);



        try {
            System.out.println("wait for saving");
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

