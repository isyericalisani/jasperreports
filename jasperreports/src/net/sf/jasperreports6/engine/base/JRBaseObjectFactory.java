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
package net.sf.jasperreports6.engine.base;

import net.sf.jasperreports6.charts.JRAreaPlot;
import net.sf.jasperreports6.charts.JRBar3DPlot;
import net.sf.jasperreports6.charts.JRBarPlot;
import net.sf.jasperreports6.charts.JRBubblePlot;
import net.sf.jasperreports6.charts.JRCandlestickPlot;
import net.sf.jasperreports6.charts.JRCategoryDataset;
import net.sf.jasperreports6.charts.JRCategorySeries;
import net.sf.jasperreports6.charts.JRChartAxis;
import net.sf.jasperreports6.charts.JRGanttDataset;
import net.sf.jasperreports6.charts.JRGanttSeries;
import net.sf.jasperreports6.charts.JRHighLowDataset;
import net.sf.jasperreports6.charts.JRHighLowPlot;
import net.sf.jasperreports6.charts.JRLinePlot;
import net.sf.jasperreports6.charts.JRMeterPlot;
import net.sf.jasperreports6.charts.JRMultiAxisPlot;
import net.sf.jasperreports6.charts.JRPie3DPlot;
import net.sf.jasperreports6.charts.JRPieDataset;
import net.sf.jasperreports6.charts.JRPiePlot;
import net.sf.jasperreports6.charts.JRPieSeries;
import net.sf.jasperreports6.charts.JRScatterPlot;
import net.sf.jasperreports6.charts.JRThermometerPlot;
import net.sf.jasperreports6.charts.JRTimePeriodDataset;
import net.sf.jasperreports6.charts.JRTimePeriodSeries;
import net.sf.jasperreports6.charts.JRTimeSeries;
import net.sf.jasperreports6.charts.JRTimeSeriesDataset;
import net.sf.jasperreports6.charts.JRTimeSeriesPlot;
import net.sf.jasperreports6.charts.JRValueDataset;
import net.sf.jasperreports6.charts.JRXyDataset;
import net.sf.jasperreports6.charts.JRXySeries;
import net.sf.jasperreports6.charts.JRXyzDataset;
import net.sf.jasperreports6.charts.JRXyzSeries;
import net.sf.jasperreports6.charts.base.JRBaseAreaPlot;
import net.sf.jasperreports6.charts.base.JRBaseBar3DPlot;
import net.sf.jasperreports6.charts.base.JRBaseBarPlot;
import net.sf.jasperreports6.charts.base.JRBaseBubblePlot;
import net.sf.jasperreports6.charts.base.JRBaseCandlestickPlot;
import net.sf.jasperreports6.charts.base.JRBaseCategoryDataset;
import net.sf.jasperreports6.charts.base.JRBaseCategorySeries;
import net.sf.jasperreports6.charts.base.JRBaseChartAxis;
import net.sf.jasperreports6.charts.base.JRBaseGanttDataset;
import net.sf.jasperreports6.charts.base.JRBaseGanttSeries;
import net.sf.jasperreports6.charts.base.JRBaseHighLowDataset;
import net.sf.jasperreports6.charts.base.JRBaseHighLowPlot;
import net.sf.jasperreports6.charts.base.JRBaseLinePlot;
import net.sf.jasperreports6.charts.base.JRBaseMeterPlot;
import net.sf.jasperreports6.charts.base.JRBaseMultiAxisPlot;
import net.sf.jasperreports6.charts.base.JRBasePie3DPlot;
import net.sf.jasperreports6.charts.base.JRBasePieDataset;
import net.sf.jasperreports6.charts.base.JRBasePiePlot;
import net.sf.jasperreports6.charts.base.JRBasePieSeries;
import net.sf.jasperreports6.charts.base.JRBaseScatterPlot;
import net.sf.jasperreports6.charts.base.JRBaseThermometerPlot;
import net.sf.jasperreports6.charts.base.JRBaseTimePeriodDataset;
import net.sf.jasperreports6.charts.base.JRBaseTimePeriodSeries;
import net.sf.jasperreports6.charts.base.JRBaseTimeSeries;
import net.sf.jasperreports6.charts.base.JRBaseTimeSeriesDataset;
import net.sf.jasperreports6.charts.base.JRBaseTimeSeriesPlot;
import net.sf.jasperreports6.charts.base.JRBaseValueDataset;
import net.sf.jasperreports6.charts.base.JRBaseXyDataset;
import net.sf.jasperreports6.charts.base.JRBaseXySeries;
import net.sf.jasperreports6.charts.base.JRBaseXyzDataset;
import net.sf.jasperreports6.charts.base.JRBaseXyzSeries;
import net.sf.jasperreports6.crosstabs.CrosstabColumnCell;
import net.sf.jasperreports6.crosstabs.JRCellContents;
import net.sf.jasperreports6.crosstabs.JRCrosstab;
import net.sf.jasperreports6.crosstabs.JRCrosstabBucket;
import net.sf.jasperreports6.crosstabs.JRCrosstabCell;
import net.sf.jasperreports6.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports6.crosstabs.JRCrosstabDataset;
import net.sf.jasperreports6.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports6.crosstabs.JRCrosstabParameter;
import net.sf.jasperreports6.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports6.crosstabs.base.BaseCrosstabColumnCell;
import net.sf.jasperreports6.crosstabs.base.JRBaseCellContents;
import net.sf.jasperreports6.crosstabs.base.JRBaseCrosstab;
import net.sf.jasperreports6.crosstabs.base.JRBaseCrosstabBucket;
import net.sf.jasperreports6.crosstabs.base.JRBaseCrosstabCell;
import net.sf.jasperreports6.crosstabs.base.JRBaseCrosstabColumnGroup;
import net.sf.jasperreports6.crosstabs.base.JRBaseCrosstabDataset;
import net.sf.jasperreports6.crosstabs.base.JRBaseCrosstabMeasure;
import net.sf.jasperreports6.crosstabs.base.JRBaseCrosstabParameter;
import net.sf.jasperreports6.crosstabs.base.JRBaseCrosstabRowGroup;
import net.sf.jasperreports6.engine.DatasetPropertyExpression;
import net.sf.jasperreports6.engine.ExpressionReturnValue;
import net.sf.jasperreports6.engine.JRAbstractObjectFactory;
import net.sf.jasperreports6.engine.JRBand;
import net.sf.jasperreports6.engine.JRBreak;
import net.sf.jasperreports6.engine.JRChart;
import net.sf.jasperreports6.engine.JRComponentElement;
import net.sf.jasperreports6.engine.JRConditionalStyle;
import net.sf.jasperreports6.engine.JRDataset;
import net.sf.jasperreports6.engine.JRDatasetParameter;
import net.sf.jasperreports6.engine.JRDatasetRun;
import net.sf.jasperreports6.engine.JRDefaultStyleProvider;
import net.sf.jasperreports6.engine.JRElementDataset;
import net.sf.jasperreports6.engine.JRElementGroup;
import net.sf.jasperreports6.engine.JREllipse;
import net.sf.jasperreports6.engine.JRExpression;
import net.sf.jasperreports6.engine.JRExpressionChunk;
import net.sf.jasperreports6.engine.JRExpressionCollector;
import net.sf.jasperreports6.engine.JRField;
import net.sf.jasperreports6.engine.JRFrame;
import net.sf.jasperreports6.engine.JRGenericElement;
import net.sf.jasperreports6.engine.JRGenericElementParameter;
import net.sf.jasperreports6.engine.JRGroup;
import net.sf.jasperreports6.engine.JRHyperlink;
import net.sf.jasperreports6.engine.JRHyperlinkParameter;
import net.sf.jasperreports6.engine.JRImage;
import net.sf.jasperreports6.engine.JRLine;
import net.sf.jasperreports6.engine.JRParameter;
import net.sf.jasperreports6.engine.JRPart;
import net.sf.jasperreports6.engine.JRPropertyExpression;
import net.sf.jasperreports6.engine.JRQuery;
import net.sf.jasperreports6.engine.JRQueryChunk;
import net.sf.jasperreports6.engine.JRRectangle;
import net.sf.jasperreports6.engine.JRReportTemplate;
import net.sf.jasperreports6.engine.JRRuntimeException;
import net.sf.jasperreports6.engine.JRScriptlet;
import net.sf.jasperreports6.engine.JRSection;
import net.sf.jasperreports6.engine.JRSortField;
import net.sf.jasperreports6.engine.JRStaticText;
import net.sf.jasperreports6.engine.JRStyle;
import net.sf.jasperreports6.engine.JRStyleContainer;
import net.sf.jasperreports6.engine.JRStyleSetter;
import net.sf.jasperreports6.engine.JRSubreport;
import net.sf.jasperreports6.engine.JRSubreportParameter;
import net.sf.jasperreports6.engine.JRSubreportReturnValue;
import net.sf.jasperreports6.engine.JRTextField;
import net.sf.jasperreports6.engine.JRVariable;
import net.sf.jasperreports6.engine.ReturnValue;
import net.sf.jasperreports6.engine.analytics.dataset.BaseDataAxis;
import net.sf.jasperreports6.engine.analytics.dataset.BaseDataAxisLevel;
import net.sf.jasperreports6.engine.analytics.dataset.BaseDataLevelBucket;
import net.sf.jasperreports6.engine.analytics.dataset.BaseDataLevelBucketProperty;
import net.sf.jasperreports6.engine.analytics.dataset.BaseDataMeasure;
import net.sf.jasperreports6.engine.analytics.dataset.BaseMultiAxisData;
import net.sf.jasperreports6.engine.analytics.dataset.BaseMultiAxisDataset;
import net.sf.jasperreports6.engine.analytics.dataset.DataAxis;
import net.sf.jasperreports6.engine.analytics.dataset.DataAxisLevel;
import net.sf.jasperreports6.engine.analytics.dataset.DataLevelBucket;
import net.sf.jasperreports6.engine.analytics.dataset.DataLevelBucketProperty;
import net.sf.jasperreports6.engine.analytics.dataset.DataMeasure;
import net.sf.jasperreports6.engine.analytics.dataset.MultiAxisData;
import net.sf.jasperreports6.engine.analytics.dataset.MultiAxisDataset;


