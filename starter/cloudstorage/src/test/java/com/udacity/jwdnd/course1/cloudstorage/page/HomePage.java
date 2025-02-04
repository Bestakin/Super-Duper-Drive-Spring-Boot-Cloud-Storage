package com.udacity.jwdnd.course1.cloudstorage.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    private WebDriver driver;
//    // Define elements on the home page (use your specific IDs or classes)
    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "note-title")
    private WebElement titleField;

    @FindBy(id = "note-description")
    private WebElement descriptionField;

    @FindBy(id = "noteSubmit")
    private WebElement noteSubmitButton;

    // Credentials Section
    @FindBy(id = "credential-username")
    private WebElement credentialUsernameField;

    @FindBy(id = "credential-password")
    private WebElement credentialPasswordField;

    @FindBy(id = "credentialSubmit")
    private WebElement credentialSubmitButton;

    // Miscellaneous
    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "fileUploadInput")
    private WebElement fileUploadInput;

    @FindBy(id = "fileUploadButton")
    private WebElement fileUploadButton;

    @FindBy(id = "nav-notes-btn")
    private WebElement addNewNote;

    @FindBy(id = "success-message")
    private WebElement successMessage;

    @FindBy(id = "credentialsTab")
    private WebElement credentialsTab;

    // Constructor initializes elements and driver
    public HomePage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }
    //Note Sections Action
    public void displayNewNoteForm(){
        this.addNewNote.click();
    }
    public void openNoteTab(){
        this.notesTab.click();
    }

    // Click the logout button
    public void logout() {
        this.logoutButton.click();
    }

    public void submitNote(){this.noteSubmitButton.click(); }

    public void createNote(String title, String description){
        this.titleField.sendKeys(title);
        this.descriptionField.sendKeys(description);
        this.noteSubmitButton.click();
    }

    public void createCredential(String username, String password){
        this.credentialUsernameField.clear();
        this.credentialUsernameField.sendKeys(username);
        this.credentialPasswordField.clear();
        this.credentialPasswordField.sendKeys(password);
        this.credentialSubmitButton.click();
    }

    public String getSuccessMessage(){
        return this.successMessage.getText();
    }

}
