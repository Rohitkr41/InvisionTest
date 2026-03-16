package pages.eyeExaminationSearch;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import pages.BasePage;

public class DiagnosisAdvicePage extends BasePage {

    public DiagnosisAdvicePage(WebDriver driver) {
        super(driver);
    }

    // =============================
    // MENU
    // =============================

//    By diagnosisMenu = By.xpath("//p[contains(text(),'Diagnosis')]");
    By diagnosisMenu = By.xpath("//i[contains(@class,'fa-sitemap')]/following-sibling::p')]");

    // =============================
    // DIAGNOSIS SECTION
    // =============================

    By diagnosisField = By.xpath("//label[contains(text(),'Diagnosis')]/following::input[1]");

    By eyeRE = By.name("RM_optionsRadiosEye");
    By eyeLE = By.xpath("//*[@id=\"RM_rdbLE\"]");
    By eyeBE = By.xpath("//*[@id=\"box-main\"]//div[2]/div/div[2]//div[3]/label");

    By remarks = By.xpath("//label[contains(text(),'Remarks')]/following::input[1]");

    By saveDiagnosisButton = By.xpath("//button[contains(text(),'Save Diagnosis')]");

    // =============================
    // TELECONSULTATION
    // =============================

    By teleYes = By.xpath("(//*[@id='RM_rdbRE'])[2]");
    By teleNo = By.xpath("(//*[@id='RM_rdbRE'])[3]");

    By teleName = By.xpath("//*[@id=\"box-main\"]//div[2]//div[3]//div[2]/div/input");
    By organization = By.xpath("(//*[@id=\"box-main\"]/div//div[3]/div/input)[1]");
    By designation = By.xpath("//*[@id=\"box-main\"]/div//div[4]/div/input");
    By teleconsSaveBtn = By.xpath("(//button[contains(text(),'Save') and not(contains(text(),'Diagnosis'))])[1]");

    // =============================
    // PRESCRIBE MEDICINE
    // =============================

    By prescribeMedicineCheckbox = By.xpath("(//*[@id=\"flexPresMedicine\"])[1]");

    By drugForm = By.xpath("(//*[@id=\"box-main\"]//div[2]//div[1]/div/select)[1]");
    By drugName = By.xpath("//*[@id=\"box-main\"]/div/div[3]//div[2]/div/input");
    By dosageStrength = By.xpath("//*[@id=\"box-main\"]/div/div[3]//div[3]/div/input");

    By eyeREMed = By.xpath("//*[@id=\"RM_rdbREs\"]");
    By eyeLEMed = By.xpath("//*[@id=\"RM_rdbLEs\"]");
    By eyeBEMed = By.xpath("//input[@value='BE']");
    By eyeNA = By.xpath("//input[@value='NA']");

    By dosageInstructions = By.xpath("//label[contains(text(),'Dosage Instructions')]/following::input[1]");

    By period = By.xpath("//label[contains(text(),'Period')]/following::input[1]");
    By duration = By.xpath("(//*[@id=\"box-main\"]//div[2]//div[1]/div/select)[2]");

    By specialInstructions = By.xpath("//label[contains(text(),'Special Instructions')]/following::input[1]");

    By addMedicineButton = By.xpath("//button[contains(text(),'Add Medicine')]");
    
	 // =============================
	 // GLASSES SECTION
	 // =============================
	
	 By glassesCheckbox = By.xpath("//label[contains(text(),'Glasses')]/preceding-sibling::input");
	
	 By continueSame = By.xpath("//label[contains(text(),'Continue Same')]/preceding-sibling::input");
	
	 By prescribeNew = By.xpath("//label[contains(text(),'Prescribe New')]/preceding-sibling::input");
	
	 By lensesField = By.xpath("//label[contains(text(),'Lenses')]/following::input[1]");
	
	 By glassesSaveBtn = By.xpath("(//button[contains(text(),'Save')])[2]");
	 
	// =============================
	// HOSPITAL REFERRAL
	// =============================

	By hospitalReferralCheckbox = By.xpath("//label[contains(text(),'Hospital Referral')]/preceding-sibling::input");

	By referralFor = By.xpath("//label[contains(text(),'Referral For')]/following::input[1]");

	By referralCenter = By.xpath("//label[contains(text(),'Referral Center')]/following::select[1]");

	By expectedVisitDate = By.xpath("//label[contains(text(),'Expected Visit Date')]/following::input[1]");

	By referralRemarks = By.xpath("//label[contains(text(),'Remarks')]/following::input[1]");

	By referralSaveBtn = By.xpath("(//button[contains(text(),'Save')])[3]");
	
	//hospital method
	
	public void enableHospitalReferral() {
	    click(hospitalReferralCheckbox);
	}

	public void enterReferralFor(String value) {
	    type(referralFor, value);
	}

