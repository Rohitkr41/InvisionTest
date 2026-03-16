package tests.eyeExaminationSearch;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.SidebarPage;
import pages.eyeExaminationSearch.EyeExaminationActionPage;
import pages.eyeExaminationSearch.DiagnosisAdvicePage;
import utils.ConfigReader;

public class DiagnosisAdviceTest extends BaseTest {

    private EyeExaminationActionPage actionPage;
    private DiagnosisAdvicePage diagnosisPage;

    @BeforeMethod
    public void setupPage() {

        // LOGIN
        LoginPage login = new LoginPage(driver);
        login.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password")
        );

        // OPEN MODULE
        SidebarPage sidebar = new SidebarPage(driver);
        sidebar.openEyeExamination();

        // PAGE OBJECT
        actionPage = new EyeExaminationActionPage(driver);
        diagnosisPage = new DiagnosisAdvicePage(driver);

        // SEARCH PATIENT
        actionPage.searchByDate("01-03-2026", "15-03-2026");
        actionPage.clickFirstRowPlusIcon();
        
    }

    @Test
    public void testDiagnosisAdvice() {

    	diagnosisPage.clickDiagnosisMenu();
    	diagnosisPage.addDiagnosis();

    	diagnosisPage.addTeleconsultation();

    	diagnosisPage.addMedicineDetails();

    	diagnosisPage.addGlassesDetails();

    	diagnosisPage.addHospitalReferralDetails();

    	diagnosisPage.completeExamination();

    }
}
