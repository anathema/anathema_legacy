package net.sf.anathema.test.character.generic.framework.charm;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import net.sf.anathema.character.generic.framework.magic.CharmNodeConnector;
import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import net.sf.anathema.graph.nodes.NodeFactory;
import net.sf.anathema.lib.testing.BasicTestCase;

public class CharmNodeConnectorTest extends BasicTestCase {

  private LinkedHashMap<String, IIdentifiedRegularNode> nodes;
  private ArrayList<ICharm> list;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    nodes = new LinkedHashMap<String, IIdentifiedRegularNode>();
    list = new ArrayList<ICharm>();
  }

  public void testNoNodes() throws Exception {
    connectNodes();
  }

  public void testSingleParentOnlyChild() throws Exception {
    String parentName = "Parent";//$NON-NLS-1$
    String childName = "Child"; //$NON-NLS-1$
    DummyCharm parent = new DummyCharm(parentName);
    DummyCharm child = new DummyCharm(childName, new ICharm[] { parent });
    list.add(child);
    nodes.put(parentName, NodeFactory.createChildlessNode(parentName));
    nodes.put(childName, NodeFactory.createChildlessNode(childName));
    connectNodes();
    IIdentifiedRegularNode parentNode = nodes.get(parentName);
    assertEquals(childName, ((IIdentifiedRegularNode) parentNode.getChildren()[0]).getId());
    IIdentifiedRegularNode childNode = nodes.get(childName);
    assertEquals(parentName, ((IIdentifiedRegularNode) childNode.getParents()[0]).getId());
  }

  public void testSingleParentMultiChild() throws Exception {
    String parentName = "Parent";//$NON-NLS-1$
    String firstChildName = "Child1"; //$NON-NLS-1$
    String secondChildName = "Child2"; //$NON-NLS-1$
    DummyCharm parent = new DummyCharm(parentName);
    DummyCharm firstChild = new DummyCharm(firstChildName, new ICharm[] { parent });
    DummyCharm secondChild = new DummyCharm(secondChildName, new ICharm[] { parent });
    list.add(firstChild);
    list.add(secondChild);
    nodes.put(parentName, NodeFactory.createChildlessNode(parentName));
    nodes.put(firstChildName, NodeFactory.createChildlessNode(firstChildName));
    nodes.put(secondChildName, NodeFactory.createChildlessNode(secondChildName));
    connectNodes();
    IIdentifiedRegularNode parentNode = nodes.get(parentName);
    IIdentifiedRegularNode firstChildNode = nodes.get(firstChildName);
    IIdentifiedRegularNode secondChildNode = nodes.get(secondChildName);
    assertEquals(2, parentNode.getChildren().length);
    IIdentifiedRegularNode firstCheckNode = ((IIdentifiedRegularNode) parentNode.getChildren()[0]);
    IIdentifiedRegularNode secondCheckNode = ((IIdentifiedRegularNode) parentNode.getChildren()[1]);
    assertTrue(firstCheckNode.equals(firstChildNode));
    assertTrue(secondCheckNode.equals(secondChildNode));
    assertEquals(parentNode, firstChildNode.getParents()[0]);
    assertEquals(parentNode, secondChildNode.getParents()[0]);
  }

  public void testAttributeNodeConnection() throws Exception {
    String charmName = "One Attribute"; //$NON-NLS-1$
    DummyCharm charm = new DummyCharm(charmName);
    charm.addAttributeRequirement(new CharmAttributeRequirement(new CharmAttribute("Attribute", true), 2)); //$NON-NLS-1$
    IIdentifiedRegularNode charmNode = NodeFactory.createChildlessNode(charmName);
    list.add(charm);
    nodes.put(charmName, charmNode);
    String attributeId = "Requirement.Attribute.2"; //$NON-NLS-1$
    nodes.put(attributeId, NodeFactory.createChildlessNode(attributeId));
    connectNodes();
    assertEquals(2, nodes.size());
    IIdentifiedRegularNode attributeNode = nodes.get(attributeId);
    assertEquals(1, attributeNode.getChildren().length);
    assertEquals(charmNode, attributeNode.getChildren()[0]);
    assertEquals(1, charmNode.getParents().length);
    assertEquals(attributeNode, charmNode.getParents()[0]);
  }

  private void connectNodes() {
    CharmNodeConnector.connectNodes(list, nodes);
  }
}