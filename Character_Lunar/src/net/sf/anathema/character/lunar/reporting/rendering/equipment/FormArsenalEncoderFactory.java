package net.sf.anathema.character.lunar.reporting.rendering.equipment;

import net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal.PreferredWeaponryHeight;
import net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal.WeaponryEncoder;
import net.sf.anathema.character.lunar.reporting.content.equipment.LunarWeaponryContent;
import net.sf.anathema.character.lunar.reporting.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

@RegisteredEncoderFactory
public class FormArsenalEncoderFactory extends AbstractEncoderFactory {

  public FormArsenalEncoderFactory() {
    super(EncoderIds.ARSENAL_LUNAR);
    setPreferredHeight(new PreferredWeaponryHeight());
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new WeaponryEncoder(LunarWeaponryContent.class);
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }
}
