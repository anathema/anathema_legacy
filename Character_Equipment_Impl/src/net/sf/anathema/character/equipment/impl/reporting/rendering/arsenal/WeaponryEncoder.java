package net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal;

import com.itextpdf.text.DocumentException;
import net.sf.anathema.character.equipment.impl.reporting.content.WeaponryContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.extent.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;

public class WeaponryEncoder extends AbstractBoxContentEncoder<WeaponryContent> {

  private final WeaponryTableEncoder customEncoder;

  public WeaponryEncoder() {
    this(null);
  }

  public WeaponryEncoder(WeaponryTableEncoder customEncoder) {
    super(WeaponryContent.class);
    this.customEncoder = customEncoder;
  }

  public void encode(SheetGraphics graphics, final ReportContent content, Bounds bounds) throws DocumentException {
    WeaponryTableEncoder tableEncoder = createTableEncoder(content);
    tableEncoder.encodeTable(graphics, content, bounds);
  }

  private WeaponryTableEncoder createTableEncoder(ReportContent reportContent) {
    if (customEncoder != null) {
      return customEncoder;
    }
    WeaponryContent content = createContent(reportContent);
    return new WeaponryTableEncoder(content.getTableContentClass());
  }
}
