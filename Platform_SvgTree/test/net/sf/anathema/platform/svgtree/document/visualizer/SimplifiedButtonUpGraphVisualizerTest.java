package net.sf.anathema.platform.svgtree.document.visualizer;

import net.sf.anathema.graph.nodes.ISimpleNode;
import net.sf.anathema.graph.nodes.IdentifiedRegularNode;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class SimplifiedButtonUpGraphVisualizerTest {

  private ITreePresentationProperties properties = mock(ITreePresentationProperties.class);
  private TestLayeredGraph graph = new TestLayeredGraph();
  private SimplifiedButtonUpGraphVisualizer visualizer = new SimplifiedButtonUpGraphVisualizer(graph, properties);

  @Test
  public void doesNotApplyForMiddleLeaf() throws Exception {
    IdentifiedRegularNode leaf = new IdentifiedRegularNode("leaf");
    IdentifiedRegularNode middleLeft = new IdentifiedRegularNode("middleLeft", leaf);
    IdentifiedRegularNode middleRight = new IdentifiedRegularNode("middleRight", leaf);
    IdentifiedRegularNode middleLeaf = new IdentifiedRegularNode("middleLeaf");
    IdentifiedRegularNode root = new IdentifiedRegularNode("root", middleLeft, middleRight, middleLeaf);
    defineLayer(1, root);
    defineLayer(2, middleLeft, middleRight, middleLeaf);
    defineLayer(3, leaf);
    assertNotApplicable();
  }

  @Test
  public void appliesForKiteShapedGraph() throws Exception {
    IdentifiedRegularNode leaf = new IdentifiedRegularNode("leaf");
    IdentifiedRegularNode middleLeft = new IdentifiedRegularNode("middleLeft", leaf);
    IdentifiedRegularNode middleRight = new IdentifiedRegularNode("middleRight", leaf);
    IdentifiedRegularNode root = new IdentifiedRegularNode("root", middleLeft, middleRight);
    defineLayer(1, root);
    defineLayer(2, middleLeft, middleRight);
    defineLayer(3, leaf);
    assertIsApplicable();
  }

  @Test
  public void doesNotApplyForTwoBranches() throws Exception {
    IdentifiedRegularNode leaf = new IdentifiedRegularNode("leaf");
    IdentifiedRegularNode branchLeaf = new IdentifiedRegularNode("branchLeaf");
    IdentifiedRegularNode middleLeft = new IdentifiedRegularNode("middleLeft", leaf);
    IdentifiedRegularNode middleRight = new IdentifiedRegularNode("middleRight", leaf);
    IdentifiedRegularNode branchMiddle = new IdentifiedRegularNode("middleLeaf", branchLeaf);
    IdentifiedRegularNode root = new IdentifiedRegularNode("root", middleLeft, middleRight, branchMiddle);
    defineLayer(1, root);
    defineLayer(2, middleLeft, middleRight, branchMiddle);
    defineLayer(3, leaf, branchLeaf);
    assertNotApplicable();
  }

  @Test
  public void appliesForFishShapedGraph() throws Exception {
    IdentifiedRegularNode leftLeaf = new IdentifiedRegularNode("leftLeaf");
    IdentifiedRegularNode rightLeaf = new IdentifiedRegularNode("rightLeaf");
    IdentifiedRegularNode waist = new IdentifiedRegularNode("waist", leftLeaf, rightLeaf);
    IdentifiedRegularNode middleLeft = new IdentifiedRegularNode("middleLeft", waist);
    IdentifiedRegularNode middleRight = new IdentifiedRegularNode("middleRight", waist);
    IdentifiedRegularNode root = new IdentifiedRegularNode("root", middleLeft, middleRight);
    defineLayer(1, root);
    defineLayer(2, middleLeft, middleRight);
    defineLayer(3, waist);
    defineLayer(4, leftLeaf, rightLeaf);
    assertIsApplicable();
  }

  @Test
  public void appliesForCowboyShapedGraph() throws Exception {
    IdentifiedRegularNode leftFoot = new IdentifiedRegularNode("leftFoot");
    IdentifiedRegularNode rightFoot = new IdentifiedRegularNode("rightFoot");
    IdentifiedRegularNode leftKnee = new IdentifiedRegularNode("leftKnee", leftFoot);
    IdentifiedRegularNode rightKnee = new IdentifiedRegularNode("rightKnee", rightFoot);
    IdentifiedRegularNode waist = new IdentifiedRegularNode("waist", leftKnee, rightKnee);
    IdentifiedRegularNode leftArm = new IdentifiedRegularNode("leftArm", waist);
    IdentifiedRegularNode rightArm = new IdentifiedRegularNode("rightArm", waist);
    IdentifiedRegularNode head = new IdentifiedRegularNode("head", leftArm, rightArm);
    defineLayer(1, head);
    defineLayer(2, leftArm, rightArm);
    defineLayer(3, waist);
    defineLayer(4, leftKnee, rightKnee);
    defineLayer(5, leftFoot, rightFoot);
    assertIsApplicable();
  }

  @Test
  public void appliesForHouseShapedGraph() throws Exception {
    IdentifiedRegularNode leftBottom = new IdentifiedRegularNode("leftBottom");
    IdentifiedRegularNode rightBottom = new IdentifiedRegularNode("rightBottom");
    IdentifiedRegularNode leftTop = new IdentifiedRegularNode("leftTop", leftBottom, rightBottom);
    IdentifiedRegularNode rightTop = new IdentifiedRegularNode("rightTop", leftBottom, rightBottom);
    IdentifiedRegularNode roof = new IdentifiedRegularNode("roof", leftTop, rightTop);
    defineLayer(1, roof);
    defineLayer(2, leftTop, rightTop);
    defineLayer(3, leftBottom, rightBottom);
    assertIsApplicable();
  }

  private void defineLayer(int layer, ISimpleNode... nodes) {
    for (ISimpleNode node : nodes) {
      graph.nodesByLayer.add(layer, node);
    }
  }

  private void assertIsApplicable() {
    assertThat(visualizer.isApplicable(), is(true));
  }

  private void assertNotApplicable() {
    assertThat(visualizer.isApplicable(), is(false));
  }
}
