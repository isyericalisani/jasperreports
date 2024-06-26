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
package net.sf.jasperreports6.charts.fill;

import net.sf.jasperreports6.charts.JRItemLabel;
import net.sf.jasperreports6.charts.JRPiePlot;
import net.sf.jasperreports6.engine.fill.JRFillChartPlot;
import net.sf.jasperreports6.engine.fill.JRFillObjectFactory;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRFillPiePlot extends JRFillChartPlot implements JRPiePlot
{


	/**
	 *
	 */
	public JRFillPiePlot(
		JRPiePlot piePlot, 
		JRFillObjectFactory factory
		)
	{
		super(piePlot, factory);
	}
	
	@Override
	public Boolean getCircular()
	{
		return ((JRPiePlot)parent).getCircular();
	}
	
	@Override
	public String getLabelFormat()
	{
		return ((JRPiePlot)parent).getLabelFormat();
	}
	
	@Override
	public String getLegendLabelFormat()
	{
		return ((JRPiePlot)parent).getLegendLabelFormat();
	}
	
	@Override
	public JRItemLabel getItemLabel()
	{
		return ((JRPiePlot)parent).getItemLabel();
	}
	
	@Override
	public Boolean getShowLabels(){
		return ((JRPiePlot)parent).getShowLabels();
	}

}
