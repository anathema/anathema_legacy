package net.sf.anathema.character.equipment.impl.reporting.rendering.panoply;

import net.sf.anathema.character.equipment.impl.reporting.content.ArmourContent;
import net.sf.anathema.character.equipment.impl.reporting.rendering.arsenal.PreferredWeaponryHeight;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.EncoderAttributeType;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.GlobalEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.IResources;

public class PanoplyEncoderFactory extends GlobalEncoderFactory {

  public PanoplyEncoderFactory() {
    super(EncoderIds.PANOPLY);
    setAttribute(EncoderAttributeType.PreferredHeight, new PreferredWeaponryHeight());
  }

  @Override
  public ContentEncoder create(IResources resources, BasicContent content) {
    return new ArmourEncoder(new ArmourTableEncoder(ArmourContent.class));
  }
}
