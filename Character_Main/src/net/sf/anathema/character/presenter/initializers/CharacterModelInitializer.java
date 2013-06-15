package net.sf.anathema.character.presenter.initializers;

import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

/**Most implementers need to be annotated as <link>@RegisteredInitializer</link> and have a constructor for ApplicationModel as its sole argument.*/
public interface CharacterModelInitializer {
  void initialize(SectionView sectionView, ICharacter character, Resources resources);
}
