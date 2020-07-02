package cuong.in.demoRestGet.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
public class Rest {

    @Value("${default.course.name}")
    private String cName;
    @Value("${default.course.chapterCount}")
    private int chaptersCount;

    @Autowired
    private CourseConfiguration courseConfiguration;


    //get data default from application.properties
    @RequestMapping("/defaultCourse")
    public  Course getDefaultCourse(@RequestParam(value="name", defaultValue = "Spring Boot", required= false ) String name,
                        @RequestParam(value = "chapterCount", defaultValue = "5", required = false) int chapterCount) {
        return  new Course(cName,chaptersCount);
    }
    //get data default from application-qa.properties
    @RequestMapping("/get")
    public HashMap<String,Object> getConfig() {
        HashMap<String,Object> map = new HashMap<String,Object>();

        map.put("name",courseConfiguration.getName());
        map.put("chapterCount",courseConfiguration.getChapterCount());
        map.put("rating",courseConfiguration.getRating());
        map.put("author",courseConfiguration.getAuthor());

        return map;
    }
    //Rest Get data
    @RequestMapping("/course")
    public  Course Rest(@RequestParam(value="name", defaultValue = "Spring Boot", required= false ) String name,
                        @RequestParam(value = "chapterCount", defaultValue = "5", required = false) int chapterCount) {
        return  new Course(name,chapterCount);
    }
    //Rest Post
    @RequestMapping(method = RequestMethod.POST, value = "/register/course")
    public String saveCourse(@RequestBody Course course) {
        return "You add name " +course.getName() + " with " + course.getChapterCount() + " success";
    }


}
