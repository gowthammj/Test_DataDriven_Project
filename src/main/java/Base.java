

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class Base {

	public static WebDriver driver;
	public static WebDriverWait explicitwait;
	public static Actions action;
	public static JavascriptExecutor jsexecutor;
	

	public static void browseroptions() {
		//In this project Edge browser were used
		EdgeOptions option=new EdgeOptions();
		option.addArguments("--disable-notifications");
		option.addArguments("--delete-cookies");
	}
	public static void launch_browser() {
		 driver=new EdgeDriver();
	}
	public static void url(String url) {
		driver.get(url);
	}
	public static void pagenavigate(String url) {
		driver.navigate().to(url);
	}
	public static void pagenavigateback() {
		driver.navigate().back();
	}
	public static void maximize() {
		driver.manage().window().maximize();
	}
	public static void close_brwoser() {
		driver.close();
	}
	public static void implicitywait(int sec) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(sec));
	}
	public static void explicitywait(WebElement locator,int sec) {
		
		explicitwait=new WebDriverWait(driver, Duration.ofSeconds(sec));
		 explicitwait.until(ExpectedConditions.visibilityOf(locator));
	}
	public static void getthetitle() {
		String title=driver.getTitle();
		System.out.println("Current Page Title : "+title);
			}
	public static void getthtcurrenturl() {
		String currenturl=driver.getCurrentUrl();
		System.out.println("Current Page URl: "+currenturl);

	}
	public String elementgettextvalue(WebElement elementtext) {
		return elementtext.getText();
		
	}
	public static void sendkeysvalue(WebElement element_sendkey,String inputs) {
		element_sendkey.sendKeys(inputs);
	}
	public static void clickelement(WebElement el) {
		el.click();
	}
	public static void quit_browser() {
		driver.quit();
	}
	public static void gettext(WebElement text) {
		text.getText();
	}
	public static void getclear(WebElement element_clear) {
		element_clear.clear();
	}
	public static void checkbox(WebElement element_checkbox) {
		
		if(! element_checkbox.isSelected()) {
			element_checkbox.click();
		}
	}
	public static void radio(WebElement element_radio) {
		if(!element_radio.isSelected()) {
			element_radio.click();
		}
	}
	public static void mousehover(WebElement element_hover) {
		action=new Actions(driver);
		action.moveToElement(element_hover).build().perform();
	}
	public static void double_click(WebElement element_doubleclick) {
		action=new Actions(driver);
		action.doubleClick(element_doubleclick).build().perform();
	}
	public static void right_click(WebElement element_rightclick) {
		action=new Actions(driver);
		action.contextClick().build().perform();
	}
	public static void click_hold(WebElement element_clickandhold) {
		action=new Actions(driver);
		action.click(element_clickandhold).build().perform();
	}
	public static void releaseactions() {
		action=new Actions(driver);
		action.release().build().perform();
	}
	public static void Robot(String path) throws AWTException {
		Robot Robo=new Robot();
		StringSelection path_selection=new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path_selection, null);
		Robo.keyPress(KeyEvent.VK_CONTROL);
		Robo.keyPress(KeyEvent.VK_V);
		Robo.keyRelease(KeyEvent.VK_V);
		Robo.keyRelease(KeyEvent.VK_CONTROL);
		Robo.keyPress(KeyEvent.VK_ENTER);
		Robo.keyRelease(KeyEvent.VK_ENTER);

	}
	public static void drag_and_drop(WebElement Source,WebElement Target) {
		action.dragAndDrop(Source, Target).build().perform();
	}
	public static void alert_accept() {
		driver.switchTo().alert().accept();
	}
	public static void alert_dismiss() {
		driver.switchTo().alert().dismiss();
	}
	public static void dropdown_selectByIndex(WebElement el,int index_value) {
		
		Select select=new Select(el);
		boolean check=select.isMultiple();
		System.out.println(check);
		int size=select.getOptions().size();
		System.out.println(size);
		select.selectByIndex(index_value);
		
	}
	public static void dropdown_selectByVale(WebElement el,String value) {
		Select select=new Select(el);
		boolean check=select.isMultiple();
		System.out.println(check);
		int size=select.getOptions().size();
		System.out.println(size);
		select.selectByValue(value);
		
	}
	public static void dropdpwn_selectby(WebElement el,String text) {
		Select select=new Select(el);
		boolean check=select.isMultiple();
		System.out.println(check);
		int size=select.getOptions().size();
		System.out.println(size);
		select.selectByVisibleText(text);
		
	}
	public static void dropdown_deselct(WebElement el) {
		Select select=new Select(el);
		select.deselectAll();
	}
	public static void dropdown_bootstrap() {

	}
	public static void dropdown_hidden() {

	}
	public static void frame_byindex(int index) {
		driver.switchTo().frame(index);
	}
	public static void frame_byname(String name) {
		driver.switchTo().frame(name);
	}
	public static void frame_bywebelement(WebElement frame_element) {
		driver.switchTo().frame(frame_element);
	}
	public static void frame_bydefault_context() {
		driver.switchTo().defaultContent();
	}
	public static void fulpage_screensshot(String path) throws IOException {
		TakesScreenshot screenshot=(TakesScreenshot)driver;
		File source=screenshot.getScreenshotAs(OutputType.FILE);
		File Target=new File(path);
		FileUtils.copyFile(source, Target);
	}
	public static void particular_elementscreenshot(WebElement screenshot_element,String path) throws IOException {
		File source=screenshot_element.getScreenshotAs(OutputType.FILE);
		File Target=new File(path);
		FileUtils.copyFile(source, Target);
	}
	
	public static void js_excecutor_click(WebElement click_element) {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("arguments[0].click()", click_element);
	}
	public static void js_excecutor_sendkeys(WebElement sendkey_element,String text) {//doubt
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("arguments[0].value=arguments[1];", sendkey_element,text);
	}
	public static void js_excecutor_scroll_down() {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("window.scrollTo(0,document.body.scrollHeight);");

	}
	public  static void js_excecutor_scroll_up() {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("window.scrollTo(0,0);");

	}
	public static void js_excecutor_scroll_specific_position(int orgin, int end) {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("window.scrollTo(arguments[0], arguments[1]);", orgin, end);
	}
	public static void js_excecutor_scroll_certain_amountofpage(int orgin, int end) {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("window.scrollBy(arguments[0], arguments[1]);", orgin, end);
	}
	public  static void js_excecutor_scroll_initial_position() {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("window.scrollBy(0,-document.body.scrollHeight);");

	}
	public static void js_executor_scroll_horizontal_position(int position) {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("window.scrollTo(arguments[0], 0);", position);
	}
	public  static void js_executor_scroll_horizontal_certain_amount(int amount) {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("window.scrollBy(arguments[0], 0);", amount);
	}
	public  static void js_executor_scroll_horizontal_specific_element(WebElement element) {
		jsexecutor=(JavascriptExecutor)driver;
		jsexecutor.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'nearest', inline: 'center'});", element);
	}

	public void submitBtn(WebElement el) {

		 el.submit();
	}
	
	
	
	
	
	
	
}
