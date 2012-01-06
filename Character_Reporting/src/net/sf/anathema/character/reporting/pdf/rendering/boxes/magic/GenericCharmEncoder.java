package net.sf.anathema.character.reporting.pdf.rendering.boxes.magic;

import com.lowagie.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IVariableContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class GenericCharmEncoder implements IVariableContentEncoder {

  private final GenericCharmTableEncoder tableEncoder;
  private IResources resources;

  public GenericCharmEncoder(IResources resources) {
    this.resources = resources;
    this.tableEncoder = new GenericCharmTableEncoder(resources);
  }

  @Override
  public String getHeader(ReportContent content) {
    return resources.getString("Sheet.Header.GenericCharms");
  }

  @Override
  public float getRequestedHeight(SheetGraphics graphics, ReportContent content, float width) {
    return tableEncoder.getRequestedHeight(graphics, content);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportContent reportContent, Bounds bounds) throws DocumentException {
    tableEncoder.encodeTable(graphics, reportContent, bounds);
  }

  @Override
  public boolean hasContent(ReportContent content) {
    return tableEncoder.hasContent(content);
  }
}
