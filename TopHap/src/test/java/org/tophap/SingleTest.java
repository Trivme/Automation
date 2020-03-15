package org.tophap;

import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class SingleTest {

    private static String HUB_URL = "http://localhost:4444/wd/hub";
    private static boolean remoteWebDriver = false;

    @BeforeAll
    private static void setUpAll() throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(HUB_URL + "/status");
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                remoteWebDriver = response.getStatusLine().getStatusCode() == HttpStatus.SC_OK;
            } catch (HttpHostConnectException e) {}
        }
    }

    private WebDriver driver;

    @BeforeEach
    private void setUp() throws MalformedURLException {

        if (remoteWebDriver) {
            this.driver = new RemoteWebDriver(new URL(HUB_URL), DesiredCapabilities.chrome());
        } else {
            this.driver = new ChromeDriver();
        }

        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterEach
    public void setDown() {
        //driver.quit();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
