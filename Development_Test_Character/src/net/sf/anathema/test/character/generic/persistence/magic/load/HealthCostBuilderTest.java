package net.sf.anathema.test.character.generic.persistence.magic.load;

import static org.junit.Assert.assertEquals;
import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.HealthCostBuilder;
import net.sf.anathema.character.generic.magic.general.IHealthCost;
import net.sf.anathema.lib.exception.PersistenceException;

import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.junit.Before;
import org.junit.Test;

public class HealthCostBuilderTest {

  private HealthCostBuilder builder;

  @Before
  public void setUp() throws Exception {
    builder = new HealthCostBuilder();
  }

  private Element getHealthElement() {
    Element element = new DefaultElement("health"); //$NON-NLS-1$
    return element;
  }

  @Test
  public void testNullElement() throws Exception {
    IHealthCost cost = builder.buildCost(null);
    assertEquals(HealthCost.NULL_HEALTH_COST, cost);
  }
  @Test(expected=PersistenceException.class)
  public void testNoCostAttribute() throws Exception {
        Element element = getHealthElement();
        builder.buildCost(element);
  }
  @Test
  public void testCostAttributeOnly() throws Exception {
    Element healthElement = getHealthElement();
    healthElement.addAttribute("cost", "4"); //$NON-NLS-1$//$NON-NLS-2$
    IHealthCost cost = builder.buildCost(healthElement);
    assertEquals("4", cost.getCost()); //$NON-NLS-1$
    assertEquals(null, cost.getText());
    assertEquals(HealthType.Lethal, cost.getType());
  }
  @Test
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
  @Test
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