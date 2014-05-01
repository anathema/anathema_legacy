package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.OffensiveStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.fx.hero.configurableview.IIntegerSpinner;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.gui.selection.ObjectSelectionView;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;

public class BasicWeaponPresenter {

  private final IOffensiveStatisticsModel model;
  private IWeaponTagsModel weaponTagsModel;
  private final EquipmentStatsView view;
  private final OffensiveStatisticsProperties properties;
  private final TagPageProperties tagProperties;
  private final WeaponDamageProperties damageProperties;

  public BasicWeaponPresenter(IOffensiveStatisticsModel model, IWeaponTagsModel weaponTagsModel,
                              EquipmentStatsView view, OffensiveStatisticsProperties properties,
                              WeaponDamageProperties damageProperties, TagPageProperties tagProperties) {
    this.model = model;
    this.weaponTagsModel = weaponTagsModel;
    this.view = view;
    this.properties = properties;
    this.tagProperties = tagProperties;
    this.damageProperties = damageProperties;
  }

  public void initPresentation() {
    initAccuracyAndRateRow();
    initWeaponDamageRow(model.getWeaponDamageModel());
    addHorizontalSeparator();
    addTags();
  }

  protected void initAccuracyAndRateRow() {
    addSpinner(properties.getAccuracyLabel(), model.getAccuracyModel());
    addSpinner(properties.getRateLabel(), model.getRateModel());
  }

  private void initWeaponDamageRow(IWeaponDamageModel damageModel) {
    addSpinner(damageProperties.getDamageLabel(), damageModel.getDamageModel());
    ObjectSelectionView<HealthType> selectionView = view.addObjectSelection(damageProperties.getHealthTypeUi());
    initDamageTypePresentation(selectionView, damageModel);
    addSpinner(damageProperties.getMinDamageLabel(), damageModel.getMinDamageModel());
  }

  private void initDamageTypePresentation(ObjectSelectionView<HealthType> damageView, IWeaponDamageModel damageModel) {
    damageView.setObjects(HealthType.values());
    damageModel.addHealthTypeChangeListener(() -> damageView.setSelectedObject(damageModel.getHealthType()));
    damageView.addObjectSelectionChangedListener(newValue -> damageModel.setHealthType(newValue));
    damageView.setSelectedObject(damageModel.getHealthType());
  }

  protected void addHorizontalSeparator() {
    view.addHorizontalSeparator();
  }

  protected void addTags() {
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
    for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
      ToggleTool tool = view.addToggleTool();
      tool.setText(tagProperties.getLabel(tag));
      tool.setTooltip(tagProperties.getToolTip(tag));
      booleanValuePresentation.initPresentation(tool, weaponTagsModel.getSelectedModel(tag));
      final BooleanValueModel enabledModel = weaponTagsModel.getEnabledModel(tag);
      enabledModel.addChangeListener(newValue -> enableBasedOnModelState(enabledModel, tool));
      enableBasedOnModelState(enabledModel, tool);
    }
  }

  private void enableBasedOnModelState(BooleanValueModel enabledModel, ToggleTool tool) {
    if (enabledModel.getValue()) {
      tool.enable();
    } else {
      tool.disable();
    }
  }

  private IIntegerSpinner addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label);
    new IntValuePresentation().initPresentation(spinner, model);
    return spinner;
  }
}