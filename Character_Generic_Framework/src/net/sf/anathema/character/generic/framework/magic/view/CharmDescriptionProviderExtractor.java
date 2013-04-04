package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.character.generic.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.generic.magic.description.AggregatedCharmDescriptionProvider;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.generic.magic.description.MagicDescriptionProviderFactory;
import net.sf.anathema.character.generic.magic.description.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.Instantiater;
import net.sf.anathema.lib.resources.Resources;

import java.util.Collection;

public class CharmDescriptionProviderExtractor {

  public static MagicDescriptionProvider CreateFor(IApplicationModel model, Resources resources) {
    AggregatedCharmDescriptionProvider provider = new AggregatedCharmDescriptionProvider(resources);
    Collection<MagicDescriptionProviderFactory> factories = findFactories(model);
    for (MagicDescriptionProviderFactory factory : factories) {
      provider.addProvider(factory.create(model));
    }
    return provider;
  }

  private static Collection<MagicDescriptionProviderFactory> findFactories(IApplicationModel model) {
    Instantiater instantiater = CharacterGenericsExtractor.getGenerics(model).getInstantiater();
    return instantiater.instantiateAll(RegisteredMagicDescriptionProviderFactory.class);
  }

}
