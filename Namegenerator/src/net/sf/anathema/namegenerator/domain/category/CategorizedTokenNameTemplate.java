package net.sf.anathema.namegenerator.domain.category;

public class CategorizedTokenNameTemplate implements ICategorizedTokenNameTemplate {

  private final TokenCategory[] categories;

  public CategorizedTokenNameTemplate(TokenCategory[] categories) {
    this.categories = categories;
  }

  public TokenCategory[] getCategories() {
    return categories;
  }
}