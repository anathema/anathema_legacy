package net.sf.anathema.namegenerator.domain.category;

import net.sf.anathema.namegenerator.domain.general.INameTokenFactory;

public interface ICategorizedTokenConfiguration {

  TokenCategory[] getRootTokenCategories();

  INameTokenFactory createTokenFactory(TokenCategory tokenCategory);

}