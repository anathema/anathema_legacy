package net.sf.anathema.character.solar.reporting;

import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.character.solar.virtueflaw.model.ISolarVirtueFlaw;
import net.sf.anathema.character.solar.virtueflaw.presenter.ISolarVirtueFlawModel;
import net.sf.anathema.lib.resources.IResources;

public class SolarVirtueFlawContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  protected SolarVirtueFlawContent(IResources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  @Override
  public String getHeaderKey() {
    return "GreatCurse.Solar"; //$NON-NLS-1$
  }

  public String getVirtueFlawName() {
    return getVirtueFlawModel().getName().getText();
  }

  public int getLimitValue() {
    return getVirtueFlawModel().getLimitTrait().getCurrentValue();
  }

  public String getLimitBreakCondition() {
    return getVirtueFlawModel().getLimitBreak().getText();
  }

  public boolean isNameDefined() {
    return !StringUtilities.isNullOrTrimEmpty(getVirtueFlawName());
  }

  public boolean isConditionDefined() {
    return !StringUtilities.isNullOrEmpty(getLimitBreakCondition());
  }

  private ISolarVirtueFlaw getVirtueFlawModel() {
    return ((ISolarVirtueFlawModel) character.getAdditionalModel(SolarVirtueFlawTemplate.ID)).getVirtueFlaw();
  }
}
