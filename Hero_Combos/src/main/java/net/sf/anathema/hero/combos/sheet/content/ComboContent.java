package net.sf.anathema.hero.combos.sheet.content;

import net.sf.anathema.hero.combos.display.presenter.Combo;
import net.sf.anathema.hero.combos.model.CombosModelFetcher;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.AbstractSubBoxContent;
import net.sf.anathema.framework.environment.Resources;

import java.util.ArrayList;
import java.util.Collections;
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
    return getCombosAsList().size() > 0;
  }

  public List<DisplayCombo> getCombos() {
    List<Combo> combos = getCombosAsList();
    return transform(combos, new ToDisplayCombo(getResources()));
  }

  private List<Combo> getCombosAsList() {
    List<Combo> genericCombos = new ArrayList<>();
    Collections.addAll(genericCombos, CombosModelFetcher.fetch(hero).getAllCombos());
    return genericCombos;
  }
}
