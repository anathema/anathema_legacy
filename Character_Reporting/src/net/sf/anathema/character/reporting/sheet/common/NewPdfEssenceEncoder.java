package net.sf.anathema.character.reporting.sheet.common;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.sheet.util.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.util.Bounds;
import net.sf.anathema.lib.resources.IResources;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;

public class NewPdfEssenceEncoder extends AbstractPdfEncoder
    implements IPdfVariableContentBoxEncoder {
  
  private BaseFont baseFont;
  private final IResources resources;
  private EssenceTableEncoder poolTable;

  public NewPdfEssenceEncoder(BaseFont baseFont, IResources resources,
                              int essenceMax, String... specialRecoveryRows) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.poolTable = new EssenceTableEncoder(this.resources, this.baseFont, essenceMax,
                                             specialRecoveryRows);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(IGenericCharacter character,
                             IGenericDescription description) {
    return "Essence"; //$NON-NLS-1$
  }

  public float getRequestedHeight(IGenericCharacter character, float width) {
    try {
      return poolTable.getTableHeight(character, width);
    }
    catch (DocumentException e) {
      System.err.println(e.toString());
      e.printStackTrace(System.err);
      return 0;
    }
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character,
                     IGenericDescription description, Bounds bounds)
      throws DocumentException {
    Bounds poolBounds = new Bounds(bounds.x, bounds.y, bounds.width, bounds.height);
    poolTable.encodeTable(directContent, character, poolBounds);
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}