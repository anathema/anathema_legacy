package net.sf.anathema.character.generic.impl.util;

import com.eteks.parser.ExpressionParameter;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.traits.ITraitType;
import net.sf.anathema.character.generic.traits.types.AbilityType;
import net.sf.anathema.character.generic.traits.types.AttributeType;
import net.sf.anathema.character.generic.traits.types.OtherTraitType;
import net.sf.anathema.character.generic.traits.types.VirtueType;

public class ComplexPoolExpressionParameter implements ExpressionParameter {
  
  private IGenericTraitCollection traitCollection;
  
  private int variableValue;
  
  public void setTraitCollection(IGenericTraitCollection traitCollection) {
    this.traitCollection = traitCollection;
  }
  
  public void setParameter(int currentValue) {
    this.variableValue = currentValue;
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
        return variableValue;
      }
    }
    catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
    throw new IllegalArgumentException("Invalid parameter key");
  }
}