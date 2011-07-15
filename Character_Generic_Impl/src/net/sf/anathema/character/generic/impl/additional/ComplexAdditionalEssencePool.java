package net.sf.anathema.character.generic.impl.additional;

import java.util.HashMap;
import java.util.Map;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.impl.util.AnathemaExpressionParameter;
import net.sf.anathema.lib.util.IIdentificate;

import com.eteks.parser.CompilationException;
import com.eteks.parser.CompiledExpression;
import com.eteks.parser.ExpressionParser;
import com.eteks.parser.IntegerInterpreter;

public class ComplexAdditionalEssencePool implements IIdentificate {

  private final String id;
  private final CompiledExpression expression;
  private final Map<Integer, Integer> override = new HashMap<Integer, Integer>();
  private final AnathemaExpressionParameter parameter = new AnathemaExpressionParameter();
  private final ExpressionParser parser = new ExpressionParser(parameter);
  
  public ComplexAdditionalEssencePool(String id, int multiplier) {
    this.id = id;
    CompiledExpression expression = null;
    try {
      expression = parser.compileExpression("=x*" + multiplier);
    }
    catch (CompilationException e) {
      assert false : e;
    }
    finally {
      this.expression = expression;
    }
  }

  public ComplexAdditionalEssencePool(String id, String formula) throws CompilationException {
    this.id = id;
    this.expression = parser.compileExpression(formula);
  }
  
  public String getId() {
    return id;
  }

  public void setFixedValue(int value, int pool) {
    override.put(value, pool);
  }

  public int getPool(IGenericTraitCollection traitCollection,
                     IMagicCollection magicCollection,
                     int x) {
    if (override.containsKey(x)) {
      return override.get(x);
    }
    
    parameter.setTraitCollection(traitCollection);
    parameter.setParameter(x);
    return (Integer)expression.computeExpression(new IntegerInterpreter());
  }
}