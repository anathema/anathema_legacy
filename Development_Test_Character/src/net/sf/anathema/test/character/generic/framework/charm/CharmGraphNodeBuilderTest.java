package net.sf.anathema.test.character.generic.framework.charm;

import java.util.ArrayList;
import java.util.List;

import net.sf.anathema.character.generic.framework.magic.CharmGraphNodeBuilder;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.lib.testing.BasicTestCase;

public class CharmGraphNodeBuilderTest extends BasicTestCase {

  public void testExternalCharmSingleOccurence() throws Exception {
    String parentName = "ExternalParent";//$NON-NLS-1$
    DummyCharm externalParent = new DummyCharm(parentName);
    DummyCharm child = new DummyCharm("Child", new ICharm[] { externalParent }); //$NON-NLS-1$
    List<ICharm> list = new ArrayList<ICharm>();
    list.add(child);
    IIdentifiedRegularNode[] nodes = CharmGraphNodeBuilder.createNodesFromCharms(list);
    for (IIdentifiedRegularNode node : nodes) {
      if (node.getId().equals(parentName)) {
        assertTrue(node.getLowerToChildren());
        assertEquals("Child", ((IIdentifiedRegularNode) node.getChildren()[0]).getId()); //$NON-NLS-1$
      }
      if (node.getId().equals("Child")) { //$NON-NLS-1$
        assertFalse(node.getLowerToChildren());
        assertEquals(parentName, ((IIdentifiedRegularNode) node.getParents()[0]).getId());
      }
    }
  }

  public void testExternalCharmMultipleOccurence() throws Exception {
    String parentName = "ExternalParent";//$NON-NLS-1$
    String firstChildName = "Child1"; //$NON-NLS-1$
    String secondChildName = "Child2"; //$NON-NLS-1$
    DummyCharm externalParent = new DummyCharm(parentName);
    DummyCharm firstChild = new DummyCharm(firstChildName, new ICharm[] { externalParent });
    DummyCharm secondChild = new DummyCharm(secondChildName, new ICharm[] { externalParent });
    List<ICharm> list = new ArrayList<ICharm>();
    list.add(firstChild);
    list.add(secondChild);
    IIdentifiedRegularNode[] nodes = CharmGraphNodeBuilder.createNodesFromCharms(list);
    for (IIdentifiedRegularNode node : nodes) {
      if (node.getId().equals(parentName)) {
        assertFalse(node.getLowerToChildren());
        assertEquals(2, node.getChildren().length);
        IIdentifiedRegularNode firstChildNode = ((IIdentifiedRegularNode) node.getChildren()[0]);
        IIdentifiedRegularNode secondChildNode = ((IIdentifiedRegularNode) node.getChildren()[1]);
        assertTrue(firstChildNode.getId().equals(firstChildName) || secondChildNode.getId().equals(firstChildName));
        assertTrue(firstChildNode.getId().equals(secondChildName) || secondChildNode.getId().equals(secondChildName));
        assertFalse(firstChildNode.getId().equals(secondChildNode.getId()));
      }
      if (node.getId().equals(firstChildName)) {
        assertFalse(node.getLowerToChildren());
        assertEquals(parentName, ((IIdentifiedRegularNode) node.getParents()[0]).getId());
      }
      if (node.getId().equals(secondChildName)) {
        assertFalse(node.getLowerToChildren());
        assertEquals(parentName, ((IIdentifiedRegularNode) node.getParents()[0]).getId());
      }
    }
  }
}