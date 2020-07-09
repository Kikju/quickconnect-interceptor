package me.oldjing.quickconnect.test;

import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import me.oldjing.quickconnect.QuickConnectInterceptor;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

@RunWith(Parameterized.class)
public class QcTest {

    private String qcid;
    private String expectedResolvedUrl;

    public QcTest(String qcid, String expectedResolvedUrl) {
        this.qcid = qcid;
        this.expectedResolvedUrl = expectedResolvedUrl;
    }

    @Parameters(name = "{index}: {0} -> {1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"http://YourQuickConnectIdWhichCanBeUsedForIntegrationTest", null},
                {"https://YourQuickConnectIdWhichCanBeUsedForIntegrationTest", null},
        });
    }

    @Test
    public void resolveQuickConnectId() {
        Interceptor interceptor = new QuickConnectInterceptor(
                new OkHttpClient.Builder()
                        .addNetworkInterceptor(new HttpLoggingInterceptor()
                                .setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
        );
        Request request = new Request.Builder()
                .url(qcid)
                .build();

        try {
            Response response = interceptor.intercept(new Interceptor.Chain() {

                @Override
                public Request request() {
                    return request;
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
            Assert.assertNotNull(expectedResolvedUrl);
            Assert.assertEquals(expectedResolvedUrl, response.request().url().toString());
        } catch (Exception e) {
            e.printStackTrace();
            Assert.assertNull(expectedResolvedUrl);
            Assert.assertTrue(e instanceof IOException);
            Assert.assertEquals("No server info found!", e.getMessage());
        }

    }
}
