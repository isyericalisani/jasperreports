/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2023 Cloud Software Group, Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports6.charts.base;

import net.sf.jasperreports6.charts.JRGanttDataset;
import net.sf.jasperreports6.charts.JRGanttSeries;
import net.sf.jasperreports6.engine.JRChartDataset;
import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRExpressionCollector;
import net.sf.jasperreports6.engine.base.JRBaseChartDataset;
import net.sf.jasperreports6.engine.base.JRBaseObjectFactory;
import net.sf.jasperreports6.engine.design.JRVerifier;

/**
 * @author Peter Risko (peter@risko.hu)
 */
//FIXME clone
public class JRBaseGanttDataset extends JRBaseChartDataset implements JRGanttDataset {

	/**
	 *
	 */
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected JRGanttSeries[] ganttSeries;

	/**
	 *
	 */
	public JRBaseGanttDataset(JRChartDataset dataset)
	{
		super(dataset);
	}

	/**
	 *
	 */
	public JRBaseGanttDataset(JRGanttDataset dataset, JRBaseObjectFactory factory)
	{
		super(dataset, factory);

		/*   */
		JRGanttSeries[] srcGanttSeries = dataset.getSeries();
		if (srcGanttSeries != null && srcGanttSeries.length > 0)
		{
			ganttSeries = new JRGanttSeries[srcGanttSeries.length];
			for(int i = 0; i < ganttSeries.length; i++)
			{
				ganttSeries[i] = factory.getGanttSeries(srcGanttSeries[i]);
			}
		}

	}


	@Override
	public JRGanttSeries[] getSeries()
	{
		return ganttSeries;
	}


	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRChartDataset#getDatasetType()
	 */
	@Override
	public byte getDatasetType() {
		return JRChartDataset.GANTT_DATASET;
	}


	@Override
	public void collectExpressions(JRExpressionCollector collector)
	{
		collector.collect(this);
	}


	@Override
	public void validate(JRVerifier verifier)
	{
		verifier.verify(this);
	}

}
