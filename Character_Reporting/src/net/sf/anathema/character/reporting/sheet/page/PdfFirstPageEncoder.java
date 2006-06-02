package net.sf.anathema.character.reporting.sheet.page;

import static net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants.PADDING;
import net.disy.commons.core.util.StringUtilities;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.reporting.sheet.pageformat.PdfPageConfiguration;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfFirstPageEncoder implements IPdfPageEncoder {

  public static final int CONTENT_HEIGHT = 755;
  private final PdfPageConfiguration pageConfiguration;
  private final PdfBoxEncoder boxEncoder;
  private final IPdfPartEncoder partEncoder;

  public PdfFirstPageEncoder(IPdfPartEncoder partEncoder, PdfPageConfiguration pageConfiguration) {
    this.partEncoder = partEncoder;
    this.boxEncoder = new PdfBoxEncoder(partEncoder.getResources(), partEncoder.getBaseFont());
    this.pageConfiguration = pageConfiguration;
  }

  public void encode(
      Document document,
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description) throws DocumentException {
    int distanceFromTop = 0;
    final int firstRowHeight = 51;
    encodePersonalInfo(directContent, character, description, distanceFromTop, firstRowHeight);
    encodeEssence(directContent, character, distanceFromTop, firstRowHeight);

    distanceFromTop += firstRowHeight + PADDING;

    encodeFirstColumn(directContent, character, distanceFromTop);
    partEncoder.encodeEditionSpecificFirstPagePart(directContent, character, distanceFromTop);
  }

  private void encodeEssence(
      PdfContentByte directContent,
      IGenericCharacter character,
      float distanceFromTop,
      final float firstRowHeight) throws DocumentException {
    Bounds essenceBounds = pageConfiguration.getThirdColumnRectangle(distanceFromTop, firstRowHeight);
    String title = getHeaderLabel("Essence"); //$NON-NLS-1$
    Bounds essenceContentBounds = boxEncoder.encodeBox(directContent, essenceBounds, title);
    partEncoder.encodeEssence(directContent, character, essenceContentBounds);
  }

  private String getHeaderLabel(String key) {
    return partEncoder.getResources().getString("Sheet.Header." + key); //$NON-NLS-1$
  }

  private void encodePersonalInfo(
      PdfContentByte directContent,
      IGenericCharacter character,
      IGenericDescription description,
      int distanceFromTop,
      final int firstRowHeight) throws DocumentException {
    Bounds infoBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, firstRowHeight, 2);
    String name = description.getName();
    String title = StringUtilities.isNullOrTrimEmpty(name) ? getHeaderLabel("PersonalInfo") : name; //$NON-NLS-1$
    Bounds infoContentBounds = boxEncoder.encodeBox(directContent, infoBounds, title);
    partEncoder.encodePersonalInfos(directContent, character, description, infoContentBounds);
  }

  private void encodeFirstColumn(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int attributeHeight = encodeAttributes(directContent, character, distanceFromTop);
    distanceFromTop += attributeHeight + PADDING;
    encodeAbilities(directContent, character, distanceFromTop);
  }

  private void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int abilitiesHeight = CONTENT_HEIGHT - distanceFromTop;
    Bounds boxBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, abilitiesHeight, 1);
    Bounds contentBounds = boxEncoder.encodeBox(directContent, boxBounds, getHeaderLabel("Abilities")); //$NON-NLS-1$
    partEncoder.encodeAbilities(directContent, character, contentBounds);
  }

  private int encodeAttributes(PdfContentByte directContent, IGenericCharacter character, int distanceFromTop)
      throws DocumentException {
    int attributeHeight = 128;
    Bounds attributeBounds = pageConfiguration.getFirstColumnRectangle(distanceFromTop, attributeHeight, 1);
    Bounds attributesContentBounds = boxEncoder.encodeBox(directContent, attributeBounds, getHeaderLabel("Attributes")); //$NON-NLS-1$
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    partEncoder.encodeAttributes(directContent, attributesContentBounds, attributeGroups, character);
    return attributeHeight;
  }
}