package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterTemplateResourceProvider;
import net.sf.anathema.character.generic.framework.module.object.ICharacterModuleObject;
import net.sf.anathema.initialization.InitializationException;
import net.sf.anathema.lib.resources.IResources;

public interface ICharacterModule<M extends ICharacterModuleObject> {

  void initModuleObject(ICharacterGenerics characterGenerics);

  void addCharacterTemplates(ICharacterTemplateResourceProvider templates, ICharacterGenerics characterGenerics);

  void addBackgroundTemplates(ICharacterGenerics generics);

  void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException;

  void addReportTemplates(ICharacterGenerics generics, IResources resources);

  void registerCommonData(ICharacterGenerics characterGenerics);

  M getModuleObject();
}
