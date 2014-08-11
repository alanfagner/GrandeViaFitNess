package com.br.GrandeViaFitness.pages.visao.basePage;

import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.Url;
import org.apache.wicket.request.resource.UrlResourceReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasePage extends WebPage {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final long serialVersionUID = 1l;

	public BasePage() {
	}

	@Override
	public void renderHead(final IHeaderResponse response) {
        /*
         * addCss(response, "./common/css/GrandeViaFitness.min.css.css");
         * addCss(response, "./common/css/jquery.mobile.icons.min.css.css");
         */
        adicionarthemas(response);
	}

    private void adicionarthemas(final IHeaderResponse response)
    {
        addCss(response, "./common/css/960.css");
        addCss(response, "./common/thema/jquery-ui.css");
        addJS(response, "./common/thema/external/jquery/jquery.js");
        addJS(response, "./common/thema/jquery-ui.js");

    }

    private void addJS(final IHeaderResponse response, final String js)
    {
        final Url url = Url.parse(js);
        response.render(JavaScriptHeaderItem.forReference(new UrlResourceReference(url)));
    }

	private void addCss(final IHeaderResponse response, final String css) {
		final Url url = Url.parse(css);
		response.render(CssHeaderItem
				.forReference(new UrlResourceReference(url)));
	}
}
