package meinkrew;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class App{
    public static void main(String[] args) {
        // Set the path to your WebDriver executable, this should be taken from args instead
        System.setProperty("webdriver.chrome.driver", "/Users/james/java_stuff/Mein-Krew-II/chromedriver-mac-arm64/chromedriver");

        WebDriver driver = new ChromeDriver();

        try {
            /* FINALLY GETS USERNAMES:MESSAGES BUT CRASHES AFTER AROUND 100 messages
            * Try:
> Run with --stacktrace option to get the stack trace.
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.
             */
            // Navigate to the Twitch stream, this should follow the leader around instead
            driver.get("https://www.twitch.tv/summit1g");

            // Allow some time for the page to load and for the user to log in if necessary
            Thread.sleep(10000);

            // Locate the chat container
            WebElement chatContainer = driver.findElement(By.className("chat-scrollable-area__message-container"));

                while (true) {
                    // Fetch all chat messages
                    List<WebElement> messages = chatContainer.findElements(By.cssSelector(".chat-line__message"));

                    for (WebElement messageElement : messages) {
                        // Extract username
                        WebElement usernameElement = messageElement.findElement(By.className("chat-author__display-name"));
                        String username = usernameElement.getText();

                        // Extract message text
                        WebElement messageTextElement = messageElement.findElement(By.className("text-fragment"));
                        String messageText = messageTextElement.getText();

                        // Print the username and message
                        System.out.println(username + ": " + messageText);
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
