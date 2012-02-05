package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.IItem;

import java.io.OutputStream;

public interface Report {

  boolean supports(IItem item);

  void print(IItem item, OutputStream stream) throws ReportException;
}
