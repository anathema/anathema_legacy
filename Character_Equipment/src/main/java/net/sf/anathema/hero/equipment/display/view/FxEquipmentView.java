package net.sf.anathema.hero.equipment.display.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import net.miginfocom.layout.CC;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentPersonalizationProperties;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentView;
import net.sf.anathema.hero.equipment.display.presenter.MagicalMaterialView;
import net.sf.anathema.hero.equipment.display.presenter.PersonalizationEditView;
import net.sf.anathema.hero.equipment.display.view.personalization.FxPersonalizationEditView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.ListSelectionView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxEquipmentView implements EquipmentView, NodeHolder {
  private final ListSelectionView<String> equipmentPickList = new ListSelectionView<>();
  private final MigPane panel = new MigPane(withoutInsets());
  private final MigPane selectionPanel = new MigPane(withoutInsets().wrapAfter(1));
  private final MigPane buttonPanel = new MigPane(withoutInsets().wrapAfter(1));
  private final MigPane itemPane = new MigPane(withoutInsets().wrapAfter(1));


  public FxEquipmentView() {
    ScrollPane itemScrollpane = new ScrollPane();
    itemScrollpane.setFitToWidth(true);
    itemScrollpane.setFitToHeight(true);
    itemScrollpane.setContent(equipmentPickList.getNode());
    selectionPanel.add(itemScrollpane, new CC().grow().push());
    panel.add(selectionPanel, new CC().push().grow());
    panel.add(buttonPanel);
    panel.add(itemPane, new CC().push().grow());
  }


  @Override
  public VetoableObjectSelectionView<String> getEquipmentTemplatePickList() {
    return equipmentPickList;
  }

  @Override
  public EquipmentObjectView addEquipmentObjectView() {
    FxEquipmentItemView itemView = new FxEquipmentItemView();
    itemPane.add(itemView.getNode(), new CC().growX().spanX());
    return itemView;
  }

  @Override
  public void removeEquipmentObjectView(EquipmentObjectView objectView) {
    itemPane.getChildren().remove(((FxEquipmentItemView) objectView).getNode());
  }

  @Override
  public Tool addToolButton() {
    FxButtonTool tool = FxButtonTool.ForAnyPurpose();
    buttonPanel.add(tool.getNode());
    return tool;
  }

  @Override
  public MagicalMaterialView addMagicMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> renderer) {
    FxMaterialView magicMaterialView = new FxMaterialView(label, renderer);
    selectionPanel.add(magicMaterialView.getNode(), new CC().growX().pushX());
    return magicMaterialView;
  }

  @Override
  public PersonalizationEditView startEditingPersonalization(EquipmentPersonalizationProperties properties) {
    return new FxPersonalizationEditView(panel.getScene().getWindow(), properties);
  }

  @Override
  public Node getNode() {
    return panel;
  }
}
