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

  public SecondEditionAstrologyInfoEncoder(BaseFont baseFont, IResources resources) {
    this.resources = resources;
    this.basefont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    int height = 0;
    height += (int) new AstrologyTableEncoder(resources, basefont).encodeTable(directContent, character, bounds);
    height += (int) new DestinyTypeTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, 0, height));
    height += (int) new TriggerTypeTableEncoder(resources, basefont).encodeTable(directContent,
    		character, getBounds(bounds, 0, height));
    
    

  }
  
  private Bounds getBounds(Bounds bounds, int offsetX, int offsetY)
  {
	  return new Bounds(
			  bounds.x + offsetX,
			  bounds.y - offsetY,
			  bounds.width / 2,
			  bounds.height);
  }

  public String getHeaderKey() {
    return "Sidereal.Astrology"; //$NON-NLS-1$
  }
}