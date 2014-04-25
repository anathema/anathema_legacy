package net.sf.anathema.character.equipment.creation.presenter;

import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArtifactStatisticsProperties;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValuePresentation;

import javax.swing.JCheckBox;
import java.awt.Component;

public class ArtifactStatisticsPresenter {
  private final IArtifactStatisticsModel artifactModel;
  private final EquipmentStatsView view;
  private final ArtifactStatisticsProperties properties;

  public ArtifactStatisticsPresenter(IArtifactStatisticsModel artifactModel, EquipmentStatsView view, ArtifactStatisticsProperties properties) {
    this.artifactModel = artifactModel;
    this.view = view;
    this.properties = properties;
  }

  public void initPresentation() {
    view.addLabelledComponentRow(new String[]{
            properties.getAttuneCostLabel()}, new Component[]{
            view.initIntegerSpinner(artifactModel.getAttuneCostModel()).getComponent()});
    JCheckBox foreignAttuneBox = new JCheckBox();
    JCheckBox requireAttuneBox = new JCheckBox();
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();

    foreignAttuneBox.setSelected(artifactModel.getForeignAttunementModel().getValue());
    requireAttuneBox.setSelected(artifactModel.getRequireAttunementModel().getValue());

    booleanValuePresentation.initPresentation(foreignAttuneBox, artifactModel.getForeignAttunementModel());
    booleanValuePresentation.initPresentation(requireAttuneBox, artifactModel.getRequireAttunementModel());

    view.addLabelledComponentRow(new String[]{
            properties.getForeignAttuneLabel()}, new Component[]{
            foreignAttuneBox});
    view.addLabelledComponentRow(new String[]{
            properties.getRequireAttuneLabel()}, new Component[]{
            requireAttuneBox});
  }
}
