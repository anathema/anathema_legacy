package net.sf.anathema.character.equipment.impl.reporting.second;

import java.awt.Color;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionArmourEncoder implements IPdfContentBoxEncoder {

  private final BaseFont baseFont;
  private final IResources resources;

  public SecondEditionArmourEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public String getHeaderKey() {
    return "ArmourSoak"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    float tableHeight = new SecondEditionArmourTableEncoder(baseFont, resources).encodeTable(
        directContent,
        character,
        bounds);
    float remainingHeight = bounds.getHeight() - tableHeight;
    float delimitingLineYPosition = bounds.getMinY() + remainingHeight - 1;
    drawDelimiter(directContent, bounds, delimitingLineYPosition);
    Bounds shieldBounds = new Bounds(
        bounds.getMinX(),
        delimitingLineYPosition - 12,
        bounds.getWidth(),
        remainingHeight - 2);
    new ShieldTableEncoder(baseFont, resources).encodeTable(directContent, character, shieldBounds);
  }

  private void drawDelimiter(PdfContentByte directContent, Bounds bounds, float delimitingLineYPosition) {
    directContent.moveTo(bounds.getMinX() + 3, delimitingLineYPosition);
    directContent.lineTo(bounds.getMaxX() - 3, delimitingLineYPosition);
    directContent.setColorStroke(Color.GRAY);
    directContent.setLineWidth(0.75f);
    directContent.stroke();
  }
}