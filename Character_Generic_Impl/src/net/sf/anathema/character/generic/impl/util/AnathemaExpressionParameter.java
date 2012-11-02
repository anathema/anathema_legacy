package net.sf.anathema.character.generic.impl.util;

import com.eteks.parser.ExpressionParameter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;
import net.sf.anathema.character.generic.traits.types.YoziType;

public class AnathemaExpressionParameter implements ExpressionParameter {
  
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
        switch (type) {
          case "Ability":
            return AbilityType.valueOf(name);
          case "Attribute":
            return AttributeType.valueOf(name);
          case "Other":
            return OtherTraitType.valueOf(name);
          case "Virtue":
            return VirtueType.valueOf(name);
          case "Yozi":
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
