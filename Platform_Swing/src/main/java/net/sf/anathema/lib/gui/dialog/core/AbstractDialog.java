package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.gui.dialog.userdialog.DialogCloseHandler;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
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
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import static net.sf.anathema.lib.gui.dialog.core.StaticDialogResult.Canceled;

public abstract class AbstractDialog {

  private static final String INITIAL_DIALOG_TITLE = "!Dialog.title!";

  private final WindowAdapter cancelingWindowListener = new WindowAdapter() {
    @Override
    public void windowClosing(WindowEvent e) {
      performCancel();
    }
  };

  private final ISwingFrameOrDialog dialog;
  private final IGenericDialogConfiguration dialogConfiguration;
  private boolean canceled = false;
  private final DialogPagePanel dialogPagePanel;
  private DialogCloseHandler closeHandler = DialogCloseHandler.NULL_HANDLER;

  public AbstractDialog(Component parent, IGenericDialogConfiguration dialogConfiguration) {
    Preconditions.checkNotNull(dialogConfiguration);
    this.dialogConfiguration = dialogConfiguration;
    dialogPagePanel = new DialogPagePanel(dialogConfiguration.getHeaderPanelConfiguration());
    dialog = createFrameOrDialog(parent);
    dialog.setModal(true);
    dialog.getContentPane().setLayout(new MigLayout(LayoutUtils.fillWithoutInsets().wrapAfter(1)));
    dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    dialog.addWindowListener(cancelingWindowListener);
    CloseOnEscapeKeyActionBehavior.attachTo(this);
  }

  protected void setCloseHandler(DialogCloseHandler handler) {
    this.closeHandler = handler;
  }

  protected boolean isMainContentGrabVerticalSpace() {
    return true;
  }

  private static ISwingFrameOrDialog createFrameOrDialog(Component parent) {
    Window window = GuiUtilities.getWindowFor(parent);
    if (window == null || !window.isVisible()) {
      JFrame frame = new JFrame(INITIAL_DIALOG_TITLE);

      if (window != null) {
        List<Image> originalIconImages = window.getIconImages();
        if (!originalIconImages.isEmpty()) {
          frame.setIconImages(originalIconImages);
        } else {
          frame.setIconImages(DialogDefaults.getInstance().getFrameIconImages());
        }
      }
      DialogDefaults dialogDefaults = DialogDefaults.getInstance();
      if (frame.getIconImages().isEmpty()) {
        //happens when 'window' is the swing fallback frame
        frame.setIconImages(dialogDefaults.getFrameIconImages());
      }
      return new SwingFrame(frame);
    }
    return new SwingDialog(GuiUtilities.createDialog(parent, INITIAL_DIALOG_TITLE));
  }

  public final void performCancel() {
    canceled = true;
    closeDialog();
    closeHandler.handleDialogClose(Canceled());
  }

  protected final IGenericDialogConfiguration getGenericDialog() {
    return dialogConfiguration;
  }

  protected final void initializeContent() {
    CC mainContentLayoutData = isMainContentGrabVerticalSpace() ? new CC().grow().push() : new CC().growX().pushX();
    dialog.getContentPane().add(dialogPagePanel.createPanel(), mainContentLayoutData);
    dialog.getContentPane().add(new HorizontalLine(), new CC().growX().pushX());
    dialog.getContentPane().add(createButtonBar(), new CC().growX().pushX());
    JComponent belowButtonsPanel = createOptionalBelowButtonsPanel();
    if (belowButtonsPanel != null) {
      dialog.getContentPane().add(belowButtonsPanel, new CC().grow().push());
    }
  }

  protected JComponent createOptionalBelowButtonsPanel() {
    return null;
  }

  protected abstract JComponent createButtonBar();

  protected void closeDialog() {
    dialog.dispose();
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
    Dimension preferredSize = dialogPagePanel.getPreferredSize();
    Dimension actualSize = dialogPagePanel.getSize();
    if (preferredSize.width > actualSize.width || preferredSize.height > actualSize.height) {
      GuiUtilities.repack(dialog.getWindow());
    }
  }

  protected final void setContent(JComponent content) {
    dialogPagePanel.setContent(content);
  }

  protected final JComponent getContent() {
    return dialogPagePanel.getContent();
  }

  protected final void setMessage(IBasicMessage message) {
    dialogPagePanel.setMessage(message);
    updateSize();
  }

  protected final void setDescription(String description) {
    dialogPagePanel.setDescription(description);
  }

  protected final void setTitle(String title) {
    dialog.setTitle(title);
  }

  protected final void setDefaultButton(JButton button) {
    dialog.getRootPane().setDefaultButton(button);
  }

  public final ISwingFrameOrDialog getDialog() {
    return dialog;
  }

  protected DialogCloseHandler getCloseHandler() {
    return closeHandler;
  }

  protected DialogResult createDialogResult() {
    if (canceled) {
      return StaticDialogResult.Canceled();
    }
    return StaticDialogResult.Confirmed();
  }
}