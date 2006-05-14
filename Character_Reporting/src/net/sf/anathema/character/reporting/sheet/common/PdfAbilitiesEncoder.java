package net.sf.anathema.character.reporting.sheet.common;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.disy.commons.core.geometry.SmartRectangle;
import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.INamedGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfAbilitiesEncoder extends AbstractPdfEncoder {

  private final Collection<ITraitType> markedAbilities = new ArrayList<ITraitType>() {
    {
      add(AbilityType.Athletics);
      add(AbilityType.Dodge);
      add(AbilityType.Larceny);
      add(AbilityType.Ride);
      add(AbilityType.Stealth);
    }
  };
  private final BaseFont baseFont;
  private final IResources resources;
  private final PdfTraitEncoder traitEncoder;
  private final int essenceMax;

  public PdfAbilitiesEncoder(BaseFont baseFont, IResources resources, int essenceMax) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public void encodeAbilities(PdfContentByte directContent, IGenericCharacter character, SmartRectangle contentBounds) {
    Point position = new Point((int) contentBounds.getMinX(), (int) contentBounds.getMaxY());
    int width = contentBounds.width;
    IIdentifiedTraitTypeGroup[] groups = character.getAbilityTypeGroups();
    int yPosition = position.y;
    for (IIdentifiedTraitTypeGroup group : groups) {
      Point groupPosition = new Point(position.x, yPosition);
      yPosition -= encodeAbilityGroup(directContent, character, group, groupPosition, width);
      yPosition -= IVoidStateFormatConstants.TEXT_PADDING;
    }
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
    yPosition -= encodeCrafts(directContent, character, new Point(position.x, yPosition), width);
    yPosition -= IVoidStateFormatConstants.LINE_HEIGHT;
    yPosition -= encodeSpecialties(directContent, character, new Point(position.x, yPosition), width);
    encodeMobilityPenaltyText(directContent, position, yPosition + 4);
  }

  private int encodeCrafts(PdfContentByte directContent,  IGenericCharacter character, Point position, int width) {
    String title = "Crafts";
    INamedGenericTrait[] traits = character.getSubTraits(AbilityType.Craft);
    return drawNamedTraitSection(directContent, title, traits, position, width, essenceMax);
  }

  private int encodeSpecialties(PdfContentByte directContent, IGenericCharacter character, Point position, int width) {
    String title = "Specialties";
    List<INamedGenericTrait> allTraits = new ArrayList<INamedGenericTrait>();
    for (IIdentifiedTraitTypeGroup group : character.getAbilityTypeGroups()) {
      for (ITraitType traitType : group.getAllGroupTypes()) {
        allTraits.addAll(Arrays.asList(character.getSpecialties(traitType)));
      }
    }
    INamedGenericTrait[] specialties = allTraits.toArray(new INamedGenericTrait[allTraits.size()]);
    return drawNamedTraitSection(directContent, title, specialties, position, width, 3);
  }

  private int drawNamedTraitSection(
      PdfContentByte directContent,
      String title,
      INamedGenericTrait[] traits,
      Point position,
      int width,
      int dotCount) {
    int height = drawSubsectionHeader(directContent, title, position, width);
    for (INamedGenericTrait trait : traits) {
      Point traitPosition = new Point(position.x, position.y - height);
      int value = trait.getCurrentValue();
      String name = trait.getName();
      traitEncoder.encodeWithText(directContent, name, traitPosition, width, value, dotCount);
      height += traitEncoder.getTraitHeight();
    }
    for (int index = traits.length; index < 9; index++) {
      Point traitPosition = new Point(position.x, position.y - height);
      traitEncoder.encodeWithLine(directContent, traitPosition, width, 0, dotCount);
      height += traitEncoder.getTraitHeight();
    }
    return height;
  }

  private int encodeMobilityPenaltyText(PdfContentByte directContent, Point position, int yPosition) {
    encodeCrossMarker(directContent, new Point(position.x, yPosition));
    String mobilityPenaltyText = " : " + resources.getString("Sheet.Comment.AbilityMobility"); //$NON-NLS-1$ //$NON-NLS-2$
    Point commentPosition = new Point(position.x + 5, yPosition);
    drawComment(directContent, mobilityPenaltyText, commentPosition, PdfContentByte.ALIGN_LEFT);
    return 10;
  }

  private int encodeAbilityGroup(
      PdfContentByte directContent,
      IGenericTraitCollection traitCollection,
      IIdentifiedTraitTypeGroup group,
      Point position,
      int width) {
    int height = 0;
    int groupLabelWidth = IVoidStateFormatConstants.LINE_HEIGHT + IVoidStateFormatConstants.TEXT_PADDING;
    int traitX = position.x + groupLabelWidth;
    ITraitType[] traitTypes = group.getAllGroupTypes();
    int groupLabelX = position.x + 4;
    int markerX = groupLabelX + IVoidStateFormatConstants.TEXT_PADDING;
    for (int index = 0; index < traitTypes.length; index++) {
      ITraitType traitType = traitTypes[index];
      int yPosition = position.y - (index + 1) * traitEncoder.getTraitHeight();
      if (markedAbilities.contains(traitType)) {
        encodeCrossMarker(directContent, new Point(markerX, yPosition + 1));
      }
      IFavorableGenericTrait trait = traitCollection.getFavorableTrait(traitType);
      String label = resources.getString(traitType.getId());
      height += encodeFavorableTrait(directContent, label, trait, new Point(traitX, yPosition), width - groupLabelWidth);
    }
    Point groupLabelPosition = new Point(groupLabelX, position.y - height / 2);
    addGroupLabel(directContent, group, groupLabelPosition);
    return height;
  }

  private int encodeFavorableTrait(
      PdfContentByte directContent,
      String label,
      IFavorableGenericTrait trait,
      Point position,
      int width) {
    int value = trait.getCurrentValue();
    boolean favored = trait.isCasteOrFavored();
    return traitEncoder.encodeWithTextAndRectangle(directContent, label, position, width, value, favored, essenceMax);
  }

  private void encodeCrossMarker(PdfContentByte directContent, Point markerPosition) {
    directContent.setLineWidth(1.0f);
    directContent.moveTo(markerPosition.x, markerPosition.y + 2);
    directContent.lineTo(markerPosition.x + 4, markerPosition.y + 2);
    directContent.moveTo(markerPosition.x + 2, markerPosition.y);
    directContent.lineTo(markerPosition.x + 2, markerPosition.y + 4);
    directContent.stroke();
  }

  private void addGroupLabel(PdfContentByte directContent, IIdentifiedTraitTypeGroup group, Point position) {
    String groupId = group.getGroupId().getId();
    String resourceKey = group.getGroupId() instanceof ICasteType ? "Caste." + groupId : "AbilityGroup." + groupId; //$NON-NLS-1$//$NON-NLS-2$
    String groupLabel = resources.getString(resourceKey);
    drawVerticalText(directContent, groupLabel, position, PdfContentByte.ALIGN_CENTER);
  }
}