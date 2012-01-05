package net.sf.anathema.campaign.concrete.plot;

import net.sf.anathema.campaign.model.plot.IPlotElement;
import net.sf.anathema.campaign.model.plot.IPlotTimeUnit;
import net.sf.anathema.framework.itemdata.model.IItemDescription;
import net.sf.anathema.framework.itemdata.model.ItemDescription;

public class PlotElement extends PlotElementContainer implements IPlotElement {

  private final IItemDescription itemDescription;

  public PlotElement(PlotIDProvider provider, IPlotTimeUnit unit, String name) {
    super(provider, unit);
    this.itemDescription = new ItemDescription(name);
  }

  public PlotElement(PlotIDProvider provider, IPlotTimeUnit unit, IItemDescription description, String id) {
    super(provider, unit, id);
    this.itemDescription = description;
  }

  public IItemDescription getDescription() {
    return itemDescription;
  }

  @Override
  public String toString() {
    return getDescription().getName().getText();
  }

  public boolean isDirty() {
    return itemDescription.isDirty();
  }

  public void setClean() {
    itemDescription.setClean();
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }

  @Override
  public int hashCode() {
    return super.hashCode();
  }
}