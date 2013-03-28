package net.sf.anathema.character.solar.reporting.content;

import com.google.common.base.Strings;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.library.virtueflaw.model.IDescriptiveVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IDescriptiveVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.character.solar.virtueflaw.SolarVirtueFlawTemplate;
import net.sf.anathema.lib.lang.StringUtilities;
import net.sf.anathema.lib.resources.IResources;

public class VirtueFlawContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  protected VirtueFlawContent(IResources resources, IGenericCharacter character) {
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
    return !StringUtilities.isNullOrTrimmedEmpty(getVirtueFlawName());
  }

  public boolean isConditionDefined() {
    return !Strings.isNullOrEmpty(getLimitBreakCondition());
  }

  private IDescriptiveVirtueFlaw getVirtueFlawModel() {
    return ((IDescriptiveVirtueFlawModel) character.getAdditionalModel(SolarVirtueFlawTemplate.ID)).getVirtueFlaw();
  }
}
