package net.sf.anathema.character.reporting.sheet.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.sf.anathema.character.generic.caste.ICasteType;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.IFavorableGenericTrait;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.groups.IIdentifiedTraitTypeGroup;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public abstract class FavorableTraitEncoder extends AbstractPdfEncoder implements IPdfContentBoxEncoder {

  private final List<ITraitType> markedTraitTypes = new ArrayList<ITraitType>();
  private final PdfTraitEncoder traitEncoder;
  private final IResources resources;
  private final int essenceMax;
  private final BaseFont baseFont;

  public FavorableTraitEncoder(BaseFont baseFont, IResources resources, int essenceMax, ITraitType... markedTypes) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.traitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
    Collections.addAll(markedTraitTypes, markedTypes);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  protected IResources getResources() {
    return resources;
  }

  protected PdfTraitEncoder getTraitEncoder() {
    return traitEncoder;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    Position position = new Position(bounds.getMinX(), bounds.getMaxY());
    float width = bounds.width;
    encodeTraitGroups(directContent, character, position, width);
  }

  protected float encodeTraitGroups(
      PdfContentByte directContent,
      IGenericCharacter character,
      Position position,
      float width) {
    IIdentifiedTraitTypeGroup[] groups = getIdentifiedTraitTypeGroups(character);
    IGenericTraitCollection traitCollection = getTraitCollection(character);
    float yPosition = position.y;
    for (IIdentifiedTraitTypeGroup group : groups) {
      Position groupPosition = new Position(position.x, yPosition);
      yPosition -= encodeTraitGroup(directContent, traitCollection, group, groupPosition, width);
      yPosition -= IVoidStateFormatConstants.TEXT_PADDING;
    }
    return yPosition;
  }

  protected IGenericTraitCollection getTraitCollection(IGenericCharacter character) {
    return character.getTraitCollection();
  }

  protected abstract IIdentifiedTraitTypeGroup[] getIdentifiedTraitTypeGroups(IGenericCharacter character);

  private float encodeTraitGroup(
      PdfContentByte directContent,
      IGenericTraitCollection traitCollection,
      IIdentifiedTraitTypeGroup group,
      Position position,
      float width) {
    float height = 0;
    float groupLabelWidth = IVoidStateFormatConstants.LINE_HEIGHT + IVoidStateFormatConstants.TEXT_PADDING;
    float traitX = position.x + groupLabelWidth;
    ITraitType[] traitTypes = group.getAllGroupTypes();
    float groupLabelX = position.x + 4;
    float markerX = groupLabelX + IVoidStateFormatConstants.TEXT_PADDING;
    for (int index = 0; index < traitTypes.length; index++) {
      ITraitType traitType = traitTypes[index];
      float yPosition = position.y - (index + 1) * traitEncoder.getTraitHeight();
      if (markedTraitTypes.contains(traitType)) {
        encodeCrossMarker(directContent, new Position(markerX, yPosition + 1));
      }
      IFavorableGenericTrait trait = traitCollection.getFavorableTrait(traitType);
      String label = resources.getString(getTraitTypePrefix() + traitType.getId());
      height += encodeFavorableTrait(directContent, label, trait, new Position(traitX, yPosition), width
          - groupLabelWidth);
    }
    Position groupLabelPosition = new Position(groupLabelX, position.y - height / 2);
    addGroupLabel(directContent, group, groupLabelPosition);
    return height;
  }

  private void addGroupLabel(PdfContentByte directContent, IIdentifiedTraitTypeGroup group, Position position) {
    String groupId = group.getGroupId().getId();
    String resourceKey = group.getGroupId() instanceof ICasteType ? "Caste." + groupId : getGroupNamePrefix() + groupId; //$NON-NLS-1$
    String groupLabel = resources.getString(resourceKey);
    drawVerticalText(directContent, groupLabel, position, PdfContentByte.ALIGN_CENTER);
  }

  protected abstract String getGroupNamePrefix();

  protected String getTraitTypePrefix() {
    return ""; //$NON-NLS-1$
  }

  private int encodeFavorableTrait(
      PdfContentByte directContent,
      String label,
      IFavorableGenericTrait trait,
      Position position,
      float width) {
    int value = trait.getCurrentValue();
    boolean favored = trait.isCasteOrFavored();
    return traitEncoder.encodeWithTextAndRectangle(directContent, label, position, width, value, favored, essenceMax);
  }

  protected final void encodeCrossMarker(PdfContentByte directContent, Position markerPosition) {
    directContent.setLineWidth(1.0f);
    directContent.moveTo(markerPosition.x, markerPosition.y + 2);
    directContent.lineTo(markerPosition.x + 4, markerPosition.y + 2);
    directContent.moveTo(markerPosition.x + 2, markerPosition.y);
    directContent.lineTo(markerPosition.x + 2, markerPosition.y + 4);
    directContent.stroke();
  }
}