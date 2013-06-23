package net.sf.anathema.character.reporting.pdf.content.magic;

import net.sf.anathema.character.generic.character.IGenericCharacter;
import net.sf.anathema.character.generic.traits.TraitType;
import net.sf.anathema.character.reporting.pdf.content.SubBoxContent;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

import java.util.ArrayList;
import java.util.List;

import static net.sf.anathema.character.reporting.pdf.rendering.page.IVoidStateFormatConstants.TYPE_LONG_FORM_CUTOFF;

public class GenericCharmContent implements SubBoxContent {

  private Resources resources;
  private Hero hero;
  private IGenericCharacter character;

  public GenericCharmContent(Resources resources, Hero hero, IGenericCharacter character) {
    this.resources = resources;
    this.hero = hero;
    this.character = character;
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
    return GenericCharmUtilities.getGenericCharmTraits(hero);
  }

  public int getGenericCharmCount() {
    return GenericCharmUtilities.getDisplayedGenericCharmCount(character);
  }
}
