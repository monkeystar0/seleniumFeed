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



//        driver.get("https://www.blognone.com/");
//
//
//        //Wait for 5 Sec
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        //WebElement searchBox = driver.findElement(By.className("content"));
//           DatabaseReference blognonRef = ref.child("blognoneInfo");
//
//        WebElement element = driver.findElement(By.className("content-container"));
//
//        List<WebElement> elements = element.findElements(By.className("node"));
//
//        Map<String, BlognonInfo> techLists = new HashMap<>();
//
//        if (elements.size()>0){
//            for (WebElement e: elements){
//                WebElement ee = e.findElement(By.className("content-title-box"));

//                System.out.println("ID:"+e.getAttribute("id").substring(5));
//                System.out.println("title:"+ee.getText());
//
//                WebElement detail = e.findElement(By.className("node-content"));
//                System.out.println("detail:"+detail.getText());
//                int id = Integer.parseInt(e.getAttribute("id").substring(5));
//                String title = ee.getText();
//                String detailInfo = detail.getText();
//                techLists.put(e.getAttribute("id").substring(5), new BlognonInfo(id,title,detailInfo,));
//            }
//        }
//        blognonRef.setValueAsync(techLists);
//
//
//
//        // Close the driver
//        driver.quit();


        //get flight cheap information
//        driver.get("http://www.tidpro.net/");
//
//
//        //Wait for 5 Sec
//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//
//        DatabaseReference tidproRef = ref.child("tidpro");
//        Map<String, BlognonInfo> tidproLists = new HashMap<>();
//
//        WebElement element = driver.findElement(By.className("article"));
//
//        List<WebElement> elements = element.findElements(By.className("excerpt"));
//
//        if (elements.size()>0){
//            for (WebElement e: elements){
//                //title
//                WebElement ee = e.findElement(By.className("title"));
//                System.out.println("title:"+ee.getText());
//                String title = ee.getText();
//
//                //id
//                WebElement eId = e.findElement(By.id("featured-thumbnail"));
//                System.out.println("ID:"+eId.getAttribute("href").substring(22));
//                int id = Integer.parseInt(eId.getAttribute("href").substring(22));
//
//                //detail
//                WebElement eDetail = e.findElement(By.className("post-content"));
//                System.out.println("detail:"+eDetail.getText());
//                String detailInfo = eDetail.getText();
//
//                //time
//                DateTime date = DateTime.now();
//
//                tidproLists.put(""+id, new BlognonInfo(id,title,detailInfo,date.toString("dd/MM/yyyy HH:mm:ss")));
//
//                //System.out.println("ID:"+e.getAttribute("id").substring(5));
////                WebElement detail = e.findElement(By.className("node-content"));
////                System.out.println("detail:"+detail.getText());
////
////
////                String detailInfo = detail.getText();
////                techLists.put(e.getAttribute("id").substring(5), new BlognonInfo(id,title,detailInfo));
//            }
//            tidproRef.setValueAsync(tidproLists);
//        }
//        driver.quit();
    }
}

