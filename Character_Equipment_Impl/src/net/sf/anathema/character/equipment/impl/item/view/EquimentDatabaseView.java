package net.sf.anathema.character.equipment.impl.item.view;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.equipment.item.view.IEquipmentDatabaseView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

public class EquimentDatabaseView implements IEquipmentDatabaseView {

  private JPanel contentPanel;
  private JPanel editTemplateView = new JPanel(new GridDialogLayout(1, false));
  private JLabel templateListHeaderLabel = new JLabel();
  private JLabel editTemplateHeaderLabel = new JLabel();
  private ListObjectSelectionView<String> templateListView = new ListObjectSelectionView<String>(String.class);

  public JComponent getComponent() {
    if (contentPanel == null) {
      contentPanel = new JPanel(new GridDialogLayout(2, false));
      contentPanel.add(templateListHeaderLabel, GridDialogLayoutData.FILL_HORIZONTAL);
      contentPanel.add(editTemplateHeaderLabel, GridDialogLayoutData.FILL_HORIZONTAL);
      contentPanel.add(new JScrollPane(templateListView.getContent()), GridDialogLayoutData.FILL_BOTH);
      contentPanel.add(editTemplateView, GridDialogLayoutData.FILL_BOTH);
    }
    return contentPanel;
  }

  public void setTemplateListHeader(String headerText) {
    templateListHeaderLabel.setText(headerText);
  }

  public void setEditTemplateHeader(String headerText) {
    editTemplateHeaderLabel.setText(headerText);
  }

  public void fillDescriptionPanel(JComponent descriptionPanel) {
    editTemplateView.add(descriptionPanel);
  }

  public IListObjectSelectionView<String> getTemplateListView() {
    return templateListView;
  }
}