/**
 * Factory of objects used in compiled reports.
 * 
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 */
public class JRBaseObjectFactory extends JRAbstractObjectFactory
{
	public static final String EXCEPTION_MESSAGE_KEY_CROSSTAB_ID_NOT_FOUND = "engine.object.factory.crosstab.id.not.found";
	public static final String EXCEPTION_MESSAGE_KEY_EXPRESSION_ID_NOT_FOUND = "engine.object.factory.expression.id.not.found";

	/**
	 *
	 */
	private JRDefaultStyleProvider defaultStyleProvider;

	/**
	 * Expression collector used to retrieve generated expression IDs.
	 */
	private JRExpressionCollector expressionCollector;
	

	/**
	 *
	 */
	protected JRBaseObjectFactory(JRDefaultStyleProvider defaultStyleProvider)
	{
		this.defaultStyleProvider = defaultStyleProvider;
	}


	/**
	 * Constructs a base object factory.
	 *
	 * @param defaultStyleProvider the default style provider
	 * @param expressionCollector the expression collector used as expression ID provider
	 */
	protected JRBaseObjectFactory(JRDefaultStyleProvider defaultStyleProvider, JRExpressionCollector expressionCollector)
	{
		this.defaultStyleProvider = defaultStyleProvider;
		this.expressionCollector = expressionCollector;
	}

	protected JRBaseObjectFactory(JRExpressionCollector expressionCollector)
	{
		this.expressionCollector = expressionCollector;
	}
	
	public void setDefaultStyleProvider(JRDefaultStyleProvider defaultStyleProvider)
	{
		this.defaultStyleProvider = defaultStyleProvider;
	}

	@Override
	public JRDefaultStyleProvider getDefaultStyleProvider()
	{
		return defaultStyleProvider;
	}


	@Override
	public JRStyle getStyle(JRStyle style)
	{
		JRBaseStyle baseStyle = null;

		if (style != null)
		{
			baseStyle = (JRBaseStyle)get(style);
			if (baseStyle == null)
			{
				baseStyle = new JRBaseStyle(style, this);
				put(style, baseStyle);
			}
		}

		return baseStyle;
	}


	/**
	 * This method preserves both specified styles and external style name references.
	 *
	 * @see JRAbstractObjectFactory#setStyle(JRStyleSetter, JRStyleContainer)
	 */
	@Override
	public void setStyle(JRStyleSetter setter, JRStyleContainer styleContainer)
	{
		JRStyle style = styleContainer.getStyle();
		String nameReference = styleContainer.getStyleNameReference();
		if (style != null)
		{
			JRStyle newStyle = getStyle(style);
			setter.setStyle(newStyle);
		}
		else if (nameReference != null)
		{
			handleStyleNameReference(setter, nameReference);
		}
	}

	protected void handleStyleNameReference(JRStyleSetter setter, String nameReference)
	{
		setter.setStyleNameReference(nameReference);
	}


