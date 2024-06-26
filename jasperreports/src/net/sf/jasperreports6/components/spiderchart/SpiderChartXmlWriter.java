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
package net.sf.jasperreports6.components.spiderchart;

import java.io.IOException;

import net.sf.jasperreports6.charts.JRCategorySeries;
import net.sf.jasperreports6.components.charts.ChartSettings;
import net.sf.jasperreports6.engine.JRAnchor;
import net.sf.jasperreports6.engine.JRComponentElement;
import net.sf.jasperreports6.engine.JRConstants;
import net.sf.jasperreports6.engine.JRExpression;
import net.sf.jasperreports6.engine.JRFont;
import net.sf.jasperreports6.engine.JRHyperlinkParameter;
import net.sf.jasperreports6.engine.JasperReportsContext;
import net.sf.jasperreports6.engine.component.Component;
import net.sf.jasperreports6.engine.component.ComponentKey;
import net.sf.jasperreports6.engine.component.ComponentXmlWriter;
import net.sf.jasperreports6.engine.component.ComponentsEnvironment;
import net.sf.jasperreports6.engine.type.EvaluationTimeEnum;
import net.sf.jasperreports6.engine.type.HyperlinkTargetEnum;
import net.sf.jasperreports6.engine.type.HyperlinkTypeEnum;
import net.sf.jasperreports6.engine.util.JRXmlWriteHelper;
import net.sf.jasperreports6.engine.util.VersionComparator;
import net.sf.jasperreports6.engine.util.XmlNamespace;
import net.sf.jasperreports6.engine.xml.JRXmlConstants;
import net.sf.jasperreports6.engine.xml.JRXmlWriter;

/**
 * 
 * @author Sanda Zaharia (shertage@users.sourceforge.net)
 */
public class SpiderChartXmlWriter implements ComponentXmlWriter
{

	public static final String ELEMENT_spiderChart = "spiderChart";
	public static final String ELEMENT_chartSettings = "chartSettings";
	public static final String ELEMENT_spiderDataset = "spiderDataset";
	public static final String ELEMENT_spiderPlot = "spiderPlot";
	public static final String ELEMENT_maxValueExpression = "maxValueExpression";
	
	public static final String ATTRIBUTE_rotation = "rotation";
	public static final String ATTRIBUTE_tableOrder = "tableOrder";
	public static final String ATTRIBUTE_webFilled = "isWebFilled";
	public static final String ATTRIBUTE_startAngle = "startAngle";
	public static final String ATTRIBUTE_headPercent = "headPercent";
	public static final String ATTRIBUTE_interiorGap = "interiorGap";
	public static final String ATTRIBUTE_axisLineColor = "axisLineColor";
	public static final String ATTRIBUTE_axisLineWidth = "axisLineWidth";
	public static final String ATTRIBUTE_labelGap = "labelGap";
	public static final String ATTRIBUTE_labelColor = "labelColor";

	
	private JasperReportsContext jasperReportsContext;
	private final String version;
	private final VersionComparator versionComparator;
	
	/**
	 * 
	 */
	public SpiderChartXmlWriter(JasperReportsContext jasperReportsContext)
	{
		this(jasperReportsContext, null, new VersionComparator());
	}
	
	public SpiderChartXmlWriter(JasperReportsContext jasperReportsContext, String version, VersionComparator versionComparator)
	{
		this.jasperReportsContext = jasperReportsContext;
		this.version = version;
		this.versionComparator = versionComparator;
	}


	@Override
	public boolean isToWrite(JRComponentElement componentElement, JRXmlWriter reportWriter)
	{
		return isNewerVersionOrEqual(version, JRConstants.VERSION_3_7_4);
	}
	
	
	@Override
	public void writeToXml(JRComponentElement componentElement, JRXmlWriter reportWriter) throws IOException
	{
		Component component = componentElement.getComponent();
		SpiderChartComponent spiderChartComponent = (SpiderChartComponent) component;
		JRXmlWriteHelper writer = reportWriter.getXmlWriteHelper();
		ComponentKey componentKey = componentElement.getComponentKey();
		
		String namespaceURI = componentKey.getNamespace();
		String schemaLocation = 
			ComponentsEnvironment.getInstance(jasperReportsContext)
				.getBundle(namespaceURI).getXmlParser().getPublicSchemaLocation();
		XmlNamespace componentNamespace = new XmlNamespace(namespaceURI, componentKey.getNamespacePrefix(),
				schemaLocation);

		
		writer.startElement(ELEMENT_spiderChart, componentNamespace);
		
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_evaluationTime, spiderChartComponent.getEvaluationTime(), EvaluationTimeEnum.NOW);

