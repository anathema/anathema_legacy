package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class PdfAttributesEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private PdfTraitEncoder smallTraitEncoder;
  private final int essenceMax;
  private final boolean encodeFavored;

  public PdfAttributesEncoder(BaseFont baseFont, IResources resources, int essenceMax, boolean encodeFavored) {
    this.resources = resources;
    this.essenceMax = essenceMax;
    this.encodeFavored = encodeFavored;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  public String getHeaderKey() {
    return "Attributes"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    IGenericTraitCollection traitCollection = character.getTraitCollection();
    encodeAttributes(directContent, bounds, attributeGroups, traitCollection);
  }

  public final void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection) {
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - groupSpacing;
    String groupId = null;
    for (IGroupedTraitType groupedTraitType : attributeGroups) {
      if (!groupedTraitType.getGroupId().equals(groupId)) {
        groupId = groupedTraitType.getGroupId();
        y -= groupSpacing;
      }
      ITraitType traitType = groupedTraitType.getTraitType();
      String traitLabel = resources.getString("AttributeType.Name." + traitType.getId()); //$NON-NLS-1$
      int value = traitCollection.getTrait(traitType).getCurrentValue();
      Position position = new Position(contentBounds.x, y);
      if (!encodeFavored) {
        y -= smallTraitEncoder.encodeWithText(
            directContent,
            traitLabel,
            position,
            contentBounds.width,
            value,
            essenceMax);
      }
      else {
        boolean favored = traitCollection.getFavorableTrait(traitType).isCasteOrFavored();
        y -= smallTraitEncoder.encodeWithTextAndRectangle(
            directContent,
            traitLabel,
            position,
            contentBounds.width,
            value,
            favored,
            essenceMax);
      }
    }
  }
}