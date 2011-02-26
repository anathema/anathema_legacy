package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.presenter.IBeastformModel;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfBoxEncoder;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionLunarDBTFormEncoder implements IPdfContentBoxEncoder {

  private final static int PHYSICAL_MAX = 15;
  private final IResources resources;
  private final PdfTraitEncoder smallTraitEncoder;
  private final BaseFont baseFont;
  private final float smallWidth;

  public SecondEditionLunarDBTFormEncoder(BaseFont baseFont, IResources resources, float smallWidth) {
    this.resources = resources;
    this.smallWidth = smallWidth;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
    this.baseFont = baseFont;
  }

  public String getHeaderKey() {
    return "Deadly Beastman Transformation"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) {
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    IBeastformModel additionalModel = (IBeastformModel) character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getBeastTraitCollection();
    encodeAttributes(directContent, bounds, attributeGroups, traitCollection);
    encodeMutations(directContent, bounds, character);
  }
  
  private final void encodeMutations(PdfContentByte directContent,
		  Bounds bounds,
		  IGenericCharacter character)
  {
	  final int spacing = 15;
	  Bounds newBounds = new Bounds(
			  bounds.x + bounds.getWidth() * 1 / 2 + spacing,
			  bounds.y,
			  bounds.getWidth() * 1 / 2 - spacing,
			  bounds.height);
	  IPdfContentBoxEncoder encoder = new GiftEncoder(baseFont, resources);
	  try {
		new PdfBoxEncoder(resources, baseFont).encodeBox(directContent, encoder, character, newBounds);
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }

  private final void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection) {
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - 2 * groupSpacing;
    int maximum = PHYSICAL_MAX;
    float width = contentBounds.getWidth() * 1 / 2;
    for (IGroupedTraitType groupedTraitType : attributeGroups)
    {
    	if (!groupedTraitType.getGroupId().equals(AttributeGroupType.Physical.name()))
    		continue;

    	ITraitType traitType = groupedTraitType.getTraitType();
      String traitLabel = resources.getString("AttributeType.Name." + traitType.getId()); //$NON-NLS-1$
      int value = traitCollection.getTrait(traitType).getCurrentValue();
      Position position = new Position(contentBounds.x, y);
      y -= smallTraitEncoder.encodeWithText(
          directContent,
          traitLabel,
          position,
          width,
          value,
          maximum);
    }
  }
}