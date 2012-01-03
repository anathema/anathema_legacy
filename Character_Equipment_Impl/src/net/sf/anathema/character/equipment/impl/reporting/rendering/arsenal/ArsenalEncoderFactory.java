package net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class ArsenalEncoderFactory extends GlobalEncoderFactory {

  public ArsenalEncoderFactory() {
    super(EncoderIds.ARSENAL);
    setAttribute(EncoderAttributeType.PreferredHeight, new PreferredWeaponryHeight());
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new WeaponryEncoder();
  }
}
