package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.lib.resources.IResources;
import net.sf.anathema.lib.util.ITransformer;

public class CharmPrintNameTransformer implements ITransformer<ICharm, String> {

  private final IResources resources;

  public CharmPrintNameTransformer(IResources resources) {
    this.resources = resources;
  }

  @Override
  public String transform(ICharm input) {
    return resources.getString(input.getId());
  }
}
