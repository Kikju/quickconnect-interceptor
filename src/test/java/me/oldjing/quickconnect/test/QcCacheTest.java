package me.oldjing.quickconnect.test;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import me.oldjing.quickconnect.QuickConnectInterceptor;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class QcCacheTest {

    private static String QC_ID_FOR_TESTING = "YourQuickConnectIdWhichCanBeUsedForIntegrationTest";
    private static String RESOLVED_IP = "192.168.0.1";

    @Test
    public void resolveQuickConnectId() throws IOException {
        Interceptor interceptor = new QuickConnectInterceptor();

        Request requestHttp = new Request.Builder().url("http://" + QC_ID_FOR_TESTING).build();
        Response responseHttp = interceptor.intercept(new Interceptor.Chain() {

            @Override
            public Request request() {
                return requestHttp;
            }

            @Override
            public Response proceed(Request request) throws IOException {
                return new Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .message("")
                        .build();
            }

            @Override
            public Connection connection() {
                throw new NotImplementedException();
            }

            @Override
            public int writeTimeoutMillis() {
                throw new NotImplementedException();
            }

            @NotNull
            @Override
            public Interceptor.Chain withWriteTimeout(int i, @NotNull TimeUnit timeUnit) {
                throw new NotImplementedException();
            }

            @NotNull
            @Override
            public Interceptor.Chain withReadTimeout(int i, @NotNull TimeUnit timeUnit) {
                throw new NotImplementedException();
            }

            @NotNull
            @Override
            public Interceptor.Chain withConnectTimeout(int i, @NotNull TimeUnit timeUnit) {
                throw new NotImplementedException();
            }

            @Override
            public int readTimeoutMillis() {
                throw new NotImplementedException();
            }

            @Override
            public int connectTimeoutMillis() {
                throw new NotImplementedException();
            }

            @NotNull
            @Override
            public Call call() {
                throw new NotImplementedException();
            }
        });
        Assert.assertEquals("http://" + RESOLVED_IP + ":5000/", responseHttp.request().url().toString());

        Request requestHttps = new Request.Builder().url("https://" + QC_ID_FOR_TESTING).build();
        Response responseHttps = interceptor.intercept(new Interceptor.Chain() {

            @Override
            public Request request() {
                return requestHttps;
            }

            @Override
            public Response proceed(Request request) throws IOException {
                return new Response.Builder()
                        .request(request)
                        .protocol(Protocol.HTTP_1_1)
                        .code(200)
                        .message("")
                        .build();
            }

            @Override
            public Connection connection() {
                throw new NotImplementedException();
            }

            @Override
            public int writeTimeoutMillis() {
                throw new NotImplementedException();
            }

            @NotNull
            @Override
            public Interceptor.Chain withWriteTimeout(int i, @NotNull TimeUnit timeUnit) {
                throw new NotImplementedException();
            }

            @NotNull
            @Override
            public Interceptor.Chain withReadTimeout(int i, @NotNull TimeUnit timeUnit) {
                throw new NotImplementedException();
            }

            @NotNull
            @Override
            public Interceptor.Chain withConnectTimeout(int i, @NotNull TimeUnit timeUnit) {
                throw new NotImplementedException();
            }

            @Override
            public int readTimeoutMillis() {
                throw new NotImplementedException();
            }

            @Override
            public int connectTimeoutMillis() {
                throw new NotImplementedException();
            }

            @NotNull
            @Override
            public Call call() {
                throw new NotImplementedException();
            }
        });
        Assert.assertEquals("https://" + RESOLVED_IP + ":5001/", responseHttps.request().url().toString());

    }
}
