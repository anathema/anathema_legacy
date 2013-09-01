package net.sf.anathema.hero.charms.sheet.content;

import net.sf.anathema.character.main.traits.TraitType;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.hero.sheet.pdf.content.SubBoxContent;
import net.sf.anathema.framework.environment.Resources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.hero.sheet.pdf.page.IVoidStateFormatConstants.TYPE_LONG_FORM_CUTOFF;

public class GenericCharmContent implements SubBoxContent {

  private Resources resources;
  private Hero hero;

  public GenericCharmContent(Resources resources, Hero hero) {
    this.resources = resources;
    this.hero = hero;
  }

  @Override
  public String getHeader() {
    return resources.getString("Sheet.Header.GenericCharms");
  }

  @Override
  public boolean hasContent() {
    return getGenericCharmCount() > 0;
  }

  public List<String> getTraitLabels() {
    List<String> traits = new ArrayList<>();
    for (TraitType trait : getTraits()) {
      String text = getTraitLabel(trait);
      traits.add(text);
    }
    return traits;
  }

  private String getTraitLabel(TraitType trait) {
    String text = resources.getString(trait.getId());
    if (text.length() >= TYPE_LONG_FORM_CUTOFF) {
      return resources.getString(trait.getId() + ".Short");
    }
    return text;
  }

  private List<TraitType> getTraits() {
    return new GenericCharmContentHelper(hero).getGenericCharmTraits();
  }

  public int getGenericCharmCount() {
    return new GenericCharmContentHelper(hero).getDisplayedGenericCharmCount();
  }
}
