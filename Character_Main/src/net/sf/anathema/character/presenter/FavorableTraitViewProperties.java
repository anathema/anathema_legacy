package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.framework.resources.CharacterUI;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;
import net.sf.anathema.character.library.intvalue.IIconToggleButtonProperties;
import net.sf.anathema.character.library.trait.Trait;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.character.main.model.concept.HeroConceptFetcher;
import net.sf.anathema.character.main.model.experience.ExperienceModelFetcher;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public class FavorableTraitViewProperties implements IIconToggleButtonProperties {

  private final Trait trait;
  private Hero hero;
  private final IPresentationProperties properties;

  public FavorableTraitViewProperties(Hero hero, IPresentationProperties properties, Trait trait) {
    this.hero = hero;
    this.properties = properties;
    this.trait = trait;
  }

  @Override
  public RelativePath createStandardIcon() {
    CasteUI casteUI = new CasteUI(properties);
    if (ExperienceModelFetcher.fetch(hero).isExperienced() && !trait.getFavorization().isCasteOrFavored()) {
      return AgnosticUIConfiguration.NO_ICON;
    }
    if (trait.getFavorization().isCaste()) {
      return casteUI.getSmallCasteIconPath(HeroConceptFetcher.fetch(hero).getCaste().getType());
    }
    return new CharacterUI().getMediumBallPath(hero.getTemplate().getTemplateType().getCharacterType());
  }

  @Override
  public RelativePath createUnselectedIcon() {
    return AgnosticUIConfiguration.NO_ICON;
  }

  @Override
  public String getToolTipText() {
    return AgnosticUIConfiguration.NO_TOOLTIP;
  }
}