package net.sf.anathema.character.infernal.reporting.content;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.infernal.urge.InfernalUrgeTemplate;
import net.sf.anathema.character.infernal.urge.model.IInfernalUrgeModel;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.IResources;

import static java.text.MessageFormat.format;

public class InfernalUrgeContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public InfernalUrgeContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "InfernalUrge.Title"; //$NON-NLS-1$
  }

  public int getLimitValue() {
    return getUrgeModel().getVirtueFlaw().getLimitTrait().getCurrentValue();
  }

  public String getUrgeDescription() {
    return getUrgeModel().getDescription().getText();
  }

  private IInfernalUrgeModel getUrgeModel() {
    return ((IInfernalUrgeModel) character.getAdditionalModel(InfernalUrgeTemplate.ID));
  }

  public String getUrgeTitle() {
    return format("{0}: ", getString("InfernalUrge.Title"));
  }
}
