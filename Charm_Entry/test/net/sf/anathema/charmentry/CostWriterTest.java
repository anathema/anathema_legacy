package net.sf.anathema.charmentry;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.charmentry.persistence.CostWriter;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.xml.ElementUtilities;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CostWriterTest {

  private CostWriter writer;
  private Element element;
  private DummyCharm charm;

  @Before
  public void setUp() throws Exception {
    writer = new CostWriter();
    element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
    charm = new DummyCharm();
  }

  @Test
  public void testNullCostList() throws Exception {
    writer.write(charm, element);
    assertNotNull(element.element("cost")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  @Test
  public void testWriteEssenceCost() throws Exception {
    charm.setTemporaryCost(new CostList(new Cost("1", null, false), null, null, null)); //$NON-NLS-1$
    writer.write(charm, element);
    Element temporaryElement = element.element("cost");//$NON-NLS-1$ //$NON-NLS-2$
    assertNotNull(temporaryElement);
    assertEquals(1, temporaryElement.element("essence").attributeCount()); //$NON-NLS-1$
    assertEquals(1, ElementUtilities.getRequiredIntAttrib(temporaryElement.element("essence"), "cost")); //$NON-NLS-1$//$NON-NLS-2$
  }

  @Test
  public void testWriteWillpowerCost() throws Exception {
    charm.setTemporaryCost(new CostList(null, new Cost("1", "test", false), null, null)); //$NON-NLS-1$ //$NON-NLS-2$
    writer.write(charm, element);
    Element temporaryElement = element.element("cost");//$NON-NLS-1$ //$NON-NLS-2$
    assertNotNull(temporaryElement);
    assertEquals(2, temporaryElement.element("willpower").attributeCount()); //$NON-NLS-1$
    assertEquals(1, ElementUtilities.getRequiredIntAttrib(temporaryElement.element("willpower"), "cost")); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals("test", temporaryElement.element("willpower").attributeValue("text")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }

  @Test
  public void testWriteHealthCost() throws Exception {
    charm.setTemporaryCost(new CostList(null, null, new HealthCost(4, "REALLY!", false, HealthType.Aggravated), null)); //$NON-NLS-1$ 
    writer.write(charm, element);
    Element temporaryElement = element.element("cost");//$NON-NLS-1$ //$NON-NLS-2$
    assertNotNull(temporaryElement);
    assertEquals(3, temporaryElement.element("health").attributeCount()); //$NON-NLS-1$
    assertEquals(4, ElementUtilities.getRequiredIntAttrib(temporaryElement.element("health"), "cost")); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals("Aggravated", temporaryElement.element("health").attributeValue("type")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    assertEquals("REALLY!", temporaryElement.element("health").attributeValue("text")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }
}