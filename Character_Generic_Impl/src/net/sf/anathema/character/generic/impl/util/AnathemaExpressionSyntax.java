package net.sf.anathema.character.generic.impl.util;

import com.eteks.parser.DefaultSyntax;

public class AnathemaExpressionSyntax extends DefaultSyntax {

  public AnathemaExpressionSyntax() {
    setConditionPartKey("?", CONDITION_THEN);
    setConditionPartKey(":", CONDITION_ELSE);
    setBinaryOperatorKey ("==",  OPERATOR_EQUAL);
    setBinaryOperatorKey ("!=",  OPERATOR_DIFFERENT);
    setBinaryOperatorKey (">=",  OPERATOR_GREATER_OR_EQUAL);
    setBinaryOperatorKey ("<=",  OPERATOR_LESS_OR_EQUAL);
    setBinaryOperatorKey (">",   OPERATOR_GREATER);
    setBinaryOperatorKey ("<",   OPERATOR_LESS);
    setBinaryOperatorKey ("||",  OPERATOR_LOGICAL_OR);
    setBinaryOperatorKey ("&&",  OPERATOR_LOGICAL_AND);
    setBinaryOperatorKey ("|",   OPERATOR_BITWISE_OR);
    setBinaryOperatorKey ("^",   OPERATOR_BITWISE_XOR);
    setBinaryOperatorKey ("&",   OPERATOR_BITWISE_AND);
    setBinaryOperatorKey ("<<",  OPERATOR_SHIFT_LEFT);
    setBinaryOperatorKey (">>",  OPERATOR_SHIFT_RIGHT);
  }
  
  /**
   * Returns <code>true</code> if <code>identifier</code> is a correct name of function
   * or parameter. <code>identifier</code> may contain letters, digits, or the characters
   * '_' or '.'. Its first character can't be a digit or '.'.
   * @param identifier the string to test.
   * @return <code>true</code> if <code>identifier</code> is a correct identifier.
   */
  @Override
  public boolean isValidIdentifier (String identifier)
  {
    char c = identifier.charAt(0);
    if (!isLetter(c) && (c != '_'))
      return false;
    for (int i = 1; i < identifier.length (); i++)
    {
      c = identifier.charAt(i);
      if (!isLetter(c) && !isDigit(c) && c != '_' && c != '.') {
        return false;
      }
    }
    return true;
  }
}
