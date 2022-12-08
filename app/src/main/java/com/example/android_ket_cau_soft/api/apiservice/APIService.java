package com.example.android_ket_cau_soft.api.apiservice;


import com.example.android_ket_cau_soft.api.request.CheckTokenReques;
import com.example.android_ket_cau_soft.api.request.LoginRequest;
import com.example.android_ket_cau_soft.api.request.LogoutRequest;
import com.example.android_ket_cau_soft.api.request.ResetPasswordRequest;
import com.example.android_ket_cau_soft.api.request.SaveInforRequest;
import com.example.android_ket_cau_soft.api.request.SignUpRequest;
import com.example.android_ket_cau_soft.api.response.DetailLessonResponse;
import com.example.android_ket_cau_soft.api.response.exam.GetQuestionResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.hot_courses.CourseResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.news.HotNewsResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.notification.MarkAsReadedResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.notification.NotificationResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.setting.CheckTokenResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.setting.LogoutResponse;
import com.example.android_ket_cau_soft.api.response.họmefrgres.setting.SaveInfoResponse;
import com.example.android_ket_cau_soft.api.response.loginresponse.LoginResponse;
import com.example.android_ket_cau_soft.api.response.loginresponse.ResetPasswordResponse;
import com.example.android_ket_cau_soft.api.response.loginresponse.SignUpResponse;
import com.example.android_ket_cau_soft.api.response.material.GetLNaturalDataResponse;
import com.example.android_ket_cau_soft.api.response.material.GetLiveLoadDataResponse;
import com.example.android_ket_cau_soft.api.response.material.GetRawMaterialDataResponse;
import com.example.android_ket_cau_soft.api.response.topic.GetEduTopicResponse;
import com.example.android_ket_cau_soft.model.lesson.LessonResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    //dang ky
    @POST("signin")
    Call<SignUpResponse> singingUp(@Body SignUpRequest req);

    //dang nhap
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest req);

    //reset pass
    @POST("reset-password")
    Call<ResetPasswordResponse> resetPassWord(@Body ResetPasswordRequest req);

    //lay tin tuc moi
    @GET("get-news")
    Call<HotNewsResponse> getHotNews();

    //lay thong tin khoa hoc hot
    @GET("get-edu-courses-hot")
    Call<CourseResponse> getHotCourses();

    //lay thong tin khoa hoc moi
    @GET("get-edu-courses-new")
    Call<CourseResponse> getNewCourses();

    //lay noi dung khoa hoc
    @GET("get-edu-course-info/{id}")
    Call<LessonResponse> getCourseContent(@Path("id") String courseId);

    //thay doi thong tin usser
    @POST("update-info")
    Call<SaveInfoResponse> updateUser(@Body SaveInforRequest saveInforRequest);

    //kiem tra token truoc moi request su dung token
    @POST("check-token")
    Call<CheckTokenResponse> checkToken(@Body CheckTokenReques checkTokenReques);

    //dang xuat
    @POST("logout")
    Call<LogoutResponse> logOut(@Body LogoutRequest logoutRequest);

    //lay danh sach thong bao
    @GET("get-notis")
    Call<NotificationResponse> getNotification(@Query("api_token") String api_token);


    @GET("set-notis-readed/{id}")
    Call<MarkAsReadedResponse> markAsReaded(@Path("id") int id, @Query("api_token") String api_token);


    @GET("get-edu-catalogs")
    Call<GetEduTopicResponse> getEduTopic();

    @GET("get-edu-catalog-courses/{id}")
    Call<CourseResponse> getDetailCourseByTopic(@Path("id") int id);

    @GET("get-edu-courses-me?api_token=")
    Call<CourseResponse> getMyCourse(@Query("api_token") String api_token);

    @GET("get-edu-lesson-info/{id}")
    Call<DetailLessonResponse> getDetailOfLesson(@Path("id") int id, @Query("api_token") String api_token);

    @GET("get-sathach-cchn?isfull=")
    Call<GetQuestionResponse> getExamQuestion(@Query("isfull") int examType);


    @GET("get-tracuu-hoattai")
    Call<GetLiveLoadDataResponse> getLiveLoadData();


    @GET("get-tracuu-vatlieu")
    Call<GetRawMaterialDataResponse> getRawMaterialData();

    @GET("get-tracuu-solieutunhien")
    Call<GetLNaturalDataResponse> getNaturalData();
}
