package net.sf.anathema.character.main.charmparser;

import net.sf.anathema.character.main.health.HealthType;
import net.sf.anathema.character.main.magic.HealthCost;
import net.sf.anathema.character.main.magic.persistence.builder.HealthCostBuilder;
import net.sf.anathema.character.main.magic.general.IHealthCost;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HealthCostBuilderTest {

  private HealthCostBuilder builder;

  @Before
  public void setUp() throws Exception {
    builder = new HealthCostBuilder();
  }

  private Element getHealthElement() {
    return new DefaultElement("health");
  }

  @Test
  public void testNullElement() throws Exception {
    IHealthCost cost = builder.buildCost(null);
    assertEquals(HealthCost.NULL_HEALTH_COST, cost);
  }

  @Test(expected = PersistenceException.class)
  public void testNoCostAttribute() throws Exception {
    Element element = getHealthElement();
    builder.buildCost(element);
  }

  @Test
  public void testCostAttributeOnly() throws Exception {
    Element healthElement = getHealthElement();
    healthElement.addAttribute("cost", "4");
    IHealthCost cost = builder.buildCost(healthElement);
    assertEquals("4", cost.getCost());
    assertEquals(null, cost.getText());
    assertEquals(HealthType.Lethal, cost.getType());
  }

  @Test
  public void testCostAndTextAttributes() throws Exception {
    Element healthElement = getHealthElement();
    healthElement.addAttribute("cost", "4");
    String expected = "and then some";
    healthElement.addAttribute("text", expected);
    IHealthCost cost = builder.buildCost(healthElement);
    assertEquals("4", cost.getCost());
    assertEquals(expected, cost.getText());
    assertEquals(HealthType.Lethal, cost.getType());
  }

  @Test
  public void testCostAttributeAndTypeAttribute() throws Exception {
    Element healthElement = getHealthElement();
    healthElement.addAttribute("cost", "2");
    healthElement.addAttribute("type", "Bashing");
    IHealthCost cost = builder.buildCost(healthElement);
    assertEquals("2", cost.getCost());
    assertEquals(null, cost.getText());
    assertEquals(HealthType.Bashing, cost.getType());
  }

}