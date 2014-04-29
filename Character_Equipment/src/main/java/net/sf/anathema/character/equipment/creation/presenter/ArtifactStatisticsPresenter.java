package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArtifactStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValuePresentation;

import javax.swing.JCheckBox;

public class ArtifactStatisticsPresenter {
  private final IArtifactStatisticsModel artifactModel;
  private final EquipmentStatsView view;
  private final ArtifactStatisticsProperties properties;

  public ArtifactStatisticsPresenter(IArtifactStatisticsModel artifactModel, EquipmentStatsView view,
                                     Resources resources) {
    this.artifactModel = artifactModel;
    this.view = view;
    this.properties = new ArtifactStatisticsProperties(resources);
  }

  public void initPresentation() {
    view.addElement(properties.getAttuneCostLabel(),
            view.initIntegerSpinner(artifactModel.getAttuneCostModel()).getComponent());
    JCheckBox foreignAttuneBox = new JCheckBox();
    JCheckBox requireAttuneBox = new JCheckBox();
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();

    foreignAttuneBox.setSelected(artifactModel.getForeignAttunementModel().getValue());
    requireAttuneBox.setSelected(artifactModel.getRequireAttunementModel().getValue());

    booleanValuePresentation.initPresentation(foreignAttuneBox, artifactModel.getForeignAttunementModel());
    booleanValuePresentation.initPresentation(requireAttuneBox, artifactModel.getRequireAttunementModel());

    view.addElement(properties.getForeignAttuneLabel(), foreignAttuneBox);
    view.addElement(properties.getRequireAttuneLabel(), requireAttuneBox);
  }
}
