package com.shc.androiddemocollection;

/**
 * Created by shuhuiChen on 2017/10/19 17:14.
 * Email: chenshuhui@corp.netease.com
 */

class Student {
    private Course[] courses;
    private String name;

    public Student(Course[] courses, String name) {
        this.courses = courses;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Course[] getCourses() {
        return courses;
    }
}
