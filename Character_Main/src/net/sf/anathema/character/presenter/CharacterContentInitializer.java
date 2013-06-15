package net.sf.anathema.character.presenter;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.framework.additionaltemplate.IAdditionalInitializer;
import net.sf.anathema.character.generic.type.ICharacterType;
import net.sf.anathema.character.model.CharacterModelGroup;
import net.sf.anathema.character.model.ICharacter;
import net.sf.anathema.character.view.SectionView;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.lib.registry.IRegistry;
import net.sf.anathema.lib.resources.Resources;

import static net.sf.anathema.character.generic.framework.CharacterGenericsExtractor.getGenerics;

public class CharacterContentInitializer {


  private final IApplicationModel anathemaModel;
  private final Resources resources;
  private final ICharacter character;

  public CharacterContentInitializer(IApplicationModel anathemaModel, Resources resources, ICharacter character) {
    this.anathemaModel = anathemaModel;
    this.resources = resources;
    this.character = character;
  }

  public void initialize(SectionView sectionView, CharacterModelGroup group) {
    IRegistry<String, IAdditionalInitializer> factoryRegistry = getGenerics(
            anathemaModel).getAdditionalInitializerRegistry();
    for (IAdditionalModel model : character.getExtendedConfiguration().getAdditionalModels(group)) {
      IAdditionalInitializer initializer = factoryRegistry.get(model.getTemplateId());
      if (initializer == null) {
        continue;
      }
      String viewName = getString("AdditionalTemplateView.TabName." + model.getTemplateId());
      ICharacterType characterType = character.getCharacterTemplate().getTemplateType().getCharacterType();
      Object view = sectionView.addView(viewName, initializer.getViewClass(), characterType);
      initializer.initialize(model, resources, characterType, view);
    }
  }

  private String getString(String resourceKey) {
    return resources.getString(resourceKey);
  }
}