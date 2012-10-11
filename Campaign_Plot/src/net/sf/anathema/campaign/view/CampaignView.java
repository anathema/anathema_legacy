package net.sf.anathema.campaign.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.campaign.presenter.view.ISeriesView;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.view.plot.PlotView;
import net.sf.anathema.framework.styledtext.ITextEditorProperties;
import net.sf.anathema.framework.view.item.AbstractItemView;

import javax.swing.Icon;
import javax.swing.JComponent;

public class CampaignView extends AbstractItemView implements ISeriesView {

  private IPlotView plotView;
  private ITextEditorProperties editorProperties;

  public CampaignView(String name, Icon icon, ITextEditorProperties editorProperties) {
    super(name, icon);
    this.editorProperties = editorProperties;
  }

  @Override
  public IPlotView addPlotView(String title) {
    Preconditions.checkArgument(plotView == null, "Only one view allowed."); //$NON-NLS-1$
    this.plotView = new PlotView(editorProperties);
    return plotView;
  }

  @Override
  public JComponent getComponent() {
    return plotView.getComponent();
  }
}