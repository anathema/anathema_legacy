package net.sf.anathema.character.thaumaturgy.model;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.framework.additionaltemplate.model.ITraitContext;
import net.sf.anathema.character.generic.impl.traits.SimpleTraitTemplate;
import net.sf.anathema.character.library.trait.DefaultTrait;
import net.sf.anathema.character.library.trait.IValueChangeChecker;
import net.sf.anathema.character.library.trait.TraitType;
import net.sf.anathema.character.library.trait.rules.TraitRules;

public abstract class ThaumaturgyMagic extends DefaultTrait implements IThaumaturgyMagic {

  public static final int ALLOWED_DOT_COUNT = 3;
  private final String art;
  private final String procedure;

  public ThaumaturgyMagic(String art, String procedure, int minValue, ITraitContext context,
		  IGenericTraitCollection collection, IValueChangeChecker checker) {
    super(new TraitRules(new TraitType(getName(art, procedure)), //$NON-NLS-1$
        SimpleTraitTemplate.createStaticLimitedTemplate(minValue, ALLOWED_DOT_COUNT),
        context.getLimitationContext()), context, checker);
    this.art = art;
    this.procedure = procedure;
  }

  public String getArt() {
    return art;
  }
  
  public String getProcedure() {
	    return procedure;
	  }
  
  private static String getName(String art, String procedure)
  {
	  return art + (procedure != null ? "(" + procedure + ")" : ""); 
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ThaumaturgyMagic)) {
      return false;
    }
    ThaumaturgyMagic other = (ThaumaturgyMagic) obj;
    return super.equals(obj) && other.getArt().equals(getArt()) &&
    		((other.getProcedure() == null && getProcedure() == null) || 
    		 (other.getProcedure() != null && getProcedure() != null && other.getProcedure().equals(getProcedure())));
  }
}