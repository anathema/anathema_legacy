package net.sf.anathema.campaign.concrete.plot;

import junit.framework.Assert;
import net.disy.commons.core.util.ContractFailedException;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import org.junit.Before;
import org.junit.Test;

public class PlotElementContainerTest {

  private static final PlotTimeUnit SCENE = new PlotTimeUnit("Scene"); //$NON-NLS-1$
  private static final PlotTimeUnit EPISODE = new PlotTimeUnit("Episode", SCENE); //$NON-NLS-1$
  private static final PlotTimeUnit STORY = new PlotTimeUnit("Story", EPISODE); //$NON-NLS-1$
  private final PlotIDProvider provider = new PlotIDProvider(STORY);

  @Test(expected = ContractFailedException.class)
  public void testIllegalArgumentExceptionOnAddElementToNonSuccessablePlotElementContainer() {
    PlotElementContainer container = new PlotElementContainer(provider, SCENE);
    container.addChild("illegal child"); //$NON-NLS-1$
  }

  private DummyPlotElementContainerListener listener;

  @Before
  public void setUp() throws Exception {
    listener = new DummyPlotElementContainerListener();
  }

  @Test
  public void testAddToPlotElementContainer() throws Exception {
    String childName = "LegalChild"; //$NON-NLS-1$
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    plotElementContainer.addChild(childName);
    IPlotElement[] children = plotElementContainer.getChildren();
    Assert.assertEquals(1, children.length);
    IPlotElement onlyChild = children[0];
    Assert.assertEquals(EPISODE, onlyChild.getTimeUnit());
    Assert.assertEquals(childName, onlyChild.getDescription().getName().getText());
  }

  @Test
  public void testRemoveDirectChild() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    plotElementContainer.addChild("First Episode"); //$NON-NLS-1$
    plotElementContainer.removeChild(plotElementContainer.getChildren()[0]);
    Assert.assertEquals(0, plotElementContainer.getChildren().length);
  }

  @Test
  public void testRemoveDeepChild() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    IPlotElement firstEpisode = plotElementContainer.addChild("First Episode"); //$NON-NLS-1$
    IPlotElement firstScene = firstEpisode.addChild("First Scene"); //$NON-NLS-1$
    Assert.assertEquals(1, firstEpisode.getChildren().length);
    plotElementContainer.removeChild(firstScene);
    Assert.assertEquals(0, firstEpisode.getChildren().length);
  }

  @Test
  public void testRemoveNonExistingChild() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    plotElementContainer.addChild("First Episode").addChild("First Scene"); //$NON-NLS-1$//$NON-NLS-2$
    plotElementContainer.removeChild(new PlotElement(provider, SCENE, "Not contained")); //$NON-NLS-1$
    Assert.assertEquals(1, plotElementContainer.getChildren().length);
    Assert.assertEquals(1, plotElementContainer.getChildren()[0].getChildren().length);
  }

  @Test
  public void testNotificationOnAddChild() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    plotElementContainer.addPlotElementContainerListener(listener);
    IPlotElement addedElement = plotElementContainer.addChild("First Episode"); //$NON-NLS-1$
    Assert.assertSame(addedElement, listener.addedChild);
    Assert.assertSame(plotElementContainer, listener.addContainer);
  }

  @Test
  public void testNotificationOnDirectRemove() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    IPlotElement addedElement = plotElementContainer.addChild("First Episode"); //$NON-NLS-1$
    plotElementContainer.addPlotElementContainerListener(listener);
    plotElementContainer.removeChild(addedElement);
    Assert.assertSame(addedElement, listener.removedChild);
  }

  @Test
  public void testNoNotificationOnDeepRemove() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    IPlotElement addedElement = plotElementContainer.addChild("First Episode").addChild("First Scene"); //$NON-NLS-1$ //$NON-NLS-2$
    plotElementContainer.addPlotElementContainerListener(listener);
    plotElementContainer.removeChild(addedElement);
    Assert.assertNull(listener.removedChild);
  }
}