	/**
	 *
	 */
	protected JRBaseScriptlet getScriptlet(JRScriptlet scriptlet)
	{
		JRBaseScriptlet baseScriptlet = null;

		if (scriptlet != null)
		{
			baseScriptlet = (JRBaseScriptlet)get(scriptlet);
			if (baseScriptlet == null)
			{
				baseScriptlet = new JRBaseScriptlet(scriptlet, this);
			}
		}

		return baseScriptlet;
	}


	/**
	 *
	 */
	protected JRBaseParameter getParameter(JRParameter parameter)
	{
		JRBaseParameter baseParameter = null;

		if (parameter != null)
		{
			baseParameter = (JRBaseParameter)get(parameter);
			if (baseParameter == null)
			{
				baseParameter = new JRBaseParameter(parameter, this);
			}
		}

		return baseParameter;
	}


	/**
	 *
	 */
	protected JRBaseQuery getQuery(JRQuery query)
	{
		JRBaseQuery baseQuery = null;

		if (query != null)
		{
			baseQuery = (JRBaseQuery)get(query);
			if (baseQuery == null)
			{
				baseQuery = new JRBaseQuery(query, this);
			}
		}

		return baseQuery;
	}


	/**
	 *
	 */
	protected JRBaseQueryChunk getQueryChunk(JRQueryChunk queryChunk)
	{
		JRBaseQueryChunk baseQueryChunk = null;

		if (queryChunk != null)
		{
			baseQueryChunk = (JRBaseQueryChunk)get(queryChunk);
			if (baseQueryChunk == null)
			{
				baseQueryChunk = new JRBaseQueryChunk(queryChunk, this);
			}
		}

		return baseQueryChunk;
	}


	/**
	 *
	 */
	protected JRBaseField getField(JRField field)
	{
		JRBaseField baseField = null;

		if (field != null)
		{
			baseField = (JRBaseField)get(field);
			if (baseField == null)
			{
				baseField = new JRBaseField(field, this);
			}
		}

		return baseField;
	}


	/**
	 *
	 */
	protected JRBaseSortField getSortField(JRSortField sortField)
	{
		JRBaseSortField baseSortField = null;

		if (sortField != null)
		{
			baseSortField = (JRBaseSortField)get(sortField);
			if (baseSortField == null)
			{
				baseSortField = new JRBaseSortField(sortField, this);
			}
		}

		return baseSortField;
	}


	/**
	 *
	 */
	public JRBaseVariable getVariable(JRVariable variable)
	{
		JRBaseVariable baseVariable = null;

		if (variable != null)
		{
			baseVariable = (JRBaseVariable)get(variable);
			if (baseVariable == null)
			{
				baseVariable = new JRBaseVariable(variable, this);
			}
		}

		return baseVariable;
	}


	@Override
	public JRExpression getExpression(JRExpression expression, boolean assignNotUsedId)
	{
		JRBaseExpression baseExpression = null;

		if (expression != null)
		{
			baseExpression = (JRBaseExpression)get(expression);
			if (baseExpression == null)
			{
				Integer expressionId = getCollectedExpressionId(expression, assignNotUsedId);
				baseExpression = new JRBaseExpression(expression, this, expressionId);
			}
		}

		return baseExpression;
	}


	private Integer getCollectedExpressionId(JRExpression expression, boolean assignNotUsedId)
	{
		Integer expressionId = null;
		if (expressionCollector != null)
		{
			expressionId = expressionCollector.getExpressionId(expression);
			if (expressionId == null)
			{
				if (assignNotUsedId)
				{
					expressionId = JRExpression.NOT_USED_ID;
				}
				else
				{
					throw 
						new JRRuntimeException(
							EXCEPTION_MESSAGE_KEY_EXPRESSION_ID_NOT_FOUND,
							new Object[]{expression.getText()});
				}
			}
		}
		return expressionId;
	}


	/**
	 *
	 */
	protected JRBaseExpressionChunk getExpressionChunk(JRExpressionChunk expressionChunk)
	{
		JRBaseExpressionChunk baseExpressionChunk = null;

		if (expressionChunk != null)
		{
			baseExpressionChunk = (JRBaseExpressionChunk)get(expressionChunk);
			if (baseExpressionChunk == null)
			{
				baseExpressionChunk = new JRBaseExpressionChunk(expressionChunk, this);
			}
		}

		return baseExpressionChunk;
	}


	/**
	 *
	 */
	protected JRBaseGroup getGroup(JRGroup group)
	{
		JRBaseGroup baseGroup = null;

		if (group != null)
		{
			baseGroup = (JRBaseGroup)get(group);
			if (baseGroup == null)
			{
				baseGroup = new JRBaseGroup(group, this);
			}
		}

		return baseGroup;
	}


	/**
	 *
	 */
	protected JRBaseSection getSection(JRSection section)
	{
		JRBaseSection baseSection = null;

		if (section != null)
		{
			baseSection = (JRBaseSection)get(section);
			if (baseSection == null)
			{
				baseSection = new JRBaseSection(section, this);
			}
		}

		return baseSection;
	}


	/**
	 *
	 */
	protected JRBaseBand getBand(JRBand band)
	{
		JRBaseBand baseBand = null;

		if (band != null)
		{
			baseBand = (JRBaseBand)get(band);
			if (baseBand == null)
			{
				baseBand = new JRBaseBand(band, this);
			}
		}

		return baseBand;
	}


	/**
	 *
	 */
	protected JRBasePart getPart(JRPart part)
	{
		JRBasePart basePart = null;

		if (part != null)
		{
			basePart = (JRBasePart)get(part);
			if (basePart == null)
			{
				basePart = new JRBasePart(part, this);
			}
		}

		return basePart;
	}


	@Override
	public void visitElementGroup(JRElementGroup elementGroup)
	{
		JRElementGroup baseElementGroup = null;

		if (elementGroup != null)
		{
			baseElementGroup = (JRElementGroup)get(elementGroup);
			if (baseElementGroup == null)
			{
				baseElementGroup = new JRBaseElementGroup(elementGroup, this);
			}
		}

		setVisitResult(baseElementGroup);
	}


	@Override
	public void visitBreak(JRBreak breakElement)
	{
		JRBaseBreak baseBreak = null;

		if (breakElement != null)
		{
			baseBreak = (JRBaseBreak)get(breakElement);
			if (baseBreak == null)
			{
				baseBreak = new JRBaseBreak(breakElement, this);
			}
		}

		setVisitResult(baseBreak);
	}


	@Override
	public void visitLine(JRLine line)
	{
		JRBaseLine baseLine = null;

		if (line != null)
		{
			baseLine = (JRBaseLine)get(line);
			if (baseLine == null)
			{
				baseLine = new JRBaseLine(line, this);
			}
		}

		setVisitResult(baseLine);
	}


