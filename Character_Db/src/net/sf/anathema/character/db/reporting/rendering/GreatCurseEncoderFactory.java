package net.sf.anathema.character.db.reporting.rendering;

import net.sf.anathema.character.db.DbCharacterModule;
import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.EncoderIds;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.AbstractEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.RegisteredEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.ContentEncoder;
import net.sf.anathema.lib.resources.Resources;

@RegisteredEncoderFactory
public class GreatCurseEncoderFactory extends AbstractEncoderFactory {

  public GreatCurseEncoderFactory() {
    super(EncoderIds.GREAT_CURSE);
  }

  @Override
  public ContentEncoder create(Resources resources, BasicContent content) {
    return new GreatCurseEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isOfType(DbCharacterModule.type);
  }
}
