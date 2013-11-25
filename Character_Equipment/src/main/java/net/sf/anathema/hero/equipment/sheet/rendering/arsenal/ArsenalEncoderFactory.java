package net.sf.anathema.hero.equipment.sheet.rendering.arsenal;

import net.sf.anathema.framework.environment.Resources;
import net.sf.anathema.hero.equipment.sheet.content.WeaponryContent;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;

@SuppressWarnings("UnusedDeclaration")
public class ArsenalEncoderFactory extends GlobalEncoderFactory {

  public ArsenalEncoderFactory() {
    super(EncoderIds.ARSENAL);
    setPreferredHeight(new PreferredWeaponryHeight());
  }

  @SuppressWarnings("unchecked")
  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new WeaponryEncoder(WeaponryContent.class);
  }
}
