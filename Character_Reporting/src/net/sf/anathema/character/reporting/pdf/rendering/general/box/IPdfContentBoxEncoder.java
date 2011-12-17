package net.sf.anathema.character.reporting.pdf.rendering.general.box;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;

public interface IPdfContentBoxEncoder {

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException;

  public boolean hasContent(IGenericCharacter character);

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description);
}
