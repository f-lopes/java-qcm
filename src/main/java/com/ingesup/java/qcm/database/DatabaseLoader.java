package com.ingesup.java.qcm.database;

import com.ingesup.java.qcm.entity.*;
import com.ingesup.java.qcm.service.CourseService;
import com.ingesup.java.qcm.service.GradeService;
import com.ingesup.java.qcm.service.RoleService;
import com.ingesup.java.qcm.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by lopes_f on 1/25/2015.
 * <florian.lopes@outlook.com>
 */
@Component
@EnableConfigurationProperties(value = {QcmDatabaseProperties.class, QcmAdministrationProperties.class})
public class DatabaseLoader {

	private UserService userService;

	private GradeService gradeService;

	private CourseService courseService;

	private RoleService roleService;

	@Autowired
	private QcmDatabaseProperties databaseProperties;

	@Autowired
	private QcmAdministrationProperties administrationProperties;

	@Autowired
	public DatabaseLoader(UserService userService, GradeService gradeService, CourseService courseService, RoleService roleService) {
		this.userService = userService;
		this.gradeService = gradeService;
		this.courseService = courseService;
		this.roleService = roleService;
	}

	@PostConstruct
	private void initDatabaseData() {
		if (databaseProperties.isInitialize()) {
			initRoles();
			initGrades();
			initCourses();
			initAdmin();
		}
	}

	private void initRoles() {
		Set<Role> roles = new HashSet<>();
		String[] rolesNames = StringUtils.split(databaseProperties.getRoles(), QcmDatabaseProperties.SEPARATOR);

		for (String roleName : rolesNames) {
			roles.add(new Role(roleName));
		}

		roleService.add(roles);
	}

	private void initGrades() {
		Set<Grade> grades = new HashSet<>();
		String[] gradesNames = StringUtils.split(administrationProperties.getGrades(), QcmAdministrationProperties.SEPARATOR);

		for (String gradeName : gradesNames) {
			grades.add(new Grade(gradeName));
		}

		gradeService.add(grades);
	}

	private void initCourses() {
		Set<Course> courses = new HashSet<>();
		String[] coursesNames = StringUtils.split(administrationProperties.getCourses(), QcmAdministrationProperties.SEPARATOR);

		for (String courseName : coursesNames) {
			courses.add(new Course(courseName));
		}

		courseService.add(courses);
	}

	private void initAdmin() {
		User adminUser = new User(administrationProperties.getFirstname(),
								  administrationProperties.getLastname(),
								  administrationProperties.getEmail(),
								  administrationProperties.getPassword());
		adminUser.addRole(RoleEnum.ROLE_ADMIN);

		userService.add(adminUser);
	}
}
