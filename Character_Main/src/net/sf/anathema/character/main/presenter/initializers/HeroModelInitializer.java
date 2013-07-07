package net.sf.anathema.character.main.presenter.initializers;

import net.sf.anathema.character.main.view.SectionView;
import net.sf.anathema.hero.model.Hero;
import net.sf.anathema.lib.resources.Resources;

/**Most implementers need to be annotated as <link>@RegisteredInitializer</link> and have a constructor for ApplicationModel as its sole argument.*/
public interface HeroModelInitializer {

  void initialize(SectionView sectionView, Hero hero, Resources resources);
}
