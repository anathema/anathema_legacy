package net.sf.anathema.hero.magic.parser;

import net.sf.anathema.character.main.magic.basic.cost.Cost;
import net.sf.anathema.character.main.magic.basic.cost.CostImpl;
import net.sf.anathema.character.main.magic.parser.magic.CostBuilder;
import net.sf.anathema.lib.exception.PersistenceException;
import org.dom4j.Element;
import org.dom4j.tree.DefaultElement;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CostBuilderTest {

  private CostBuilder builder;

  @Before
  public void setUp() throws Exception {
    builder = new CostBuilder();
  }

  @Test
  public void testNullElement() throws Exception {
    Cost cost = builder.buildCost(null);
    assertEquals(CostImpl.NULL_COST, cost);
  }

  @Test
  public void testValueButNoText() throws Exception {
    Element costElement = getCostElement();
    costElement.addAttribute("cost", "5");
    Cost cost = builder.buildCost(costElement);
    assertEquals("5", cost.getCost());
    assertEquals(null, cost.getText());
  }

  @Test
  public void testValueAndText() throws Exception {
    Element costElement = getCostElement();
    costElement.addAttribute("cost", "7");
    String expectedText = "expectedText";
    costElement.addAttribute("text", expectedText);
    Cost cost = builder.buildCost(costElement);
    assertEquals("7", cost.getCost());
    assertEquals(expectedText, cost.getText());
  }

  @Test
  public void testNonIntegerValue() throws Exception {
    Element costElement = getCostElement();
    String expectedText = "10+";
    costElement.addAttribute("cost", expectedText);
    Cost cost = builder.buildCost(costElement);
    assertEquals(expectedText, cost.getCost());
  }

  @Test
  public void testNonIntegerValueAndText() throws Exception {
    Element costElement = getCostElement();
    String expectedText1 = "Some motes";
    String expectedText2 = "and some more";
    costElement.addAttribute("cost", expectedText1);
    costElement.addAttribute("text", expectedText2);
    Cost cost = builder.buildCost(costElement);
    assertEquals(expectedText1, cost.getCost());
    assertEquals(expectedText2, cost.getText());
  }

  private Element getCostElement() {
    return new DefaultElement("cost");
  }

  @Test(expected = PersistenceException.class)
  public void testNoCostAttribute() throws Exception {
    Element costElement = getCostElement();
    builder.buildCost(costElement);
  }
}