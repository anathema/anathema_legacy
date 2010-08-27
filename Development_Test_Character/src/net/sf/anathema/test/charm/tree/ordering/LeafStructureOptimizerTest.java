package net.sf.anathema.test.charm.tree.ordering;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import net.sf.anathema.graph.ordering.IStructureNode;
import net.sf.anathema.graph.ordering.LeafStructureOptimizer;

import org.junit.Test;

public class LeafStructureOptimizerTest {

  private static class TestStructureNode implements IStructureNode {

    private boolean isLeaf;

    private TestStructureNode(boolean isLeaf) {
      this.isLeaf = isLeaf;
    }

    public boolean isLeafNode() {
      return isLeaf;
    }

    public boolean hasMultipleParents() {
      return false;
    }
  }

  private void assertNodeStructure(IStructureNode[] inputArray, IStructureNode[] expectedNodes) {
    assertEquals(Arrays.asList(expectedNodes), new LeafStructureOptimizer<IStructureNode>().optimize(inputArray));
  }

  private IStructureNode[] createInputArray(boolean... isLeaf) {
    IStructureNode[] inputNodes = new IStructureNode[isLeaf.length];
    for (int index = 0; index < inputNodes.length; index++) {
      inputNodes[index] = new TestStructureNode(isLeaf[index]);
    }
    return inputNodes;
  }

  @Test
  public void leaveBetweenTrees() throws Exception {
    IStructureNode[] inputArray = createInputArray(false, true, false);
    IStructureNode[] expectedNodes = new IStructureNode[] { inputArray[1], inputArray[0], inputArray[2] };
    assertNodeStructure(inputArray, expectedNodes);
  }

  @Test
  public void twoLeaveBetweenTrees() throws Exception {
    IStructureNode[] inputArray = createInputArray(false, true, true, false);
    IStructureNode[] expectedNodes = new IStructureNode[] { inputArray[1], inputArray[0], inputArray[3], inputArray[2] };
    assertNodeStructure(inputArray, expectedNodes);
  }

  @Test
  public void singleNodeLayer() throws Exception {
    IStructureNode[] singleNodeArray = createInputArray(true);
    IStructureNode[] expectedNodes = new IStructureNode[] { singleNodeArray[0] };
    assertNodeStructure(singleNodeArray, expectedNodes);
  }
}