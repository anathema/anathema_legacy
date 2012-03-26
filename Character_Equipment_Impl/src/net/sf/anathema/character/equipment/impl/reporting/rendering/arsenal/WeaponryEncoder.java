package net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.equipment.impl.reporting.content.Weaponry2ndEditionContent;
import net.sf.anathema.character.equipment.impl.reporting.content.WeaponryContent;
import net.sf.anathema.character.reporting.pdf.content.ReportSession;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class WeaponryEncoder extends AbstractContentEncoder<WeaponryContent> {

  private final WeaponryTableEncoder tableEncoder;

  public WeaponryEncoder() {
    this(new WeaponryTableEncoder(Weaponry2ndEditionContent.class));
  }

  public WeaponryEncoder(WeaponryTableEncoder tableEncoder) {
    super(WeaponryContent.class);
    this.tableEncoder = tableEncoder;
  }

  @Override
  public void encode(SheetGraphics graphics, ReportSession session, Bounds bounds) throws DocumentException {
    tableEncoder.encodeTable(graphics, session, bounds);
  }
}