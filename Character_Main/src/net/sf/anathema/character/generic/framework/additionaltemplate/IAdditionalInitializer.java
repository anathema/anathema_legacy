package net.sf.anathema.character.generic.framework.additionaltemplate;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.lib.resources.Resources;

public interface IAdditionalInitializer {

  void initialize(SectionView sectionView, ICharacter character, Resources resources, IAdditionalModel model);
}