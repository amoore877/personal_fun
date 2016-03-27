package games.rolePlayingGames.scenario.tracking;

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Table for scenario tracking.
 * 
 * @author Andrew
 */
public class DefaultScenarioTrackingTable extends JTable {
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 3615657127453484768L;

	@Override
	public String getToolTipText(final MouseEvent event) {
		/**
		 * Modify tooltip behavior for the table.
		 */
		String tip = null;
		final Point p = event.getPoint();

		// Locate the renderer under the event location
		final int hitColumnIndex = columnAtPoint(p);
		final int hitRowIndex = rowAtPoint(p);

		if ((hitColumnIndex != -1) && (hitRowIndex != -1)) {
			final TableCellRenderer renderer = getCellRenderer(hitRowIndex,
					hitColumnIndex);
			final Component component = prepareRenderer(renderer, hitRowIndex,
					hitColumnIndex);

			// Now have to see if the component is a JComponent before
			// getting the tip
			/**
			 * New behavior. Use cell value first.
			 */
			final Object cellValue = getValueAt(hitRowIndex, hitColumnIndex);

			if (cellValue != null) {
				// only use if string value is not empty (otherwise small
				// tooltip still appears)
				final String cellStringValue = cellValue.toString();

				if (cellStringValue != null && !cellStringValue.isEmpty()) {
					tip = cellValue.toString();
				}
			}
			/**
			 * End new behavior.
			 */
			else if (component instanceof JComponent) {
				// Convert the event to the renderer's coordinate system
				final Rectangle cellRect = getCellRect(hitRowIndex,
						hitColumnIndex, false);
				p.translate(-cellRect.x, -cellRect.y);
				final MouseEvent newEvent = new MouseEvent(component,
						event.getID(), event.getWhen(), event.getModifiers(),
						p.x, p.y, event.getXOnScreen(), event.getYOnScreen(),
						event.getClickCount(), event.isPopupTrigger(),
						MouseEvent.NOBUTTON);

				tip = ((JComponent) component).getToolTipText(newEvent);
			}
		}

		// No tip from the renderer get our own tip
		if (tip == null)
			tip = getToolTipText();

		return tip;
	}
}
