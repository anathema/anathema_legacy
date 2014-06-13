package net.sf.anathema.hero.equipment.display.view;

import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import net.miginfocom.layout.AC;
import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentItemRenderer;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentView;
import net.sf.anathema.hero.equipment.display.presenter.MagicalMaterialView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.ListSelectionView;
import net.sf.anathema.platform.fx.NodeHolder;
import net.sf.anathema.platform.tool.FxButtonTool;
import org.tbee.javafx.scene.layout.MigPane;

import static net.sf.anathema.lib.gui.layout.LayoutUtils.withoutInsets;

public class FxEquipmentView implements EquipmentView, NodeHolder {
  private final ListSelectionView<String> equipmentPickList = new ListSelectionView<>();
  private final MigPane panel = new MigPane(withoutInsets(), new AC().index(2).grow());
  private final MigPane selectionPanel = new MigPane(withoutInsets().wrapAfter(1));
  private final MigPane ownedPanel = new MigPane(withoutInsets().wrapAfter(1));
  private final MigPane buttonPanel = new MigPane(withoutInsets().wrapAfter(1));

  public FxEquipmentView() {
    ScrollPane availableItemScroller = createScrollPane(equipmentPickList.getNode(), true, true);
    selectionPanel.add(availableItemScroller, new CC().grow().push());
    panel.add(selectionPanel, new CC().push().grow());
    panel.add(buttonPanel);
    panel.add(ownedPanel, new CC().push().grow());
  }

  private ScrollPane createScrollPane(Node node, boolean fitWidth, boolean fitHeight) {
    ScrollPane scroller = new ScrollPane();
    scroller.setFitToWidth(fitWidth);
    scroller.setFitToHeight(fitHeight);
    scroller.setContent(node);
    return scroller;
  }


  @Override
  public VetoableObjectSelectionView<String> getEquipmentTemplatePickList() {
    return equipmentPickList;
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
  public ObjectSelectionView<IEquipmentItem> addOwnedEquipmentList(EquipmentItemRenderer renderer) {
    ListSelectionView<IEquipmentItem> selectionView = new ListSelectionView<>();
    selectionView.setCellRenderer(new EquipmentListCellFactory(renderer));
    ownedPanel.add(selectionView.getNode(), new CC().growX().pushX());
    return selectionView;
  }

  @Override
  public EquipmentObjectView addItemEditView() {
    FxEquipmentItemView itemView = new FxEquipmentItemView();
    ownedPanel.add(itemView.getNode(), new CC().growX().spanX());
    return itemView;
  }

  @Override
  public Node getNode() {
    return panel;
  }
}
