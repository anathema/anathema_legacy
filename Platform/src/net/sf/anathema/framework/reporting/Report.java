package net.sf.anathema.framework.reporting;

import net.sf.anathema.framework.repository.Item;

import java.io.OutputStream;

public interface Report {

  boolean supports(Item item);

  void print(Item item, OutputStream stream) throws ReportException;
}
