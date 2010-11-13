package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.acceptance.fixture.character.CharacterSummary;
import net.sf.anathema.character.library.trait.visitor.IDefaultTrait;
import fit.RowFixture;

public class BackgroundListFixture extends RowFixture {

  @Override
  public Object[] query() throws Exception {
    CharacterSummary characterSummary = new CharacterSummary(summary);
    IDefaultTrait[] allBackgrounds = characterSummary.getCharacter()
        .getStatistics()
        .getTraitConfiguration()
        .getBackgrounds()
        .getBackgrounds();
    AcceptanceBackground[] backgrounds = new AcceptanceBackground[allBackgrounds.length];
    for (int index = 0; index < allBackgrounds.length; index++) {
      backgrounds[index] = new AcceptanceBackground(allBackgrounds[index]);
    }
    return backgrounds;
  }

  @Override
  public Class< ? > getTargetClass() {
    return AcceptanceBackground.class;
  }
}