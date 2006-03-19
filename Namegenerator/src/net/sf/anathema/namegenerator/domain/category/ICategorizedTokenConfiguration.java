package net.sf.anathema.namegenerator.domain.category;

import net.sf.anathema.namegenerator.domain.general.INameTokenFactory;

public interface ICategorizedTokenConfiguration {

  public TokenCategory[] getRootTokenCategories();

  public INameTokenFactory createTokenFactory(TokenCategory tokenCategory);

}