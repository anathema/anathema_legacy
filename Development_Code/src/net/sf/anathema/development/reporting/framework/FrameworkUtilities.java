package net.sf.anathema.development.reporting.framework;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.ILimitationContext;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterListening;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharacterModelContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ICharmContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitValueStrategy;
import net.sf.anathema.character.generic.template.ICharacterTemplate;
import net.sf.anathema.character.impl.model.context.BasicCharacterContext;
import net.sf.anathema.character.impl.model.context.CharacterListening;
import net.sf.anathema.character.impl.model.context.magic.CreationCharmLearnStrategy;
import net.sf.anathema.character.impl.model.context.trait.CreationTraitValueStrategy;
import net.sf.anathema.dummy.character.DummyGenericCharacter;
import net.sf.anathema.dummy.character.charm.DummyCharmContext;

public class FrameworkUtilities {

  public static ICharacterModelContext createDummyModelContext(ICharacterTemplate template) {
    final CreationTraitValueStrategy strategy = new CreationTraitValueStrategy();
    final CreationCharmLearnStrategy charmLearnStrategy = new CreationCharmLearnStrategy();
    final DummyGenericCharacter genericCharacter = new DummyGenericCharacter(template);
    final ITraitContext traitContext = new ITraitContext() {

      public ITraitValueStrategy getTraitValueStrategy() {
        return strategy;
      }

      public ILimitationContext getLimitationContext() {
        return genericCharacter;
      }
    };
    return new ICharacterModelContext() {
      private final ICharmContext charmContext = new DummyCharmContext(genericCharacter, charmLearnStrategy);
      private final CharacterListening characterListening = new CharacterListening();

      public IAdditionalRules getAdditionalRules() {
        return genericCharacter.getTemplate().getAdditionalRules();
      }

      public ICharmContext getCharmContext() {
        return charmContext;
      }

      public IMagicCollection getMagicCollection() {
        return genericCharacter;
      }

      public IGenericTraitCollection getTraitCollection() {
        return genericCharacter;
      }

      public ITraitContext getTraitContext() {
        return traitContext;
      }

      public ICharacterListening getCharacterListening() {
        return characterListening;
      }
      
      public IBasicCharacterData getBasicCharacterContext() {
        return new BasicCharacterContext(genericCharacter);
      }
    };
  }
}