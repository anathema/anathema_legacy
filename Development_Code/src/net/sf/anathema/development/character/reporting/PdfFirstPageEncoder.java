package net.sf.anathema.development.character.reporting;

import static net.sf.anathema.development.reporting.encoder.voidstate.format.IVoidStateFormatConstants.PADDING;
import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.development.character.reporting.page.PdfPageConfiguration;

import com.lowagie.text.pdf.PdfContentByte;

public class PdfFirstPageEncoder {

  private final PdfPageConfiguration pageConfiguration = new PdfPageConfiguration();
  private final PdfBoxEncoder boxEncoder;
  private final IPdfPartEncoder partEncoder;
  private final int overallContentHeight = 755;

  public PdfFirstPageEncoder(IPdfPartEncoder partEncoder) {
    this.partEncoder = partEncoder;
    this.boxEncoder = new PdfBoxEncoder(partEncoder.getBaseFont());
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character) {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, character, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, character, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    encodeFirstColumn(directContent, character, distanceFromTop);
    SmartRectangle editionBounds = pageConfiguration.getSecondColumnRectangle(distanceFromTop, 694, 2);
    partEncoder.encodeEditionSpecificFirstPagePart(directContent, editionBounds);
  }

  private void encodeEssence(
      PdfContentByte directContent,
      IGenericCharacter character,
      int distanceFromTop,
      final int firstRowHeight) {
    SmartRectangle essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, firstRowHeight);
    boxEncoder.encodeBox(directContent, essenceBounds, "Essence");
  }

  private void encodePersonalInfo(
      PdfContentByte directContent,
      IGenericCharacter character,
      int distanceFromTop,
      final int firstRowHeight) {
    SmartRectangle personalInfoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    boxEncoder.encodeBox(directContent, personalInfoBounds, "Personal Information");
  }

  private void encodeFirstColumn(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop) {
    int attributeHeight = encodeAttributes(directContent, character, distanceFromTop);
    distanceFromTop += attributeHeight + PADDING;
    encodeAbilities(directContent, character, distanceFromTop);
  }

  private void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop) {
    int abilitiesHeight = overallContentHeight - distanceFromTop;
    SmartRectangle abilityBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    boxEncoder.encodeBox(directContent, abilityBounds, "Abilities");
  }

  private int encodeAttributes(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop) {
    int attributeHeight = 128;
    SmartRectangle attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    SmartRectangle attributesContentBounds = boxEncoder.encodeBox(directContent, attributeBounds, "Attributes");
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    partEncoder.encodeAttributes(directContent, attributesContentBounds, attributeGroups, character);
    return attributeHeight;
  }
}