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
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Object document = dataSnapshot.getValue();
//                System.out.println(document);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });




        System.setProperty("webdriver.chrome.driver", "/Users/quantumbreak/etc/chromedriver");

        DatabaseReference blognonRef = ref.child("blognoneInfo");
        BlognoneTask blognoneTask = new BlognoneTask();
        Map<String, BlognonInfo> techLists = blognoneTask.processTask();
        blognonRef.setValueAsync(techLists);


        DatabaseReference tidproRef = ref.child("tidpro");
        TidproTask tidproTask = new TidproTask();
        Map<String, BlognonInfo> tidproLists = tidproTask.processTask();
        tidproRef.setValueAsync(tidproLists);

    }
}

