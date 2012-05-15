package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.disy.commons.swing.layout.grid.IGridDialogLayoutData;
import net.sf.anathema.lib.gui.dialog.core.preferences.IDialogPreferences;
import net.sf.anathema.lib.gui.dialog.userdialog.IDialogCloseHandler;
import net.sf.anathema.lib.gui.dialog.userdialog.UserDialog;
import net.sf.anathema.lib.gui.dialog.wizard.WizardDialog;
import net.sf.anathema.lib.gui.swing.GuiUtilities;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;
import net.sf.anathema.lib.message.IBasicMessage;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public abstract class AbstractDialog {

  private static final String INITIAL_DIALOG_TITLE = "!Dialog.title!"; //$NON-NLS-1$

  private final WindowAdapter cancelingWindowListener = new WindowAdapter() {
    @Override
    public void windowClosing(final WindowEvent e) {
      final Window parentComponent = GuiUtilities.getWindowFor(e);
      performCancel(parentComponent);
    }
  };

  private final ISwingFrameOrDialog dialog;
  private final IGenericDialogConfiguration dialogConfiguration;
  private boolean canceled = false;
  private final DialogPagePanel dialogPagePanel;
  private IDialogCloseHandler closeHandler = IDialogCloseHandler.NULL_HANDLER;

  public AbstractDialog(Component parent, IGenericDialogConfiguration dialogConfiguration) {
    Preconditions.checkNotNull(dialogConfiguration);
    this.dialogConfiguration = dialogConfiguration;
    dialogPagePanel = new DialogPagePanel(dialogConfiguration.getHeaderPanelConfiguration());
    dialog = createFrameOrDialog(parent);
    dialog.setModal(true);
    dialog.getContentPane().setLayout(new GridDialogLayout(1, true, 0, 0));
    dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    dialog.addWindowListener(cancelingWindowListener);
    CloseOnEscapeKeyActionBehavior.attachTo(this);
    adjustToPreferences();
  }

  protected void adjustToPreferences() {
    IDialogPreferences preferences = dialogConfiguration.getPreferences();
    if (preferences == null) {
      return;
    }
    final Rectangle bounds = preferences.getBounds();
    if (bounds == null) {
      return;
    }
    dialog.getWindow().setBounds(bounds);
  }

  private void storePereferences(final ISwingFrameOrDialog dialog, final IDialogPreferences preferences) {
    if (preferences == null) {
      return;
    }
    preferences.setBounds(dialog.getWindow().getBounds());
  }

  protected boolean isMainContentGrabVerticalSpace() {
    return true;
  }

  private static ISwingFrameOrDialog createFrameOrDialog(final Component parent) {
    final Window window = GuiUtilities.getWindowFor(parent);
    if (window == null || !window.isVisible()) {
      final JFrame frame = new JFrame(INITIAL_DIALOG_TITLE);

      if (window != null) {
        final List<Image> originalIconImages = window.getIconImages();
        if (!originalIconImages.isEmpty()) {
          frame.setIconImages(originalIconImages);
        } else {
          frame.setIconImages(DialogDefaults.getInstance().getFrameIconImages());
        }
      }
      final DialogDefaults dialogDefaults = DialogDefaults.getInstance();
      if (frame.getIconImages().isEmpty()) {
        //happens when 'window' is the swing fallback frame
        frame.setIconImages(dialogDefaults.getFrameIconImages());
      }
      return new SwingFrame(frame);
    }
    return new SwingDialog(GuiUtilities.createDialog(parent, INITIAL_DIALOG_TITLE));
  }

  public final void performCancel(final Component parentComponent) {
    final boolean success = cancelPressed(parentComponent);
    if (!success) {
      return;
    }
    canceled = true;
    closeDialog();
    closeHandler.handleDialogClose(new DialogResult(true));
  }

  /**
   * @see UserDialog#show()
   * @see UserDialog#showNonModal(IDialogCloseHandler)
   * @see WizardDialog#show()
   * @deprecated As of 12.11.2009 (gebhard), replaced by {@link IDialogResult#isCanceled()}
   */
  @Deprecated
  public final boolean isCanceled() {
    return canceled;
  }

  protected final IGenericDialogConfiguration getGenericDialog() {
    return dialogConfiguration;
  }

  protected final void initializeContent() {
    final IGridDialogLayoutData mainContentLayoutData =
            isMainContentGrabVerticalSpace() ? GridDialogLayoutData.FILL_BOTH : GridDialogLayoutData.FILL_HORIZONTAL;
    dialog.getContentPane().add(dialogPagePanel.createPanel(), mainContentLayoutData);
    dialog.getContentPane().add(new HorizontalLine(), GridDialogLayoutData.FILL_HORIZONTAL);
    dialog.getContentPane().add(createButtonBar(), GridDialogLayoutData.FILL_HORIZONTAL);
    final JComponent belowButtonsPanel = createOptionalBelowButtonsPanel();
    if (belowButtonsPanel != null) {
      dialog.getContentPane().add(belowButtonsPanel, GridDialogLayoutData.FILL_BOTH);
    }
  }

  protected JComponent createOptionalBelowButtonsPanel() {
    return null;
  }

  protected abstract JComponent createButtonBar();

  protected abstract boolean cancelPressed(Component parentComponent);

  protected void closeDialog() {
    storePereferences(dialog, dialogConfiguration.getPreferences());
    dialog.dispose();
    //Bugfix (gebhard) 26.09.2006: Memory leak by reference from JDialog to this class
    dialog.removeWindowListener(cancelingWindowListener);
  }

  /**
   * Computes the correct dialog size for the current page and resizes itself if nessessary. Also
   * causes the container to refresh its layout.
   */
  public final void updateSize() {
    if (getContent() == null) {
      dialog.pack();
      return;
    }
    final Dimension preferredSize = dialogPagePanel.getPreferredSize();
    final Dimension actualSize = dialogPagePanel.getSize();
    if (preferredSize.width > actualSize.width || preferredSize.height > actualSize.height) {
      GuiUtilities.repack(dialog.getWindow());
    }
  }

  protected final void setContent(final JComponent content) {
    dialogPagePanel.setContent(content);
  }

  protected final JComponent getContent() {
    return dialogPagePanel.getContent();
  }

  protected final void setMessage(final IBasicMessage message) {
    dialogPagePanel.setMessage(message);
    updateSize();
  }

  protected final void setDescription(final String description) {
    dialogPagePanel.setDescription(description);
  }

  protected final void setTitle(final String title) {
    dialog.setTitle(title);
  }

  protected final void setDefaultButton(final JButton button) {
    dialog.getRootPane().setDefaultButton(button);
  }

  public final ISwingFrameOrDialog getDialog() {
    return dialog;
  }

  protected final void setCloseHandler(final IDialogCloseHandler dialogCloseHandler) {
    Preconditions.checkNotNull(dialogCloseHandler);
    closeHandler = dialogCloseHandler;
  }

  public IDialogCloseHandler getCloseHandler() {
    return closeHandler;
  }
}