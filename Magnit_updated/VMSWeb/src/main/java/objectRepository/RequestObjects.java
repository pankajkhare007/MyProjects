package objectRepository;

public class RequestObjects {
    public static String txtManagerInput = "xpath=//input[@name='managerIdCombo']";
    public static String lblFilteredManagerForRequest = "xpath=//li[text()='%s']";
    public static String txtReqTypeCombo = "xpath=//input[@id='reqTypeCombo']";
    public static String lblReqTypeComboSelection = "xpath=//li[text()='%s']";
    public static String btnGo = "xpath=//input[@id='goButton']";
    public static String ddlRequestProjectCategory = "xpath=//select[@id='jobCategory']";
    public static String txtRequestProjectTitle = "xpath=//input[@id='requisition.name']";
    public static String ddlReqJobCateogryOption="xpath=//select[@id='jobCategory']/option[text()='%s']";
    public static String txtRequestProjectDescription = "xpath=//iframe[@id='requisition.description_ifr']";
    public static String txtRequestProjectScope = "xpath=//iframe[@id='requisition.experience_ifr']";
    public static String txtRequestProjectDeliverable = "xpath=//iframe[@id='requisition.deliverable_ifr']";

    public static String txtRequestProjectStartDate = "xpath=//input[@name='startt-date']";
    public static String txtRequestProjectEndDate = "xpath=//input[@name='estimated-end-date']";
    public static String ddlReqProjReason = "xpath=//select[@id='requisition.reason']";

    public static String txtReqProjOtherClientLoc = "xpath=//input[@id='clientSiteIdCombo']";
    public static String lblReqProjOthClientFilteredLoc = "xpath=//li[@role='option']";
    public static String linkReqProjFinancialDepartmentAddNew = "xpath=//a[text()='Add New']";
    public static String txtReqProjFinancialDepartSearch = "xpath=//input[@type='submit']";
    public static String linkReqProjFinancialDepartSelection = "xpath=//a[text()='Select']";
    public static String ddlReqProjFinancialDepartCurrency = "xpath=//select[@id='currId']";

    public static String ddlReqGLCode="xpath=//select[@id='customFields[1].valueSelect']";
    public static String txtReqProjFinancialDepartmentEstimation = "xpath=//input[@id='estimatedCost']";
    public static String chkBoxReqProjectSourcingSelectAll="xpath=//input[@id='checkAllSuppliers']";
    public static String linkRequestSupplierNotShown="xpath=//a[text()='Request Suppliers Not Shown...']";
    public static String txtReqProjectSupplierNameSearchInput="xpath=//input[@id='supplierName']";
    public static String btnReqProjectSupplierSearch="xpath=//a[text()='Search']";
    public static String chkBoxReqProjectSupplierSelection="xpath=//input[@name='supplierCheckboxPopup']";
    public static String btnReqProjectSaveSupplierSearch="xpath=//div[@id='popUpDivSuppliers']//input[@id='save']";
    public static String btnReqProjectSaveAsDraft="xpath=//input[@value='Save as Draft']";
    public static String txtReqProjectConfirm="xpath=//div[@id='confirm-text']";
    public static String btnReqProejctContniuetoConfirmation="xpath=//input[@value='Continue to Confirmation']";
    public static String lblReqProjectContinueOnConfirmationlandingPage="xpath=//div[contains(text(),'submit for processing')]";
    public static String btnReqProjectSubmit="xpath=//input[@value='Submit']";
    public static String tabReqProjectDetails="xpath=//a[text()='Details']";
}
