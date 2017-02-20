package com.airlink.model.services;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.airlink.model.services.employees.EmployeeSvcJPAImplTest;
import com.airlink.model.services.jobs.JobSvcJPAImplTest;

@RunWith(Suite.class)
@SuiteClasses({EmployeeSvcJPAImplTest.class, JobSvcJPAImplTest.class})
public class AllServiceTests {

}
