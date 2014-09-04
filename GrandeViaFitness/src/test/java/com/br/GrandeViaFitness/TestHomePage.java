package com.br.GrandeViaFitness;

import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;
import com.br.GrandeViaFitness.application.WicketApplication;
import com.br.GrandeViaFitness.pages.visao.HomePageIndex;

/**
 * Simple test using the WicketTester
 */
public class TestHomePage
{
	private WicketTester tester;

	@Before
	public void setUp()
	{
		tester = new WicketTester(new WicketApplication());
	}

	@Test
	public void homepageRendersSuccessfully()
	{
		//start and render the test page
		tester.startPage(HomePageIndex.class);

		//assert rendered page class
		tester.assertRenderedPage(HomePageIndex.class);
	}
}
