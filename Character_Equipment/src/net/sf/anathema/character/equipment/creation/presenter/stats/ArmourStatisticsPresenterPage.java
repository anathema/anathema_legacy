package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;

import net.sf.anathema.character.equipment.creation.model.stats.IArmourStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourStatisticsProperties;
import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.framework.value.IconToggleButton;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;

public class ArmourStatisticsPresenterPage extends
    AbstractEquipmentStatisticsPresenterPage<IArmourStatisticsModel, ArmourStatisticsProperties> {

  public ArmourStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, new ArmourStatisticsProperties(resources), model, model.getArmourStatisticsModel(), viewFactory);
  }

  @Override
  protected void addAdditionalContent() {
    addLabelledComponentRow(new String[] {
        getProperties().getBashingSoakLabel(),
        getProperties().getBashingHardnessLabel() }, new Component[] {
        initIntegerSpinner(getPageModel().getBashingSoakModel()).getComponent(),
        initIntegerSpinner(getPageModel().getBashingHardnessModel()).getComponent() });
    final IIntValueModel lethalSoakModel = getPageModel().getLethalSoakModel();
    addLabelledComponentRow(new String[] {
        getProperties().getLethalSoakLabel(),
        getProperties().getLethalHardnessLabel() }, new Component[] {
        initIntegerSpinner(lethalSoakModel).getComponent(),
        initIntegerSpinner(getPageModel().getLethalHardnessModel()).getComponent() });
    final IconToggleButton linkToggleButton = new IconToggleButton(new CharacterUI(getResources()).getLinkIcon());
    final IIntValueModel aggravatedSoakModel = getPageModel().getSoakModel(HealthType.Aggravated);
    final JComponent aggravatedSoakSpinner = initIntegerSpinner(aggravatedSoakModel).getComponent();
    addLabelledComponentRow(
        new String[] { getProperties().getAggravatedSoakLabel(), getProperties().getLinkSoakLabel() },
        new Component[] { aggravatedSoakSpinner, linkToggleButton.getComponent() });
    linkToggleButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        boolean isLinkToggled = !linkToggleButton.isSelected();
        if (isLinkToggled) {
          aggravatedSoakModel.setValue(lethalSoakModel.getValue());
        }
        aggravatedSoakSpinner.setEnabled(!isLinkToggled);
        linkToggleButton.setSelected(isLinkToggled);
      }
    });

    linkToggleButton.setSelected(true);
    aggravatedSoakSpinner.setEnabled(false);
    lethalSoakModel.addIntValueChangeListener(new IIntValueChangedListener() {
      public void valueChanged(int newValue) {
        if (linkToggleButton.isSelected()) {
          aggravatedSoakModel.setValue(lethalSoakModel.getValue());
        }
      }
    });
    addLabelledComponentRow(
        new String[] { getProperties().getMobilityPenaltyLabel(), getProperties().getFatigueLabel() },
        new Component[] {
            initIntegerSpinner(getPageModel().getMobilityPenaltyModel()).getComponent(),
            initIntegerSpinner(getPageModel().getFatigueModel()).getComponent() });
  }

  @Override
  protected boolean isTagsSupported() {
    return false;
  }
}