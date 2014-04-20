package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.OffensiveStatisticsProperties;
import net.sf.anathema.equipment.editor.wizard.CheckInputListener;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.data.Condition;

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
    WeaponDamageView damageView = getViewFactory().createWeaponDamageView();
    new WeaponDamagePresenter(getResources(), damageModel, damageView).initPresentation();
    getPageContent().addView(damageView, new CC());
  }

  @Override
  protected final void addFollowUpPages(CheckInputListener inputListener) {
    WeaponTagsPresenterPage page = new WeaponTagsPresenterPage(getResources(), getOverallModel(), getViewFactory());
    addFollowupPage(page, inputListener, new Condition() {
      @Override
      public boolean isFulfilled() {
        return isInLegalState();
      }
    });
  }
}