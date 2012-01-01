package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.equipment.impl.reporting.content.AbstractWeaponryContent;
import net.sf.anathema.character.equipment.impl.reporting.content.WeaponryContent;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.AbstractBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class WeaponryEncoder extends AbstractBoxContentEncoder<WeaponryContent> {

  private final IResources resources;
  private final BaseFont baseFont;
  private final WeaponryTableEncoder customEncoder;

  public WeaponryEncoder(IResources resources, BaseFont baseFont) {
    this(resources, baseFont, null);
  }

  public WeaponryEncoder(IResources resources, BaseFont baseFont, WeaponryTableEncoder customEncoder) {
    super(WeaponryContent.class);
    this.baseFont = baseFont;
    this.resources = resources;
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
    return new WeaponryTableEncoder(content.getTableContentClass(), baseFont);
  }
}
