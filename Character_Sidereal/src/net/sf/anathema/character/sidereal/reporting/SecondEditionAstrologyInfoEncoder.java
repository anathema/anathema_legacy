package net.sf.anathema.character.sidereal.reporting;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionAstrologyInfoEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private final BaseFont basefont;
  private final float SPACING = 5;

  public SecondEditionAstrologyInfoEncoder(BaseFont baseFont, IResources resources) {
    this.resources = resources;
    this.basefont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    int height = (int) SPACING;
    height += (int) new SecondEditionAstrologyTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, 0, height)) + SPACING + 1;
    height += (int) new DestinyTypeTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, 0, height)) + SPACING + 2;
    height += (int) new ResplendentDestinyTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, 0, height)) + SPACING + 2;
    
    int horizSpan = (int) (bounds.width / 2);
    height = (int) SPACING;
    
    height += (int) new TriggerTypeTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, horizSpan, height)) + SPACING;
    height += (int) new ScopeTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, horizSpan, height)) + SPACING;
    height += (int) new DurationTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, horizSpan, height)) + SPACING;
    height += (int) new FrequencyTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, horizSpan, height)) + SPACING;

  }
  
  private Bounds getBounds(Bounds bounds, int offsetX, int offsetY)
  {
	  return new Bounds(
			  bounds.x + offsetX,
			  bounds.y - offsetY,
			  bounds.width / 2 - SPACING,
			  bounds.height);
  }

  public String getHeaderKey() {
    return "Sidereal.Astrology"; //$NON-NLS-1$
  }
}