package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionArmourEncoder implements IPdfContentEncoder {

  private final BaseFont baseFont;
  private final IResources resources;

  public SecondEditionArmourEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    new SecondEditionArmourTableEncoder(baseFont, resources).encodeTable(directContent, character, bounds);
  }
}