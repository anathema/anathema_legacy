package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.elements.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.AbstractPdfEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.Graphics;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ExtendedPdfEssenceEncoder extends AbstractPdfEncoder implements IVariableBoxContentEncoder {

  private BaseFont baseFont;
  private final IResources resources;
  private EssenceTableEncoder poolTable;

  public ExtendedPdfEssenceEncoder(BaseFont baseFont, IResources resources, int essenceMax, String... specialRecoveryRows) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.poolTable = new EssenceTableEncoder(this.resources, this.baseFont, essenceMax, specialRecoveryRows);
  }

  @Override
  protected BaseFont getBaseFont() {
    return baseFont;
  }

  public String getHeaderKey(ReportContent reportContent) {
    return "Essence"; //$NON-NLS-1$
  }

  public float getRequestedHeight(ReportContent content, float width) {
    try {
      return poolTable.getTableHeight(content, width);
    }
    catch (DocumentException e) {
      System.err.println(e.toString());
      e.printStackTrace(System.err);
      return 0;
    }
  }

  public void encode(Graphics graphics, ReportContent reportContent) throws DocumentException {
    Bounds poolBounds = new Bounds(graphics.getBounds().x, graphics.getBounds().y, graphics.getBounds().width, graphics.getBounds().height);
    poolTable.encodeTable(graphics.getDirectContent(), reportContent, poolBounds);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
