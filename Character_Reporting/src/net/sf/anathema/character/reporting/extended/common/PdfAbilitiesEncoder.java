package net.sf.anathema.character.reporting.extended.common;

import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.reporting.extended.util.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

public class PdfAbilitiesEncoder extends FavorableTraitEncoder {

  public static PdfAbilitiesEncoder createWithCraftsOnly(
      BaseFont baseFont,
      IResources resources,
      int essenceMax,
      int craftCount) {
    PdfAbilitiesEncoder pdfAbilitiesEncoder = new PdfAbilitiesEncoder(baseFont, resources, essenceMax);
    PdfTraitEncoder traitEncoder = pdfAbilitiesEncoder.getTraitEncoder();
    pdfAbilitiesEncoder.addNamedTraitEncoder(new CraftEncoder(resources, baseFont, traitEncoder, essenceMax, craftCount));
    return pdfAbilitiesEncoder;
  }

  public static PdfAbilitiesEncoder createWithSpecialtiesOnly(
      BaseFont baseFont,
      IResources resources,
      int essenceMax,
      int specialtyCount) {
    PdfAbilitiesEncoder pdfAbilitiesEncoder = new PdfAbilitiesEncoder(baseFont, resources, essenceMax);
    PdfTraitEncoder traitEncoder = pdfAbilitiesEncoder.getTraitEncoder();
    pdfAbilitiesEncoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, baseFont, traitEncoder, specialtyCount));
    return pdfAbilitiesEncoder;
  }

  public static PdfAbilitiesEncoder createWithCraftsAndSpecialties(
      BaseFont baseFont,
      IResources resources,
      int essenceMax,
      int craftCount,
      int specialtyCount) {
    PdfAbilitiesEncoder pdfAbilitiesEncoder = new PdfAbilitiesEncoder(baseFont, resources, essenceMax);
    PdfTraitEncoder traitEncoder = pdfAbilitiesEncoder.getTraitEncoder();
    pdfAbilitiesEncoder.addNamedTraitEncoder(new CraftEncoder(resources, baseFont, traitEncoder, essenceMax, craftCount));
    pdfAbilitiesEncoder.addNamedTraitEncoder(new SpecialtiesEncoder(resources, baseFont, traitEncoder, specialtyCount));
    return pdfAbilitiesEncoder;
  }

  private PdfAbilitiesEncoder(BaseFont baseFont, IResources resources, int essenceMax) {
    super(
        baseFont,
        resources,
        essenceMax,
        AbilityType.Athletics,
        AbilityType.Dodge,
        AbilityType.Larceny,
        AbilityType.Ride,
        AbilityType.Stealth);

  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
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

  @Override
  protected String getExcellencyCommentKey() {
    return "Sheet.Comment.AbilityExcellency"; //$NON-NLS-1$
  }
  
  @Override
  protected boolean shouldShowExcellencies(IGenericCharacter character) {
    return character.getTemplate().getMagicTemplate().getFavoringTraitType() == FavoringTraitType.AbilityType;
  }
}