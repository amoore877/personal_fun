package games.rolePlayingGames.scenario.tracking.shadowrun;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

/**
 * Table cell renderer for Shadowrun. Colors backgrounds based on row data.
 * 
 * @author Andrew
 *
 */
public class ShadowrunScenarioTrackingTableCellRenderer extends
		DefaultTableCellRenderer {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 5746258843575162555L;

	/**
	 * Constructor.
	 */
	public ShadowrunScenarioTrackingTableCellRenderer() {
		super();
	}

	@Override
	public Component getTableCellRendererComponent(final JTable table,
			final Object value, final boolean isSelected,
			final boolean hasFocus, final int row, final int column) {
		// TODO does not affect Acted or Initiative
		final TableModel model = table.getModel();

		// the component we are interested in
		final Component c = super.getTableCellRendererComponent(table, value,
				isSelected, hasFocus, row, column);

		c.setBackground(calculateBackgroundColor(row, model));

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
	private static Color calculateBackgroundColor(final int row,
			final TableModel model) {
		// if OK status, entire row should be white
		// if non-OK status, color entire row based on status
		final Object statusObject = model.getValueAt(row,
				ShadowrunScenarioTrackingTableModel.STATUS_COL_INDEX);
		if ((statusObject == null)
				|| !(statusObject instanceof ShadowrunCharacterStatus)) {
			return Color.WHITE;
		} else {
			return ShadowrunCharacterStatus
					.getColorForStatus((ShadowrunCharacterStatus) statusObject);
		}
	}
}