	@Override
	public void visitRectangle(JRRectangle rectangle)
	{
		JRBaseRectangle baseRectangle = null;

		if (rectangle != null)
		{
			baseRectangle = (JRBaseRectangle)get(rectangle);
			if (baseRectangle == null)
			{
				baseRectangle = new JRBaseRectangle(rectangle, this);
			}
		}

		setVisitResult(baseRectangle);
	}


	@Override
	public void visitEllipse(JREllipse ellipse)
	{
		JRBaseEllipse baseEllipse = null;

		if (ellipse != null)
		{
			baseEllipse = (JRBaseEllipse)get(ellipse);
			if (baseEllipse == null)
			{
				baseEllipse = new JRBaseEllipse(ellipse, this);
			}
		}

		setVisitResult(baseEllipse);
	}


	@Override
	public void visitImage(JRImage image)
	{
		JRBaseImage baseImage = null;

		if (image != null)
		{
			baseImage = (JRBaseImage)get(image);
			if (baseImage == null)
			{
				baseImage = new JRBaseImage(image, this);
			}
		}

		setVisitResult(baseImage);
	}


	@Override
	public void visitStaticText(JRStaticText staticText)
	{
		JRBaseStaticText baseStaticText = null;

		if (staticText != null)
		{
			baseStaticText = (JRBaseStaticText)get(staticText);
			if (baseStaticText == null)
			{
				baseStaticText = new JRBaseStaticText(staticText, this);
			}
		}

		setVisitResult(baseStaticText);
	}


	@Override
	public void visitTextField(JRTextField textField)
	{
		JRBaseTextField baseTextField = null;

		if (textField != null)
		{
			baseTextField = (JRBaseTextField)get(textField);
			if (baseTextField == null)
			{
				baseTextField = new JRBaseTextField(textField, this);
			}
		}

		setVisitResult(baseTextField);
	}


	@Override
	public void visitSubreport(JRSubreport subreport)
	{
		JRBaseSubreport baseSubreport = null;

		if (subreport != null)
		{
			baseSubreport = (JRBaseSubreport)get(subreport);
			if (baseSubreport == null)
			{
				baseSubreport = new JRBaseSubreport(subreport, this);
			}
		}

		setVisitResult(baseSubreport);
	}


	/**
	 *
	 */
	public JRBaseSubreportParameter getSubreportParameter(JRSubreportParameter subreportParameter)
	{
		JRBaseSubreportParameter baseSubreportParameter = null;

		if (subreportParameter != null)
		{
			baseSubreportParameter = (JRBaseSubreportParameter)get(subreportParameter);
			if (baseSubreportParameter == null)
			{
				baseSubreportParameter = new JRBaseSubreportParameter(subreportParameter, this);
				put(subreportParameter, baseSubreportParameter);
			}
		}

		return baseSubreportParameter;
	}


	protected JRBaseDatasetParameter getDatasetParameter(JRDatasetParameter datasetParameter)
	{
		JRBaseDatasetParameter baseSubreportParameter = null;

		if (datasetParameter != null)
		{
			baseSubreportParameter = (JRBaseDatasetParameter) get(datasetParameter);
			if (baseSubreportParameter == null)
			{
				baseSubreportParameter = new JRBaseDatasetParameter(datasetParameter, this);
				put(datasetParameter, baseSubreportParameter);
			}
		}

		return baseSubreportParameter;
	}


	@Override
	public JRPieDataset getPieDataset(JRPieDataset pieDataset)
	{
		JRBasePieDataset basePieDataset = null;

		if (pieDataset != null)
		{
			basePieDataset = (JRBasePieDataset)get(pieDataset);
			if (basePieDataset == null)
			{
				basePieDataset = new JRBasePieDataset(pieDataset, this);
			}
		}

		return basePieDataset;
	}


	@Override
	public JRPiePlot getPiePlot(JRPiePlot piePlot)
	{
		JRBasePiePlot basePiePlot = null;

		if (piePlot != null)
		{
			basePiePlot = (JRBasePiePlot)get(piePlot);
			if (basePiePlot == null)
			{
				basePiePlot = new JRBasePiePlot(piePlot, this);
			}
		}

		return basePiePlot;
	}


	@Override
	public JRPie3DPlot getPie3DPlot(JRPie3DPlot pie3DPlot)
	{
		JRBasePie3DPlot basePie3DPlot = null;

		if (pie3DPlot != null)
		{
			basePie3DPlot = (JRBasePie3DPlot)get(pie3DPlot);
			if (basePie3DPlot == null)
			{
				basePie3DPlot = new JRBasePie3DPlot(pie3DPlot, this);
			}
		}

		return basePie3DPlot;
	}


	@Override
	public JRCategoryDataset getCategoryDataset(JRCategoryDataset categoryDataset)
	{
		JRBaseCategoryDataset baseCategoryDataset = null;

		if (categoryDataset != null)
		{
			baseCategoryDataset = (JRBaseCategoryDataset)get(categoryDataset);
			if (baseCategoryDataset == null)
			{
				baseCategoryDataset = new JRBaseCategoryDataset(categoryDataset, this);
			}
		}

		return baseCategoryDataset;
	}

	@Override
	public JRTimeSeriesDataset getTimeSeriesDataset( JRTimeSeriesDataset timeSeriesDataset ){
		JRBaseTimeSeriesDataset baseTimeSeriesDataset = null;
		if( timeSeriesDataset != null ){
			baseTimeSeriesDataset = (JRBaseTimeSeriesDataset)get( timeSeriesDataset );
			if( baseTimeSeriesDataset == null ){
				baseTimeSeriesDataset = new JRBaseTimeSeriesDataset( timeSeriesDataset, this );
			}
		}

		return baseTimeSeriesDataset;
	}


	@Override
	public JRTimePeriodDataset getTimePeriodDataset( JRTimePeriodDataset timePeriodDataset ){
		JRBaseTimePeriodDataset baseTimePeriodDataset = null;
		if( timePeriodDataset != null ){
			baseTimePeriodDataset = (JRBaseTimePeriodDataset)get( timePeriodDataset );
			if( baseTimePeriodDataset == null ){
				baseTimePeriodDataset = new JRBaseTimePeriodDataset( timePeriodDataset, this );
			}
		}
		return baseTimePeriodDataset;
	}

