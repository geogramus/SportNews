package geogram.example.sportnews.ui.activityes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import org.json.JSONObject;

import javax.inject.Inject;

import geogram.example.sportnews.MyApplication;
import geogram.example.sportnews.rest.api.ApiTest;
import geogram.example.sportnews.rest.models.TestResponseModel;
import okhttp3.Headers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Inject
    ApiTest test;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getsApplicationComponent().inject(this);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);

        test.test(getApplicationContext().getPackageName(),
             tm.getSimOperatorName(), tm.getNetworkCountryIso(),
             tm.getNetworkOperator(), tm.getNetworkCountryIso(),
             android.os.Build.MANUFACTURER,android.os.Build.MODEL,
                "additional data"
                ).enqueue(new Callback<TestResponseModel>() {
            @Override
            public void onResponse(Call<TestResponseModel> call, Response<TestResponseModel> response) {
//        if(response.isSuccessful())
                    TestResponseModel model=response.body();
                   if(model!=null){
                    String urlBase= model.getUrl();
                    int end=urlBase.indexOf(".com/")+4;
                     url =urlBase.substring(0, end);
//                     startWeb(url);
                       startMain();
                   }else {

                       startMain();
                   }
            }

            @Override
            public void onFailure(Call<TestResponseModel> call, Throwable t) {
                startMain();
            }
        });

//        test.getRequestStatus().enqueue(new Callback<JSONObject>() {
//            @Override
//            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
//                Headers allHeaders = response.headers();
//
//               String headerValue;
//                switch (response.code()) {
//                    case 301:
//                        headerValue = allHeaders.get("Location");
//                        int end=headerValue.indexOf(".com/");
//                        String url =headerValue.substring(0, end);
//                        startWeb(url);
//                        break;
//                    case 302:
//                        headerValue = allHeaders.get("Location");
//                        int end1=headerValue.indexOf(".com/");
//                        String url1 =headerValue.substring(0, end1);
//                        startWeb(url1);
//                        break;
//                    default:
//                        startMain();
//                }
//
//
//            }
//
//            @Override
//            public void onFailure(Call<JSONObject> call, Throwable t) {
//                startMain();
//            }
//        });


    }

    public void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    public void startWeb(String url) {
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
        finish();
    }
}
