package net.sf.anathema.character.equipment.reporting.rendering.arsenal;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.hero.sheet.pdf.content.SubBoxContent;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.AbstractContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.general.Bounds;
import net.sf.anathema.hero.sheet.pdf.encoder.graphics.SheetGraphics;
import net.sf.anathema.hero.sheet.pdf.session.ReportSession;

public class WeaponryEncoder<C extends SubBoxContent> extends AbstractContentEncoder<C> {

  private final WeaponryTableEncoder tableEncoder;

  @SuppressWarnings("unchecked")
  public WeaponryEncoder(Class<C> contentClass) {
    super(contentClass);
    this.tableEncoder = new WeaponryTableEncoder(contentClass);
  }

  @SuppressWarnings("unchecked")
  @Override
  public void encode(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    tableEncoder.encodeTable(graphics, session, bounds);
  }
}
