package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfAbilitiesEncoder extends FavorableTraitEncoder {

  private final CraftEncoder craftEncoder;
  private final SpecialtiesEncoder specialtiesEncoder;

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
    this.craftEncoder = new CraftEncoder(resources, baseFont, getTraitEncoder(), essenceMax);
    this.specialtiesEncoder = new SpecialtiesEncoder(resources, baseFont, getTraitEncoder());
  }

  public String getHeaderKey() {
    return "Abilities"; //$NON-NLS-1$
  }

  @Override
  protected String getGroupNamePrefix() {
    return "AbilityGroup."; //$NON-NLS-1$
  }

  @Override
  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Position position = new Position(bounds.getMinX(), bounds.getMaxY());
    float width = bounds.width;
    float yPosition = encodeTraitGroups(directContent, character, position, width);
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
    yPosition -= craftEncoder.encode(directContent, character, new Position(position.x, yPosition), width);
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
    yPosition -= specialtiesEncoder.encode(directContent, character, new Position(position.x, yPosition), width);
    encodeMobilityPenaltyText(directContent, position, bounds.getMinY() + 4);
  }

  @Override
  protected IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups(IGenericCharacter character) {
    return character.getAbilityTypeGroups();
  }

  private float encodeMobilityPenaltyText(PdfContentByte directContent, Position position, float yPosition) {
    encodeCrossMarker(directContent, new Position(position.x, yPosition));
    String mobilityPenaltyText = " : " + getResources().getString("Sheet.Comment.AbilityMobility"); //$NON-NLS-1$ //$NON-NLS-2$
    Position commentPosition = new Position(position.x + 5, yPosition);
    drawComment(directContent, mobilityPenaltyText, commentPosition, PdfContentByte.ALIGN_LEFT);
    return 10;
  }
}