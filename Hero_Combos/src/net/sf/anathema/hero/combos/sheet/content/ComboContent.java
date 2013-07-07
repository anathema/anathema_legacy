package net.sf.anathema.hero.combos.sheet.content;

import net.sf.anathema.character.main.GenericCombo;
import net.sf.anathema.character.main.magic.IGenericCombo;
import net.sf.anathema.character.main.model.combos.CombosModelFetcher;
import net.sf.anathema.character.main.charm.ICombo;
import net.sf.anathema.character.reporting.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.transform;

public class ComboContent extends AbstractSubBoxContent {

  private Hero hero;

  public ComboContent(Hero hero, Resources resources) {
    super(resources);
    this.hero = hero;
  }

  @Override
  public String getHeaderKey() {
    return "Combos";
  }

  @Override
  public boolean hasContent() {
    return getGenericCombos().size() > 0;
  }

  public List<DisplayCombo> getCombos() {
    return transform(getGenericCombos(), new ToDisplayCombo(getResources()));
  }

  private List<IGenericCombo> getGenericCombos() {
    List<IGenericCombo> genericCombos = new ArrayList<>();
    for (ICombo combo : CombosModelFetcher.fetch(hero).getAllCombos()) {
      genericCombos.add(new GenericCombo(combo));
    }
    return genericCombos;
  }
}