	/*
	 * 
	 */
	public JRGanttDataset getGanttDataset(JRGanttDataset ganttDataset)
	{
		JRBaseGanttDataset baseGanttDataset = null;
		
		if (ganttDataset != null)
		{
			baseGanttDataset = (JRBaseGanttDataset)get(ganttDataset);
			if (baseGanttDataset == null)
			{
				baseGanttDataset = new JRBaseGanttDataset(ganttDataset, this);
			}
		}
		
		return baseGanttDataset;
	}

	
	@Override
	public JRPieSeries getPieSeries(JRPieSeries pieSeries)
	{
		JRBasePieSeries basePieSeries = null;

		if (pieSeries != null)
		{
			basePieSeries = (JRBasePieSeries)get(pieSeries);
			if (basePieSeries == null)
			{
				basePieSeries = new JRBasePieSeries(pieSeries, this);
			}
		}

		return basePieSeries;
	}
	
	
	@Override
	public JRCategorySeries getCategorySeries(JRCategorySeries categorySeries)
	{
		JRBaseCategorySeries baseCategorySeries = null;

		if (categorySeries != null)
		{
			baseCategorySeries = (JRBaseCategorySeries)get(categorySeries);
			if (baseCategorySeries == null)
			{
				baseCategorySeries = new JRBaseCategorySeries(categorySeries, this);
			}
		}

		return baseCategorySeries;
	}


	/**
	 *
	 */
	public JRXySeries getXySeries(JRXySeries xySeries)
	{
		JRBaseXySeries baseXySeries = null;

		if (xySeries != null)
		{
			baseXySeries = (JRBaseXySeries)get(xySeries);
			if (baseXySeries == null)
			{
				baseXySeries = new JRBaseXySeries(xySeries, this);
			}
		}

		return baseXySeries;
	}


	@Override
	public JRTimeSeries getTimeSeries(JRTimeSeries timeSeries)
	{
		JRBaseTimeSeries baseTimeSeries = null;

		if (timeSeries != null)
		{
			baseTimeSeries = (JRBaseTimeSeries)get(timeSeries);
			if (baseTimeSeries == null)
			{
				baseTimeSeries = new JRBaseTimeSeries(timeSeries, this);
			}
		}

		return baseTimeSeries;
	}

	@Override
	public JRTimePeriodSeries getTimePeriodSeries( JRTimePeriodSeries timePeriodSeries ){
		JRBaseTimePeriodSeries baseTimePeriodSeries = null;
		if( timePeriodSeries != null ){
			baseTimePeriodSeries = (JRBaseTimePeriodSeries)get( timePeriodSeries );
			if( baseTimePeriodSeries == null ){
				baseTimePeriodSeries = new JRBaseTimePeriodSeries( timePeriodSeries, this );
			}
		}

		return baseTimePeriodSeries;
	}


	/**
	 * 
	 */
	public JRGanttSeries getGanttSeries(JRGanttSeries ganttSeries)
	{
		JRBaseGanttSeries baseGanttSeries = null;
		
		if (ganttSeries != null)
		{
			baseGanttSeries = (JRBaseGanttSeries)get(ganttSeries);
			if (baseGanttSeries == null)
			{
				baseGanttSeries = new JRBaseGanttSeries(ganttSeries, this);
			}
		}
		
		return baseGanttSeries;
	}


	@Override
	public JRBarPlot getBarPlot(JRBarPlot barPlot)
	{
		JRBaseBarPlot baseBarPlot = null;

		if (barPlot != null)
		{
			baseBarPlot = (JRBaseBarPlot)get(barPlot);
			if (baseBarPlot == null)
			{
				baseBarPlot = new JRBaseBarPlot(barPlot, this);
			}
		}

		return baseBarPlot;
	}


	/* (non-Javadoc)
	 * @see net.sf.jasperreports.engine.JRAbstractObjectFactory#getBar3DPlot(net.sf.jasperreports.charts.JRBar3DPlot)
	 */
	@Override
	public JRBar3DPlot getBar3DPlot(JRBar3DPlot barPlot) {
		JRBaseBar3DPlot baseBarPlot = null;

		if (barPlot != null)
		{
			baseBarPlot = (JRBaseBar3DPlot)get(barPlot);
			if (baseBarPlot == null)
			{
				baseBarPlot = new JRBaseBar3DPlot(barPlot, this);
			}
		}

		return baseBarPlot;
	}


	@Override
	public JRLinePlot getLinePlot(JRLinePlot linePlot) {
		JRBaseLinePlot baseLinePlot = null;

		if (linePlot != null)
		{
			baseLinePlot = (JRBaseLinePlot)get(linePlot);
			if (baseLinePlot == null)
			{
				baseLinePlot = new JRBaseLinePlot(linePlot, this);
			}
		}

		return baseLinePlot;
	}


	@Override
	public JRAreaPlot getAreaPlot(JRAreaPlot areaPlot) {
		JRBaseAreaPlot baseAreaPlot = null;

		if (areaPlot != null)
		{
			baseAreaPlot = (JRBaseAreaPlot)get(areaPlot);
			if (baseAreaPlot == null)
			{
				baseAreaPlot = new JRBaseAreaPlot(areaPlot, this);
			}
		}

		return baseAreaPlot;
	}


	@Override
	public JRXyzDataset getXyzDataset(JRXyzDataset xyzDataset) {
		JRBaseXyzDataset baseXyzDataset = null;

		if (xyzDataset != null)
		{
			baseXyzDataset = (JRBaseXyzDataset)get(xyzDataset);
			if (baseXyzDataset == null)
			{
				baseXyzDataset = new JRBaseXyzDataset(xyzDataset, this);
			}
		}

		return baseXyzDataset;
	}


	/*
	 *
	 */
	public JRXyDataset getXyDataset(JRXyDataset xyDataset) {
		JRBaseXyDataset baseXyDataset = null;

		if (xyDataset != null)
		{
			baseXyDataset = (JRBaseXyDataset)get(xyDataset);
			if (baseXyDataset == null)
			{
				baseXyDataset = new JRBaseXyDataset(xyDataset, this);
			}
		}

		return baseXyDataset;
	}

	/*
	 *
	 */
	public JRHighLowDataset getHighLowDataset(JRHighLowDataset highLowDataset) {
		JRBaseHighLowDataset baseHighLowDataset = null;

		if (highLowDataset != null)
		{
			baseHighLowDataset = (JRBaseHighLowDataset)get(highLowDataset);
			if (baseHighLowDataset == null)
			{
				baseHighLowDataset = new JRBaseHighLowDataset(highLowDataset, this);
			}
		}

		return baseHighLowDataset;
	}


	@Override
	public JRXyzSeries getXyzSeries(JRXyzSeries xyzSeries) {
		JRBaseXyzSeries baseXyzSeries = null;

		if (xyzSeries != null)
		{
			baseXyzSeries = (JRBaseXyzSeries)get(xyzSeries);
			if (baseXyzSeries == null)
			{
				baseXyzSeries = new JRBaseXyzSeries(xyzSeries, this);
			}
		}

		return baseXyzSeries;
	}


