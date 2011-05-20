//Copyright (c) 2004 by disy Informationssysteme GmbH
package net.sf.anathema.lib.gui.table;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;

import net.disy.commons.core.util.Ensure;

// NOT_PUBLISHED
public class DisableableProxyAction extends AbstractAction {
	private static final long serialVersionUID = 505097053360803709L;
	private final Action action;
	private boolean enabled = true;

	public DisableableProxyAction(Action action) {
		Ensure.ensureNotNull(action);
		this.action = action;
		action.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				firePropertyChange(evt.getPropertyName(), evt.getOldValue(),
						evt.getNewValue());
			}
		});
	}

	@Override
	public boolean isEnabled() {
		return enabled && action.isEnabled();
	}

	@Override
	public void setEnabled(boolean enabled) {
		if (this.enabled == enabled) {
			return;
		}
		this.enabled = enabled;
		firePropertyChange("enabled", //$NON-NLS-1$
				Boolean.valueOf(!isEnabled()), Boolean.valueOf(isEnabled()));
	}

	public void actionPerformed(ActionEvent e) {
		action.actionPerformed(e);
	}

	@Override
	public Object getValue(String key) {
		return action.getValue(key);
	}

	@Override
	public void putValue(String key, Object newValue) {
		action.putValue(key, newValue);
	}
}