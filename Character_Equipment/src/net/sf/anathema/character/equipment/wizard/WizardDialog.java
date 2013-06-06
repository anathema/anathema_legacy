package net.sf.anathema.character.equipment.wizard;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.dialog.core.AbstractDialog;
import net.sf.anathema.lib.gui.dialog.core.DialogButtonBarBuilder;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.core.IDialogConstants;
import net.sf.anathema.lib.gui.dialog.core.ISwingFrameOrDialog;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.sf.anathema.lib.gui.swing.GuiUtilities;

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Component;

import static net.sf.anathema.lib.gui.dialog.core.StaticDialogResult.Confirmed;

/**
 * A dialog to show a wizard to the end user. In typical usage, the client instantiates this class
 * with a particular wizard. The dialog serves as the wizard container and orchestrates the
 * presentation of its pages. The standard layout is roughly as follows: it has an area at the top
 * containing both the wizard's title, description, and image; the actual wizard page appears in the
 * middle; below that is a progress indicator (which is made visible if needed); and at the bottom
 * of the page is message line and a button bar containing Help, Next, Back, Finish, and Cancel
 * buttons (or some subset). Clients may subclass WizardDialog, although this is rarely required.
 */
public class WizardDialog extends AbstractDialog implements IWizardContainer, IDialogConstants {

  public static final String FINISH_BUTTON_NAME = "WizardDialog.FinishButton.ComponentName";
  private JButton finishButton;
  private JButton backButton;
  private JButton nextButton;
  private IWizardPage currentPage;
  private final IWizardConfiguration configuration;
  private JButton cancelButton;

  /**
   * Creates a new wizard dialog for the given wizard.
   *
   * @param parent        a parent swing
   * @param configuration the wizard configuration this dialog is working on
   */
  public WizardDialog(Component parent, IWizardConfiguration configuration) {
    super(parent, configuration);
    this.configuration = configuration;
    configuration.setContainer(this);
    initializeContent();
  }

  @Override
  protected JComponent createButtonBar() {
    SmartAction backAction = new SmartAction(DialogMessages.WIZARD_BACK) {
      @Override
      protected void execute(Component parentComponent) {
        backPressed();
      }
    };

    backButton = new JButton(backAction);

    SmartAction nextAction = new SmartAction(DialogMessages.WIZARD_NEXT) {
      @Override
      protected void execute(Component parentComponent) {
        nextPressed();
      }
    };

    nextButton = new JButton(nextAction);

    IDialogButtonConfiguration buttonConfiguration = configuration.getButtonConfiguration();

    final IActionConfiguration okActionConfiguration = buttonConfiguration.getOkActionConfiguration();
    SmartAction finishAction = new SmartAction(
            okActionConfiguration != null ? okActionConfiguration : new ActionConfiguration()) {
      @Override
      protected void execute(Component parentComponent) {
        performFinish();
      }
    };

    finishButton = new JButton(finishAction);
    finishButton.setName(FINISH_BUTTON_NAME);

    final IActionConfiguration cancelActionConfiguration = buttonConfiguration.getCancelActionConfiguration();
    SmartAction cancelAction = new SmartAction(
            cancelActionConfiguration != null ? cancelActionConfiguration : new ActionConfiguration()) {
      @Override
      protected void execute(Component parentComponent) {
        performCancel();
      }
    };

    cancelButton = new JButton(cancelAction);

    DialogButtonBarBuilder buttonBarBuilder = new DialogButtonBarBuilder();
    buttonBarBuilder.addButtonsCompacted(backButton, nextButton);
    if (okActionConfiguration != null) {
      buttonBarBuilder.addButtons(finishButton);
    }
    if (cancelActionConfiguration != null) {
      buttonBarBuilder.addButtons(cancelButton);
    }
    return buttonBarBuilder.createButtonBar();
  }

  /**
   * Notifies that the back button of this dialog has been pressed.
   */
  protected final void backPressed() {
    showPage(getCurrentPage().getPreviousPage());
  }

  /**
   * The Next button has been pressed
   */
  protected void nextPressed() {
    showPage(getCurrentPage().getNextPage());
  }

  public void showPage(IWizardPage page) {
    Preconditions.checkNotNull(page);
    currentPage = page;
    updateMessage();
    updateDescription();
    updateTitle();
    updateContent();
    updateButtons();
    updateSize();
    currentPage.getPageContent().requestFocus();
  }

  protected void updateContent() {
    setContent(getCurrentPage().getPageContent().getContent());
  }

  @Override
  public IWizardPage getCurrentPage() {
    return currentPage;
  }

  @Override
  public void updateButtons() {
    IWizardPage page = getCurrentPage();
    nextButton.setEnabled(page.canFlipToNextPage());
    backButton.setEnabled(page.getPreviousPage() != null);
    finishButton.setEnabled(configuration.canFinish());
    cancelButton.setEnabled(true);
    if (finishButton.isEnabled()) {
      setDefaultButton(finishButton);
    } else {
      setDefaultButton(nextButton);
    }
  }

  @Override
  public void updateMessage() {
    setMessage(getCurrentPage().getMessage());
  }

  @Override
  public void updateDescription() {
    setDescription(getCurrentPage().getDescription());
  }

  @Override
  public void updateTitle() {
    setTitle(getCurrentPage().getTitle());
  }

  @Override
  public final DialogResult show() {
    ISwingFrameOrDialog configuredDialog = getConfiguredDialog();
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    showDialog(configuredDialog);
    return createDialogResult();
  }

  private void showDialog(final ISwingFrameOrDialog configuredDialog) {
    configuredDialog.show();
  }

  /**
   * For internal use only (demos)
   */
  public ISwingFrameOrDialog getConfiguredDialog() {
    IWizardPage startingPage = configuration.getStartingPage();
    if (startingPage == null) {
      throw new RuntimeException("Starting page may not be null in IWizard.getStartingPage()");
    }
    showPage(startingPage);
    ISwingFrameOrDialog configuredDialog = getDialog();
    if (configuredDialog == null) {
      throw new IllegalStateException("WizardDialog is already disposed and may not be shown more often than once");
    }
    if (configuredDialog.isVisible()) {
      throw new IllegalStateException("WizardDialog is already visible");
    }
    return configuredDialog;
  }

  @Override
  public final void requestFinish() {
    performFinish();
  }

  private void performFinish() {
    currentPage.getNextPage();
    closeDialog();
    getCloseHandler().handleDialogClose(Confirmed());
  }
}