	@Override
	public JRBubblePlot getBubblePlot(JRBubblePlot bubblePlot) {
		JRBaseBubblePlot baseBubblePlot = null;

		if (bubblePlot != null)
		{
			baseBubblePlot = (JRBaseBubblePlot)get(bubblePlot);
			if (baseBubblePlot == null)
			{
				baseBubblePlot = new JRBaseBubblePlot(bubblePlot, this);
			}
		}

		return baseBubblePlot;
	}


	@Override
	public JRCandlestickPlot getCandlestickPlot(JRCandlestickPlot candlestickPlot)
	{
		JRBaseCandlestickPlot baseCandlestickPlot = null;

		if (candlestickPlot != null)
		{
			baseCandlestickPlot = (JRBaseCandlestickPlot)get(candlestickPlot);
			if (baseCandlestickPlot == null)
			{
				baseCandlestickPlot = new JRBaseCandlestickPlot(candlestickPlot, this);
			}
		}

		return baseCandlestickPlot;
	}


	/**
	 *
	 */
	public JRHighLowPlot getHighLowPlot(JRHighLowPlot highLowPlot)
	{
		JRBaseHighLowPlot baseHighLowPlot = null;

		if (highLowPlot != null)
		{
			baseHighLowPlot = (JRBaseHighLowPlot)get(highLowPlot);
			if (baseHighLowPlot == null)
			{
				baseHighLowPlot = new JRBaseHighLowPlot(highLowPlot, this);
			}
		}

		return baseHighLowPlot;
	}


	/**
	 *
	 */
	public JRScatterPlot getScatterPlot(JRScatterPlot scatterPlot)
	{
		JRBaseScatterPlot baseScatterPlot = null;

		if (scatterPlot != null)
		{
			baseScatterPlot = (JRBaseScatterPlot)get(scatterPlot);
			if (baseScatterPlot == null)
			{
				baseScatterPlot = new JRBaseScatterPlot(scatterPlot, this);
			}
		}

		return baseScatterPlot;
	}



	public JRTimeSeriesPlot getTimeSeriesPlot( JRTimeSeriesPlot plot ){
		JRBaseTimeSeriesPlot basePlot = null;
		if( plot != null ){
			basePlot = (JRBaseTimeSeriesPlot)get( plot );
			if( basePlot == null ){
				basePlot = new JRBaseTimeSeriesPlot( plot, this );
			}
		}

		return basePlot;
	}


	/**
	 *
	 */
	public JRValueDataset getValueDataset(JRValueDataset valueDataset)
	{
		JRBaseValueDataset baseValueDataset = null;

		if (valueDataset != null)
		{
			baseValueDataset = (JRBaseValueDataset)get(valueDataset);
			if (baseValueDataset == null)
			{
				baseValueDataset = new JRBaseValueDataset(valueDataset, this);
			}
		}

		return baseValueDataset;
	}


	/**
	 *
	 */
	public JRMeterPlot getMeterPlot(JRMeterPlot meterPlot)
	{
		JRBaseMeterPlot baseMeterPlot = null;

		if (meterPlot != null)
		{
			baseMeterPlot = (JRBaseMeterPlot)get(meterPlot);
			if (baseMeterPlot == null)
			{
				baseMeterPlot = new JRBaseMeterPlot(meterPlot, this);
			}
		}

		return baseMeterPlot;
	}


	/**
	 *
	 */
	public JRThermometerPlot getThermometerPlot(JRThermometerPlot thermometerPlot)
	{
		JRBaseThermometerPlot baseThermometerPlot = null;

		if (thermometerPlot != null)
		{
			baseThermometerPlot = (JRBaseThermometerPlot)get(thermometerPlot);
			if (baseThermometerPlot == null)
			{
				baseThermometerPlot = new JRBaseThermometerPlot(thermometerPlot, this);
			}
		}

		return baseThermometerPlot;
	}


	/**
	 *
	 */
	public JRMultiAxisPlot getMultiAxisPlot(JRMultiAxisPlot multiAxisPlot)
	{
		JRBaseMultiAxisPlot baseMultiAxisPlot = null;

		if (multiAxisPlot != null)
		{
			baseMultiAxisPlot = (JRBaseMultiAxisPlot)get(multiAxisPlot);
			if (baseMultiAxisPlot == null)
			{
				baseMultiAxisPlot = new JRBaseMultiAxisPlot(multiAxisPlot, this);
			}
		}

		return baseMultiAxisPlot;
	}


	@Override
	public void visitChart(JRChart chart)
	{
		JRBaseChart baseChart = null;

		if (chart != null)
		{
			baseChart = (JRBaseChart)get(chart);
			if (baseChart == null)
			{
				baseChart = new JRBaseChart(chart, this);
			}
		}

		setVisitResult(baseChart);
	}

	/**
	 *
	 */
	public JRBaseSubreportReturnValue getSubreportReturnValue(JRSubreportReturnValue returnValue)
	{
		JRBaseSubreportReturnValue baseSubreportReturnValue = null;

		if (returnValue != null)
		{
			baseSubreportReturnValue = (JRBaseSubreportReturnValue)get(returnValue);
			if (baseSubreportReturnValue == null)
			{
				baseSubreportReturnValue = new JRBaseSubreportReturnValue(returnValue, this);
				put(returnValue, baseSubreportReturnValue);
			}
		}

		return baseSubreportReturnValue;
	}


	protected BaseReturnValue getReturnValue(ReturnValue returnValue)
	{
		BaseReturnValue baseReturnValue = null;

		if (returnValue != null)
		{
			baseReturnValue = (BaseReturnValue) get(returnValue);
			if (baseReturnValue == null)
			{
				baseReturnValue = new BaseReturnValue(returnValue, this);
				put(returnValue, baseReturnValue);
			}
		}

		return baseReturnValue;
	}


	protected BaseExpressionReturnValue getReturnValue(ExpressionReturnValue returnValue)
	{
		BaseExpressionReturnValue baseReturnValue = null;

		if (returnValue != null)
		{
			baseReturnValue = (BaseExpressionReturnValue) get(returnValue);
			if (baseReturnValue == null)
			{
				baseReturnValue = new BaseExpressionReturnValue(returnValue, this);
				put(returnValue, baseReturnValue);
			}
		}

		return baseReturnValue;
	}


	@Override
	public JRConditionalStyle getConditionalStyle(JRConditionalStyle conditionalStyle, JRStyle style)
	{
		JRBaseConditionalStyle baseConditionalStyle = null;
		if (conditionalStyle != null)
		{
			baseConditionalStyle = (JRBaseConditionalStyle) get(conditionalStyle);
			if (baseConditionalStyle == null) {
				baseConditionalStyle = new JRBaseConditionalStyle(conditionalStyle, style, this);
				put(conditionalStyle, baseConditionalStyle);
			}
		}
		return baseConditionalStyle;
	}


