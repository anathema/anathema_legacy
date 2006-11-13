package net.sf.anathema.character.reporting.sheet.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.framework.configuration.AnathemaCharacterPreferences;
import net.sf.anathema.character.generic.framework.resources.TraitInternationalizer;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfAbilitiesEncoder extends FavorableTraitEncoder implements IPdfContentBoxEncoder {

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
  }

  public String getHeaderKey() {
    return "Abilities"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    encodeTraits(directContent, character, bounds);
  }

  private void encodeTraits(PdfContentByte directContent, IGenericCharacter character, Bounds contentBounds) {
    Position position = new Position(contentBounds.getMinX(), contentBounds.getMaxY());
    float width = contentBounds.width;
    IIdentifiedTraitTypeGroup[] groups = character.getAbilityTypeGroups();
    float yPosition = position.y;
    for (IIdentifiedTraitTypeGroup group : groups) {
      Position groupPosition = new Position(position.x, yPosition);
      yPosition -= encodeTraitGroup(directContent, character.getTraitCollection(), group, groupPosition, width);
      yPosition -= IVoidStateFormatConstants.TEXT_PADDING;
    }
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
    yPosition -= encodeCrafts(directContent, character, new Position(position.x, yPosition), width);
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
    yPosition -= encodeSpecialties(directContent, character, new Position(position.x, yPosition), width);
    encodeMobilityPenaltyText(directContent, position, yPosition + 4);
  }

  private int encodeCrafts(PdfContentByte directContent, IGenericCharacter character, Position position, float width) {
    String title = getResources().getString("Sheet.AbilitySubHeader.Crafts"); //$NON-NLS-1$
    INamedGenericTrait[] traits = character.getSubTraits(AbilityType.Craft);
    if (!AnathemaCharacterPreferences.getDefaultPreferences().printZeroCrafts()) {
      List<INamedGenericTrait> nonZeroCrafts = new ArrayList<INamedGenericTrait>();
      for (INamedGenericTrait trait : traits) {
        if (trait.getCurrentValue() != 0) {
          nonZeroCrafts.add(trait);
        }
      }
      traits = nonZeroCrafts.toArray(new INamedGenericTrait[nonZeroCrafts.size()]);
    }
    IValuedTraitReference[] crafts = getTraitReferences(traits, AbilityType.Craft);
    return drawNamedTraitSection(directContent, title, crafts, position, width, getEssenceMax());
  }

  private IValuedTraitReference[] getTraitReferences(INamedGenericTrait[] traits, ITraitType type) {
    List<IValuedTraitReference> references = new ArrayList<IValuedTraitReference>();
    for (INamedGenericTrait trait : traits) {
      references.add(new NamedGenericTraitReference(trait, type));
    }
    return references.toArray(new IValuedTraitReference[references.size()]);
  }

  private int encodeSpecialties(
      PdfContentByte directContent,
      IGenericCharacter character,
      Position position,
      float width) {
    String title = getResources().getString("Sheet.AbilitySubHeader.Specialties"); //$NON-NLS-1$
    List<IValuedTraitReference> references = new ArrayList<IValuedTraitReference>();
    for (IIdentifiedTraitTypeGroup group : character.getAbilityTypeGroups()) {
      for (ITraitType traitType : group.getAllGroupTypes()) {
        Collections.addAll(references, getTraitReferences(character.getSpecialties(traitType), traitType));
      }
    }
    IValuedTraitReference[] specialties = references.toArray(new IValuedTraitReference[references.size()]);
    return drawNamedTraitSection(directContent, title, specialties, position, width, 3);
  }

  private int drawNamedTraitSection(
      PdfContentByte directContent,
      String title,
      IValuedTraitReference[] traits,
      Position position,
      float width,
      int dotCount) {
    int height = drawSubsectionHeader(directContent, title, position, width);
    TraitInternationalizer internationalizer = new TraitInternationalizer(getResources());
    for (IValuedTraitReference trait : traits) {
      String name = internationalizer.getSheetName(trait);
      Position traitPosition = new Position(position.x, position.y - height);
      int value = trait.getValue();
      getTraitEncoder().encodeWithText(directContent, name, traitPosition, width, value, dotCount);
      height += getTraitEncoder().getTraitHeight();
    }
    for (int index = traits.length; index < 9; index++) {
      Position traitPosition = new Position(position.x, position.y - height);
      getTraitEncoder().encodeWithLine(directContent, traitPosition, width, 0, dotCount);
      height += getTraitEncoder().getTraitHeight();
    }
    return height;
  }

  private float encodeMobilityPenaltyText(PdfContentByte directContent, Position position, float yPosition) {
    encodeCrossMarker(directContent, new Position(position.x, yPosition));
    String mobilityPenaltyText = " : " + getResources().getString("Sheet.Comment.AbilityMobility"); //$NON-NLS-1$ //$NON-NLS-2$
    Position commentPosition = new Position(position.x + 5, yPosition);
    drawComment(directContent, mobilityPenaltyText, commentPosition, PdfContentByte.ALIGN_LEFT);
    return 10;
  }

  private void encodeCrossMarker(PdfContentByte directContent, Position markerPosition) {
    directContent.setLineWidth(1.0f);
    directContent.moveTo(markerPosition.x, markerPosition.y + 2);
    directContent.lineTo(markerPosition.x + 4, markerPosition.y + 2);
    directContent.moveTo(markerPosition.x + 2, markerPosition.y);
    directContent.lineTo(markerPosition.x + 2, markerPosition.y + 4);
    directContent.stroke();
  }
}