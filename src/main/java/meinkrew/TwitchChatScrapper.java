package meinkrew;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TwitchChatScrapper {
    public static void main(String[] args) {
        // Set the path to your WebDriver executable
        System.setProperty("webdriver.chrome.driver", "/Users/<OMITTED>/java_stuff/Mein-Krew-II/chromedriver-mac-arm64/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the Twitch stream
            driver.get("https://www.twitch.tv/eslcs");

            // Allow some time for the page to load and for the user to log in if necessary
            Thread.sleep(10000);

            // Locate the chat container
            WebElement chatContainer = driver.findElement(By.className("chat-scrollable-area__message-container"));

            while (true) {
                // Fetch all chat messages
                List<WebElement> messages = chatContainer.findElements(By.className("text-fragment"));

                for (WebElement message : messages) {
                    String text = message.getText();
                    // Print messages containing specific keywords
                    if (text.contains("GO") || text.contains("bye")) {
                        System.out.println("Keyword found: " + text);
                    }
                }

                // Wait for a short period before fetching new messages
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
