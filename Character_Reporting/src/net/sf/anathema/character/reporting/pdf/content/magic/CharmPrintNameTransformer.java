package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.framework.magic.MagicDisplayLabeler;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.ITransformer;

public class CharmPrintNameTransformer implements ITransformer<ICharm, String> {

  private final MagicDisplayLabeler labeler;

  public CharmPrintNameTransformer(IResources resources) {
    this.labeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public String transform(ICharm input) {
    return labeler.getLabelForMagic(input);
  }
}
