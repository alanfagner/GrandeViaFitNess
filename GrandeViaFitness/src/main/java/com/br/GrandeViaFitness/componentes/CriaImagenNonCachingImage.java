package com.br.GrandeViaFitness.componentes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.resource.DynamicImageResource;

public class CriaImagenNonCachingImage
{

   public static NonCachingImage criaImagen(final String id, final String pathName)
   {
      return new NonCachingImage(id, new AbstractReadOnlyModel<DynamicImageResource>()
         {

            /**
             *
             */
            private static final long serialVersionUID = -6757567088449341462L;

            @Override
            public DynamicImageResource getObject()
            {
               final DynamicImageResource dir = new DynamicImageResource()
               {
                  private static final long serialVersionUID = 418992039772460815L;

                  @Override
                  protected byte[] getImageData(final Attributes attributes)
                  {

                     byte[] imageBytes = null;
                     if (pathName.isEmpty() == false)
                     {
                        imageBytes = CriaImagenNonCachingImage.getImageAsBytes(pathName);
                     }

                     return imageBytes;
                  }
               };
               dir.setFormat("image/png");
               return dir;
            }
      })
      {
         /**
          *
          */
         private static final long serialVersionUID = 7480786959610094023L;

         @Override
         protected void onConfigure()
         {
            setVisibilityAllowed(pathName != null);
         }
      };
   }

   private static byte[] getImageAsBytes(final String label)
   {
      final byte[] imageBytes = null;
      try
      {
         final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
         final InputStream inStream = new FileInputStream(new File(FormularioBase.getPathName() + "\\" + label));
         CriaImagenNonCachingImage.copy(inStream, outStream);
         inStream.close();
         outStream.close();
         return outStream.toByteArray();

      }
      catch (final IOException e)
      {
         e.printStackTrace();
      }
      return imageBytes;

   }

   private static void copy(final InputStream source, final OutputStream destination) throws IOException
   {

      final byte[] buf = new byte[1024];
      int len;
      while ((len = source.read(buf)) > 0)
      {
         destination.write(buf, 0, len);
      }
      source.close();
      destination.close();

   }
}
