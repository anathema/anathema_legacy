package net.sf.anathema.hero.equipment.display.view;

import net.miginfocom.layout.CC;
import net.miginfocom.swing.MigLayout;
import net.sf.anathema.character.main.library.taskpane.ITaskPaneGroupViewFactory;
import net.sf.anathema.character.main.library.taskpane.TaskPaneView;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.framework.swing.selection.ListObjectSelectionView;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentPersonalizationProperties;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentView;
import net.sf.anathema.hero.equipment.display.presenter.MagicalMaterialView;
import net.sf.anathema.hero.equipment.display.presenter.PersonalizationEditView;
import net.sf.anathema.hero.equipment.display.view.personalization.DialogPersonalizationEditView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.swing.interaction.ActionInteraction;
import net.sf.anathema.view.interaction.AddToComponent;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class EquipmentViewImpl implements EquipmentView, IView {
  private final ListObjectSelectionView<String> equipmentPickList = new ListObjectSelectionView<>(String.class);
  private final JPanel panel = new JPanel(new MigLayout(withoutInsets()));
  private final JPanel buttonPanel = new JPanel(new MigLayout(withoutInsets().wrapAfter(1)));
  private final TaskPaneView<SwingEquipmentItemView> taskPaneView = new TaskPaneView<>(new ITaskPaneGroupViewFactory<SwingEquipmentItemView>() {
    @Override
    public SwingEquipmentItemView createView() {
      return new SwingEquipmentItemView();
    }
  });
  private final SwingMagicMaterialView magicMaterialView = new SwingMagicMaterialView();

  public EquipmentViewImpl() {
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
  public net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView addEquipmentObjectView() {
    return taskPaneView.addEquipmentObjectView();
  }

  @Override
  public void removeEquipmentObjectView(net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView objectView) {
    taskPaneView.removeEquipmentObjectView((SwingEquipmentItemView) objectView);
  }

  @Override
  public Tool addToolButton() {
    ActionInteraction tool = new ActionInteraction();
    tool.addTo(new AddToComponent(buttonPanel));
    return tool;
  }

  @Override
  public void revalidateEquipmentViews() {
    taskPaneView.revalidateView();
  }

  @Override
  public PersonalizationEditView startEditingPersonalization(EquipmentPersonalizationProperties properties) {
    return new DialogPersonalizationEditView(properties);
  }

  @Override
  public VetoableObjectSelectionView<String> getEquipmentTemplatePickList() {
    return equipmentPickList;
  }

  @Override
  public MagicalMaterialView getMagicMaterialView() {
    return magicMaterialView;
  }
}