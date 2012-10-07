package net.sf.anathema.lib.gui.dialog.core;

import com.google.common.base.Preconditions;
import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.lib.control.IChangeListener;
import net.sf.anathema.lib.gui.dialog.DialogIconResources;
import net.sf.anathema.lib.gui.dialog.core.message.DialogMessageModel;
import net.sf.anathema.lib.gui.dialog.core.message.DialogMessagePanel;
import net.sf.anathema.lib.gui.icon.CompositeIcon;
import net.sf.anathema.lib.gui.layout.LayoutUtils;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;
import net.sf.anathema.lib.model.ObjectModel;

import javax.swing.Box;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Dimension;

public class DialogHeaderPanel {
  private final DialogMessagePanel messagePanel;
  private final JLabel descriptionLabel;
  private final ObjectModel<String> descriptionModel;
  private JComponent content;

  public DialogHeaderPanel(DialogMessageModel messageModel, ObjectModel<String> descriptionModel) {
    Preconditions.checkNotNull(messageModel);
    Preconditions.checkNotNull(descriptionModel);
    this.descriptionModel = descriptionModel;
    messagePanel = new DialogMessagePanel(messageModel);
    descriptionLabel = new JLabel("!Dialog.description!", SwingConstants.LEFT); //$NON-NLS-1$
    descriptionLabel.setForeground(IDialogConstants.HEADER_TEXT_COLOR);
    descriptionLabel.setFont(IDialogConstants.HEADER_TITLE_FONT);
    descriptionModel.addChangeListener(new IChangeListener() {
      @Override
      public void changeOccurred() {
        updateDescription();
      }
    });
    updateDescription();
  }

  public JComponent getContent() {
    if (content == null) {
      JPanel innerPanel = new JPanel(new BorderLayout(2, 2));
      innerPanel.add(Box.createRigidArea(new Dimension(1, 22)), BorderLayout.EAST);
      innerPanel.add(messagePanel.getContent(), BorderLayout.CENTER);
      innerPanel.add(descriptionLabel, BorderLayout.NORTH);
      innerPanel.setBorder(new EmptyBorder(2, 4, 0, 2));
      innerPanel.setBackground(IDialogConstants.HEADER_BACKGROUND_COLOR);
      JPanel panel = new JPanel(new MigLayout(LayoutUtils.withoutInsets()));
      panel.setBackground(IDialogConstants.HEADER_BACKGROUND_COLOR);
      panel.add(innerPanel, new CC().grow().pushX());
      panel.add(new JLabel(new CompositeIcon(DialogIconResources.DIALOG_HEADER_ICON_BACKGROUND)), new CC().alignY("bottom"));
      JPanel contentPanel = new JPanel(new BorderLayout(0, 0));
      contentPanel.add(panel, BorderLayout.CENTER);
      contentPanel.add(new HorizontalLine(), BorderLayout.SOUTH);
      content = contentPanel;
    }
    return content;
  }

  private void updateDescription() {
    String description = descriptionModel.getValue();
    descriptionLabel.setText(description);
  }
}