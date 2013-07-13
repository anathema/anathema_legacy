package net.sf.anathema.hero.equipment.display.view;

import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.framework.swing.IView;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentObjectView;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentPersonalizationProperties;
import net.sf.anathema.hero.equipment.display.presenter.EquipmentView;
import net.sf.anathema.hero.equipment.display.presenter.MagicalMaterialView;
import net.sf.anathema.hero.equipment.display.presenter.PersonalizationEditView;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;
import net.sf.anathema.platform.fx.BridgingPanel;

import javax.swing.JComponent;

public class BridgingEquipmentView implements EquipmentView, IView {

  private final BridgingPanel panel = new BridgingPanel();
  private final FxEquipmentView fxView;

  public BridgingEquipmentView(FxEquipmentView fxView) {
    this.fxView = fxView;
    panel.init(fxView, "skin/platform/tooltip.css");
  }

  @Override
  public VetoableObjectSelectionView<String> getEquipmentTemplatePickList() {
    return fxView.getEquipmentTemplatePickList();
  }

  @Override
  public EquipmentObjectView addEquipmentObjectView() {
    return fxView.addEquipmentObjectView();
  }

  @Override
  public void removeEquipmentObjectView(EquipmentObjectView objectView) {
    fxView.removeEquipmentObjectView(objectView);
  }

  @Override
  public Tool addToolButton() {
    return fxView.addToolButton();
  }

  @Override
  public MagicalMaterialView addMagicMaterialView(String label, AgnosticUIConfiguration<MagicalMaterial> renderer) {
    return fxView.addMagicMaterialView(label, renderer);
  }

  @Override
  public void revalidateEquipmentViews() {
    fxView.revalidateEquipmentViews();
  }

  @Override
  public PersonalizationEditView startEditingPersonalization(EquipmentPersonalizationProperties properties) {
    return fxView.startEditingPersonalization(properties);
  }

  @Override
  public JComponent getComponent() {
    return panel.getComponent();
  }
}
