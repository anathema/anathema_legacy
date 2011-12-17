package net.sf.anathema.character.lunar.reporting.sheet;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.common.encoder.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.common.encoder.PdfTraitEncoder;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class FirstEditionLunarBeastformAttributesEncoder implements IPdfContentBoxEncoder {

  private final static int PHYSICAL_MAX = 30;
  private final static int STANDARD_MAX = 10;
  private final IResources resources;
  private final PdfTraitEncoder smallTraitEncoder;
  private final float smallWidth;

  public FirstEditionLunarBeastformAttributesEncoder(BaseFont baseFont, IResources resources, float smallWidth) {
    this.resources = resources;
    this.smallWidth = smallWidth;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Attributes"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) {
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    IBeastformModel additionalModel = (IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
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
    int maximum = 0;
    float width = 0;
    for (IGroupedTraitType groupedTraitType : attributeGroups) {
      if (!groupedTraitType.getGroupId().equals(groupId)) {
        groupId = groupedTraitType.getGroupId();
        y -= groupSpacing;
        if (groupId.equals(AttributeGroupType.Physical.name())) {
          maximum = PHYSICAL_MAX;
          width = contentBounds.width - 3;
        }
        else {
          maximum = STANDARD_MAX;
          width = smallWidth;
        }
      }
      ITraitType traitType = groupedTraitType.getTraitType();
      String traitLabel = resources.getString("AttributeType.Name." + traitType.getId()); //$NON-NLS-1$
      int value = traitCollection.getTrait(traitType).getCurrentValue();
      Position position = new Position(contentBounds.x, y);
      boolean favored = traitCollection.isFavoredOrCasteTrait(traitType);
      y -= smallTraitEncoder.encodeWithTextAndRectangle(
          directContent,
          traitLabel,
          position,
          width,
          value,
          favored,
          maximum);
    }
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
