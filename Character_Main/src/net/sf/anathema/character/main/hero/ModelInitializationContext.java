package net.sf.anathema.character.main.hero;

import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.impl.template.magic.ICharmProvider;
import net.sf.anathema.character.generic.template.ITemplateRegistry;
import net.sf.anathema.character.generic.type.CharacterTypes;

import java.util.List;

public class ModelInitializationContext implements InitializationContext {

  private ICharacterModelContext context;
  private ICharacterGenerics generics;

  public ModelInitializationContext(ICharacterModelContext context, ICharacterGenerics generics) {
    this.context = context;
    this.generics = generics;
  }

  @Override
  @Deprecated
  public IMagicCollection getMagicCollection() {
    return context.getMagicCollection();
  }

  @Override
  public <T> List<T> getAllRegistered(Class<T> interfaceClass) {
    return context.getAllRegistered(interfaceClass);
  }

  @Override
  public CharacterTypes getCharacterTypes() {
    return generics.getCharacterTypes();
  }

  @Override
  public ITemplateRegistry getTemplateRegistry() {
    return generics.getTemplateRegistry();
  }

  @Override
  public ICharmProvider getCharmProvider() {
    return generics.getCharmProvider();
  }
}
