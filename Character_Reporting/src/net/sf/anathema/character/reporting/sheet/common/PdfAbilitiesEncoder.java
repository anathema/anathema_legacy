package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;

public class PdfAbilitiesEncoder extends FavorableTraitEncoder {

  public PdfAbilitiesEncoder(BaseFont baseFont, IResources resources, int essenceMax) {
    super(
        baseFont,
        resources,
        essenceMax,
        AbilityType.Athletics,
        AbilityType.Dodge,
        AbilityType.Larceny,
        AbilityType.Ride,
        AbilityType.Stealth);
    addNamedTraitEncoder(new CraftEncoder(resources, baseFont, getTraitEncoder(), essenceMax));
    addNamedTraitEncoder(new SpecialtiesEncoder(resources, baseFont, getTraitEncoder()));
  }

  public String getHeaderKey() {
    return "Abilities"; //$NON-NLS-1$
  }

  @Override
  protected String getGroupNamePrefix() {
    return "AbilityGroup."; //$NON-NLS-1$
  }

  @Override
  protected IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups(IGenericCharacter character) {
    return character.getAbilityTypeGroups();
  }

  @Override
  protected String getMarkerCommentKey() {
    return "Sheet.Comment.AbilityMobility"; //$NON-NLS-1$
  }
}