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
    boxEncoder.encodeBox(directContent, pageConfiguration.getFirstColumnRectangle(199, 393, 1), "Attributes");
    boxEncoder.encodeBox(directContent, pageConfiguration.getFirstColumnRectangle(602, 153, 1), "Combat");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(61, 282, 1), "Middle Column");
    boxEncoder.encodeBox(directContent, pageConfiguration.getThirdColumnRectangle(0, 343), "Essence");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(353, 170, 2), "Weaponry");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(353, 170, 2), "Weaponry");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(533, 51, 2), "Armour");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(594, 119, 2), "Health");
    boxEncoder.encodeBox(directContent, pageConfiguration.getSecondColumnRectangle(721, 34, 2), "Combat Sequence");
  }
}