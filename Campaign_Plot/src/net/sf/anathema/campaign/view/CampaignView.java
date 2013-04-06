package net.sf.anathema.campaign.view;

import com.google.common.base.Preconditions;
import net.sf.anathema.campaign.presenter.view.SeriesView;
import net.sf.anathema.campaign.presenter.view.plot.IPlotView;
import net.sf.anathema.campaign.view.plot.PlotView;
import net.sf.anathema.framework.swing.styledtext.ITextEditorProperties;
import net.sf.anathema.framework.view.item.AbstractItemView;
import net.sf.anathema.lib.file.RelativePath;

import javax.swing.JComponent;

public class CampaignView extends AbstractItemView implements SeriesView {

  private IPlotView plotView;
  private ITextEditorProperties editorProperties;

  public CampaignView(String name, RelativePath icon, ITextEditorProperties editorProperties) {
    super(name, icon);
    this.editorProperties = editorProperties;
  }

  @Override
  public IPlotView addPlotView(String title) {
    Preconditions.checkArgument(plotView == null, "Only one view allowed.");
    this.plotView = new PlotView(editorProperties);
    return plotView;
  }

  @Override
  public JComponent getComponent() {
    return plotView.getComponent();
  }
}