		if (spiderChartComponent.getEvaluationTime() == EvaluationTimeEnum.GROUP)
		{
			writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_evaluationGroup, spiderChartComponent.getEvaluationGroup());
		}
		
		ChartSettings chartSettings = spiderChartComponent.getChartSettings();
		writeChart(chartSettings, writer, reportWriter, componentNamespace);
		
		SpiderDataset dataset = (SpiderDataset)spiderChartComponent.getDataset();
		writeSpiderDataSet(dataset, writer, reportWriter, componentNamespace);
		
		SpiderPlot spiderPlot = (SpiderPlot)spiderChartComponent.getPlot();
		writeSpiderPlot(spiderPlot, chartSettings, writer, componentNamespace);
		
		writer.closeElement();//spiderChart
	}

	/**
	 *
	 */
	private void writeChart(ChartSettings chartSettings, JRXmlWriteHelper writer, JRXmlWriter reportWriter, XmlNamespace namespace) throws IOException
	{
		writer.startElement(ELEMENT_chartSettings, namespace);
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_isShowLegend, chartSettings.getShowLegend());
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_backcolor, chartSettings.getBackcolor());

		writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_hyperlinkType, chartSettings.getLinkType(), HyperlinkTypeEnum.NONE.getName());
		writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_hyperlinkTarget, chartSettings.getLinkTarget(), HyperlinkTargetEnum.SELF.getName());
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_bookmarkLevel, chartSettings.getBookmarkLevel(), JRAnchor.NO_BOOKMARK);
		if(isNewerVersionOrEqual(version, JRConstants.VERSION_3_7_5))
		{
			writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_customizerClass, chartSettings.getCustomizerClass());
		}
		writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_renderType, chartSettings.getRenderType());

		// write title
		writer.startElement(JRXmlConstants.ELEMENT_chartTitle, JRXmlWriter.JASPERREPORTS_NAMESPACE);
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_position, chartSettings.getTitlePosition());
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_color, chartSettings.getTitleColor());
		writeFont(chartSettings.getTitleFont(), writer);
		if (chartSettings.getTitleExpression() != null)
		{
			writeExpression(JRXmlConstants.ELEMENT_titleExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getTitleExpression(), false, writer);
		}
		writer.closeElement();

		// write subtitle
		writer.startElement(JRXmlConstants.ELEMENT_chartSubtitle, JRXmlWriter.JASPERREPORTS_NAMESPACE);
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_color, chartSettings.getSubtitleColor());
		writeFont(chartSettings.getSubtitleFont(), writer);
		if (chartSettings.getSubtitleExpression() != null)
		{
			writeExpression(JRXmlConstants.ELEMENT_subtitleExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getSubtitleExpression(), false, writer);
		}
		writer.closeElement();

		// write chartLegend
		writer.startElement(JRXmlConstants.ELEMENT_chartLegend, JRXmlWriter.JASPERREPORTS_NAMESPACE);
		if (chartSettings.getLegendColor() != null)
		{
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_textColor, chartSettings.getLegendColor());
		}
		if (chartSettings.getLegendBackgroundColor() != null)
		{
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_backgroundColor, chartSettings.getLegendBackgroundColor());
		}
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_position, chartSettings.getLegendPosition());
		writeFont(chartSettings.getLegendFont(), writer);
		writer.closeElement();

		writeExpression(JRXmlConstants.ELEMENT_anchorNameExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getAnchorNameExpression(), false, writer);
		if(isNewerVersionOrEqual(version, JRConstants.VERSION_6_13_0))
		{
			writeExpression(JRXmlConstants.ELEMENT_bookmarkLevelExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getBookmarkLevelExpression(), false, writer);
		}
		writeExpression(JRXmlConstants.ELEMENT_hyperlinkReferenceExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getHyperlinkReferenceExpression(), false, writer);
		writeExpression(JRXmlConstants.ELEMENT_hyperlinkWhenExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getHyperlinkWhenExpression(), false, writer);
		writeExpression(JRXmlConstants.ELEMENT_hyperlinkAnchorExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getHyperlinkAnchorExpression(), false, writer);
		writeExpression(JRXmlConstants.ELEMENT_hyperlinkPageExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getHyperlinkPageExpression(), false, writer);
		writeExpression(JRXmlConstants.ELEMENT_hyperlinkTooltipExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, chartSettings.getHyperlinkTooltipExpression(), false, writer);
		writeHyperlinkParameters(chartSettings.getHyperlinkParameters(), writer);

		writer.closeElement();
	}
	
	private void writeSpiderDataSet(SpiderDataset dataset, JRXmlWriteHelper writer, JRXmlWriter reportWriter, XmlNamespace namespace) throws IOException
	{
		writer.startElement(ELEMENT_spiderDataset, namespace);

		reportWriter.writeElementDataset(dataset);

		/*   */
		JRCategorySeries[] categorySeries = dataset.getSeries();
		if (categorySeries != null && categorySeries.length > 0)
		{
			for(int i = 0; i < categorySeries.length; i++)
			{
				writeCategorySeries(categorySeries[i], writer, reportWriter);
			}
		}

		writer.closeElement();
	}

	/**
	 *
	 */
	private void writeCategorySeries(JRCategorySeries categorySeries, JRXmlWriteHelper writer, JRXmlWriter reportWriter) throws IOException
	{
		writer.startElement(JRXmlConstants.ELEMENT_categorySeries, JRXmlWriter.JASPERREPORTS_NAMESPACE);

		writeExpression(JRXmlConstants.ELEMENT_seriesExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, categorySeries.getSeriesExpression(), false, writer);
		writeExpression(JRXmlConstants.ELEMENT_categoryExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, categorySeries.getCategoryExpression(), false, writer);
		writeExpression(JRXmlConstants.ELEMENT_valueExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, categorySeries.getValueExpression(), false, writer);
		writeExpression(JRXmlConstants.ELEMENT_labelExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE, categorySeries.getLabelExpression(), false, writer);
		reportWriter.writeHyperlink(JRXmlConstants.ELEMENT_itemHyperlink, JRXmlWriter.JASPERREPORTS_NAMESPACE, categorySeries.getItemHyperlink());

		writer.closeElement();
	}


	private void writeFont(JRFont font, JRXmlWriteHelper writer) throws IOException
	{
		if (font != null)
		{
			writer.startElement(JRXmlConstants.ELEMENT_font, JRXmlWriter.JASPERREPORTS_NAMESPACE);
			writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_fontName, font.getOwnFontName());
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_size, font.getOwnFontsize());
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_isBold, font.isOwnBold());
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_isItalic, font.isOwnItalic());
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_isUnderline, font.isOwnUnderline());
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_isStrikeThrough, font.isOwnStrikeThrough());
			writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_pdfFontName, font.getOwnPdfFontName());
			writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_pdfEncoding, font.getOwnPdfEncoding());
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_isPdfEmbedded, font.isOwnPdfEmbedded());
			writer.closeElement(true);
		}
	}

	private void writeHyperlinkParameters(JRHyperlinkParameter[] parameters, JRXmlWriteHelper writer) throws IOException
	{
		if (parameters != null)
		{
			for (int i = 0; i < parameters.length; i++)
			{
				JRHyperlinkParameter parameter = parameters[i];
				writeHyperlinkParameter(parameter, writer);
			}
		}
	}


	private void writeHyperlinkParameter(JRHyperlinkParameter parameter, JRXmlWriteHelper writer) throws IOException
	{
		if (parameter != null)
		{
			writer.startElement(JRXmlConstants.ELEMENT_hyperlinkParameter, JRXmlWriter.JASPERREPORTS_NAMESPACE);
			writer.addEncodedAttribute(JRXmlConstants.ATTRIBUTE_name, parameter.getName());

			writeExpression(JRXmlConstants.ELEMENT_hyperlinkParameterExpression, JRXmlWriter.JASPERREPORTS_NAMESPACE,
					parameter.getValueExpression(), false, writer);

			writer.closeElement();
		}
	}

	
	private void writeSpiderPlot(SpiderPlot spiderPlot, ChartSettings chartSettings, JRXmlWriteHelper writer, XmlNamespace namespace) throws IOException
	{
		writer.startElement(ELEMENT_spiderPlot, namespace);
		writer.addAttribute(ATTRIBUTE_rotation, spiderPlot.getRotation());
		writer.addAttribute(ATTRIBUTE_tableOrder, spiderPlot.getTableOrder());
		writer.addAttribute(ATTRIBUTE_webFilled, spiderPlot.getWebFilled());
		writer.addAttribute(ATTRIBUTE_startAngle, spiderPlot.getStartAngle());
		writer.addAttribute(ATTRIBUTE_headPercent, spiderPlot.getHeadPercent());
		writer.addAttribute(ATTRIBUTE_interiorGap, spiderPlot.getInteriorGap());
		writer.addAttribute(ATTRIBUTE_axisLineColor, spiderPlot.getAxisLineColor());
		writer.addAttribute(ATTRIBUTE_axisLineWidth, spiderPlot.getAxisLineWidth());
		writer.addAttribute(ATTRIBUTE_labelGap, spiderPlot.getLabelGap());
		writer.addAttribute(ATTRIBUTE_labelColor, spiderPlot.getLabelColor());
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_backcolor, spiderPlot.getBackcolor());
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_backgroundAlpha, spiderPlot.getBackgroundAlpha());
		writer.addAttribute(JRXmlConstants.ATTRIBUTE_foregroundAlpha, spiderPlot.getForegroundAlpha());
		
		//FIXME: series colors
