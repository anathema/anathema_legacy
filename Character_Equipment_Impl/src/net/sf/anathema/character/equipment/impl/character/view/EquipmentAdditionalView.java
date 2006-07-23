package net.sf.anathema.character.equipment.impl.character.view;

import java.awt.Dimension;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.disy.commons.swing.layout.grid.GridDialogLayout;
import net.disy.commons.swing.layout.grid.GridDialogLayoutData;
import net.sf.anathema.character.equipment.character.model.IEquipmentTemplate;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.library.taskpane.ITaskPaneGroupViewFactory;
import net.sf.anathema.character.library.taskpane.TaskPaneView;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.lib.gui.selection.ListObjectSelectionView;

public class EquipmentAdditionalView implements IEquipmentAdditionalView {

  private final ListObjectSelectionView<IEquipmentTemplate> equipmentPickList = new ListObjectSelectionView<IEquipmentTemplate>(
      IEquipmentTemplate.class);
  private final JLabel pickListLabel = new JLabel();
  private final JPanel panel = new JPanel(new GridDialogLayout(1, false));
  private final JButton selectButton = new JButton();
  private final TaskPaneView<EquipmentObjectView> taskPaneView = new TaskPaneView<EquipmentObjectView>(
      new ITaskPaneGroupViewFactory<EquipmentObjectView>() {
        public EquipmentObjectView createView() {
          return new EquipmentObjectView();
        }
      });

  public EquipmentAdditionalView() {
    JScrollPane itemScrollpane = new JScrollPane(equipmentPickList.getContent());
    itemScrollpane.setPreferredSize(new Dimension(150, 250));
    panel.add(pickListLabel);
    panel.add(itemScrollpane, GridDialogLayoutData.FILL_HORIZONTAL);
    panel.add(selectButton, GridDialogLayoutData.CENTER);
    panel.add(taskPaneView.getContent(), GridDialogLayoutData.FILL_BOTH);
  }

  public JComponent getComponent() {
    return panel;
  }

  public IEquipmentObjectView addEquipmentObjectView() {
    return taskPaneView.addEquipmentObjectView();
  }

  public void removeEquipmentObjectView(IEquipmentObjectView objectView) {
    taskPaneView.removeEquipmentObjectView((EquipmentObjectView) objectView);
  }

  public void setSelectButtonAction(Action action) {
    selectButton.setAction(action);
  }

  public boolean needsScrollbar() {
    return false;
  }

  public IListObjectSelectionView<IEquipmentTemplate> getEquipmentTemplatePickList() {
    return equipmentPickList;
  }
}