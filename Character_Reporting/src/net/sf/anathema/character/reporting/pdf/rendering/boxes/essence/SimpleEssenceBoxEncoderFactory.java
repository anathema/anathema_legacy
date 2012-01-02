package net.sf.anathema.character.reporting.pdf.rendering.boxes.essence;

import net.sf.anathema.character.reporting.pdf.content.BasicContent;
import net.sf.anathema.character.reporting.pdf.rendering.boxes.BoxContentEncoderFactory;
import net.sf.anathema.character.reporting.pdf.rendering.general.box.IBoxContentEncoder;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.Identificate;

public class SimpleEssenceBoxEncoderFactory extends Identificate implements BoxContentEncoderFactory {

  public static final String ID = SimpleEssenceBoxEncoderFactory.class.getName();

  public SimpleEssenceBoxEncoderFactory() {
    super(ID);
  }

  @Override
  public IBoxContentEncoder create(IResources resources) {
    return new SimpleEssenceBoxContentEncoder();
  }

  @Override
  public boolean supports(BasicContent content) {
    return content.isExalt();
  }
}
