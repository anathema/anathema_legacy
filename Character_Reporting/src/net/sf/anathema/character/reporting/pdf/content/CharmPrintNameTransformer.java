package net.sf.anathema.character.reporting.pdf.content;

import net.disy.commons.core.util.ITransformer;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;

public class CharmPrintNameTransformer implements ITransformer<ICharm, String> {

  private final IResources resources;

  public CharmPrintNameTransformer(IResources resources) {
    this.resources = resources;
  }

  public String transform(ICharm input) {
    return resources.getString(input.getId());
  }
}
