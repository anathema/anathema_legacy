package net.sf.anathema.character.reporting.pdf.content.combo;

import com.google.common.base.Function;
import net.disy.commons.core.util.ArrayUtilities;
import net.sf.anathema.character.generic.magic.IGenericCombo;
import net.sf.anathema.character.reporting.pdf.content.magic.CharmPrintNameTransformer;
import net.sf.anathema.lib.lang.AnathemaStringUtilities;
import net.sf.anathema.lib.resources.IResources;

import javax.annotation.Nullable;

public class ToDisplayCombo implements Function<IGenericCombo, DisplayCombo> {

  private IResources resources;

  public ToDisplayCombo(IResources resources) {
    this.resources = resources;
  }

  @Override
  public DisplayCombo apply(@Nullable IGenericCombo combo) {
    String displayName = getDisplayName(combo);
    String displayCharms = getCharmString(combo);
    return new DisplayCombo(displayName, displayCharms);
  }

  private String getDisplayName(IGenericCombo combo) {
    String name = combo.getName();
    return name == null ? "???" : name; //$NON-NLS-1$
  }

  private String getCharmString(IGenericCombo combo) {
    CharmPrintNameTransformer transformer = new CharmPrintNameTransformer(resources);
    String[] charmNames = ArrayUtilities.transform(combo.getCharms(), String.class, transformer);
    return AnathemaStringUtilities.concat(charmNames, ", "); //$NON-NLS-1$
  }
}
