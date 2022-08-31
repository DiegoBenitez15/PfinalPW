import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import urlrn.UrlRnGrpc;
import urlrn.UrlRnOuterClass;

import javax.net.ssl.SSLException;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class main {
    private static UrlRnGrpc.UrlRnBlockingStub urlInterfaz;

    public static UrlRnOuterClass.CrearUrlResponse crearURL(String longUrl, String usuario){
        return urlInterfaz.createUrl(UrlRnOuterClass.CrearUrlRequest.newBuilder().setLongUrl(longUrl).setUsuario(usuario).build());
    }

    public static UrlRnOuterClass.listarUrlsResponse listarURL(String usuario,String fecha){
        return urlInterfaz.listarUrl(UrlRnOuterClass.listarUrlsRequest.newBuilder().setUsuario(usuario).setFecha(fecha).build());
    }

    public static void main(String[] args) throws SSLException {
        String host = "localhost";
        int port = 9090;

        File trustCertCollectionFile = new File("src/main/resources/keys/server.pem");
        SslContext sslContext = GrpcSslContexts.forClient()
                .trustManager(trustCertCollectionFile)
                .build();

        ManagedChannel channel = NettyChannelBuilder.forAddress(host, port)
                .overrideAuthority("localhost")
                .sslContext(sslContext)
                .build();

        urlInterfaz = UrlRnGrpc.newBlockingStub(channel);
        String hoy = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        System.out.println("/////////////CREAR URL////////////////\n" + crearURL("https://www.google.com","admin"));
        System.out.println("////////////LISTAR URLS///////////////\n" + listarURL("admin",hoy));
    }
}