	public void selectReferralCenter(String center) {
	    selectByVisibleText(referralCenter, center);
	}

	public void enterExpectedVisitDate(String date) {
	    type(expectedVisitDate, date);
	}

	public void enterReferralRemarks(String value) {
	    type(referralRemarks, value);
	}

	public void saveHospitalReferral() {
	    click(referralSaveBtn);
	    closeSuccessAlert();
	}



    // =============================
    // COMPLETE BUTTON
    // =============================

    By saveCompleteExam = By.xpath("//button[contains(text(),'Save & Complete Examination')]");

    // =============================
    // CLICK MENU
    // =============================


    public void clickDiagnosisMenu() {

        waitForVisibility(diagnosisMenu);

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", driver.findElement(diagnosisMenu));

        click(diagnosisMenu);
    }





    // =============================
    // DIAGNOSIS AUTOCOMPLETE
    // =============================

  public void selectDiagnosis(String diagnosis) {
    selectFromAutocomplete(diagnosisField, diagnosis);
}


    // =============================
    // DIAGNOSIS METHODS
    // =============================

    public void selectEyeRE() {
        click(eyeRE);
    }

    public void enterRemarks(String value) {
        type(remarks, value);
    }

    public void saveDiagnosis() {
        click(saveDiagnosisButton);
        waitForModalToDisappear();
        closeSuccessAlert();
    }

    // =============================
    // TELECONSULTATION METHODS
    // =============================

    public void selectTeleconsultationYes() {
        click(teleYes);
    }

    public void enterTeleconsultantName(String value) {
        type(teleName, value);
    }

    public void enterOrganizationName(String value) {
        type(organization, value);
    }

    public void enterDesignation(String value) {
        type(designation, value);
    }
    
    public void clickTeleconsultationSave() {
        click(teleconsSaveBtn);
        waitForModalToDisappear();
        closeSuccessAlert();
    }


    // =============================
    // PRESCRIBE MEDICINE
    // =============================
    
    public void clickPrescribeMedicineCheckbox() {
        click(prescribeMedicineCheckbox);
    }
    
    public void selectDrugForm(String form) {
        selectByVisibleText(drugForm, form);
    }


    public void enterDrugName(String value) {
        type(drugName, value);
    }

    public void enterDosageStrength(String value) {
        type(dosageStrength, value);
    }

    public void enterDosageInstructions(String value) {
        type(dosageInstructions, value);
    }

    public void enterPeriod(String value) {
        type(period, value);
    }
    public void selectMedicineEyeRE() {
        click(eyeREMed);
    }


    public void enterSpecialInstructions(String value) {
        type(specialInstructions, value);
    }

    public void addMedicine() {
        click(addMedicineButton);
    }

    // =============================
    // COMPLETE EXAMINATION
    // =============================

    public void completeExamination() {
        click(saveCompleteExam);
        waitForModalToDisappear();
        closeSuccessAlert();
    }

    // =============================
    // COMPLETE FLOW
    // =============================

 // =============================
 // DIAGNOSIS SECTION
 // =============================

 public void addDiagnosis() {

     selectDiagnosis("Conjunctival cysts");

     selectEyeRE();

     enterRemarks("Initial Stage");

     saveDiagnosis();
 }
 
 
 //glasses Method
 
 public void enableGlasses() {
	    click(glassesCheckbox);
	}

	public void selectContinueSame() {
	    click(continueSame);
	}

	public void selectPrescribeNew() {
	    click(prescribeNew);
	}

	public void enterLenses(String value) {
	    type(lensesField, value);
	}

	public void saveGlasses() {
	    click(glassesSaveBtn);
	    closeSuccessAlert();
	}

	
//=============================
//TELECONSULTATION SECTION
//=============================

public void addTeleconsultation() {

  selectTeleconsultationYes();

  enterTeleconsultantName("V3M");

  enterOrganizationName("Invision Hospital");

  enterDesignation("Doctor");

  clickTeleconsultationSave();
}

//=============================
//MEDICINE SECTION
//=============================

public void addMedicineDetails() {

 clickPrescribeMedicineCheckbox();

 selectDrugForm("Drop");

 enterDrugName("Eye Drop");

 enterDosageStrength("10ml");

 selectMedicineEyeRE();

 enterDosageInstructions("Twice a day");

 enterPeriod("5");

 enterSpecialInstructions("Use regularly");

 addMedicine();
}

public void addGlassesDetails() {

    enableGlasses();

    selectContinueSame();   // ya selectPrescribeNew()

    enterLenses("Single Vision");

    saveGlasses();
}

public void addHospitalReferralDetails() {

    enableHospitalReferral();

    enterReferralFor("Eye Surgery");

    selectReferralCenter("City Hospital");

    enterExpectedVisitDate("16-03-2026");

    enterReferralRemarks("Urgent Case");

    saveHospitalReferral();
}

}
