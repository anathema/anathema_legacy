package net.sf.anathema.character.equipment.creation.presenter;

import net.miginfocom.layout.CC;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.OffensiveStatisticsProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.TagPageProperties;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.WeaponDamageProperties;
import net.sf.anathema.character.equipment.creation.view.swing.EquipmentStatsView;
import net.sf.anathema.character.equipment.creation.view.swing.IWeaponTagsView;
import net.sf.anathema.character.equipment.creation.view.swing.SwingWeaponDamageView;
import net.sf.anathema.character.equipment.creation.view.swing.WeaponDamagePresenter;
import net.sf.anathema.character.equipment.creation.view.swing.WeaponDamageView;
import net.sf.anathema.character.equipment.creation.view.swing.WeaponTagsView;
import net.sf.anathema.lib.control.IBooleanValueChangedListener;
import net.sf.anathema.lib.gui.layout.AdditiveView;
import net.sf.anathema.lib.gui.widgets.HorizontalLine;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValuePresentation;

import javax.swing.JCheckBox;
import javax.swing.JPanel;
import java.awt.Component;

public class AbstractWeaponPresenter {

  private final IOffensiveStatisticsModel model;
  private IWeaponTagsModel weaponTagsModel;
  private final EquipmentStatsView view;
  private final OffensiveStatisticsProperties properties;
  private final TagPageProperties tagProperties;
  private final WeaponDamageProperties damageProperties;

  public AbstractWeaponPresenter(IOffensiveStatisticsModel model, IWeaponTagsModel weaponTagsModel, EquipmentStatsView view, OffensiveStatisticsProperties properties, WeaponDamageProperties damageProperties, TagPageProperties tagProperties) {
    this.model = model;
    this.weaponTagsModel = weaponTagsModel;
    this.view = view;
    this.properties = properties;
    this.tagProperties = tagProperties;
    this.damageProperties = damageProperties;
  }

  public void initPresentation() {
    initAccuracyAndRateRow(model.supportsRate());
    initWeaponDamageRow(model.getWeaponDamageModel());
    addHorizontalSeparator();
    addTags();
  }

  protected void initAccuracyAndRateRow(boolean showRate) {
    Component secondComponent;
    String[] labels;
    if (showRate) {
      secondComponent = view.initIntegerSpinner(model.getRateModel()).getComponent();
      labels = new String[]{properties.getAccuracyLabel(), properties.getRateLabel()};
    } else {
      secondComponent = new JPanel();
      labels = new String[]{properties.getAccuracyLabel(), ""};
    }
    view.addLabelledComponentRow(labels, new Component[]{
            view.initIntegerSpinner(model.getAccuracyModel()).getComponent(),
            secondComponent});
  }

  private void initWeaponDamageRow(IWeaponDamageModel damageModel) {
    WeaponDamageView damageView = new SwingWeaponDamageView();
    new WeaponDamagePresenter(damageProperties, damageModel, damageView).initPresentation();
    view.addView(damageView);
  }

  protected void addHorizontalSeparator() {
    view.addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel) {
        panel.add(new HorizontalLine(), new CC().newline().pushX().growX().spanX());
      }
    });
  }

  protected void addTags() {
    view.addView(new AdditiveView() {
      @Override
      public void addTo(JPanel panel) {
        IWeaponTagsView tagsView = new WeaponTagsView();
        BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
        for (IWeaponTag tag : weaponTagsModel.getAllTags()) {
          final JCheckBox checkBox = tagsView.addCheckBox(tagProperties.getLabel(tag));
          checkBox.setToolTipText(tagProperties.getToolTip(tag));
          booleanValuePresentation.initPresentation(checkBox, weaponTagsModel.getSelectedModel(tag));
          final BooleanValueModel enabledModel = weaponTagsModel.getEnabledModel(tag);
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
}
