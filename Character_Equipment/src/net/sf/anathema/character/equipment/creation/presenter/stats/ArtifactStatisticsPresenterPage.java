package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;

import javax.swing.JCheckBox;

import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArtifactStatisticsProperties;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.workflow.booleanvalue.BooleanValuePresentation;

public class ArtifactStatisticsPresenterPage extends
    AbstractEquipmentStatisticsPresenterPage<IArtifactStatisticsModel, ArtifactStatisticsProperties> {

  public ArtifactStatisticsPresenterPage(
      IResources resources,
      IEquipmentStatisticsCreationModel model,
      IEquipmentStatisticsCreationViewFactory viewFactory) {
    super(resources, new ArtifactStatisticsProperties(resources), model, model.getArtifactStatisticsModel(), viewFactory);
  }

  @Override
  protected void addAdditionalContent() {
    addLabelledComponentRow(new String[] {
        getProperties().getAttuneCostLabel()}, new Component[] {
        initIntegerSpinner(getPageModel().getAttuneCostModel()).getComponent() });
    final JCheckBox foreignAttuneBox = new JCheckBox();
    final JCheckBox requireAttuneBox = new JCheckBox();  
    BooleanValuePresentation booleanValuePresentation = new BooleanValuePresentation();
    
    foreignAttuneBox.setSelected(getPageModel().getForeignAttunementModel().getValue());
    requireAttuneBox.setSelected(getPageModel().getRequireAttunementModel().getValue());

    booleanValuePresentation.initPresentation(foreignAttuneBox, getPageModel().getForeignAttunementModel());
    booleanValuePresentation.initPresentation(requireAttuneBox, getPageModel().getRequireAttunementModel());
    
    addLabelledComponentRow(new String[] {
            getProperties().getForeignAttuneLabel() }, new Component[] {
            foreignAttuneBox });
    addLabelledComponentRow(new String[] {
            getProperties().getRequireAttuneLabel() }, new Component[] {
            requireAttuneBox });
  }

  @Override
  protected boolean isTagsSupported() {
    return false;
  }
}