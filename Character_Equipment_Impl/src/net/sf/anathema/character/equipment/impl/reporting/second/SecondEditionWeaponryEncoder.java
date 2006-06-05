package net.sf.anathema.character.equipment.impl.reporting.second;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.sheet.common.IPdfContentEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class SecondEditionWeaponryEncoder implements IPdfContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public SecondEditionWeaponryEncoder(IResources resources, BaseFont baseFont) {
    this.baseFont = baseFont;
    this.resources = resources;
  }

  public String getHeaderKey() {
    return "Weapons";
  }
  
  public void encode(PdfContentByte directContent, IGenericCharacter character, Bounds bounds) throws DocumentException {
    new SecondEditionWeaponryTableEncoder(baseFont, resources).encodeTable(directContent, character, bounds);
  }
}