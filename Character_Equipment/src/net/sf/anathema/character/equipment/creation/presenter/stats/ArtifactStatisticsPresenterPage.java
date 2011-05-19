package net.sf.anathema.character.equipment.creation.presenter.stats;

import java.awt.Component;

import net.sf.anathema.character.equipment.creation.model.stats.IArtifactStatisticsModel;
import net.sf.anathema.character.equipment.creation.model.stats.IEquipmentStatisticsCreationModel;
import net.sf.anathema.character.equipment.creation.presenter.stats.properties.ArtifactStatisticsProperties;
import net.sf.anathema.lib.resources.IResources;

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
  }

  @Override
  protected boolean isTagsSupported() {
    return false;
  }
}