package net.sf.anathema.character.equipment.impl.character.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.equipment.character.view.IEquipmentAdditionalView;
import net.sf.anathema.character.equipment.character.view.IEquipmentObjectView;
import net.sf.anathema.character.equipment.character.view.IMagicalMaterialView;
import net.sf.anathema.character.library.taskpane.ITaskPaneGroupViewFactory;
import net.sf.anathema.character.library.taskpane.TaskPaneView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.SwingActionTool;
import net.sf.anathema.lib.gui.selection.IListObjectSelectionView;
import net.sf.anathema.framework.swing.selection.ListObjectSelectionView;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class EquipmentAdditionalView implements IEquipmentAdditionalView, IView {
  private final ListObjectSelectionView<String> equipmentPickList = new ListObjectSelectionView<>(String.class);
  private final JPanel panel = new JPanel(new MigLayout(withoutInsets()));
  private final JPanel buttonPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
  private final TaskPaneView<EquipmentObjectView> taskPaneView = new TaskPaneView<>(
          new ITaskPaneGroupViewFactory<EquipmentObjectView>() {
            @Override
            public EquipmentObjectView createView() {
              return new EquipmentObjectView();
            }
          });
  private final MagicMaterialView magicMaterialView = new MagicMaterialView();

  public EquipmentAdditionalView() {
    JScrollPane itemScrollpane = new JScrollPane(equipmentPickList.getComponent());
    itemScrollpane.setPreferredSize(new Dimension(150, 250));
    taskPaneView.getComponent().setPreferredSize(new Dimension(150, 250));
    JPanel selectionPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
    selectionPanel.add(itemScrollpane, new CC().grow().push());
    selectionPanel.add(magicMaterialView.getComponent(), new CC().growX().pushX());
    panel.add(selectionPanel, new CC().push().grow());
    panel.add(buttonPanel);
    panel.add(taskPaneView.getComponent(), new CC().push().grow());
  }

  @Override
  public JComponent getComponent() {
    return panel;
  }

  @Override
  public IEquipmentObjectView addEquipmentObjectView() {
    return taskPaneView.addEquipmentObjectView();
  }

  @Override
  public void removeEquipmentObjectView(IEquipmentObjectView objectView) {
    taskPaneView.removeEquipmentObjectView((EquipmentObjectView) objectView);
  }

  @Override
  public Tool addToolButton() {
    SwingActionTool tool = new SwingActionTool();
    buttonPanel.add(new JButton(tool.getAction()));
    return tool;
  }

  @Override
  public void revalidateEquipmentViews() {
    taskPaneView.revalidateView();
  }

  @Override
  public IListObjectSelectionView<String> getEquipmentTemplatePickList() {
    return equipmentPickList;
  }

  @Override
  public IMagicalMaterialView getMagicMaterialView() {
    return magicMaterialView;
  }
}