package net.sf.anathema.namegenerator.domain.category;

public class AggregatedTokenCategory extends TokenCategory {

  private final TokenCategory[] subCategories;

  public AggregatedTokenCategory(String id, TokenCategory[] subCategories) {
    super(id);
    this.subCategories = subCategories;
  }

  public TokenCategory[] getSubCateogories() {
    return subCategories;
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}