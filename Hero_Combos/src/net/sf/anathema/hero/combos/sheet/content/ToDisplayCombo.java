package net.sf.anathema.hero.combos.sheet.content;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import net.sf.anathema.character.main.magic.IGenericCombo;
import net.sf.anathema.character.reporting.pdf.content.magic.CharmPrintNameTransformer;
import net.sf.anathema.lib.resources.Resources;

public class ToDisplayCombo implements Function<IGenericCombo, DisplayCombo> {

  private Resources resources;

  public ToDisplayCombo(Resources resources) {
    this.resources = resources;
  }

  @Override
  public DisplayCombo apply(IGenericCombo combo) {
    String displayName = getDisplayName(combo);
    String displayCharms = getCharmString(combo);
    return new DisplayCombo(displayName, displayCharms);
  }

  private String getDisplayName(IGenericCombo combo) {
    String name = combo.getName();
    return name == null ? "???" : name;
  }

  private String getCharmString(IGenericCombo combo) {
    CharmPrintNameTransformer transformer = new CharmPrintNameTransformer(resources);
    String[] charmNames = net.sf.anathema.lib.lang.ArrayUtilities.transform(combo.getCharms(), String.class, transformer);
    return Joiner.on(", ").join(charmNames);
  }
}
