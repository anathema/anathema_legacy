package net.sf.anathema.character.lunar.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.impl.traits.EssenceTemplate;
import net.sf.anathema.character.generic.template.abilities.IGroupedTraitType;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AttributeGroupType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.lunar.beastform.BeastformTemplate;
import net.sf.anathema.character.lunar.beastform.model.SecondEditionBeastformModel;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.sheet.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.sheet.util.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.sheet.util.PdfTraitEncoder;
import net.sf.anathema.character.reporting.sheet.util.TableEncodingUtilities;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.character.reporting.util.Position;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionLunarSpiritFormEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private final PdfTraitEncoder smallTraitEncoder;
  private final BaseFont baseFont;

  public SecondEditionLunarSpiritFormEncoder(BaseFont baseFont, IResources resources, float smallWidth) {
    this.resources = resources;
    this.smallTraitEncoder = PdfTraitEncoder.createSmallTraitEncoder(baseFont);
    this.baseFont = baseFont;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Lunar.SpiritForm"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) {
    IGroupedTraitType[] attributeGroups = character.getTemplate().getAttributeGroups();
    SecondEditionBeastformModel additionalModel = (SecondEditionBeastformModel)
    	character.getAdditionalModel(BeastformTemplate.TEMPLATE_ID);
    IGenericTraitCollection traitCollection = additionalModel.getSpiritTraitCollection();
    encodeAttributes(directContent, bounds, attributeGroups, traitCollection);
    encodeForm(directContent, bounds, additionalModel.getSpiritForm());
  }
  
  private final void encodeForm(PdfContentByte directContent, Bounds bounds, String form)
  {
	  Font font = TableEncodingUtilities.createFont(baseFont);
	  Bounds newBounds = new Bounds(
			  bounds.x,
			  bounds.y,
			  bounds.width,
			  bounds.height - 50);
	  String text = resources.getString("Sheet.Header.Lunar.SpiritForm") + ": " + form;
	  //font.setSize(IVoidStateFormatConstants.COMMENT_FONT_SIZE);
	  try
	  {
		  PdfTextEncodingUtilities.encodeText(directContent, new Phrase(text, font),
				  newBounds, IVoidStateFormatConstants.LINE_HEIGHT - 2);
	  }
	  catch (DocumentException e) { }
  }
  
  private final void encodeAttributes(
      PdfContentByte directContent,
      Bounds contentBounds,
      IGroupedTraitType[] attributeGroups,
      IGenericTraitCollection traitCollection) {
    float groupSpacing = smallTraitEncoder.getTraitHeight() / 2;
    float y = contentBounds.getMaxY() - 2 * groupSpacing;
    int maximum = EssenceTemplate.SYSTEM_ESSENCE_MAX;
    float width = contentBounds.getWidth();
    for (IGroupedTraitType groupedTraitType : attributeGroups)
    {
    	if (!groupedTraitType.getGroupId().equals(AttributeGroupType.Physical.name()) &&
    		!groupedTraitType.getTraitType().getId().equals(AttributeType.Appearance.name()))
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
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}