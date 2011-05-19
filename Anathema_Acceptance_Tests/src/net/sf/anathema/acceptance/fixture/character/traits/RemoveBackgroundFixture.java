package net.sf.anathema.acceptance.fixture.character.traits;

import net.sf.anathema.character.generic.backgrounds.IBackgroundTemplate;
import net.sf.anathema.character.model.background.IBackground;
import net.sf.anathema.character.model.background.IBackgroundConfiguration;

public class RemoveBackgroundFixture extends AbstractBackgroundFixture {

  @Override
  public void enterRow() throws Exception {
    IBackgroundTemplate template = getTraitType();
    IBackgroundConfiguration backgrounds = getCharacterStatistics().getTraitConfiguration().getBackgrounds();
    IBackground background = backgrounds.getBackgroundByTemplate(template);
    backgrounds.removeBackground(background);
  }
}