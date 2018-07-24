package jupeter.sele.task;


import jupeter.sele.model.BlognonInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlognoneTask {

//    public static void main(String[] args){
//
//
//    }


    public BlognoneTask() {

    }

    public Map<String, BlognonInfo> processTask(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.blognone.com/");

        //Wait for 5 Sec
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        WebElement element = driver.findElement(By.className("content-container"));

        List<WebElement> elements = element.findElements(By.className("node"));

        Map<String, BlognonInfo> techLists = new HashMap<>();

        if (elements.size()>0){
            try {
                for (WebElement e: elements){
                    System.out.println(e.getAttribute("outerHTML"));
                    WebElement ee = e.findElement(By.className("content-title-box"));

                    System.out.println("ID:"+e.getAttribute("id").substring(5));
                    System.out.println("title:"+ee.getText());

                    WebElement detail = e.findElement(By.className("node-content"));
                    System.out.println("detail:"+detail.getText());
                    int id = Integer.parseInt(e.getAttribute("id").substring(5));
                    String title = ee.getText();
                    String detailInfo = detail.getText();

                    WebElement eSubmitBy = e.findElement(By.className("submitted"));
                    String submitBy = eSubmitBy.getText();
                    System.out.println("submitBy:"+submitBy);
                    techLists.put(e.getAttribute("id").substring(5), new BlognonInfo(id,title,detailInfo,submitBy));
                }
            }catch (Exception e){
                e.printStackTrace();
                driver.quit();
                return null;
            }

        }

        // Close the driver
        driver.quit();

        return techLists;
    }
}
