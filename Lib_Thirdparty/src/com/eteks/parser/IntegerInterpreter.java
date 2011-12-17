package com.eteks.parser;

public class IntegerInterpreter implements Interpreter {

  /**
   * The integer constant matching the constant FALSE (equal to <code>new Integer(0)</code>).
   */
  public static final Integer FALSE_INT = 0;
  /**
   * The integer constant matching the constant TRUE (equal to <code>new Integer(1)</code>).
   */
  public static final Integer TRUE_INT  = 1;

  /**
   * Returns the value of the literal <code>literal</code>. <code>literal</code>
   * may be an instance of <code>Integer</code> or <code>Long</code>.
   * @param literal an instance of <code>Integer</code>.
   * @return the value of the literal. The returned value is an instance of <code>Integer</code>.
   * @throws IllegalArgumentException if <code>literal</code> isn't an instance of <code>Integer</code>.
   */
  public Object getLiteralValue (Object literal)
  {
    if (literal instanceof Integer)
      return literal;
    else if (literal instanceof Long) {
      return ((Long) literal).intValue();
    }
    else
      throw new IllegalArgumentException ("Literal " + literal + " not an instance of Integer or Long (type is " + literal.getClass().getName() + ")");
  }

  /**
   * Returns the value of the parameter <code>parameter</code>. <code>parameter</code>
   * may be an instance of <code>Integer</code>.
   * This method may throw an exception if the interpreter doesn't accept the type of
   * <code>parameter</code>.
   * @param parameter an instance of <code>Integer</code>.
   * @return the value of the parameter. The returned value is an instance of <code>Integer</code>.
   * @throws IllegalArgumentException if <code>parameter</code> isn't an instance of <code>Number</code>.
   */
  public Object getParameterValue (Object parameter)
  {
    if (parameter instanceof Integer)
      return parameter;
    else
      throw new IllegalArgumentException ("Parameter " + parameter + " not an instance of Integer");
  }

  /**
   * Returns the value of the constant <code>constantKey</code>. <code>constantKey</code>
   * may be the key of a constant of <code>Syntax</code> (one of <code>CONSTANT_PI</code>,
   * <code>CONSTANT_E</code>, <code>CONSTANT_FALSE</code>, <code>CONSTANT_TRUE</code>).
   * @param constantKey the key of a constant of <code>Syntax</code>.
   * @return the value of the constant. The returned value is an instance of <code>Double</code>.
   * @throws IllegalArgumentException if <code>constantKey</code> isn't a key of a constant of
   *         <code>Syntax</code>.
   */
  public Object getConstantValue (Object constantKey)
  {
    if (Syntax.CONSTANT_FALSE.equals (constantKey))
      return FALSE_INT;
    else if (Syntax.CONSTANT_TRUE.equals (constantKey))
      return TRUE_INT;
    else
      throw new IllegalArgumentException ("Constant key " + constantKey + " not implemented");
  }

  /**
   * Returns the value of the operation of the unary operator <code>unaryOperatorKey</code> applied
   * on the operand <code>operand</code>. <code>unaryOperatorKey</code> must be the key
   * of an unary operator of <code>Syntax</code> (one of <code>OPERATOR_POSITIVE</code>,
   * <code>OPERATOR_OPPOSITE</code>, <code>OPERATOR_LOGICAL_NOT</code>, <code>OPERATOR_BITWISE_NOT</code>).
   * @param unaryOperatorKey the key of an unary operator of <code>Syntax</code>.
   * @param operand          the operand (instance of <code>Number</code>).
   * @return the result of the operation. The returned value is an instance of <code>Double</code>.
   * @throws IllegalArgumentException if <code>operand</code> isn't an instance of <code>Number</code>,
   *         if <code>operand</code> isn't an integer operand when <code>unaryOperatorKey</code> is the
   *         key <code>Syntax.OPERATOR_BITWISE_NOT</code> or if <code>unaryOperatorKey</code>
   *         isn't the key of an unary operator of <code>Syntax</code>.
   */
  public Object getUnaryOperatorValue (Object unaryOperatorKey, Object operand)
  {
    if (!(operand instanceof Integer))
      throw new IllegalArgumentException ("Operand " + operand + " not an instance of Integer");

    Integer number = (Integer)operand;

    if (unaryOperatorKey.equals (Syntax.OPERATOR_OPPOSITE))
      return -number;
    else if (unaryOperatorKey.equals (Syntax.OPERATOR_POSITIVE))
      return operand;
    else if (unaryOperatorKey.equals (Syntax.OPERATOR_LOGICAL_NOT))
      return isTrue(operand) ? FALSE_INT : TRUE_INT;
    else if (unaryOperatorKey.equals (Syntax.OPERATOR_BITWISE_NOT))
    {
      return ~((Integer)operand);
    }
    else
      throw new IllegalArgumentException ("Unary operator key " + unaryOperatorKey + " not implemented");
  }

