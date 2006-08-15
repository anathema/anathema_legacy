package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;

import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.properties.OffensiveStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.lib.resources.IResources;

public abstract class AbstractOffensiveStatisticsPresenterPage<O extends IOffensiveStatisticsModel, P extends OffensiveStatisticsProperties> extends
    AbstractEquipmentStatisticsPresenterPage<O, P> {

  public AbstractOffensiveStatisticsPresenterPage(
      IResources resources,
      P properties,
      IEquipmentStatisticsCreationModel overallModel,
      O pageModel,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, properties, overallModel, pageModel, viewFactory);
  }

  @Override
  protected final void addAdditionalContent() {
    addIndividualRows();
    initAccuracyAndRateRow();
    initWeaponDamageRow(getPageModel().getWeaponDamageModel());
  }

  protected abstract void addIndividualRows();

  private void initAccuracyAndRateRow() {
    String[] labels = new String[] { getProperties().getAccuracyLabel(), getProperties().getRateLabel() };
    addLabelledComponentRow(labels, new Component[] {
        initIntegerSpinner(getPageModel().getAccuracyModel()).getComponent(),
        initIntegerSpinner(getPageModel().getRateModel()).getComponent() });
  }

  private void initWeaponDamageRow(final IWeaponDamageModel damageModel) {
    final IWeaponDamageView damageView = getViewFactory().createWeaponDamageView();
    new WeaponDamagePresenter(getResources(), damageModel, damageView).initPresentation();
    getPageContent().addDialogComponent(damageView);
  }
  
  @Override
  protected boolean isTagsSupported() {
    return true;
  }
}