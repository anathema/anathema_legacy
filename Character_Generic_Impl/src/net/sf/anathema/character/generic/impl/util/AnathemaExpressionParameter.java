package net.sf.anathema.character.generic.impl.util;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

import com.eteks.parser.ExpressionParameter;

public class AnathemaExpressionParameter implements ExpressionParameter {
  
  private static final long serialVersionUID = 300038980886900074L;
  
  private IGenericTraitCollection traitCollection;
  
  private Object x;
  
  public void setTraitCollection(IGenericTraitCollection traitCollection) {
    this.traitCollection = traitCollection;
  }
  
  public void setParameter(Object x) {
    this.x = x;
  }

  @Override
  public Object getParameterKey(String parameter) {
    String[] splitParameter = parameter.split("\\.", 2);
    
    if (splitParameter.length == 2) {
      String type = splitParameter[0];
      String name = splitParameter[1];
      
      try {
        if (type.equals("Ability")) {
          return AbilityType.valueOf(name);
        }
        else if (type.equals("Attribute")) {
          return AttributeType.valueOf(name);
        }
        else if (type.equals("Other")) {
          return OtherTraitType.valueOf(name);
        }
        else if (type.equals("Virtue")) {
          return VirtueType.valueOf(name);
        }
        else if (type.equals("Yozi")) {
          return YoziType.valueOf(name);
        }
      }
      catch (IllegalArgumentException e) {
          //nothing to do
      }
    }
    else if (splitParameter.length == 1) {
      if (parameter.equals("x")) {
        return parameter;
      }
    }
    return null;
  }

  @Override
  public Object getParameterValue(Object key) {
    try {
      if (key instanceof ITraitType) {
        return traitCollection.getTrait((ITraitType)key).getCurrentValue();
      }
      else if (key.equals("x")) {
        return x;
      }
    }
    catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
    throw new IllegalArgumentException("Invalid parameter key");
  }
}
