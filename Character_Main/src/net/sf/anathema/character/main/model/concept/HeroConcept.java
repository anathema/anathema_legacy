package net.sf.anathema.character.main.model.concept;

import net.sf.anathema.character.generic.caste.CasteType;
import net.sf.anathema.character.model.IIntegerDescription;
import net.sf.anathema.character.model.ITypedDescription;
import net.sf.anathema.hero.model.HeroModel;
import net.sf.anathema.lib.util.Identifier;
import net.sf.anathema.lib.util.SimpleIdentifier;

public interface HeroConcept extends HeroModel {

  public static final Identifier ID = new SimpleIdentifier("Concept");

  ITypedDescription<CasteType> getCaste();

  IIntegerDescription getAge();
}