/**
 * Copyright (C) 2005, 2011 disy Informationssysteme GmbH and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 */
package net.sf.anathema.lib.gui.dialog.wizard;

import com.google.common.base.Preconditions;
import net.sf.anathema.lib.gui.action.ActionConfiguration;
import net.sf.anathema.lib.gui.action.IActionConfiguration;
import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.dialog.DialogMessages;
import net.sf.anathema.lib.gui.dialog.core.AbstractDialog;
import net.sf.anathema.lib.gui.dialog.core.DialogButtonBarBuilder;
import net.sf.anathema.lib.gui.dialog.core.DialogResult;
import net.sf.anathema.lib.gui.dialog.core.IDialogConstants;
import net.sf.anathema.lib.gui.dialog.core.IDialogHelpHandler;
import net.sf.anathema.lib.gui.dialog.core.IDialogResult;
import net.sf.anathema.lib.gui.dialog.core.ISwingFrameOrDialog;
import net.sf.anathema.lib.gui.dialog.core.IVetoDialogCloseHandler;
import net.sf.anathema.lib.gui.dialog.input.IRequestFinishListener;
import net.sf.anathema.lib.gui.dialog.userdialog.buttons.IDialogButtonConfiguration;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.gui.swing.IEnableable;

import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Component;
import java.util.Collection;
import java.util.HashSet;

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

  public static final String FINISH_BUTTON_NAME = "WizardDialog.FinishButton.ComponentName"; //$NON-NLS-1$
  private JButton finishButton;
  private JButton backButton;
  private JButton nextButton;
  private IWizardPage currentPage;
  private final IWizardConfiguration configuration;
  private final Collection<IWizardPage> pages = new HashSet<IWizardPage>();

  private final IRequestFinishListener requestFinishListener = new IRequestFinishListener() {
    @Override
    public void requestFinish() {
      if (nextButton != null && nextButton.isEnabled()) {
        nextButton.doClick();
        return;
      }
      if (finishButton != null && finishButton.isEnabled()) {
        finishButton.doClick();
      }
    }
  };
  private JButton cancelButton;
  private IEnableable helpEnableable;

  /**
   * Creates a new wizard dialog for the given wizard.
   * 
   * @param parent a parent swing
   * @param configuration the wizard configuration this dialog is working on
   */
  public WizardDialog(final Component parent, final IWizardConfiguration configuration) {
    super(parent, configuration);
    this.configuration = configuration;
    configuration.setContainer(this);
    configuration.addPages();
    initializeContent();
  }

  @Override
  protected JComponent createButtonBar() {
    final SmartAction backAction = new SmartAction(DialogMessages.WIZARD_BACK) {
      @Override
      protected void execute(final Component parentComponent) {
        backPressed();
      }
    };

    backButton = new JButton(backAction);

    final SmartAction nextAction = new SmartAction(DialogMessages.WIZARD_NEXT) {
      @Override
      protected void execute(final Component parentComponent) {
        nextPressed();
      }
    };

    nextButton = new JButton(nextAction);

    final IDialogButtonConfiguration buttonConfiguration = getWizard().getButtonConfiguration();

    final IActionConfiguration okActionConfiguration = buttonConfiguration
        .getOkActionConfiguration();
    final SmartAction finishAction = new SmartAction(okActionConfiguration != null
        ? okActionConfiguration
        : new ActionConfiguration()) {
      @Override
      protected void execute(final Component parentComponent) {
        performFinish(parentComponent);
      }
    };

    finishButton = new JButton(finishAction);
    finishButton.setName(FINISH_BUTTON_NAME);

    final IActionConfiguration cancelActionConfiguration = buttonConfiguration
        .getCancelActionConfiguration();
    final SmartAction cancelAction = new SmartAction(cancelActionConfiguration != null
        ? cancelActionConfiguration
        : new ActionConfiguration()) {
      @Override
      protected void execute(final Component parentComponent) {
        performCancel(parentComponent);
      }
    };

    cancelButton = new JButton(cancelAction);

    final DialogButtonBarBuilder buttonBarBuilder = new DialogButtonBarBuilder();
    buttonBarBuilder.addButtonsCompacted(backButton, nextButton);
    final JButton[] additionalButtons = createAdditionalButtons();
    buttonBarBuilder.addButtons(additionalButtons);
    if (okActionConfiguration != null) {
      buttonBarBuilder.addButtons(finishButton);
    }
    if (cancelActionConfiguration != null) {
      buttonBarBuilder.addButtons(cancelButton);
    }
    if (getWizard().isHelpAvailable()) {
      final IDialogHelpHandler helpHandler = new IDialogHelpHandler() {
        @Override
        public void execute(final Component parentComponent) {
          final IDialogHelpHandler pageHelpHandler = getCurrentPage().getHelpHandler();
          pageHelpHandler.execute(parentComponent);
        }
      };
      helpEnableable = buttonBarBuilder.setHelpHandler(helpHandler);
    }
    return buttonBarBuilder.createButtonBar();
  }

  private JButton[] createAdditionalButtons() {
    return new JButton[0];
  }

  /** Notifies that the back button of this dialog has been pressed. */
  protected final void backPressed() {
    showPage(getCurrentPage().getPreviousPage());
  }

  /** Notifies that the cancel button of this dialog has been pressed. */
  @Override
  protected final boolean cancelPressed(final Component parentComponent) {
    final IVetoDialogCloseHandler vetoCloseHandler = configuration.getVetoCloseHandler();
    final boolean success = vetoCloseHandler.handleDialogAboutToClose(
        new DialogResult(true),
        parentComponent);
    return success;
  }

  /** The Next button has been pressed */
  protected void nextPressed() {
    showPage(getCurrentPage().getNextPage());
  }

  public void showPage(final IWizardPage page) {
    Preconditions.checkNotNull(page);
    if (currentPage != null) {
      currentPage.leave();
      currentPage.removeRequestFinishListener(requestFinishListener);
    }
    page.addRequestFinishListener(requestFinishListener);
    currentPage = page;
    pages.add(page);
    updateMessage();
    updateDescription();
    updateTitle();
    updateContent();
    updateButtons();
    updateSize();
    currentPage.enter();
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
    final IWizardPage page = getCurrentPage();
    nextButton.setEnabled(page.canFlipToNextPage());
    backButton.setEnabled(page.getPreviousPage() != null);
    finishButton.setEnabled(getWizard().canFinish());
    cancelButton.setEnabled(getWizard().canCancel());
    if (helpEnableable != null) {
      helpEnableable.setEnabled(page.getHelpHandler() != null);
    }
    if (finishButton.isEnabled()) {
      setDefaultButton(finishButton);
    }
    else {
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
  public final IDialogResult show() {
    final ISwingFrameOrDialog configuredDialog = getConfiguredDialog();
    GuiUtilities.centerToParent(configuredDialog.getWindow());
    configuredDialog.show();
    return new DialogResult(isCanceled());
  }

  /** For internal use only (demos) */
  public ISwingFrameOrDialog getConfiguredDialog() {
    final IWizardPage startingPage = getWizard().getStartingPage();
    if (startingPage == null) {
      throw new RuntimeException("Starting page may not be null in IWizard.getStartingPage()"); //$NON-NLS-1$
    }
    showPage(startingPage);
    final ISwingFrameOrDialog configuredDialog = getDialog();
    if (configuredDialog == null) {
      throw new IllegalStateException(
          "WizardDialog is already disposed and may not be shown more often than once"); //$NON-NLS-1$
    }
    if (configuredDialog.isVisible()) {
      throw new IllegalStateException("WizardDialog is already visible"); //$NON-NLS-1$
    }
    return configuredDialog;
  }

  protected final IWizardConfiguration getWizard() {
    return configuration;
  }

  private void performFinish(final Component parentComponent) {
    currentPage.getNextPage();
    currentPage.leave();

    final IVetoDialogCloseHandler vetoCloseHandler = configuration.getVetoCloseHandler();
    final boolean success = vetoCloseHandler.handleDialogAboutToClose(
        new DialogResult(false),
        parentComponent);
    if (success) {
      closeDialog();
    }
  }

  @Override
  public final void requestFinish() {
    performFinish(getDialog().getWindow());
  }

  @Override
  protected final void closeDialog() {
    super.closeDialog();
    currentPage.leave();
    for (final IWizardPage page : pages) {
      page.getPageContent().dispose();
    }
  }
}