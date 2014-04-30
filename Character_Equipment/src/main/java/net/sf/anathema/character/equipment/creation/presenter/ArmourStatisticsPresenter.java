package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArmourStatisticsProperties;
import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.health.HealthType;
import net.sf.anathema.interaction.ToggleTool;
import net.sf.anathema.lib.gui.widgets.IIntegerSpinner;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueModel;
import net.sf.anathema.lib.workflow.intvalue.IIntValueModel;
import net.sf.anathema.lib.workflow.intvalue.IntValuePresentation;

public class ArmourStatisticsPresenter {
  private final EquipmentStatsView view;
  private final ArmourStatisticsProperties properties;
  private final IArmourStatisticsModel armourModel;


  public ArmourStatisticsPresenter(IArmourStatisticsModel armourModel, EquipmentStatsView view, Resources resources) {
    this.view = view;
    this.properties = new ArmourStatisticsProperties(resources);
    this.armourModel = armourModel;
  }

  public void initPresentation() {
    addSpinner(properties.getBashingSoakLabel(), armourModel.getBashingSoakModel());
    addSpinner(properties.getBashingHardnessLabel(), armourModel.getBashingHardnessModel());

    IIntValueModel lethalSoakModel = armourModel.getLethalSoakModel();
    addSpinner(properties.getLethalSoakLabel(), lethalSoakModel);
    addSpinner(properties.getLethalHardnessLabel(), armourModel.getLethalHardnessModel());

    final IIntValueModel aggravatedSoakModel = armourModel.getSoakModel(HealthType.Aggravated);
    IIntegerSpinner aggravatedSoakSpinner = addSpinner(properties.getAggravatedSoakLabel(), aggravatedSoakModel);
    ToggleTool tool = view.addToggleTool(properties.getLinkSoakLabel());
    tool.setIcon(new CharacterUI().getLinkIconPath());

    addSpinner(properties.getMobilityPenaltyLabel(), armourModel.getMobilityPenaltyModel());
    addSpinner(properties.getFatigueLabel(), armourModel.getFatigueModel());

    tool.setCommand(() -> {
      BooleanValueModel soakLinkModel = armourModel.getSoakLinkModel();
      soakLinkModel.setValue(!soakLinkModel.getValue());
    });
    armourModel.getSoakLinkModel().addChangeListener(isLinkToggled -> {
      if (isLinkToggled) {
        aggravatedSoakModel.setValue(lethalSoakModel.getValue());
      }
      aggravatedSoakSpinner.setEnabled(!isLinkToggled);
      setSelectionBasedOnLinkState(tool);
    });
    boolean linked = lethalSoakModel.getValue() == aggravatedSoakModel.getValue();
    setSelectionBasedOnLinkState(tool);
    aggravatedSoakSpinner.setEnabled(!linked);
    lethalSoakModel.addIntValueChangeListener(newValue -> {
      if (armourModel.getSoakLinkModel().getValue()) {
        aggravatedSoakModel.setValue(newValue);
      }
    });
  }

  private void setSelectionBasedOnLinkState(ToggleTool tool) {
    if (armourModel.getSoakLinkModel().getValue()) {
      tool.select();
    } else {
      tool.deselect();
    }
  }

  private IIntegerSpinner addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label, model.getValue());
    new IntValuePresentation().initPresentation(spinner, model);
    return spinner;
  }
}