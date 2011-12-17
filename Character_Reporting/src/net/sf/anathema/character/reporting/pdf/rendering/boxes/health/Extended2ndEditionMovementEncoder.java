package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.character.IGenericDescription;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IPdfContentBoxEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.IPdfTableEncoder;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionMovementEncoder implements IPdfContentBoxEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public Extended2ndEditionMovementEncoder(IResources resources, BaseFont baseFont, BaseFont symbolBaseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  public String getHeaderKey(IGenericCharacter character, IGenericDescription description) {
    return "Movement"; //$NON-NLS-1$
  }

  public void encode(PdfContentByte directContent, IGenericCharacter character, IGenericDescription description,
                     Bounds bounds) throws DocumentException {
    IPdfTableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(directContent, character, bounds);
  }

  protected final BaseFont getBaseFont() {
    return baseFont;
  }

  protected final IResources getResources() {
    return resources;
  }

  protected IPdfTableEncoder createTableEncoder() {
    return new Simple2ndEditionMovementTableEncoder(getResources(), getBaseFont());
  }

  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }

  public boolean hasContent(IGenericCharacter character) {
    return true;
  }
}
