package net.sf.anathema.campaign.concrete.plot.test;

import net.disy.commons.core.util.ContractFailedException;
import net.disy.commons.core.util.ISimpleBlock;
import net.sf.anathema.campaign.concrete.plot.PlotElement;
import net.sf.anathema.campaign.concrete.plot.PlotElementContainer;
import net.sf.anathema.campaign.concrete.plot.PlotIDProvider;
import net.sf.anathema.campaign.concrete.plot.PlotTimeUnit;
import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.lib.testing.BasicTestCase;

public class PlotElementContainerTest extends BasicTestCase {

  private static final PlotTimeUnit SCENE = new PlotTimeUnit("Scene"); //$NON-NLS-1$
  private static final PlotTimeUnit EPISODE = new PlotTimeUnit("Episode", SCENE); //$NON-NLS-1$
  private static final PlotTimeUnit STORY = new PlotTimeUnit("Story", EPISODE); //$NON-NLS-1$
  private final PlotIDProvider provider = new PlotIDProvider(STORY);

  public void testIllegalArgumentExceptionOnAddElementToNonSuccessablePlotElementContainer() throws Exception {
    final PlotElementContainer container = new PlotElementContainer(provider, SCENE);
    assertThrowsException(ContractFailedException.class, new ISimpleBlock() {
      public void execute() {
        container.addChild("illegal child"); //$NON-NLS-1$
      }
    });
  }

  private DummyPlotElementContainerListener listener;

  @Override
  protected void setUp() throws Exception {
    super.setUp();
    listener = new DummyPlotElementContainerListener();
  }

  public void testAddToPlotElementContainer() throws Exception {
    String childName = "LegalChild"; //$NON-NLS-1$
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    plotElementContainer.addChild(childName);
    IPlotElement[] children = plotElementContainer.getChildren();
    assertEquals(1, children.length);
    IPlotElement onlyChild = children[0];
    assertEquals(EPISODE, onlyChild.getTimeUnit());
    assertEquals(childName, onlyChild.getDescription().getName().getText());
  }

  public void testRemoveDirectChild() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    plotElementContainer.addChild("First Episode"); //$NON-NLS-1$
    plotElementContainer.removeChild(plotElementContainer.getChildren()[0]);
    assertEquals(0, plotElementContainer.getChildren().length);
  }

  public void testRemoveDeepChild() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    IPlotElement firstEpisode = plotElementContainer.addChild("First Episode"); //$NON-NLS-1$
    IPlotElement firstScene = firstEpisode.addChild("First Scene"); //$NON-NLS-1$
    assertEquals(1, firstEpisode.getChildren().length);
    plotElementContainer.removeChild(firstScene);
    assertEquals(0, firstEpisode.getChildren().length);
  }

  public void testRemoveNonExistingChild() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    plotElementContainer.addChild("First Episode").addChild("First Scene"); //$NON-NLS-1$//$NON-NLS-2$
    plotElementContainer.removeChild(new PlotElement(provider, SCENE, "Not contained")); //$NON-NLS-1$
    assertEquals(1, plotElementContainer.getChildren().length);
    assertEquals(1, plotElementContainer.getChildren()[0].getChildren().length);
  }

  public void testNotificationOnAddChild() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    plotElementContainer.addPlotElementContainerListener(listener);
    IPlotElement addedElement = plotElementContainer.addChild("First Episode"); //$NON-NLS-1$
    assertSame(addedElement, listener.addedChild);
    assertSame(plotElementContainer, listener.addContainer);
  }

  public void testNotificationOnDirectRemove() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    IPlotElement addedElement = plotElementContainer.addChild("First Episode"); //$NON-NLS-1$
    plotElementContainer.addPlotElementContainerListener(listener);
    plotElementContainer.removeChild(addedElement);
    assertSame(addedElement, listener.removedChild);
  }

  public void testNoNotificationOnDeepRemove() throws Exception {
    PlotElementContainer plotElementContainer = new PlotElementContainer(provider, STORY);
    IPlotElement addedElement = plotElementContainer.addChild("First Episode").addChild("First Scene"); //$NON-NLS-1$ //$NON-NLS-2$
    plotElementContainer.addPlotElementContainerListener(listener);
    plotElementContainer.removeChild(addedElement);
    assertNull(listener.removedChild);
  }
}