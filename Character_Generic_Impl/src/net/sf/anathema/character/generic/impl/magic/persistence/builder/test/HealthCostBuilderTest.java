package net.sf.anathema.character.generic.impl.magic.persistence.builder.test;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.HealthCostBuilder;
import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.lib.exception.PersistenceException;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.testing.ExceptionConvertingBlock;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;

public class HealthCostBuilderTest extends BasicTestCase {

  private HealthCostBuilder builder;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    builder = new HealthCostBuilder();
  }

  private Element getHealthElement() {
    Element element = new DefaultElement("health"); //$NON-NLS-1$
    return element;
  }

  public void testNullElement() throws Exception {
    IHealthCost cost = builder.buildCost(null);
    assertEquals(HealthCost.NULL_HEALTH_COST, cost);
  }

  public void testNoCostAttribute() throws Exception {
    assertThrowsException(PersistenceException.class, new ExceptionConvertingBlock() {
      @Override
      public void executeExceptionThrowing() throws Exception {
        Element element = getHealthElement();
        builder.buildCost(element);
      }
    });
  }

  public void testCostAttributeOnly() throws Exception {
    Element healthElement = getHealthElement();
    healthElement.addAttribute("cost", "4"); //$NON-NLS-1$//$NON-NLS-2$
    IHealthCost cost = builder.buildCost(healthElement);
    assertEquals("4", cost.getCost()); //$NON-NLS-1$
    assertEquals(null, cost.getText());
    assertEquals(HealthType.Lethal, cost.getType());
  }

  public void testCostAndTextAttributes() throws Exception {
    Element healthElement = getHealthElement();
    healthElement.addAttribute("cost", "4"); //$NON-NLS-1$//$NON-NLS-2$
    String expected = "and then some"; //$NON-NLS-1$
    healthElement.addAttribute("text", expected); //$NON-NLS-1$
    IHealthCost cost = builder.buildCost(healthElement);
    assertEquals("4", cost.getCost()); //$NON-NLS-1$
    assertEquals(expected, cost.getText());
    assertEquals(HealthType.Lethal, cost.getType());
  }

  public void testCostAttributeAndTypeAttribute() throws Exception {
    Element healthElement = getHealthElement();
    healthElement.addAttribute("cost", "2"); //$NON-NLS-1$//$NON-NLS-2$
    healthElement.addAttribute("type", "Bashing"); //$NON-NLS-1$//$NON-NLS-2$
    IHealthCost cost = builder.buildCost(healthElement);
    assertEquals("2", cost.getCost()); //$NON-NLS-1$
    assertEquals(null, cost.getText());
    assertEquals(HealthType.Bashing, cost.getType());
  }

}