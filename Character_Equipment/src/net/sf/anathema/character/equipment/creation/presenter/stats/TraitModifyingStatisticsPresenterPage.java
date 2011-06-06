package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;

import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.ITraitModifyingStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TraitBoostStatisticsProperties;
import net.sf.anathema.lib.resources.IResources;

public class TraitModifyingStatisticsPresenterPage extends
    AbstractEquipmentStatisticsPresenterPage<ITraitModifyingStatisticsModel, TraitBoostStatisticsProperties> {

  public TraitModifyingStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, new TraitBoostStatisticsProperties(resources), model, model.getTraitModifyingStatisticsModel(), viewFactory);
  }

  @Override
  protected void addAdditionalContent() {

    addLabelledComponentRow(new String[] {
        getProperties().getDDVLabel(),
        getProperties().getPDVLabel(),
        getProperties().getMPDVLabel(),
        getProperties().getMPDVLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getDDVModel()).getComponent(),
        initIntegerSpinner(getPageModel().getPDVModel()).getComponent(),
        initIntegerSpinner(getPageModel().getMPDVModel()).getComponent(),
        initIntegerSpinner(getPageModel().getMPDVModel()).getComponent() });
    addLabelledComponentRow(new String[] {
            getProperties().getMeleeSpeedLabel(),
            getProperties().getMeleeAccuracyLabel(),
            getProperties().getMeleeDamageLabel(),
            getProperties().getMeleeRateLabel() }, new Component[] {
            initIntegerSpinner(getPageModel().getMeleeWeaponSpeedModel()).getComponent(),
            initIntegerSpinner(getPageModel().getMeleeWeaponAccuracyModel()).getComponent(),
            initIntegerSpinner(getPageModel().getMeleeWeaponDamageModel()).getComponent(),
            initIntegerSpinner(getPageModel().getMeleeWeaponRateModel()).getComponent() });
    addLabelledComponentRow(new String[] {
            getProperties().getRangedSpeedLabel(),
            getProperties().getRangedAccuracyLabel(),
            getProperties().getRangedDamageLabel(),
            getProperties().getRangedRateLabel() }, new Component[] {
            initIntegerSpinner(getPageModel().getRangedWeaponSpeedModel()).getComponent(),
            initIntegerSpinner(getPageModel().getRangedWeaponAccuracyModel()).getComponent(),
            initIntegerSpinner(getPageModel().getRangedWeaponDamageModel()).getComponent(),
            initIntegerSpinner(getPageModel().getRangedWeaponRateModel()).getComponent() });
    addLabelledComponentRow(new String[] {
            getProperties().getJoinBattleLabel(),
            getProperties().getJoinDebateLabel(),
            getProperties().getJoinWarLabel() }, new Component[] {
            initIntegerSpinner(getPageModel().getJoinBattleModel()).getComponent(),
            initIntegerSpinner(getPageModel().getJoinDebateModel()).getComponent(),
            initIntegerSpinner(getPageModel().getJoinWarModel()).getComponent() });
  }

  @Override
  protected boolean isTagsSupported() {
    return false;
  }
}