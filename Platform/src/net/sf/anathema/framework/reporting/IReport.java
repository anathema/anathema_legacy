package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

public interface IReport {
  public boolean supports(IItem item);
}