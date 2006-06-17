package net.sf.anathema.test.character.generic.persistence.magic.save;

import net.sf.anathema.character.generic.health.HealthType;
import net.sf.anathema.character.generic.impl.magic.Cost;
import net.sf.anathema.character.generic.impl.magic.CostList;
import net.sf.anathema.character.generic.impl.magic.HealthCost;
import net.sf.anathema.character.generic.impl.magic.persistence.writer.CostWriter;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.lib.testing.BasicTestCase;
import net.sf.anathema.lib.xml.ElementUtilities;

import org.dom4j.DocumentFactory;
import org.dom4j.Element;

public class CostWriterTest extends BasicTestCase {
  private CostWriter writer;
  private Element element;
  private DummyCharm charm;

  @Override
  protected void setUp() throws Exception {
    writer = new CostWriter();
    element = DocumentFactory.getInstance().createElement("charm"); //$NON-NLS-1$    
    charm = new DummyCharm();
  }

  public void testNullCostList() throws Exception {
    writer.write(charm, element);
    assertNotNull(element.element("cost").element("temporary")); //$NON-NLS-1$ //$NON-NLS-2$
  }

  public void testWriteEssenceCost() throws Exception {
    charm.setTemporaryCost(new CostList(new Cost("1", null), null, null)); //$NON-NLS-1$
    writer.write(charm, element);
    Element temporaryElement = element.element("cost").element("temporary");//$NON-NLS-1$ //$NON-NLS-2$
    assertNotNull(temporaryElement);
    assertEquals(1, temporaryElement.element("essence").attributeCount()); //$NON-NLS-1$
    assertEquals(1, ElementUtilities.getRequiredIntAttrib(temporaryElement.element("essence"), "cost")); //$NON-NLS-1$//$NON-NLS-2$
  }

  public void testWriteWillpowerCost() throws Exception {
    charm.setTemporaryCost(new CostList(null, new Cost("1", "test"), null)); //$NON-NLS-1$ //$NON-NLS-2$
    writer.write(charm, element);
    Element temporaryElement = element.element("cost").element("temporary");//$NON-NLS-1$ //$NON-NLS-2$
    assertNotNull(temporaryElement);
    assertEquals(2, temporaryElement.element("willpower").attributeCount()); //$NON-NLS-1$
    assertEquals(1, ElementUtilities.getRequiredIntAttrib(temporaryElement.element("willpower"), "cost")); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals("test", temporaryElement.element("willpower").attributeValue("text")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }

  public void testWriteHealthCost() throws Exception {
    charm.setTemporaryCost(new CostList(null, null, new HealthCost(4, "REALLY!", HealthType.Aggravated))); //$NON-NLS-1$ 
    writer.write(charm, element);
    Element temporaryElement = element.element("cost").element("temporary");//$NON-NLS-1$ //$NON-NLS-2$
    assertNotNull(temporaryElement);
    assertEquals(3, temporaryElement.element("health").attributeCount()); //$NON-NLS-1$
    assertEquals(4, ElementUtilities.getRequiredIntAttrib(temporaryElement.element("health"), "cost")); //$NON-NLS-1$//$NON-NLS-2$
    assertEquals("Aggravated", temporaryElement.element("health").attributeValue("type")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
    assertEquals("REALLY!", temporaryElement.element("health").attributeValue("text")); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
  }
}