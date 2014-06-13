package net.sf.anathema.hero.equipment.display.presenter;

import net.sf.anathema.character.equipment.character.EquipmentHeroEvaluator;
import net.sf.anathema.character.equipment.character.EquipmentOptionsProvider;
import net.sf.anathema.character.equipment.character.model.IEquipmentItem;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.EquipmentUI;
import net.sf.anathema.character.equipment.item.EquipmentTemplateNameComparator;
import net.sf.anathema.equipment.core.MagicalMaterial;
import net.sf.anathema.equipment.core.MaterialComposition;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.framework.presenter.resources.BasicUi;
import net.sf.anathema.hero.equipment.EquipmentModel;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.control.CollectionListener;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.gui.selection.VetoableObjectSelectionView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.sf.anathema.equipment.core.MaterialComposition.Fixed;
import static net.sf.anathema.equipment.core.MaterialComposition.None;
import static net.sf.anathema.equipment.core.MaterialComposition.Variable;
import static net.sf.anathema.lib.lang.StringUtilities.isNullOrTrimmedEmpty;

public class EquipmentPresenter {

  private final Resources resources;
  private final EquipmentModel model;
  private final EquipmentView view;
  private ObjectSelectionView<IEquipmentItem> ownedEquipmentOverview;
  private EquipmentObjectPresenter objectPresenter;

  public EquipmentPresenter(Resources resources, EquipmentModel model, EquipmentView view) {
    this.resources = resources;
    this.model = model;
    this.view = view;
    EquipmentHeroEvaluator heroEvaluator = model.getHeroEvaluator();
    EquipmentOptionsProvider optionProvider = model.getOptionProvider();
    this.objectPresenter = new EquipmentObjectPresenter(new EquipmentStringBuilder(resources), heroEvaluator, optionProvider, resources);
  }

  public void initPresentation() {
    VetoableObjectSelectionView<String> equipmentTemplatePickList = view.getEquipmentTemplatePickList();
    this.ownedEquipmentOverview = view.addOwnedEquipmentList(new FilteringEquipmentItemRenderer(resources, model.getHeroEvaluator()));
    model.addEquipmentObjectListener(new UpdateOwnedItems());
    equipmentTemplatePickList.setCellRenderer(new EquipmentItemUIConfiguration(model, resources));
    setObjects(equipmentTemplatePickList);
    MagicalMaterialView magicalMaterialView = initMaterialView(equipmentTemplatePickList);
    addAddButton(equipmentTemplatePickList, magicalMaterialView);
    addRemoveButton();
    addRefreshTool(equipmentTemplatePickList);
    EquipmentObjectView editView = view.addItemEditView();
    ownedEquipmentOverview.addObjectSelectionChangedListener(item -> initItemPresentation(item, editView));
    refreshOwnedItemOverview();
  }

  private void addRemoveButton() {
    Tool remove = view.addToolButton();
    remove.setIcon(new BasicUi().getLeftArrowIconPath());
    remove.setTooltip(resources.getString("AdditionalTemplateView.RemoveTemplate.Action.Name"));
    remove.setCommand(() -> model.removeItem(ownedEquipmentOverview.getSelectedObject()));
    ownedEquipmentOverview.addObjectSelectionChangedListener(newValue -> {
      if (newValue == null || !(model.canBeRemoved(newValue))) {
        remove.disable();
      } else {
        remove.enable();
      }
    });
    remove.disable();
  }

  private void addAddButton(VetoableObjectSelectionView<String> equipmentTemplatePickList,
                            MagicalMaterialView magicalMaterialView) {
    Tool addTool = view.addToolButton();
    addTool.setIcon(new BasicUi().getRightArrowIconPath());
    addTool.setTooltip(resources.getString("AdditionalTemplateView.AddTemplate.Action.Tooltip"));
    addTool.setCommand(() -> model.addItem(equipmentTemplatePickList.getSelectedObject(),
            magicalMaterialView.getSelectedMaterial()));
    equipmentTemplatePickList.addObjectSelectionChangedListener(newValue -> setEnabled(newValue, addTool));
    setEnabled(equipmentTemplatePickList.getSelectedObject(), addTool);
  }

  private void addRefreshTool(VetoableObjectSelectionView<String> equipmentTemplatePickList) {
    Tool refreshTool = view.addToolButton();
    refreshTool.setTooltip(resources.getString("AdditionalTemplateView.RefreshDatabase.Action.Tooltip"));
    refreshTool.setIcon(new EquipmentUI().getRefreshIconPath());
    refreshTool.setCommand(() -> {
      setObjects(equipmentTemplatePickList);
      model.refreshItems();
    });
  }

  private void updateMagicalMaterialSelector(MagicalMaterialView magicalMaterialView, String templateId) {
    MaterialComposition composition = templateId == null ? None : model.getMaterialComposition(templateId);
    MagicalMaterial magicMaterial = null;
    if (composition == Variable) {
      magicMaterial = model.getDefaultMaterial();
    } else if (composition == Fixed) {
      magicMaterial = model.getMagicalMaterial(templateId);
    }
    magicalMaterialView.setSelectedMaterial(magicMaterial, composition == Variable);
  }

  private MagicalMaterialView initMaterialView(VetoableObjectSelectionView<String> equipmentTemplatePickList) {
    String label = resources.getString("MagicMaterial.Label") + ":";
    AgnosticUIConfiguration<MagicalMaterial> renderer = new MagicMaterialUIConfiguration(resources);
    MagicalMaterialView magicMaterialView = view.addMagicMaterialView(label, renderer);
    magicMaterialView.setMaterials(MagicalMaterial.values());
    equipmentTemplatePickList.addObjectSelectionChangedListener(
            templateId -> updateMagicalMaterialSelector(magicMaterialView, templateId));
    return magicMaterialView;
  }

  private void setObjects(VetoableObjectSelectionView<String> equipmentTemplatePickList) {
    String[] templates = model.getAvailableTemplateIds();
    Arrays.sort(templates, new EquipmentTemplateNameComparator());
    equipmentTemplatePickList.setObjects(templates);
  }

  private void setEnabled(String newValue, Tool selectTool) {
    if (isNullOrTrimmedEmpty(newValue)) {
      selectTool.disable();
    } else {
      selectTool.enable();
    }
  }

  private void initItemPresentation(IEquipmentItem item, EquipmentObjectView objectView) {
    if (item == null) {
      objectView.clear();
      return;
    }
    objectPresenter.initPresentation(item, objectView);
    if (model.canBeRemoved(item)) {
      objectPresenter.initPersonalization();
    }
  }

  private void refreshOwnedItemOverview() {
    List<IEquipmentItem> allItems = new ArrayList<>();
    allItems.addAll(model.getNaturalWeapons());
    allItems.addAll(model.getEquipmentItems());
    ownedEquipmentOverview.setObjects(allItems);
  }

  private class UpdateOwnedItems implements CollectionListener {
    @Override
    public void itemAdded() {
      refreshOwnedItemOverview();
    }

    @Override
    public void itemRemoved() {
      refreshOwnedItemOverview();
    }
  }
}