package net.sf.anathema.hero.traits.display;

import net.sf.anathema.character.main.CharacterUI;
import net.sf.anathema.character.main.library.trait.favorable.FavorableState;
import net.sf.anathema.character.main.template.presentation.IPresentationProperties;
import net.sf.anathema.character.main.type.CharacterType;
import net.sf.anathema.hero.concept.CasteType;
import net.sf.anathema.hero.concept.HeroConceptFetcher;
import net.sf.anathema.hero.concept.display.caste.presenter.CasteUI;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.interaction.Tool;
import net.sf.anathema.lib.file.RelativePath;
import net.sf.anathema.lib.gui.AgnosticUIConfiguration;

public class FavoredIconSelector {
  private final Tool tool;
  private final IPresentationProperties presentationProperties;

  public FavoredIconSelector(Tool tool, IPresentationProperties presentationProperties) {
    this.tool = tool;
    this.presentationProperties = presentationProperties;
  }


  public void setIconFor(Hero hero, FavorableState state) {
    RelativePath path = determineIconPath(hero, state);
    tool.setIcon(path);
  }

  private RelativePath determineIconPath(Hero hero, FavorableState state) {
    if (state == FavorableState.Caste) {
      CasteType casteType = HeroConceptFetcher.fetch(hero).getCaste().getType();
      return new CasteUI(presentationProperties).getSmallCasteIconPath(casteType);
    }
    if (state == FavorableState.Favored) {
      CharacterType characterType = hero.getTemplate().getTemplateType().getCharacterType();
      return new CharacterUI().getMediumBallPath(characterType);
    }
    return AgnosticUIConfiguration.NO_ICON;
  }
}