package net.sf.anathema.character.equipment.impl.reporting;

import java.awt.Color;

import net.sf.anathema.character.generic.character.*;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class ArmourEncoder implements IPdfContentBoxEncoder {

  private final BaseFont baseFont;
  private final IResources resources;
  private final IPdfTableEncoder encoder;

  public ArmourEncoder(IResources resources, BaseFont baseFont, IPdfTableEncoder encoder) {
    this.resources = resources;
    this.baseFont = baseFont;
    this.encoder = encoder;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "ArmourSoak"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description, Bounds bounds) throws DocumentException {
    float tableHeight = encoder.encodeTable(directContent, character, bounds);
    float remainingHeight = bounds.getHeight() - tableHeight;
    float delimitingLineYPosition = bounds.getMinY() + remainingHeight - 3;
    drawDelimiter(directContent, bounds, delimitingLineYPosition);
    Bounds shieldBounds = new Bounds(
        bounds.getMinX(),
        bounds.getMinY(),
        bounds.getWidth(),
        remainingHeight - 6);
    new ShieldTableEncoder(baseFont, resources).encodeTable(directContent, character, shieldBounds);
  }

  private void drawDelimiter(PdfContentByte directContent, Bounds bounds, float delimitingLineYPosition) {
    directContent.moveTo(bounds.getMinX() + 3, delimitingLineYPosition);
    directContent.lineTo(bounds.getMaxX() - 3, delimitingLineYPosition);
    directContent.setColorStroke(Color.GRAY);
    directContent.setLineWidth(0.75f);
    directContent.stroke();
    directContent.setColorStroke(Color.BLACK);
  }
  
  public boolean hasContent(IGenericCharacter character)
  {
	  return true;
  }
}
