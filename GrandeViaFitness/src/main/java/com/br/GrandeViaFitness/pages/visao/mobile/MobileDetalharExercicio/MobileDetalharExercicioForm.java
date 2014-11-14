package com.br.GrandeViaFitness.pages.visao.mobile.MobileDetalharExercicio;

import java.util.Date;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.model.CompoundPropertyModel;
import com.br.GrandeViaFitness.componentes.CriaImagenNonCachingImage;
import com.br.GrandeViaFitness.componentes.FeedBackPanelCustom;
import com.br.GrandeViaFitness.componentes.FormularioBase;
import com.br.GrandeViaFitness.model.Pessoa;
import com.br.GrandeViaFitness.model.TipoExercicio;
import com.br.GrandeViaFitness.pages.visao.mobile.ExecutaExercicio.MobileExecutarExercicioIndex;
import com.br.GrandeViaFitness.pages.visao.mobile.TipoExercicio.MobileTipoExercicoIndex;
import com.br.GrandeViaFitness.utilitario.Util;

public class MobileDetalharExercicioForm extends FormularioBase<TipoExercicio>
{

   private static final long serialVersionUID = 336643108380788892L;
   private final TipoExercicio tipoExercicio;
   private NonCachingImage image1;
   private NonCachingImage image2;
   private final Date dataCadastro;
   private final Pessoa usuarioAtividade;
   private FeedBackPanelCustom feedBack;

   public MobileDetalharExercicioForm(final String id, final TipoExercicio tipoExercicio, final Date dataCadastro,
      final Pessoa usuarioAtividade)
   {
      super(id, new CompoundPropertyModel<TipoExercicio>(tipoExercicio));
      this.usuarioAtividade = usuarioAtividade;
      this.tipoExercicio = tipoExercicio;
      this.dataCadastro = dataCadastro;
      inicializar();
   }

   private void inicializar()
   {
      addOrReplace(new AjaxLink<TipoExercicio>("btnVoltar")
      {
         private static final long serialVersionUID = 2774454734879746242L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new MobileTipoExercicoIndex(tipoExercicio.getCorpo(), dataCadastro, usuarioAtividade));
         }
      });

      addOrReplace(new AjaxLink<TipoExercicio>("btnExecutar")
      {
         private static final long serialVersionUID = 2774454734879746242L;

         @Override
         public void onClick(final AjaxRequestTarget target)
         {
            setResponsePage(new MobileExecutarExercicioIndex(tipoExercicio, dataCadastro, usuarioAtividade));
         }
      });

      criaLabel();
      criaImagen();
      criaFeedBack();
   }

   private void criaFeedBack()
   {
      feedBack = new FeedBackPanelCustom("feedback");
      feedBack.setOutputMarkupPlaceholderTag(true);

      addOrReplace(feedBack);
   }



   private void criaImagen()
   {
      image1 = CriaImagenNonCachingImage.criaImagen("image1", tipoExercicio.getFoto1());
      image2 = CriaImagenNonCachingImage.criaImagen("image2", tipoExercicio.getFoto2());
      addOrReplace(image1, image2);
   }

   private void criaLabel()
   {
      addOrReplace(new Label("lbnNomeExercicio", tipoExercicio.getNomeExercicio()));
      addOrReplace(new Label("lbnDescricao", tipoExercicio.getDescricaoExercicio()));
      addOrReplace(new Label("lbnLogado", usuarioAtividade.getNomePessoa()));
      addOrReplace(new Label("lbnDataSelecionada", Util.formataData(dataCadastro, "dd/MM/yyyy")));
   }

   @Override
   public void renderHead(final IHeaderResponse response)
   {
      super.renderHead(response);
      response.render(JavaScriptHeaderItem.forScript(
         "$(document).ready(function(){$('.bxslider') .cycle({fx: 'fade', speed: 'slow', timeout: 2000});});", "tes"));
   }
}