  /**
   * Returns the value of the operation of the binary operator <code>binaryOperatorKey</code> applied on
   * the two operands <code>operand1</code> and <code>operand2</code>. <code>binaryOperatorKey</code>
   * must be the key of a binary operator of <code>Syntax</code> (one of <code>OPERATOR_ADD</code>,
   * <code>OPERATOR_SUBSTRACT</code>, <code>OPERATOR_MULTIPLY</code>, <code>OPERATOR_DIVIDE</code>,...).
   * @param binaryOperatorKey the key of a binary operator of <code>Syntax</code>.
   * @param operand1          the first operand (instance of <code>Number</code>).
   * @param operand2          the second operand (instance of <code>Number</code>).
   * @return the result of the operation. The returned value is an instance of <code>Double</code>.
   * @throws IllegalArgumentException if <code>operand1</code> or <code>operand2</code> aren't
   *         instances of <code>Number</code>, if <code>operand1</code> or <code>operand2</code> are
   *         not integer operands when <code>binaryOperatorKey</code> is the key of a bit operator
   *         (<code>Syntax.OPERATOR_BITWISE_...</code> and Syntax.OPERATOR_SHIFT_...)
   *         or if <code>binaryOperatorKey</code> isn't the key of a binary operator of <code>Syntax</code>.
   */
  public Object getBinaryOperatorValue (Object binaryOperatorKey, Object operand1, Object operand2)
  {
    if (!(operand1 instanceof Integer))
      throw new IllegalArgumentException ("Operand " + operand1 + " not an instance of Integer");
    if (!(operand2 instanceof Integer))
      throw new IllegalArgumentException ("Operand " + operand2 + " not an instance of Integer");

    int number1 = (Integer)operand1;
    int number2 = (Integer)operand2;

    if (binaryOperatorKey.equals (Syntax.OPERATOR_ADD))
      return number1 + number2;
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_SUBSTRACT))
      return number1 - number2;
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_MULTIPLY))
      return number1 * number2;
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_DIVIDE))
      return number1 / number2;
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_POWER))
      return (int)Math.pow(number1, number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_MODULO)) {
      // Removed the pointless computations (kelemen@github.com).
      // This will return the same value as the previous implementation.
      return number1 % number2;
    }
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_REMAINDER))
      return number1 % number2;
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_EQUAL))
      return truthValue(number1 == number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_DIFFERENT))
      return truthValue(number1 != number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_GREATER_OR_EQUAL))
      return truthValue(number1 >= number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_LESS_OR_EQUAL))
      return truthValue(number1 <= number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_GREATER))
      return truthValue(number1 > number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_LESS))
      return truthValue(number1 < number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_LOGICAL_OR))
      return truthValue(isTrue(operand1) || isTrue(operand2));
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_LOGICAL_AND))
      return truthValue(isTrue(operand1) && isTrue(operand2));
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_LOGICAL_XOR))
      return truthValue(isTrue(operand1) != isTrue(operand2));
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_BITWISE_OR))
      return (number1 | number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_BITWISE_XOR))
      return (number1 ^ number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_BITWISE_AND))
      return (number1 & number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_SHIFT_LEFT))
      return (number1 << number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_SHIFT_RIGHT))
      return (number1 >> number2);
    else if (binaryOperatorKey.equals (Syntax.OPERATOR_SHIFT_RIGHT_0))
      return (number1 >>> number2);
    else
      // User binary operators must be implemented in a sub class
      throw new IllegalArgumentException ("Binary operator key " + binaryOperatorKey + " not implemented");
  }

  /**
   * Returns the value of the common function <code>commonFunctionKey</code> with
   * the parameter <code>param</code>. <code>commonFunctionKey</code> must be the key
   * of a commomon function of <code>Syntax</code> (one of <code>FUNCTION_LN</code>,
   * <code>FUNCTION_LOG</code>, <code>FUNCTION_EXP</code>, <code>FUNCTION_SQR</code>,...).
   * @param commonFunctionKey the key of a common function of <code>Syntax</code>.
   * @param param             the parameter of the function (instance of <code>Number</code>).
   * @return the result of the function. The returned value is an instance of <code>Double</code>.
   * @throws IllegalArgumentException if <code>param</code> isn't an instance of <code>Number</code>
   *         or if <code>commonFunctionKey</code> isn't the key of a commomon function of <code>Syntax</code>.
   */
  public Object getCommonFunctionValue (Object commonFunctionKey, Object param)
  {
    if (!(param instanceof Integer))
      throw new IllegalArgumentException ("Parameter " + param + " not an instance of Integer");

    int number = (Integer)param;

    if (commonFunctionKey.equals (Syntax.FUNCTION_LN))
      return (int)Math.log(number);
    else if (commonFunctionKey.equals (Syntax.FUNCTION_LOG))
      return (int)Math.log10(number);
    else if (commonFunctionKey.equals (Syntax.FUNCTION_EXP))
      return (int)Math.exp(number);
    else if (commonFunctionKey.equals (Syntax.FUNCTION_SQR))
      return number * number;
    else if (commonFunctionKey.equals (Syntax.FUNCTION_SQRT))
      return (int)Math.sqrt(number);
    else if (commonFunctionKey.equals (Syntax.FUNCTION_INTEGER))
      return number;
    else if (commonFunctionKey.equals (Syntax.FUNCTION_FLOOR))
      return number;
    else if (commonFunctionKey.equals (Syntax.FUNCTION_CEIL))
      return number;
    else if (commonFunctionKey.equals (Syntax.FUNCTION_ROUND))
      return number;
    else if (commonFunctionKey.equals (Syntax.FUNCTION_ABS))
      return Math.abs(number);
    else if (commonFunctionKey.equals (Syntax.FUNCTION_OPPOSITE))
      return -number;
    else if (commonFunctionKey.equals (Syntax.FUNCTION_NOT))
      return isTrue(param) ? FALSE_INT : TRUE_INT;
    else
      throw new IllegalArgumentException ("Common function key " + commonFunctionKey + " not implemented");
  }

  /**
   * Returns the value <code>paramThen</code> or <code>paramElse</code> depending
   * on whether <code>isTrue (paramIf)</code> returning <code>true</code> or <code>false</code>.
   * As the implementation of the <code>supportsRecursiveCall ()</code> method in this class returns
   * <code>true</code>, this method isn't called internally to get the result of a condition.
   * @param paramIf   the condition.
   * @param paramThen the true condition value.
   * @param paramElse the false condition value.
   * @return the result of the condition.
   * @see #supportsRecursiveCall
   */
  public Object getConditionValue (Object paramIf, Object paramThen,  Object paramElse)
  {
    return isTrue (paramIf) ? paramThen : paramElse;
  }

  /**
   * Returns <code>true</code> or <code>false</code> according to the value of <code>condition</code>.
   * @param condition the value to test (instance of <code>Number</code>).
   * @return <code>false</code> if the double value of <code>condition</code> equals 0.
   *         otherwise <code>true</code>.
   */
  public boolean isTrue (Object condition)
  {
    if (!(condition instanceof Integer))
      throw new IllegalArgumentException ("Condition " + condition + " not an instance of Integer");
    return ((Integer)condition) != 0;
  }
  
  private Integer truthValue(boolean condition) {
    return (condition ? TRUE_INT : FALSE_INT);
  }

  /**
   * Returns <code>true</code> thus enabling this interpreter to evaluate the value of recursive
   * calls.
   * @return <code>true</code>.
   * @see #getConditionValue
   */
  public boolean supportsRecursiveCall ()
  {
    return true;
  }

  /**
   * Returns the value of the function <code>function</code> with its parameters <code>parametersValue</code>.
   * This method returns the result of <code>function.computeFunction (this, parametersValue)</code>.
   * @param function        the function to compute.
   * @param parametersValue the value of function's parameters.
   * @param recursiveCall <code>true</code> if the call of this function is a recursive call, meaning that
   *                      the current evaluated function calls itself.
   * @return the result of the function.
   */
  public Object getFunctionValue (Function    function,
                                  Object []   parametersValue,
                                  boolean     recursiveCall)
  {
    return function.computeFunction (this, parametersValue);
  }
}
