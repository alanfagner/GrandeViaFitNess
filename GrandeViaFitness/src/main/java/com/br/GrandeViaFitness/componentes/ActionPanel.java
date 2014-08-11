package com.br.GrandeViaFitness.componentes;

import java.util.List;

import org.apache.wicket.ajax.markup.html.form.AjaxButton;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

public abstract class ActionPanel<T> extends Panel
{

    private static final long serialVersionUID = -4848781299323335193L;
    private final T auxEntidade;

    public ActionPanel(final String id, final IModel<T> entidade, final List<AjaxButton> listaBotes)
    {
        super(id);
        this.auxEntidade = entidade.getObject();
        criabotoes(listaBotes);
    }

    private void criabotoes(final List<AjaxButton> listaBotes)
    {
        for (final AjaxButton auxBotao : listaBotes)
        {
            add(auxBotao);
        }

    }

    public ActionPanel(final String id, final IModel<T> entidade)
    {
        super(id);
        this.auxEntidade = entidade.getObject();
        add(new Link<T>("select")
        {

            private static final long serialVersionUID = 2035588357639837386L;

            @Override
            public void onClick()
            {
                onClickLink(getAuxEntidade());
            }
        });
    }

    protected abstract void onClickLink(T entidade);

    public T getAuxEntidade()
    {
        return auxEntidade;
    }
}
