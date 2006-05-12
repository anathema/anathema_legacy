package net.sf.anathema.development.character.reporting;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.development.character.reporting.page.PdfPageConfiguration;

import com.lowagie.text.pdf.PdfContentByte;

public class PdfFirstPageEncoder {

  private final PdfPageConfiguration pageConfiguration = new PdfPageConfiguration();
  private final PdfBoxEncoder boxEncoder;
  private final IPdfPartEncoder partEncoder;

  public PdfFirstPageEncoder(IPdfPartEncoder partEncoder) {
    this.partEncoder = partEncoder;
    this.boxEncoder = new PdfBoxEncoder(partEncoder.getBaseFont());
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character) {
    boxEncoder.encodeBox(directContent, pageConfiguration.getFirstColumnRectangle(0, 51, 2), "Personal Information");
    SmartRectangle attributesContentBounds = boxEncoder.encodeBox(
        directContent,
        pageConfiguration.getFirstColumnRectangle(61, 128, 1),
        "Attributes");
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    partEncoder.encodeAttributes(directContent, attributesContentBounds, attributeGroups, character);
    boxEncoder.encodeBox(directContent, pageConfiguration.getFirstColumnRectangle(199, 556, 1), "Abilities");
    boxEncoder.encodeBox(directContent, pageConfiguration.getThirdColumnRectangle(0, 51), "Essence");
    partEncoder.encodeEditionSpecificFirstPagePart(
        directContent,
        pageConfiguration.getSecondColumnRectangle(61, 694, 2));
  }
}