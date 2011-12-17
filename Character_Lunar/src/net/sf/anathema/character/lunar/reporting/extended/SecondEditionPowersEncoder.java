package net.sf.anathema.character.lunar.reporting.extended;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.character.generic.template.TemplateType;
import net.sf.anathema.character.generic.type.CharacterType;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.PdfTextEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.TableEncodingUtilities;
import net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class SecondEditionPowersEncoder implements IPdfContentBoxEncoder
{
  private static final String TERRIFYING_BEASTMAN_ALTERATION = "Lunar.TerrifyingBeastmanAlteration";
  private Font font;
  private float lineHeight = IVoidStateFormatConstants.LINE_HEIGHT - 2;
  private final String powerBase = "Sheet.Lunar.Powers.";
  private final IResources resources;
  private final BaseFont baseFont;
  private final boolean isHorizontal;
  private int tellMDV;
  
  private static final TemplateType castelessType = new TemplateType(CharacterType.LUNAR, new Identificate(
  "Casteless")); //$NON-NLS-1$

  public SecondEditionPowersEncoder(IResources resources, BaseFont baseFont, boolean isHorizontal) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.isHorizontal = isHorizontal;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds)
  {
	  tellMDV = hasTBA(character) ? 8 : 12;
	  
	  int offsetX = 0, offsetY = isHorizontal ? 0 : 5;
	  font = TableEncodingUtilities.createFont(baseFont);
	  
	  if (isHorizontal)
	  {
		  bounds = new Bounds(bounds.x, bounds.y, bounds.width / 2, bounds.height);
		  font.setSize(IVoidStateFormatConstants.COMMENT_FONT_SIZE);
		  lineHeight -= 2;
	  }
	  
	  try
	  {
		  offsetY += writePowerNotes(directContent, "Shapeshifting", bounds, offsetX, offsetY);
		  if (!character.getTemplate().getTemplateType().equals(castelessType))
			  offsetY += writePowerNotes(directContent, "Tattoos", bounds, offsetX, offsetY);

		  if (isHorizontal)
		  {
			  bounds = new Bounds(
					  bounds.x + bounds.width,
					  bounds.y,
					  bounds.width,
					  bounds.height);
					  offsetY = 0;
		  }
		  
		  offsetY += writePowerNotes(directContent, "Tell", bounds, offsetX, offsetY);
	  }
	  catch (DocumentException e) { }
  }
  
  private boolean hasTBA(IGenericCharacter character)
	{
		for (ICharm charm : character.getLearnedCharms())
			if (charm.getId().equals(TERRIFYING_BEASTMAN_ALTERATION))
				return true;
		return false;
	}
  
  private int writePowerNotes(PdfContentByte directContent,
		  String power,
		  Bounds bounds,
		  int offsetX,
		  int offsetY) throws DocumentException
  {
	  Font boldFont = new Font(font);
	  boldFont.setStyle(Font.BOLD);
	  String text = resources.getString(powerBase + power);
	  Phrase phrase = new Phrase(text, boldFont);
	  int index = 0;
	  int totalHeight = 0;
	  while (!text.startsWith("##"))
	  {
		  Bounds newBounds = new Bounds(
				  bounds.x + offsetX,
				  bounds.y,
				  bounds.width - offsetX,
				  bounds.height - offsetY - totalHeight);
		  totalHeight += PdfTextEncodingUtilities.encodeText(directContent, phrase,
				  newBounds, lineHeight).getLinesWritten() * lineHeight;
		  text = resources.getString(powerBase + power + (++index));
		  text = text.replace("TELLMDV", "" + tellMDV);
		  phrase = new Phrase(text, font);
	  }
	  if (!isHorizontal)
	  {
		  Bounds newBounds = new Bounds(
				  bounds.x + offsetX,
				  bounds.y + bounds.height - offsetY - totalHeight,
				  bounds.x - offsetX,
				  lineHeight);
		  totalHeight += PdfTextEncodingUtilities.encodeText(directContent, new Phrase(" ", font), newBounds, lineHeight).getLinesWritten() * lineHeight;
	  }
	  return totalHeight;
  }

	@Override
	public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
		return "Lunar.Powers";
	}
	
	public boolean hasContent(IGenericCharacter character)
	  {
		  return true;
	  }
}
