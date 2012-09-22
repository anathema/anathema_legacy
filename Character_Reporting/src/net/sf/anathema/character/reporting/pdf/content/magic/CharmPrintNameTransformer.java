package net.sf.anathema.character.reporting.pdf.content.magic;

import com.google.common.base.Function;
import net.sf.anathema.character.generic.framework.magic.MagicDisplayLabeler;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;

public class CharmPrintNameTransformer implements Function<ICharm, String> {

  private final MagicDisplayLabeler labeler;

  public CharmPrintNameTransformer(IResources resources) {
    this.labeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public String apply(ICharm input) {
    return labeler.getLabelForMagic(input);
  }
}
