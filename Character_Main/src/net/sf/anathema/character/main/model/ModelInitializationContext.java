package net.sf.anathema.character.main.model;

import net.sf.anathema.character.change.ChangeAnnouncer;
import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.TraitContext;
import net.sf.anathema.character.generic.template.HeroTemplate;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.main.model.change.ChangeAnnouncerAdapter;

import java.util.List;

public class ModelInitializationContext implements InitializationContext {

  private ICharacterModelContext context;
  private Hero hero;
  private HeroTemplate template;

  public ModelInitializationContext(ICharacterModelContext context, Hero hero, HeroTemplate template) {
    this.context = context;
    this.hero = hero;
    this.template = template;
  }

  @Override
  @Deprecated
  public IBasicCharacterData getBasicCharacterContext() {
    return context.getBasicCharacterContext();
  }

  @Override
  @Deprecated
  public ICharacterListening getCharacterListening() {
    return context.getCharacterListening();
  }

  @Override
  @Deprecated
  public TraitContext getTraitContext() {
    return context.getTraitContext();
  }

  @Override
  @Deprecated
  public IMagicCollection getMagicCollection() {
    return context.getMagicCollection();
  }

  @Override
  @Deprecated
  public ICharmContext getCharmContext() {
    return context.getCharmContext();
  }

  @Override
  public <T> List<T> getAllRegistered(Class<T> interfaceClass) {
    return context.getAllRegistered(interfaceClass);
  }

  @Override
  public HeroTemplate getTemplate() {
    return template;
  }

  @Override
  public ChangeAnnouncer getChangeAnnouncer() {
    CharacterListening listening = (CharacterListening) context.getCharacterListening();
    return new ChangeAnnouncerAdapter(listening, hero);
  }
}
