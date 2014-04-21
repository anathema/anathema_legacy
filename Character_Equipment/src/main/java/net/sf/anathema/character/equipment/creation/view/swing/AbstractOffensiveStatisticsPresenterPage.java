package net.sf.anathema.character.equipment.creation.view.swing;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.IOffensiveStatisticsModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponDamageModel;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTag;
import net.sf.anathema.character.equipment.creation.presenter.IWeaponTagsModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.OffensiveStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.item.model.EquipmentStatisticsType;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.control.ChangeListener;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValuePresentation;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.Component;

public abstract class AbstractOffensiveStatisticsPresenterPage<O extends IOffensiveStatisticsModel, P extends OffensiveStatisticsProperties> extends
        AbstractEquipmentStatisticsPresenterPage<O, P> {

  private final TagPageProperties tagProperties;

  public AbstractOffensiveStatisticsPresenterPage(
          Resources resources,
          P properties,
          IEquipmentStatisticsCreationModel overallModel,
          O pageModel) {
    super(resources, properties, overallModel, pageModel);
    this.tagProperties = new TagPageProperties(resources);
  }

  @Override
  protected final void addAdditionalContent() {
    addIndividualRows();
    initAccuracyAndRateRow(getPageModel().supportsRate());
    initWeaponDamageRow(getPageModel().getWeaponDamageModel());
    addHorizontalSeparator();
    addTags();
  }

  protected void addTags() {
    addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel) {
        IWeaponTagsView tagsView = new WeaponTagsView();
        BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
        for (IWeaponTag tag : getOverallModel().getWeaponTagsModel().getAllTags()) {
          final JCheckBox checkBox = tagsView.addCheckBox(tagProperties.getLabel(tag));
          checkBox.setToolTipText(tagProperties.getToolTip(tag));
          booleanValuePresentation.initPresentation(checkBox, getOverallModel().getWeaponTagsModel().getSelectedModel(tag));
          final BooleanValueModel enabledModel = getOverallModel().getWeaponTagsModel().getEnabledModel(tag);
          enabledModel.addChangeListener(new IBooleanValueChangedListener() {
            @Override
            public void valueChanged(boolean newValue) {
              checkBox.setEnabled(enabledModel.getValue());
            }
          });
          checkBox.setEnabled(enabledModel.getValue());
        }
        panel.add(tagsView.getComponent(), new CC().spanX().growX().pushX());
      }
    });
  }

  @Override
  public void setInputValidListener(ChangeListener inputValidListener) {
    super.setInputValidListener(inputValidListener);
    IWeaponTagsModel weaponTagsModel = getOverallModel().getWeaponTagsModel();
    for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
      weaponTagsModel.getSelectedModel(tag).addChangeListener(new CheckInputListener(new Runnable() {
        @Override
        public void run() {
          inputValidListener.changeOccurred();
        }
      }));
    }
  }

  protected void addHorizontalSeparator() {
    addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel) {
        panel.add(new HorizontalLine(), new CC().newline().pushX().growX().spanX());
      }
    });
  }

  protected abstract void addIndividualRows();

  protected void initAccuracyAndRateRow(boolean showRate) {
    Component secondComponent;
    String[] labels;
    if (showRate) {
      secondComponent = initIntegerSpinner(getPageModel().getRateModel()).getComponent();
      labels = new String[]{getProperties().getAccuracyLabel(), getProperties().getRateLabel()};
    } else {
      secondComponent = new JPanel();
      labels = new String[]{getProperties().getAccuracyLabel(), ""};
    }
    addLabelledComponentRow(labels, new Component[]{
            initIntegerSpinner(getPageModel().getAccuracyModel()).getComponent(),
            secondComponent});
  }

  private void initWeaponDamageRow(IWeaponDamageModel damageModel) {
    WeaponDamageView damageView = new SwingWeaponDamageView();
    new WeaponDamagePresenter(getResources(), damageModel, damageView).initPresentation();
    addView(damageView);
  }

  @Override
  public boolean canFinish() {
    return !isIllegalRangedWeapon();
  }

  private boolean isIllegalRangedWeapon() {
    if (getOverallModel().getEquipmentType() == EquipmentStatisticsType.RangedCombat) {
      IWeaponTagsModel weaponTagsModel = getOverallModel().getWeaponTagsModel();
      if (!weaponTagsModel.isRangedTypeTagSelected()) {
        return true;
      }
      if (!weaponTagsModel.isThrownTypeTagSelected()
              && weaponTagsModel.isThrownWeaponTagSelected()) {
        return true;
      }
    }
    return false;
  }
}