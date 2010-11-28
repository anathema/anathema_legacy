package net.sf.anathema.namegenerator.domain.category;

import net.sf.anathema.namegenerator.domain.general.ConcatenatedNameTokenFactory;
import net.sf.anathema.namegenerator.domain.general.INameTokenFactory;
import net.sf.anathema.namegenerator.domain.general.SpaceTokenFactory;

public class CategorizedTokenNameFactory {

  private ICategorizedTokenConfiguration categorizedTokenRegistry;

  public CategorizedTokenNameFactory(ICategorizedTokenConfiguration categorizedTokenRegistry) {
    this.categorizedTokenRegistry = categorizedTokenRegistry;
  }

  public String createName(ICategorizedTokenNameTemplate template) {
    TokenCategory[] categories = template.getCategories();
    return new ConcatenatedNameTokenFactory(createFactories(categories)).createToken();
  }

  private INameTokenFactory[] createFactories(TokenCategory[] categories) {
    INameTokenFactory[] factories = new INameTokenFactory[(categories.length - 1) * 2 + 1];
    for (int index = 0; index < categories.length; index++) {
      int tokenIndex = 2 * index;
      factories[tokenIndex] = categorizedTokenRegistry.createTokenFactory(categories[index]);
      if (tokenIndex + 1 < factories.length) {
        factories[tokenIndex + 1] = new SpaceTokenFactory();
      }
    }
    return factories;
  }
}