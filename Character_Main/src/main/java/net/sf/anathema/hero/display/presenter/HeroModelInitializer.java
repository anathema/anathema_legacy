package net.sf.anathema.hero.display.presenter;

import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.framework.environment.Resources;

public interface HeroModelInitializer {

  void initialize(SectionView sectionView, Hero hero, Resources resources);
}
