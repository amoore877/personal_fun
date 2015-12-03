package games.rolePlayingGames.scenario.tracking;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

public abstract class AbstractScenarioTrackingTableCellRenderer extends
		DefaultTableCellRenderer {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 6140080922869824898L;

	/**
	 * Color of selected rows.
	 */
	private final Color mySelectedColor;

	/**
	 * Column index of status information in table.
	 */
	private final int myStatusColIndex;

	/**
	 * Constructor.
	 * 
	 * @param iSelectedColor
	 *            color of selected rows.
	 * @param statusColIndex
	 *            index of status column.
	 */
	public AbstractScenarioTrackingTableCellRenderer(
			final Color iSelectedColor, final int statusColIndex) {
		super();

		mySelectedColor = iSelectedColor;

		myStatusColIndex = statusColIndex;
	}

	/**
	 * @return color for selected rows.
	 */
	protected final Color getSelectedColor() {
		return mySelectedColor;
	}

	/**
	 * @return column index of status information in table.
	 */
	protected final int getStatusColIndex() {
		return myStatusColIndex;
	}

	@Override
	public final Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		// does not affect Acted or Initiative
		final TableModel model = table.getModel();

		// the component we are interested in
		final Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		// make light blue if selected row
		final int[] selectedRows = table.getSelectedRows();
		boolean selected = false;
		for (final int currSelectedRow : selectedRows) {
			if (currSelectedRow == row) {
				selected = true;
				break;
			}
		}

		if (selected) {
			c.setBackground(getSelectedColor());
		} else {
			c.setBackground(calculateBackgroundColor(row, model));
		}

		return c;
	}

	/**
	 * Calculate color based on row data.
	 * 
	 * @param row
	 *            index of row to look at.
	 * @param model
	 *            model to observe.
	 * @return color to use.
	 */
	protected final Color calculateBackgroundColor(final int row,
			final TableModel model) {
		// if OK status, entire row should be white
		// if non-OK status, color entire row based on status
		final Object statusObject = model.getValueAt(row, getStatusColIndex());
		if ((statusObject == null)
				|| !(statusObject instanceof CharacterStatus)) {
			return Color.WHITE;
		} else {
			return CharacterStatus
					.getColorForStatus((CharacterStatus) statusObject);
		}
	}
}
