package meinkrew;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class App{
    public static void main(String[] args) {
        // Set the path to your WebDriver executable, this should be taken from args instead
        System.setProperty("webdriver.chrome.driver", "/Users/james/java_stuff/Mein-Krew-II/chromedriver-mac-arm64/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the Twitch stream, this should follow the leader around instead
            driver.get("https://www.twitch.tv/summit1g");

            // Allow some time for the page to load and for the user to log in if necessary
            Thread.sleep(10000);

            // Locate the chat container
            WebElement chatContainer = driver.findElement(By.className("chat-scrollable-area__message-container"));

            while (true) {
                Thread.sleep(10000); // dont remove this
                // Fetch all chat messages
                List<WebElement> messages = chatContainer.findElements(By.cssSelector(".chat-line__message"));

                for (WebElement messageElement : messages) {
                    // Extract username
                    WebElement usernameElement = messageElement.findElement(By.className("chat-author__display-name"));
                    String username = usernameElement.getText();

                    try {
                        WebElement messageTextElement = messageElement.findElement(By.className("text-fragment"));
                        String messageText = messageTextElement.getText();
                        // Print the username and message
                        System.out.println(username + ": " + messageText);
                    } catch (NoSuchElementException e) {
                        System.out.println("Element does not exist, continuing");
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
