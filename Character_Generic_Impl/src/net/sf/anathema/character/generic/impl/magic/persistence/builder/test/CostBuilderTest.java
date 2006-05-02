package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostBuilder;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

public class CostBuilderTest extends BasicTestCase {

  private CostBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    builder = new CostBuilder();
  }

  public void testNullElement() throws Exception {
    ICost cost = builder.buildCost(null);
    assertEquals(Cost.NULL_COST, cost);
  }

  public void testValueButNoText() throws Exception {
    Element costElement = getCostElement();
    costElement.addAttribute("cost", "5"); //$NON-NLS-1$//$NON-NLS-2$
    ICost cost = builder.buildCost(costElement);
    assertEquals("5", cost.getCost()); //$NON-NLS-1$
    assertEquals(null, cost.getText());
  }

  public void testValueAndText() throws Exception {
    Element costElement = getCostElement();
    costElement.addAttribute("cost", "7"); //$NON-NLS-1$//$NON-NLS-2$
    String expectedText = "expectedText"; //$NON-NLS-1$
    costElement.addAttribute("text", expectedText); //$NON-NLS-1$
    ICost cost = builder.buildCost(costElement);
    assertEquals("7", cost.getCost()); //$NON-NLS-1$
    assertEquals(expectedText, cost.getText());
  }

  public void testNonIntegerValue() throws Exception {
    Element costElement = getCostElement();
    String expectedText = "10+"; //$NON-NLS-1$
    costElement.addAttribute("cost", expectedText); //$NON-NLS-1$
    ICost cost = builder.buildCost(costElement);
    assertEquals(expectedText, cost.getCost());
  }

  public void testNonIntegerValueAndText() throws Exception {
    Element costElement = getCostElement();
    String expectedText1 = "Some motes"; //$NON-NLS-1$
    String expectedText2 = "and some more"; //$NON-NLS-1$
    costElement.addAttribute("cost", expectedText1); //$NON-NLS-1$
    costElement.addAttribute("text", expectedText2); //$NON-NLS-1$
    ICost cost = builder.buildCost(costElement);
    assertEquals(expectedText1, cost.getCost());
    assertEquals(expectedText2, cost.getText());
  }

  private Element getCostElement() {
    Element costElement = new DefaultElement("cost"); //$NON-NLS-1$
    return costElement;
  }

  public void testNoCostAttribute() throws Exception {
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        Element costElement = getCostElement();
        builder.buildCost(costElement);
      }
    });
  }
}