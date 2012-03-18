package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.magic.description.AggregatedCharmDescriptionProvider;
import net.sf.anathema.character.generic.magic.description.CharmDescriptionProvider;
import net.sf.anathema.character.generic.magic.description.CharmDescriptionProviderFactory;
import net.sf.anathema.character.generic.magic.description.RegisteredCharmDescriptionProviderFactory;
import net.sf.anathema.framework.IAnathemaModel;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.resources.IResources;

import java.util.Collection;

public class CharmDescriptionProviderExtractor {

  public static CharmDescriptionProvider CreateFor(IAnathemaModel model, IResources resources) {
    AggregatedCharmDescriptionProvider provider = new AggregatedCharmDescriptionProvider(resources);
    Collection<CharmDescriptionProviderFactory> factories = findFactories(model);
    for (CharmDescriptionProviderFactory factory : factories) {
      provider.addProvider(factory.create(model));
    }
    return provider;
  }

  private static Collection<CharmDescriptionProviderFactory> findFactories(IAnathemaModel model) {
    Instantiater instantiater = CharacterGenericsExtractor.getGenerics(model).getInstantiater();
    return instantiater.instantiateAll(RegisteredCharmDescriptionProviderFactory.class);
  }


}
