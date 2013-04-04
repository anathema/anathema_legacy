package net.sf.anathema.character.generic.framework.magic;

import net.sf.anathema.character.generic.impl.magic.CharmAttribute;
import net.sf.anathema.character.generic.impl.magic.CharmAttributeRequirement;
import net.sf.anathema.character.generic.magic.ICharm;
import net.sf.anathema.dummy.character.magic.DummyCharm;
import net.sf.anathema.graph.nodes.IIdentifiedRegularNode;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CharmNodeBuilderTest {

  private ArrayList<ICharm> list;
  private LinkedHashMap<String, IIdentifiedRegularNode> nodes;

  @Before
  public void setUp() throws Exception {
    list = new ArrayList<>();
    nodes = new LinkedHashMap<>();
  }

  @Test
  public void testNoCharms() throws Exception {
    buildNodes();
    assertTrue(nodes.isEmpty());
  }

  @Test
  public void testSingleCharm() throws Exception {
    String name = "dummy";
    DummyCharm charm = new DummyCharm(name);
    list.add(charm);
    buildNodes();
    assertEquals(1, nodes.size());
    IIdentifiedRegularNode node = nodes.get(name);
    assertTrue(node.isLeafNode());
    assertTrue(node.isRootNode());
  }

  @Test
  public void testMultipleCharms() throws Exception {
    String name = "dummy";
    DummyCharm dummy = new DummyCharm(name);
    String otherName = "dommy";
    DummyCharm dommy = new DummyCharm(otherName);
    list.add(dummy);
    list.add(dommy);
    buildNodes();
    assertEquals(2, nodes.size());
    IIdentifiedRegularNode node = nodes.get(name);
    assertTrue(node.isLeafNode());
    assertTrue(node.isRootNode());
    IIdentifiedRegularNode otherNode = nodes.get(otherName);
    assertTrue(otherNode.isLeafNode());
    assertTrue(otherNode.isRootNode());
  }

  @Test
  public void testExternalCharmSingleOccurence() throws Exception {
    String parentName = "ExternalParent";
    String childName = "Child";
    DummyCharm externalParent = new DummyCharm(parentName);
    DummyCharm child = new DummyCharm(childName, new ICharm[]{externalParent});
    list.add(child);
    buildNodes();
    assertEquals(2, nodes.size());
    IIdentifiedRegularNode parentNode = nodes.get(parentName);
    assertTrue(parentNode.getLowerToChildren());
  }

  @Test
  public void testExternalCharmMultipleOccurence() throws Exception {
    String parentName = "ExternalParent";
    String firstChildName = "Child1";
    String secondChildName = "Child2";
    DummyCharm externalParent = new DummyCharm(parentName);
    DummyCharm firstChild = new DummyCharm(firstChildName, new ICharm[]{externalParent});
    DummyCharm secondChild = new DummyCharm(secondChildName, new ICharm[]{externalParent});
    list.add(firstChild);
    list.add(secondChild);
    buildNodes();
    assertEquals(3, nodes.size());
    IIdentifiedRegularNode parentNode = nodes.get(parentName);
    assertFalse(parentNode.getLowerToChildren());
  }

  @Test
  public void testNoAttributes() throws Exception {
    DummyCharm charm = new DummyCharm("No Attribs");
    list.add(charm);
    buildNodes();
    assertEquals(1, nodes.size());
  }

  @Test
  public void testRequirementNodeId() throws Exception {
    String charmName = "One Attribute";
    String attributeName = "Attribute";
    DummyCharm charm = new DummyCharm(charmName);
    charm.addAttributeRequirement(new CharmAttributeRequirement(new CharmAttribute(attributeName, true), 3));
    list.add(charm);
    buildNodes();
    assertEquals(2, nodes.size());
    IIdentifiedRegularNode attributeNode = nodes.get("Requirement.Attribute.3");
    assertNotNull(attributeNode);
  }

  private void buildNodes() {
    CharmNodeBuilder.buildNodes(list, nodes);
  }
}