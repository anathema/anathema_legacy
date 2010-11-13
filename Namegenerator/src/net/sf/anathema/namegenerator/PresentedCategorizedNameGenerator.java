package net.sf.anathema.namegenerator;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import net.sf.anathema.namegenerator.domain.category.CategorizedTokenNameFactory;
import net.sf.anathema.namegenerator.domain.category.CategorizedTokenNameTemplate;
import net.sf.anathema.namegenerator.domain.category.ICategorizedTokenConfiguration;
import net.sf.anathema.namegenerator.domain.category.TokenCategory;
import net.sf.anathema.namegenerator.view.category.CategorizedNameGeneratorView;
import net.sf.anathema.namegenerator.view.category.ICategorizedNameGeneratorView;

public class PresentedCategorizedNameGenerator {

  private String createCategorizedTokenName(TokenCategory[] relevantTokens) {
    CategorizedTokenNameFactory factory = new CategorizedTokenNameFactory(tokenRegistry);
    return factory.createName(new CategorizedTokenNameTemplate(relevantTokens));
  }

  private final ICategorizedNameGeneratorView view = new CategorizedNameGeneratorView();
  private final ICategorizedTokenConfiguration tokenRegistry;

  public PresentedCategorizedNameGenerator(ICategorizedTokenConfiguration tokenRegistry) {
    this.tokenRegistry = tokenRegistry;
    new CategorizedNamegeneratorPresenter(view, tokenRegistry).initPresentation();
  }

  private TokenCategory[] getRelevantTokens() {
    final List<TokenCategory> relevantTokens = new ArrayList<TokenCategory>();
    for (Object category : view.getSelectedCategories()) {
      if (category instanceof TokenCategory) {
        relevantTokens.add((TokenCategory) category);
      }
    }
    return relevantTokens.toArray(new TokenCategory[relevantTokens.size()]);
  }

  public String[] createNames(int count) {
    TokenCategory[] relevantTokens = getRelevantTokens();
    if (relevantTokens.length == 0) {
      return new String[0];
    }
    String[] names = new String[count];
    for (int index = 0; index < names.length; index++) {
      names[index] = createCategorizedTokenName(relevantTokens);
    }
    return names;
  }

  public JComponent getViewComponent() {
    return view.getComponent();
  }
}