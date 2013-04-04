package net.sf.anathema.character.generic.persistence.load.load;

import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.impl.magic.persistence.builder.CostBuilder;
import net.sf.anathema.character.generic.magic.general.ICost;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CostBuilderTest{

  private CostBuilder builder;

  @Before
  public void setUp() throws Exception {
    builder = new CostBuilder();
  }

  @Test
  public void testNullElement() throws Exception {
    ICost cost = builder.buildCost(null);
    assertEquals(Cost.NULL_COST, cost);
  }
  @Test
  public void testValueButNoText() throws Exception {
    Element costElement = getCostElement();
    costElement.addAttribute("cost", "5");
    ICost cost = builder.buildCost(costElement);
    assertEquals("5", cost.getCost());
    assertEquals(null, cost.getText());
  }
  @Test
  public void testValueAndText() throws Exception {
    Element costElement = getCostElement();
    costElement.addAttribute("cost", "7");
    String expectedText = "expectedText";
    costElement.addAttribute("text", expectedText);
    ICost cost = builder.buildCost(costElement);
    assertEquals("7", cost.getCost());
    assertEquals(expectedText, cost.getText());
  }
  @Test
  public void testNonIntegerValue() throws Exception {
    Element costElement = getCostElement();
    String expectedText = "10+";
    costElement.addAttribute("cost", expectedText);
    ICost cost = builder.buildCost(costElement);
    assertEquals(expectedText, cost.getCost());
  }
  @Test
  public void testNonIntegerValueAndText() throws Exception {
    Element costElement = getCostElement();
    String expectedText1 = "Some motes";
    String expectedText2 = "and some more";
    costElement.addAttribute("cost", expectedText1);
    costElement.addAttribute("text", expectedText2);
    ICost cost = builder.buildCost(costElement);
    assertEquals(expectedText1, cost.getCost());
    assertEquals(expectedText2, cost.getText());
  }

  private Element getCostElement() {
    return new DefaultElement("cost");
  }
  @Test(expected=PersistenceException.class)
  public void testNoCostAttribute() throws Exception {
        Element costElement = getCostElement();
        builder.buildCost(costElement);
  }
}