package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionMovementEncoder implements ContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;

  public Extended2ndEditionMovementEncoder(IResources resources, BaseFont baseFont) {
    this.resources = resources;
    this.baseFont = baseFont;
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.Movement");
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    ITableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(graphics, reportContent, bounds);
  }

  protected final BaseFont getBaseFont() {
    return baseFont;
  }

  protected final IResources getResources() {
    return resources;
  }

  protected ITableEncoder createTableEncoder() {
    return new Simple2ndEditionMovementTableEncoder(getResources(), getBaseFont());
  }

  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return true;
  }
}
