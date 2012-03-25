package net.sf.anathema.character.reporting.pdf.rendering.boxes.health;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;
import net.sf.anathema.character.generic.rules.IExaltedEdition;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.general.table.ITableEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class Extended2ndEditionMovementEncoder implements ContentEncoder {

  private final IResources resources;

  public Extended2ndEditionMovementEncoder(IResources resources) {
    this.resources = resources;
  }

  @Override
  public String getHeader(ReportSession session) {
    return resources.getString("Sheet.Header.Movement");
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession reportSession, Bounds bounds) throws DocumentException {
    ITableEncoder tableEncoder = createTableEncoder();
    tableEncoder.encodeTable(graphics, reportSession, bounds);
  }

  protected final IResources getResources() {
    return resources;
  }

  protected ITableEncoder createTableEncoder() {
    return new Simple2ndEditionMovementTableEncoder(getResources());
  }

  protected final IExaltedEdition getEdition() {
    return ExaltedEdition.SecondEdition;
  }

  @Override
  public boolean hasContent(ReportSession session) {
    return true;
  }
}