	public JRBaseCrosstabDataset getCrosstabDataset(JRCrosstabDataset crosstabDataset)
	{
		JRBaseCrosstabDataset baseCrosstabDataset = null;

		if (crosstabDataset != null)
		{
			baseCrosstabDataset = (JRBaseCrosstabDataset) get(crosstabDataset);
			if (baseCrosstabDataset == null)
			{
				baseCrosstabDataset = new JRBaseCrosstabDataset(crosstabDataset, this);
			}
		}

		return baseCrosstabDataset;
	}


	public JRBaseCrosstabRowGroup getCrosstabRowGroup(JRCrosstabRowGroup group)
	{
		JRBaseCrosstabRowGroup baseCrosstabRowGroup = null;

		if (group != null)
		{
			baseCrosstabRowGroup = (JRBaseCrosstabRowGroup) get(group);
			if (baseCrosstabRowGroup == null)
			{
				baseCrosstabRowGroup = new JRBaseCrosstabRowGroup(group, this);
			}
		}

		return baseCrosstabRowGroup;
	}


	public JRBaseCrosstabColumnGroup getCrosstabColumnGroup(JRCrosstabColumnGroup group)
	{
		JRBaseCrosstabColumnGroup baseCrosstabDataset = null;

		if (group != null)
		{
			baseCrosstabDataset = (JRBaseCrosstabColumnGroup) get(group);
			if (baseCrosstabDataset == null)
			{
				baseCrosstabDataset = new JRBaseCrosstabColumnGroup(group, this);
			}
		}

		return baseCrosstabDataset;
	}


	public JRBaseCrosstabBucket getCrosstabBucket(JRCrosstabBucket bucket)
	{
		JRBaseCrosstabBucket baseCrosstabBucket = null;

		if (bucket != null)
		{
			baseCrosstabBucket = (JRBaseCrosstabBucket) get(bucket);
			if (baseCrosstabBucket == null)
			{
				baseCrosstabBucket = new JRBaseCrosstabBucket(bucket, this);
			}
		}

		return baseCrosstabBucket;
	}


	public JRBaseCrosstabMeasure getCrosstabMeasure(JRCrosstabMeasure measure)
	{
		JRBaseCrosstabMeasure baseCrosstabMeasure = null;

		if (measure != null)
		{
			baseCrosstabMeasure = (JRBaseCrosstabMeasure) get(measure);
			if (baseCrosstabMeasure == null)
			{
				baseCrosstabMeasure = new JRBaseCrosstabMeasure(measure, this);
			}
		}

		return baseCrosstabMeasure;
	}


	@Override
	public void visitCrosstab(JRCrosstab crosstab)
	{
		JRBaseCrosstab baseCrosstab = null;

		if (crosstab != null)
		{
			baseCrosstab = (JRBaseCrosstab) get(crosstab);
			if (baseCrosstab == null)
			{
				int crosstabId = resolveCrosstabId(crosstab);
				baseCrosstab = new JRBaseCrosstab(crosstab, this, crosstabId);
			}
		}

		setVisitResult(baseCrosstab);
	}


	protected int resolveCrosstabId(JRCrosstab crosstab)
	{
		Integer id = expressionCollector.getCrosstabId(crosstab);
		if (id == null)
		{
			throw 
				new JRRuntimeException(
					EXCEPTION_MESSAGE_KEY_CROSSTAB_ID_NOT_FOUND,
					(Object[])null);
		}
		return id;
	}


	public JRBaseDataset getDataset(JRDataset dataset)
	{
		JRBaseDataset baseDataset = null;

		if (dataset != null)
		{
			baseDataset = (JRBaseDataset)get(dataset);
			if (baseDataset == null)
			{
				baseDataset = new JRBaseDataset(dataset, this);
			}
		}

		return baseDataset;
	}


	public JRBaseDatasetRun getDatasetRun(JRDatasetRun datasetRun)
	{
		JRBaseDatasetRun baseDatasetRun = null;

		if (datasetRun != null)
		{
			baseDatasetRun = (JRBaseDatasetRun)get(datasetRun);
			if (baseDatasetRun == null)
			{
				baseDatasetRun = new JRBaseDatasetRun(datasetRun, this);
			}
		}

		return baseDatasetRun;
	}


	public JRBaseCellContents getCell(JRCellContents cell)
	{
		JRBaseCellContents baseCell = null;

		if (cell != null)
		{
			baseCell = (JRBaseCellContents)get(cell);
			if (baseCell == null)
			{
				baseCell = new JRBaseCellContents(cell, this);
			}
		}

		return baseCell;
	}


	public JRCrosstabCell getCrosstabCell(JRCrosstabCell cell)
	{
		JRBaseCrosstabCell baseCell = null;

		if (cell != null)
		{
			baseCell = (JRBaseCrosstabCell)get(cell);
			if (baseCell == null)
			{
				baseCell = new JRBaseCrosstabCell(cell, this);
			}
		}

		return baseCell;
	}


	public JRBaseCrosstabParameter getCrosstabParameter(JRCrosstabParameter parameter)
	{
		JRBaseCrosstabParameter baseParameter = null;

		if (parameter != null)
		{
			baseParameter = (JRBaseCrosstabParameter) get(parameter);
			if (baseParameter == null)
			{
				baseParameter = new JRBaseCrosstabParameter(parameter, this);
			}
		}

		return baseParameter;
	}


	@Override
	public void visitFrame(JRFrame frame)
	{
		JRBaseFrame baseFrame = null;

		if (frame != null)
		{
			baseFrame = (JRBaseFrame) get(frame);
			if (baseFrame == null)
			{
				baseFrame = new JRBaseFrame(frame, this);
			}
		}

		setVisitResult(baseFrame);
	}


	public JRHyperlinkParameter getHyperlinkParameter(JRHyperlinkParameter parameter)
	{
		JRHyperlinkParameter baseParameter = null;

		if (parameter != null)
		{
			baseParameter = (JRHyperlinkParameter) get(parameter);
			if (baseParameter == null)
			{
				baseParameter = new JRBaseHyperlinkParameter(parameter, this);
			}
		}

		return baseParameter;
	}


	public JRHyperlink getHyperlink(JRHyperlink hyperlink)
	{
		JRHyperlink link = null;
		if (hyperlink != null)
		{
			link = (JRHyperlink) get(hyperlink);
			if (link == null)
			{
				link = new JRBaseHyperlink(hyperlink, this);
			}
		}
		return link;
	}


	public JRChartAxis getChartAxis(JRChartAxis axis)
	{
		JRChartAxis baseAxis = null;
		if (axis != null)
		{
			baseAxis = (JRChartAxis) get(axis);
			if (baseAxis == null)
			{
				baseAxis = new JRBaseChartAxis(axis, this);
			}
		}
		return baseAxis;
	}

