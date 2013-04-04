package net.sf.anathema.character.db.reporting.content;

import net.sf.anathema.character.db.virtueflaw.DbVirtueFlawTemplate;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.library.virtueflaw.model.IVirtueFlaw;
import net.sf.anathema.character.library.virtueflaw.presenter.IVirtueFlawModel;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.lib.resources.Resources;

public class DbGreatCurseContent extends AbstractSubBoxContent {

  private IGenericCharacter character;

  public DbGreatCurseContent(Resources resources, IGenericCharacter character) {
    super(resources);
    this.character = character;
  }

  public int getLimitValue() {
    return getVirtueFlaw().getLimitTrait().getCurrentValue();
  }

  public String getGreatCurseMessage() {
    String virtue = getVirtueString();
    String aspect = getAspectString();
    return getString("Sheet.GreatCurse.Message.SecondEdition", virtue, aspect);
  }

  private String getVirtueString() {
    if (isRootSelected()) {
      ITraitType rootVirtue = getVirtueFlaw().getRoot();
      return getString(rootVirtue.getId());
    }
    return getString("Sheet.GreatCurse.UnknownVirtue");
  }

  private String getAspectString() {
    String casteTypeString = character.getCasteType().getId();
    if (casteTypeString != null) {
      String casteType = getString("Caste." + casteTypeString);
      return getString("Sheet.GreatCurse.AspectMessage", casteType);
    }
    if (isRootSelected()) {
      return getString("Sheet.GreatCurse.UnknownAspectKnownVirtue");
    }
    return getString("Sheet.GreatCurse.UnknownAspectUnknownVirtue");
  }

  private boolean isRootSelected() {
    return getRootVirtue() != null;
  }

  private ITraitType getRootVirtue() {
    return getVirtueFlaw().getRoot();
  }

  private IVirtueFlaw getVirtueFlaw() {
    IVirtueFlawModel model = (IVirtueFlawModel) character.getAdditionalModel(DbVirtueFlawTemplate.TEMPLATE_ID);
    return model.getVirtueFlaw();
  }

  @Override
  public String getHeaderKey() {
    return "GreatCurse.Dragon-Blooded";
  }
}
