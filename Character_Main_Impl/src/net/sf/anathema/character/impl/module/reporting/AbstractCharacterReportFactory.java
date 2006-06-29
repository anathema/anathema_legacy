package net.sf.anathema.character.impl.module.reporting;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.ICharacterGenericsExtension;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.framework.initialization.IReportFactory;
import net.sf.anathema.lib.registry.IRegistry;

public abstract class AbstractCharacterReportFactory implements IReportFactory {

  protected final ICharacterGenerics getCharacterGenerics(IRegistry<String, IAnathemaExtension> extensionPointRegistry) {
    ICharacterGenericsExtension extension = (ICharacterGenericsExtension) extensionPointRegistry.get(ICharacterGenericsExtension.ID);
    return extension.getCharacterGenerics();
  }
}