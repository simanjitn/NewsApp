package com.simanjit.dxminds.viewModel;

import android.util.Log;

import com.simanjit.dxminds.app.AppConstants;
import com.simanjit.dxminds.model.NewsResponse;
import com.simanjit.dxminds.networkApiCalls.APIService;
import com.simanjit.dxminds.networkApiCalls.DataApi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<NewsResponse> userResponseMutableLiveData;

    public LiveData<NewsResponse> getUserResponse() {

        userResponseMutableLiveData = new MutableLiveData<NewsResponse>();
        loadNewsDetail();

        return userResponseMutableLiveData;
    }


    public void loadNewsDetail() {

        DataApi dataApi = APIService.getClient().create(DataApi.class);

        dataApi.getTopHeadlines(AppConstants.COUNTRY, AppConstants.APIKEY).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, final Response<NewsResponse> response) {

                userResponseMutableLiveData.setValue(response.body());

            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.d("STATUS", "ResponseRequestFailed " + t);
            }
        });

    }

}
