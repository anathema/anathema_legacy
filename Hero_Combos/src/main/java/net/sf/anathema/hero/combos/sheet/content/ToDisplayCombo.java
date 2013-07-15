package net.sf.anathema.hero.combos.sheet.content;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import net.sf.anathema.hero.charms.sheet.content.CharmPrintNameTransformer;
import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.lib.resources.Resources;

public class ToDisplayCombo implements Function<Combo, DisplayCombo> {

  private Resources resources;

  public ToDisplayCombo(Resources resources) {
    this.resources = resources;
  }

  @Override
  public DisplayCombo apply(Combo combo) {
    String displayName = getDisplayName(combo);
    String displayCharms = getCharmString(combo);
    return new DisplayCombo(displayName, displayCharms);
  }

  private String getDisplayName(Combo combo) {
    String name = combo.getName().getText();
    return name == null ? "???" : name;
  }

  private String getCharmString(Combo combo) {
    CharmPrintNameTransformer transformer = new CharmPrintNameTransformer(resources);
    String[] charmNames = net.sf.anathema.lib.lang.ArrayUtilities.transform(combo.getCharms(), String.class, transformer);
    return Joiner.on(", ").join(charmNames);
  }
}