	public JRReportTemplate getReportTemplate(JRReportTemplate template)
	{
		JRReportTemplate baseTemplate = null;
		if (template != null)
		{
			baseTemplate = (JRReportTemplate) get(template);
			if (baseTemplate == null)
			{
				baseTemplate = new JRBaseReportTemplate(template, this);
			}
		}
		return baseTemplate;
	}

	public JRPropertyExpression[] getPropertyExpressions(JRPropertyExpression[] props)
	{
		JRPropertyExpression[] propertyExpressions = null;
		if (props != null && props.length > 0)
		{
			propertyExpressions = new JRPropertyExpression[props.length];
			for (int i = 0; i < props.length; i++)
			{
				propertyExpressions[i] = getPropertyExpression(props[i]);
			}
		}
		return propertyExpressions;
	}

	public JRPropertyExpression getPropertyExpression(JRPropertyExpression propertyExpression)
	{
		JRPropertyExpression baseProp = null;
		if (propertyExpression != null)
		{
			baseProp = (JRPropertyExpression) get(propertyExpression);
			if (baseProp == null)
			{
				baseProp = new JRBasePropertyExpression(propertyExpression, this);
			}
		}
		return baseProp;
	}

	public DatasetPropertyExpression[] getPropertyExpressions(DatasetPropertyExpression[] props)
	{
		DatasetPropertyExpression[] propertyExpressions = null;
		if (props != null && props.length > 0)
		{
			propertyExpressions = new DatasetPropertyExpression[props.length];
			for (int i = 0; i < props.length; i++)
			{
				propertyExpressions[i] = getPropertyExpression(props[i]);
			}
		}
		return propertyExpressions;
	}

	public DatasetPropertyExpression getPropertyExpression(DatasetPropertyExpression propertyExpression)
	{
		DatasetPropertyExpression baseProp = null;
		if (propertyExpression != null)
		{
			baseProp = (DatasetPropertyExpression) get(propertyExpression);
			if (baseProp == null)
			{
				baseProp = new BaseDatasetPropertyExpression(propertyExpression, this);
			}
		}
		return baseProp;
	}


	@Override
	public void visitComponentElement(JRComponentElement componentElement)
	{
		JRBaseComponentElement base = null;

		if (componentElement != null)
		{
			base = (JRBaseComponentElement) get(componentElement);
			if (base == null)
			{
				base = new JRBaseComponentElement(componentElement, this);
			}
		}

		setVisitResult(base);
	}


	public JRGenericElementParameter getGenericElementParameter(
			JRGenericElementParameter elementParameter)
	{
		JRGenericElementParameter baseParameter = null;
		if (elementParameter != null)
		{
			baseParameter = (JRGenericElementParameter) get(elementParameter);
			if (baseParameter == null)
			{
				baseParameter = new JRBaseGenericElementParameter(
						elementParameter, this);
			}
		}
		return baseParameter;
	}


	@Override
	public void visitGenericElement(JRGenericElement element)
	{
		JRBaseGenericElement base = null;
		if (element != null)
		{
			base = (JRBaseGenericElement) get(element);
			if (base == null)
			{
				base = new JRBaseGenericElement(element, this);
			}
		}
		setVisitResult(base);
	}


	public MultiAxisData getMultiAxisData(MultiAxisData data)
	{
		MultiAxisData baseData = null;
		if (data != null)
		{
			baseData = (MultiAxisData) get(data);
			if (baseData == null)
			{
				baseData = new BaseMultiAxisData(data, this);
			}
		}
		return baseData;
	}


	public MultiAxisDataset getMultiAxisDataset(MultiAxisDataset dataset)
	{
		MultiAxisDataset baseDataset = null;
		if (dataset != null)
		{
			baseDataset = (MultiAxisDataset) get(dataset);
			if (baseDataset == null)
			{
				baseDataset = new BaseMultiAxisDataset(dataset, this);
			}
		}
		return baseDataset;
	}

	
	public JRElementDataset getElementDataset(JRElementDataset dataset)
	{
		JRElementDataset baseDataset = null;
		if (dataset != null)
		{
			baseDataset = (JRElementDataset) get(dataset);
			if (baseDataset == null)
			{
				baseDataset = new JRBaseElementDataset(dataset, this);
			}
		}
		return baseDataset;
	}


	public DataAxis getDataAxis(DataAxis axis)
	{
		DataAxis baseAxis = null;
		if (axis != null)
		{
			baseAxis = (DataAxis) get(axis);
			if (baseAxis == null)
			{
				baseAxis = new BaseDataAxis(axis, this);
			}
		}
		return baseAxis;
	}


	public DataAxisLevel getDataAxisLevel(DataAxisLevel level)
	{
		DataAxisLevel baseLevel = null;
		if (level != null)
		{
			baseLevel = (DataAxisLevel) get(level);
			if (baseLevel == null)
			{
				baseLevel = new BaseDataAxisLevel(level, this);
			}
		}
		return baseLevel;
	}


	public DataLevelBucket getDataLevelBucket(DataLevelBucket bucket)
	{
		DataLevelBucket baseBucket = null;
		if (bucket != null)
		{
			baseBucket = (DataLevelBucket) get(bucket);
			if (baseBucket == null)
			{
				baseBucket = new BaseDataLevelBucket(bucket, this);
			}
		}
		return baseBucket;
	}


	public DataMeasure getDataMeasure(DataMeasure measure)
	{
		DataMeasure baseMeasure = null;
		if (measure != null)
		{
			baseMeasure = (DataMeasure) get(measure);
			if (baseMeasure == null)
			{
				baseMeasure = new BaseDataMeasure(measure, this);
			}
		}
		return baseMeasure;
	}


	public DataLevelBucketProperty getDataLevelBucketProperty(DataLevelBucketProperty bucketProperty)
	{
		DataLevelBucketProperty baseBucketProperty = null;
		if (bucketProperty != null)
		{
			baseBucketProperty = (DataLevelBucketProperty) get(bucketProperty);
			if (baseBucketProperty == null)
			{
				baseBucketProperty = new BaseDataLevelBucketProperty(bucketProperty, this);
			}
		}
		return baseBucketProperty;
	}


	public CrosstabColumnCell getCrosstabColumnCell(CrosstabColumnCell cell)
	{
		BaseCrosstabColumnCell baseCell = null;

		if (cell != null)
		{
			baseCell = (BaseCrosstabColumnCell) get(cell);
			if (baseCell == null)
			{
				baseCell = new BaseCrosstabColumnCell(cell, this);
			}
		}

		return baseCell;
	}
}
