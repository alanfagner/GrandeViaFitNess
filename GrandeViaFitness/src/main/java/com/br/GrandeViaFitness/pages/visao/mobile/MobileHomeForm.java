package com.br.GrandeViaFitness.pages.visao.mobile;

import java.util.Date;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.OnDomReadyHeaderItem;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PageableListView;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import com.br.GrandeViaFitness.as.CorpoAS;
import com.br.GrandeViaFitness.as.PessoaAS;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.enumUtil.PermissaoEnum;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.pages.visao.mobile.MembroCorpo.MobileMembroCorpoIndex;

public class MobileHomeForm extends FormularioBase<Pessoa>
{
   private static final long serialVersionUID = -3231330308077618602L;

   @SpringBean
   private CorpoAS corpoAS;
   @SpringBean
   private PessoaAS pessoaAS;
   private Date dataCadastro;
   private Pessoa usuarioAtividade;

   private TextField<Date> campoData;

   private WebMarkupContainer containerLateral;

   private PageableListView<Pessoa> listaPessoa;

   private Label labelPessoa;

   public MobileHomeForm(final String id)
   {
      super(id);
      inicializar();
   }

   private void inicializar()
   {
      usuarioAtividade = getUsuarioLogado();
      criaBotoes();
      criaLabel();
      criaCampoData();
      criaModalLateral();
   }

   private void criaModalLateral()
   {
      containerLateral = new WebMarkupContainer("containerLateral")
      {
         private static final long serialVersionUID = 9185559717783344942L;

         @Override
         protected void onConfigure()
         {
            if (usuarioAtividade.getCargoEnum() != PermissaoEnum.INSTRUTOR)
            {
               setVisibilityAllowed(false);
            }
         }
      };
      containerLateral.setOutputMarkupId(true);
      addOrReplace(containerLateral);

      listaPessoa = new PageableListView<Pessoa>("listaPessoa", pessoaAS.listaPessa(), 5)
      {
         private static final long serialVersionUID = 1L;

         @Override
         protected void populateItem(final ListItem<Pessoa> item)
         {
            final AjaxLink<Pessoa> linkPessoa = new AjaxLink<Pessoa>("linkPessoa")
            {
               private static final long serialVersionUID = -4462625745278258235L;

               @Override
               public void onClick(final AjaxRequestTarget target)
               {
                  usuarioAtividade = item.getModelObject();
                  criaLabel();
                  target.add(labelPessoa);
               }
            };
            linkPessoa.add(new Label("lbnNome", item.getModelObject().getNomePessoa()));
            item.add(linkPessoa);
         }

         @Override
         public void renderHead(final IHeaderResponse response)
         {
            final String script = "$('#listview').listview().listview('refresh');";
            response.render(OnDomReadyHeaderItem.forScript(script));
         }

      };

      add(new WebMarkupContainer("containerBTN"){

         private static final long serialVersionUID = -5260343120905854698L;

         @Override
         protected void onConfigure()
         {
            if (usuarioAtividade.getCargoEnum() != PermissaoEnum.INSTRUTOR)
            {
               setVisibilityAllowed(false);
            }
         }
      });

      containerLateral.add(listaPessoa);

   }

   private void criaCampoData()
   {
      final WebMarkupContainer containerInput = new WebMarkupContainer("containerInput");
      campoData = new TextField<Date>("campoData", new PropertyModel<Date>(this, "dataCadastro"))
      {
         private static final long serialVersionUID = 8493880801180408785L;

         @Override
         protected void onConfigure()
         {
            setOutputMarkupId(true);

         }
      };
      containerInput.addOrReplace(campoData);
      add(containerInput);
   }

   private void criaLabel()
   {
      labelPessoa = new Label("lbnLogado", usuarioAtividade.getNomePessoa());
      labelPessoa.setOutputMarkupId(true);
      addOrReplace(labelPessoa);

   }

   private void criaBotoes()
   {

      addOrReplace(new AjaxSubmitLink("btnAvancar")
      {
         private static final long serialVersionUID = 1782016523465683542L;
         @Override
         protected void onSubmit(final AjaxRequestTarget target, final Form<?> form)
         {
            setResponsePage(new MobileMembroCorpoIndex(dataCadastro, usuarioAtividade));
         }
      });
   }
}
