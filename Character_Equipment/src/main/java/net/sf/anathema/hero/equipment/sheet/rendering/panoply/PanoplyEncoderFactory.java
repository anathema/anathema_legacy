package net.sf.anathema.hero.equipment.sheet.rendering.panoply;

import net.sf.anathema.hero.equipment.sheet.content.ArmourContent;
import net.sf.anathema.hero.equipment.sheet.rendering.arsenal.PreferredWeaponryHeight;
import net.sf.anathema.hero.sheet.pdf.content.BasicContent;
import net.sf.anathema.hero.sheet.pdf.encoder.EncoderIds;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.ContentEncoder;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.GlobalEncoderFactory;
import net.sf.anathema.hero.sheet.pdf.encoder.boxes.RegisteredEncoderFactory;
import net.sf.anathema.framework.environment.Resources;

@RegisteredEncoderFactory
public class PanoplyEncoderFactory extends GlobalEncoderFactory {

  public PanoplyEncoderFactory() {
    super(EncoderIds.PANOPLY);
    setPreferredHeight(new PreferredWeaponryHeight());
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new ArmourEncoder(resources, new ArmourTableEncoder(ArmourContent.class));
  }
}