//		writeSeriesColors(spiderPlot.getSeriesColors(), writer);
		
		writeLabelFont(spiderPlot.getLabelFont(), writer);
		writeExpression(ELEMENT_maxValueExpression, namespace, spiderPlot.getMaxValueExpression(), false, writer);
		writer.closeElement(true);
		
	}

/*
	private void writeSeriesColors(SortedSet seriesColors, JRXmlWriteHelper writer) throws IOException
	{
		if (seriesColors == null || seriesColors.size() == 0)
		{
			return;
		}
		JRSeriesColor[] colors = (JRSeriesColor[])seriesColors.toArray(new JRSeriesColor[seriesColors.size()]);
		for (int i = 0; i < colors.length; i++)
		{
			writer.startElement(JRXmlConstants.ELEMENT_seriesColor);
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_seriesOrder, colors[i].getSeriesOrder());
			writer.addAttribute(JRXmlConstants.ATTRIBUTE_color, colors[i].getColor());
			writer.closeElement();
		}
	}
*/
	
	private void writeLabelFont(JRFont labelFont, JRXmlWriteHelper writer) throws IOException
	{
		if (labelFont != null)
		{
			writer.startElement(JRXmlConstants.ELEMENT_labelFont, JRXmlWriter.JASPERREPORTS_NAMESPACE);
			writeFont(labelFont, writer);
			writer.closeElement();
		}
	}

	protected boolean isNewerVersionOrEqual(String currentVersion, String oldVersion) 
	{
		return versionComparator.compare(currentVersion, oldVersion) >= 0;
	}

	@SuppressWarnings("deprecation")
	protected void writeExpression(String name, XmlNamespace namespace, JRExpression expression, boolean writeClass, JRXmlWriteHelper writer)  throws IOException
	{
		if(isNewerVersionOrEqual(version, JRConstants.VERSION_4_1_1))
		{
			writer.writeExpression(name, namespace, expression);
		}
		else
		{
			writer.writeExpression(name, namespace, expression, writeClass);
		}
	}
	
}
