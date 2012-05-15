package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;
import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.lib.gui.dialog.core.message.DialogMessageModel;
import net.sf.anathema.lib.gui.dialog.userdialog.IMessageSetable;
import net.sf.anathema.lib.message.IBasicMessage;
import net.sf.anathema.lib.model.ObjectModel;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;

public class DialogPagePanel implements IMessageSetable {

  private static final Border DEFAULT_BORDER = BorderFactory.createEmptyBorder(10, 8, 10, 8);
  private static final Border WITH_TOOLBAR_BORDER = BorderFactory.createEmptyBorder(0, 8, 10, 8);
  private final JPanel contentPanel;
  private final IDialogHeaderPanelConfiguration headerPanelConfiguration;
  private final DialogMessageModel messageModel = new DialogMessageModel();
  private final ObjectModel<String> descriptionModel = new ObjectModel<String>();
  private JComponent content;

  public DialogPagePanel(final IDialogHeaderPanelConfiguration headerPanelConfiguration) {
    Preconditions.checkNotNull(headerPanelConfiguration);
    this.headerPanelConfiguration = headerPanelConfiguration;
    contentPanel = new JPanel(new GridLayout(0, 1));
    contentPanel.setBorder(DEFAULT_BORDER);
  }

  public JComponent createPanel() {
    final JPanel dialogPagePanel = new JPanel(new BorderLayout());
    if (headerPanelConfiguration.isHeaderPanelVisible()) {
      final DialogHeaderPanel headerPanel = new DialogHeaderPanel(
          messageModel,
          descriptionModel,
          headerPanelConfiguration.getLargeDialogIcon());
      dialogPagePanel.add(headerPanel.getContent(), BorderLayout.NORTH);
    }
    dialogPagePanel.add(createDialogPagePanel(), BorderLayout.CENTER);
    return dialogPagePanel;
  }

  private Component createDialogPagePanel() {
    final JPanel dialogPagePanel = new JPanel(new GridDialogLayout(1, false, 0, 0));
    final GridDialogLayoutData data = new GridDialogLayoutData(GridDialogLayoutData.FILL_BOTH);
    data.setWidthHint(IDialogConstants.MINIMUM_CONTENT_SIZE.width);
    data.setHeightHint(IDialogConstants.MINIMUM_CONTENT_SIZE.height);
    if (headerPanelConfiguration.getToolBar() != null) {
      contentPanel.setBorder(WITH_TOOLBAR_BORDER);
      dialogPagePanel.add(
          headerPanelConfiguration.getToolBar(),
          GridDialogLayoutData.FILL_HORIZONTAL);
    }
    dialogPagePanel.add(contentPanel, data);
    return dialogPagePanel;
  }

  @Override
  public final void setMessage(final IBasicMessage message) {
    messageModel.setMessage(message);
    contentPanel.validate();
    contentPanel.repaint();
  }

  public final void setDescription(final String description) {
    descriptionModel.setValue(description);
  }

  public void setContent(final JComponent content) {
    this.content = content;
    contentPanel.removeAll();
    contentPanel.add(content);
    contentPanel.revalidate();
  }

  public JComponent getContent() {
    return content;
  }

  public Dimension getPreferredSize() {
    return contentPanel.getPreferredSize();
  }

  public Dimension getSize() {
    return contentPanel.getSize();
  }

}