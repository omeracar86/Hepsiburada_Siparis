/* Web Otomasyon - Hepsiburada.com üzerinden sipariş tamamlama */

package com.test;

import com.sun.javafx.scene.control.skin.ListCellSkin;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver; //chrome driver ile test edilir.
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.List;
import java.util.Random;

import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestUygulama {

    protected WebDriver driver;

    @Before
    public void setUp() throws Exception{

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        System.setProperty("webdriver.chrome.driver", "/Users/neslihan/Desktop/Test Automation Activities/testAutomation/testuygulama/driver/chromedriver.exe"); //chromedriver path
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout( 60 , TimeUnit.SECONDS);
        driver.manage().timeouts().setScriptTimeout( 60 , TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait( 10 , TimeUnit.SECONDS);

    }

    @Test
    public void UrunAramaKontrolu() {

        //Random rnd = new Random();
        //int index = rnd.nextInt ( 24) + 1;
        driver.get("https://www.hepsiburada.com/laptop-notebook-dizustu-bilgisayarlar-c-98/"); //Elektronik > Dizüstü Bilgisayar kategorisine gidilir.
        driver.findElement(By.id("myAccount")).click(); //Hesabım için önce giriş yapılmalıdır
        driver.findElement(By.id("login")).click();
        driver.findElement(By.id("email")).sendKeys("omer.acar@outlook.com.tr"); //kendi gerçek bilgileri ekledim. test hesabı varsa kullanabilirsiniz. lütfen hesabımı kullanmayınız.
        driver.findElement(By.id("password")).sendKeys("0707077"); //lütfen kendi hesabınızı kullanın. şifremi kullanmayın.
        driver.findElement(By.cssSelector("[class='btn full btn-login-submit']")).click(); // hesap ayarları boş olmalıdır ki fatura adresi sıfırdan eklenebilsin.
        //driver.get("https://www.hepsiburada.com/laptop-notebook-dizustu-bilgisayarlar-c-98/");
        //driver.findElement(By.id("productSearch")).sendKeys("Dizüstü Bilgisayar");
        //driver.findElement(By.id("buttonProductSearch")).click();
        List<WebElement> urunlerIsimListesi = driver.findElements(By.cssSelector("p[class='hb-pl-cn']")); //Elektronik > Dizüstü Bilgisayar kategorisinde ürünler listelenir.
        String urunIsmi = urunlerIsimListesi.get(5).getText(); //ürün listesinden 6. ürün bulunur.
        List<WebElement> urunListesi = driver.findElements(By.cssSelector("li[class='search-item col lg-1 md-1 sm-1  custom-hover not-fashion-flex']"));
        urunListesi.get(5).click(); // 6. ürüne tıklanır.
        String urununSayfasindakiIsmi = driver.findElement(By.id("product-name")).getText();
        Assert.assertEquals("Ürün ismi, arama sayfasi ve ürün sayfasında aynı değil!", urununSayfasindakiIsmi, urunIsmi); //kontrol yapılır.
        driver.findElement(By.id("addToCart")).click(); // karta eklenir.
        driver.findElement(By.id("CartButton")).click(); //devam edilir.
        String sepettekiUrunIsmi = driver.findElement(By.cssSelector("[class='product-name']")).getText();
        Assert.assertEquals("Ürün ismi, sepet sayfasi ve ürün sayfasında aynı değil!", sepettekiUrunIsmi, urununSayfasindakiIsmi); //sepetteki ürün kontrolü yapılır.
        driver.findElement(By.cssSelector("[class='cart-proceed-container']")).click(); // Alışverişi tamamla seçeneği tıklanır.
        driver.findElement(By.cssSelector("li[class='list-item']")).click();
        driver.findElement(By.xpath("//*[@id=\"easy-point\"]/div/div[1]/div/ul/li/div/ul/li[4]/div[2]/div[1]")).click(); //Teslimat noktasına gönder tıklanır ve “İstanbul Kanyon - Easy Point” seçeneği işaretlenir.
        driver.findElement(By.xpath("//*[@id=\"easy-point\"]/div/div[2]/div/a")).click(); // Fatura bilgileri ekle.

        driver.findElement(By.xpath("//*[@id=\"first-name\"]")).sendKeys("omer"); //Fatura bilgileri eklenir.
        driver.findElement(By.xpath("//*[@id=\"last-name\"]")).sendKeys("acar");
        driver.findElement(By.xpath("//*[@id=\"form-address\"]/div/div/section[2]/div[2]/div/div/button/span[2]")).click(); //istanbul
        driver.findElement(By.xpath("//*[@id=\"form-address\"]/div/div/section[2]/div[2]/div/div/div/ul/li[2]/a/span\n")).click();
        driver.findElement(By.xpath("//*[@id=\"form-address\"]/div/div/section[2]/div[3]/div/div/button/span[2]")).click(); //adalar
        driver.findElement(By.xpath("//*[@id=\"form-address\"]/div/div/section[2]/div[3]/div/div/div/ul/li[2]/a/span")).click();
        driver.findElement(By.xpath("//*[@id=\"form-address\"]/div/div/section[2]/div[4]/div/div/button/span[2]")).click(); //atatürk mah.
        driver.findElement(By.xpath("//*[@id=\"form-address\"]/div/div/section[2]/div[4]/div/div/div/ul/li[2]/a/span")).click();

        driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("İstanbul");
        driver.findElement(By.xpath("//*[@id=\"address-name\"]")).sendKeys("Ev Adresi");
        driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("5306154307");
        driver.findElement(By.xpath("//*[@id=\"form-address\"]/div/div/div[4]/div/button/span")).click(); // fatura bilgileri kaydedilir.

        driver.findElement(By.xpath("//*[@id=\"ep-first-name\"]")).sendKeys("Neslihan"); //Alıcı bilgileri eklenir.
        driver.findElement(By.xpath("//*[@id=\"ep-last-name\"]")).sendKeys("Acar");
        driver.findElement(By.xpath("//*[@id=\"easy-point-phone\"]")).sendKeys("5536183007");

        driver.findElement(By.xpath("//*[@id=\"short-summary\"]/div[1]/div[2]/button/span")).click(); //Devam et tıklanır
        driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div/div/div[2]/button")).click(); // Çıkan pop-up da teslimat noktası onaylanır
        driver.findElement(By.xpath("//*[@id=\"payment-container\"]/div[3]/div/div/div[3]/a/span")).click(); //Havale seçeneği seçilir.
        driver.findElement(By.xpath("//*[@id=\"payment-type-2\"]/div[1]/ul/li[1]")).click(); //Akbank seçilir.
        driver.findElement(By.xpath("//*[@id=\"short-summary\"]/div[1]/div[2]/button/span")).click(); // Devam Et tıklanır.
        driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/div[1]/div[2]")).click(); // “Sipariş sonrası IBAN/Hesap No'ya havale/EFT ile ödemenizi yapın.” seçeneği
        String urununSiparistekiIsmi = driver.findElement(By.xpath("//*[@id=\"order-container\"]/div/div[1]/ul/li/div[1]/div[1]")).getText();
        String urununSiparisDetayUcreti = driver.findElement(By.xpath("//*[@id=\"order-container\"]/div/div[1]/ul/li/div[3]/span")).getText();
        String urununSiparisToplamUcreti = driver.findElement(By.xpath("//*[@id=\"item-prices\"]/div/div[1]/div/strong")).getText();

        Assert.assertEquals("Ürün ismi, sepet sayfasi ve ürün sayfasında aynı değil!", urununSiparistekiIsmi, urununSayfasindakiIsmi); //ürün ismi kontrolü yapılır.
        Assert.assertEquals("Ürün ücreti, sepet sayfasi ve sipariş detayı sayfasında aynı değil!", urununSiparisDetayUcreti, urununSiparisToplamUcreti); //ürün ücret kontrolü yapılır.

        driver.findElement(By.xpath("//*[@id=\"frm-save-order\"]/button/span\n")).click(); // Siparişi tamamla.
    }

    @After
    public void tearDown() throws Exception{
        driver.quit();
    }
}