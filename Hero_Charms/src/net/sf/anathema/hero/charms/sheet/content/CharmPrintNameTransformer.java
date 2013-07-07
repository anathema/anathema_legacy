package net.sf.anathema.hero.charms.sheet.content;

import com.google.common.base.Function;
import net.sf.anathema.character.main.magic.ICharm;
import net.sf.anathema.character.main.charmtree.builder.MagicDisplayLabeler;
import net.sf.anathema.lib.resources.Resources;

public class CharmPrintNameTransformer implements Function<ICharm, String> {

  private final MagicDisplayLabeler labeler;

  public CharmPrintNameTransformer(Resources resources) {
    this.labeler = new MagicDisplayLabeler(resources);
  }

  @Override
  public String apply(ICharm input) {
    return labeler.getLabelForMagic(input);
  }
}
