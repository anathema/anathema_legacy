package net.sf.anathema.character.main.magic.view;

import net.sf.anathema.character.main.framework.CharacterGenericsExtractor;
import net.sf.anathema.character.main.magic.description.AggregatedCharmDescriptionProvider;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProvider;
import net.sf.anathema.character.main.magic.description.MagicDescriptionProviderFactory;
import net.sf.anathema.character.main.magic.description.RegisteredMagicDescriptionProviderFactory;
import net.sf.anathema.framework.IApplicationModel;
import net.sf.anathema.initialization.ObjectFactory;
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
    ObjectFactory objectFactory = CharacterGenericsExtractor.getGenerics(model).getInstantiater();
    return objectFactory.instantiateAll(RegisteredMagicDescriptionProviderFactory.class);
  }

}
