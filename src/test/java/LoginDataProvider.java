import org.testng.annotations.DataProvider;

public class LoginDataProvider {

    @DataProvider(name = "login-data")
    public Object[][] loginDataProvider(){
      return ExcelUtils.getTestData("C:\\Users\\gowth\\IdeaProjects\\TDD_Project\\Luma_DataDriven.xlsx","Sheet1");
    }
}
