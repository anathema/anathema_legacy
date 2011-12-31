package net.sf.anathema.character.equipment.impl.reporting;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import net.sf.anathema.character.reporting.pdf.content.ReportContent;
import net.sf.anathema.character.reporting.pdf.rendering.Bounds;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.character.reporting.pdf.rendering.graphics.SheetGraphics;
import net.sf.anathema.lib.resources.IResources;

public class WeaponryEncoder implements IBoxContentEncoder {

  private final IResources resources;
  private final BaseFont baseFont;
  private final WeaponryTableEncoder customEncoder;

  public WeaponryEncoder(IResources resources, BaseFont baseFont) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.customEncoder = null;
  }

  public WeaponryEncoder(IResources resources, BaseFont baseFont, WeaponryTableEncoder customEncoder) {
    this.baseFont = baseFont;
    this.resources = resources;
    this.customEncoder = customEncoder;
  }

  public String getHeaderKey(ReportContent content) {
    return "Weapons"; //$NON-NLS-1$
  }

  public void encode(SheetGraphics graphics, final ReportContent content, Bounds bounds) throws DocumentException {
    WeaponryTableEncoder tableEncoder = createTableEncoder(content);
    tableEncoder.encodeTable(graphics, content, bounds);
  }

  private WeaponryTableEncoder createTableEncoder(ReportContent content) {
    WeaponryContentClassFinder contentClassFinder = new WeaponryContentClassFinder();
    content.getCharacter().getRules().getEdition().accept(contentClassFinder);
    return new WeaponryTableEncoder(contentClassFinder.contentClass, baseFont);
  }

  public boolean hasContent(ReportContent content) {
    return true;
  }
}
