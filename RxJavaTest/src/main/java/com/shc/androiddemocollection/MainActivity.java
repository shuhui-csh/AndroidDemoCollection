package com.shc.androiddemocollection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Student[] mStudents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStudents = new Student[5];
        Course[] courses = new Course[2];
        mStudents[0] = new Student(courses, "csh");
    }

    private void mapTest() {
        Observable.just(mStudents[0])
                .subscribeOn(Schedulers.io())
                .map(new Func1<Student, String>() {
                    @Override
                    public String call(Student students) {
                        return students.getName();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String name) {
                        Log.d("csh", name);
                    }
                });
    }

    private void flatMapTest() {

        Observable.from(mStudents)
                .flatMap(new Func1<Student, Observable<Course>>() {
                    @Override
                    public Observable<Course> call(Student student) {
                        Observable observable = Observable.from(student.getCourses());
                        return observable;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Course>() {
                    @Override
                    public void call(Course course) {
                        Log.d("csh", course.getName());
                    }
                });
    }
}
