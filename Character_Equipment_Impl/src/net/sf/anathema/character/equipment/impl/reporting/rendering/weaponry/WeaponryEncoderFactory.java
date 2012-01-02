package net.sf.anathema.character.equipment.impl.reporting.rendering.weaponry;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.content.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractBoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class WeaponryEncoderFactory extends AbstractBoxContentEncoderFactory {

  public WeaponryEncoderFactory() {
    super(EncoderIds.WEAPONRY_ID);
    setAttribute(EncoderAttributeType.PreferredHeight, new PreferredWeaponryHeight());
  }

  @Override
  public IBoxContentEncoder create(IResources resources, BasicContent content) {
    return new WeaponryEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return true;
  }

}
