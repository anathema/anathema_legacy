package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.resources.IResources;

public interface ICharacterModule {

  void initModuleObject(ICharacterGenerics characterGenerics, IResources resources);

  void addCharacterTemplates(ICharacterGenerics characterGenerics);

  void addBackgroundTemplates(ICharacterGenerics generics);

  void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException;

  void registerCommonData(ICharacterGenerics characterGenerics);
}