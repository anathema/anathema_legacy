package net.sf.anathema.character.reporting.extended.second;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.common.Bounds;
import net.sf.anathema.character.reporting.common.encoder.IPdfTableEncoder;
import net.sf.anathema.character.reporting.common.pageformat.IVoidStateFormatConstants;
import net.sf.anathema.character.reporting.common.boxes.movement.AbstractHealthAndMovementEncoder;
import net.sf.anathema.lib.resources.IResources;

public class SecondEditionHealthEncoder extends AbstractHealthAndMovementEncoder {

  public SecondEditionHealthEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    super(resources, baseFont, symbolBaseFont);
  }

  @Override
  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Health"; //$NON-NLS-1$
  }

  @Override
  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException {
    Bounds tableBounds = new Bounds(bounds.x, bounds.y + bounds.height - 94f, bounds.width, 94f);
    IPdfTableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(directContent, character, tableBounds);
    float textHeight = tableBounds.getMinY() - bounds.y - IVoidStateFormatConstants.TEXT_PADDING;
    Bounds textBounds = new Bounds(bounds.x, bounds.y, bounds.width, textHeight);
    encodeText(directContent, textBounds);
  }

  @Override
  protected IPdfTableEncoder createTableEncoder() {
    return new SecondEditionHealthTableEncoder(getResources(), getBaseFont());
  }

  @Override
  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }
}
