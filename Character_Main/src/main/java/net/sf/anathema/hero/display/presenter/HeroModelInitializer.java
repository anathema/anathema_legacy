package net.sf.anathema.hero.display.presenter;

import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.framework.environment.Environment;
import net.sf.anathema.hero.model.Hero;

public interface HeroModelInitializer {

  void initialize(SectionView sectionView, Hero hero, Environment environment);
}
