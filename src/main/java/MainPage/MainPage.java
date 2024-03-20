package MainPage;
import utils.BasePage;

import org.openqa.selenium.By;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class MainPage extends BasePage{
    public MainPage(){
         isLoaded();
    }

    @Override
    protected void isLoaded(){
        $(By.xpath("/html/body/div[1]/main/header/div/div/div[3]//*[@id='block-menyusverkhunewru']")).shouldBe(visible.because("No upper toolbar"));
        $(By.xpath("//div[contains(@class='header__controls')]")).shouldBe(visible.because("No upper right toolbar"));
    }
}
