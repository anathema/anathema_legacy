package net.sf.anathema.character.generic.framework;

import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.framework.extension.IAnathemaExtension;
import net.sf.anathema.lib.registry.IRegistry;

public class CharacterGenericsExtractor {

  public static ICharacterGenerics getGenerics(IApplicationModel model) {
    IRegistry<String, IAnathemaExtension> registry = model.getExtensionPointRegistry();
    ICharacterGenericsExtension genericsExtension = (ICharacterGenericsExtension) registry.get(ICharacterGenericsExtension.ID);
    return genericsExtension.getCharacterGenerics();
  }
}
