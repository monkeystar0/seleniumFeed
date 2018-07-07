package jupeter.sele.task;


import jupeter.sele.model.BlognonInfo;
import org.joda.time.DateTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TidproTask {

//    public static void main(String[] args){
//
//
//    }


    public TidproTask() {

    }

    public Map<String, BlognonInfo> processTask(){
        WebDriver driver = new ChromeDriver();
        driver.get("http://www.tidpro.net/");

        //Wait for 5 Sec
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement element = driver.findElement(By.className("article"));

        List<WebElement> elements = element.findElements(By.className("excerpt"));

        Map<String, BlognonInfo> tidproLists = new HashMap<>();

        if (elements.size()>0){
            try {
                for (WebElement e: elements){
                    //title
                    WebElement ee = e.findElement(By.className("title"));
                    System.out.println("title:"+ee.getText());
                    String title = ee.getText();

                    //id
                    WebElement eId = e.findElement(By.id("featured-thumbnail"));
                    System.out.println("ID:"+eId.getAttribute("href").substring(22));
                    int id = Integer.parseInt(eId.getAttribute("href").substring(22));

                    //detail
                    WebElement eDetail = e.findElement(By.className("post-content"));
                    System.out.println("detail:"+eDetail.getText());
                    String detailInfo = eDetail.getText();

                    //time
                    DateTime date = DateTime.now();

                    tidproLists.put(""+id, new BlognonInfo(id,title,detailInfo,date.toString("dd/MM/yyyy HH:mm:ss")));
                }
            }catch (Exception e){
                e.printStackTrace();
                driver.quit();
                return null;
            }

        }

        // Close the driver
        driver.quit();

        return tidproLists;
    }
}
