package net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.content.SubBoxContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class WeaponryEncoder<C extends SubBoxContent> extends AbstractContentEncoder<C> {

  private final WeaponryTableEncoder tableEncoder;

  public WeaponryEncoder(Class<C> contentClass) {
    super(contentClass);
    this.tableEncoder = new WeaponryTableEncoder(contentClass);
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    tableEncoder.encodeTable(graphics, session, bounds);
  }
}
