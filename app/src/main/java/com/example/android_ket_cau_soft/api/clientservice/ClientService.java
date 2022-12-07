package com.example.android_ket_cau_soft.api.clientservice;


import com.example.android_ket_cau_soft.api.apiservice.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientService {

				private static final String BASE_URL = "https://ketcausoft.com/api/v1/";
				private static ClientService instance;

				private Retrofit retrofit = null;

				private ClientService() {
								//for singleton

				}

				public static  ClientService getInstance() {
								if (instance == null) {
												instance = new ClientService();
								}
								return instance;
				}

				public APIService getRetrofit() {
								if (retrofit == null) {
												Gson gson = new GsonBuilder()
																.setDateFormat("yyyy-MM-dd  HH-mm-ss")
																.create();

												OkHttpClient client = new OkHttpClient.Builder().callTimeout(30, TimeUnit.SECONDS).build();
												retrofit = new Retrofit.Builder()
																.baseUrl(BASE_URL)
																.addConverterFactory(GsonConverterFactory.create(gson))
																.client(client)
																.build();

								}
								return retrofit.create(APIService.class);

				}
}
