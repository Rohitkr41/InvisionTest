package tests.eyeExaminationSearch;

import org.testng.annotations.Test;
import base.BaseTest;
import pages.eyeExaminationSearch.ComplaintOcularPage;
import pages.eyeExaminationSearch.EyeExaminationActionPage;
import tests.eyeExaminationSearch.EyeExaminationActionTest;

public class ComplaintOcularTest extends BaseTest {

    @Test
    public void testComplaintAndOcularHistory() {

        ComplaintOcularPage page = new ComplaintOcularPage(driver);

        // Click left menu
        page.clickComplaintMenu();

        // Add Chief Complaint
        page.addChiefComplaint();

        // Add Ocular History
        page.addOcularHistory();
    }
}