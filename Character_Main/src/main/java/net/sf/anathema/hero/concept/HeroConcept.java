package net.sf.anathema.hero.concept;

import net.sf.anathema.character.framework.IIntegerDescription;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface HeroConcept extends HeroModel {

  public static final Identifier ID = new SimpleIdentifier("Concept");

  CasteSelection getCaste();

  CasteCollection getCasteCollection();

  IIntegerDescription getAge();
}