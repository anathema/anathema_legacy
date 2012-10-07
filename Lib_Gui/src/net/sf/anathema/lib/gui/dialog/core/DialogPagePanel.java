package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
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

import static java.lang.String.valueOf;
import static net.sf.anathema.lib.gui.dialog.core.IDialogConstants.MINIMUM_CONTENT_SIZE;
import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class DialogPagePanel implements IMessageSetable {
  private static final Border DEFAULT_BORDER = BorderFactory.createEmptyBorder(10, 8, 10, 8);
  private final JPanel contentPanel;
  private final IDialogHeaderPanelConfiguration headerPanelConfiguration;
  private final DialogMessageModel messageModel = new DialogMessageModel();
  private final ObjectModel<String> descriptionModel = new ObjectModel<String>();
  private JComponent content;

  public DialogPagePanel(IDialogHeaderPanelConfiguration headerPanelConfiguration) {
    Preconditions.checkNotNull(headerPanelConfiguration);
    this.headerPanelConfiguration = headerPanelConfiguration;
    contentPanel = new JPanel(new GridLayout(0, 1));
    contentPanel.setBorder(DEFAULT_BORDER);
  }

  public JComponent createPanel() {
    JPanel dialogPagePanel = new JPanel(new BorderLayout());
    if (headerPanelConfiguration.isHeaderPanelVisible()) {
      DialogHeaderPanel headerPanel = new DialogHeaderPanel(messageModel, descriptionModel);
      dialogPagePanel.add(headerPanel.getContent(), BorderLayout.NORTH);
    }
    dialogPagePanel.add(createDialogPagePanel(), BorderLayout.CENTER);
    return dialogPagePanel;
  }

  private Component createDialogPagePanel() {
    JPanel dialogPagePanel = new JPanel(new MigLayout(withoutInsets()));
    dialogPagePanel.add(contentPanel, new CC().grow().push().minWidth(valueOf(MINIMUM_CONTENT_SIZE.width)).minHeight(
            valueOf(MINIMUM_CONTENT_SIZE.height)));
    return dialogPagePanel;
  }

  @Override
  public final void setMessage(IBasicMessage message) {
    messageModel.setMessage(message);
    contentPanel.validate();
    contentPanel.repaint();
  }

  public final void setDescription(String description) {
    descriptionModel.setValue(description);
  }

  public void setContent(JComponent content) {
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