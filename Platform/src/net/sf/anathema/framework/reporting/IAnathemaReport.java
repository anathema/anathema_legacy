package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

public interface IAnathemaReport {
  public boolean supports(IItem item);
}