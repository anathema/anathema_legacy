package net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.equipment.impl.reporting.content.WeaponryContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class WeaponryEncoder extends AbstractContentEncoder<WeaponryContent> {

  private final WeaponryTableEncoder customEncoder;

  public WeaponryEncoder() {
    this(null);
  }

  public WeaponryEncoder(WeaponryTableEncoder customEncoder) {
    super(WeaponryContent.class);
    this.customEncoder = customEncoder;
  }

  public void encode(SheetGraphics graphics, final ReportSession session, Bounds bounds) throws DocumentException {
    WeaponryTableEncoder tableEncoder = createTableEncoder(session);
    tableEncoder.encodeTable(graphics, session, bounds);
  }

  private WeaponryTableEncoder createTableEncoder(ReportSession reportSession) {
    if (customEncoder != null) {
      return customEncoder;
    }
    WeaponryContent content = createContent(reportSession);
    return new WeaponryTableEncoder(content.getTableContentClass());
  }
}
