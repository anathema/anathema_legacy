package net.sf.anathema.character.equipment.creation.presenter.stats;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.model.stats.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.OffensiveStatisticsProperties;
import net.sf.anathema.character.equipment.creation.view.IWeaponDamageView;
import net.sf.anathema.character.equipment.wizard.CheckInputListener;
import net.sf.anathema.lib.data.ICondition;
import net.sf.anathema.lib.resources.Resources;

import javax.swing.JPanel;
import java.awt.Component;

public abstract class AbstractOffensiveStatisticsPresenterPage<O extends IOffensiveStatisticsModel, P extends OffensiveStatisticsProperties> extends
    AbstractEquipmentStatisticsPresenterPage<O, P> {

  public AbstractOffensiveStatisticsPresenterPage(
      Resources resources,
      P properties,
      IEquipmentStatisticsCreationModel overallModel,
      O pageModel,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, properties, overallModel, pageModel, viewFactory);
  }

  @Override
  protected final void addAdditionalContent() {
    addIndividualRows();
    initAccuracyAndRateRow(getPageModel().supportsRate());
    initWeaponDamageRow(getPageModel().getWeaponDamageModel());
  }

  protected abstract void addIndividualRows();

  protected void initAccuracyAndRateRow(boolean showRate) {
    Component secondComponent;
    String[] labels;
    if (showRate) {
      secondComponent = initIntegerSpinner(getPageModel().getRateModel()).getComponent();
      labels = new String[] { getProperties().getAccuracyLabel(), getProperties().getRateLabel() };
    }
    else {
      secondComponent = new JPanel();
      labels = new String[] { getProperties().getAccuracyLabel(), "" };
    }
    addLabelledComponentRow(labels, new Component[] {
        initIntegerSpinner(getPageModel().getAccuracyModel()).getComponent(),
        secondComponent });
  }

  private void initWeaponDamageRow(IWeaponDamageModel damageModel) {
    IWeaponDamageView damageView = getViewFactory().createWeaponDamageView();
    new WeaponDamagePresenter(getResources(), damageModel, damageView).initPresentation();
    getPageContent().addView(damageView, new CC());
  }

  @Override
  protected final void addFollowUpPages(CheckInputListener inputListener) {
    WeaponTagsPresenterPage page = new WeaponTagsPresenterPage(getResources(), getOverallModel(), getViewFactory());
    addFollowupPage(page, inputListener, new ICondition() {
      @Override
      public boolean isFulfilled() {
        return isInLegalState();
      }
    });
  }
}