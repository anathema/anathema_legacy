package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArtifactStatisticsProperties;
import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.fx.hero.configurableview.IIntegerSpinner;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValueView;

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
    addSpinner(properties.getAttuneCostLabel(), artifactModel.getAttuneCostModel());

    BooleanValueView foreignAttuneBox = view.addBooleanSelector(properties.getForeignAttuneLabel());
    BooleanValueView requireAttuneBox = view.addBooleanSelector(properties.getRequireAttuneLabel());
    
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();

    foreignAttuneBox.setSelected(artifactModel.getForeignAttunementModel().getValue());
    requireAttuneBox.setSelected(artifactModel.getRequireAttunementModel().getValue());

    booleanValuePresentation.initPresentation(foreignAttuneBox, artifactModel.getForeignAttunementModel());
    booleanValuePresentation.initPresentation(requireAttuneBox, artifactModel.getRequireAttunementModel());
  }

  private void addSpinner(String label, IIntValueModel model) {
    IIntegerSpinner spinner = view.addIntegerSpinner(label, model.getValue());
    new IntValuePresentation().initPresentation(spinner, model);
  }
}
