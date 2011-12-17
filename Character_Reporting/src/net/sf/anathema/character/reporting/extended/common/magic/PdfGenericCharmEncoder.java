package net.sf.anathema.character.reporting.extended.common.magic;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.encoder.IPdfVariableContentBoxEncoder;
import net.sf.anathema.lib.resources.IResources;

public class PdfGenericCharmEncoder implements IPdfVariableContentBoxEncoder {

  private final PdfGenericCharmTableEncoder tableEncoder;

  public PdfGenericCharmEncoder(IResources resources, BaseFont baseFont) {
    this.tableEncoder = new PdfGenericCharmTableEncoder(resources, baseFont);
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "GenericCharms"; //$NON-NLS-1$
  }

  @Override
  public float getRequestedHeight(IGenericCharacter character, float width) {
    return tableEncoder.getRequestedHeight(character);
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException {
    tableEncoder.encodeTable(directContent, character, bounds);
  }

  public boolean hasContent(IGenericCharacter character) {
    return tableEncoder.hasContent(character);
  }
}
