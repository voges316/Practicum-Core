package com.airlink.model;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.airlink.model.domain.AllDomainTests;
import com.airlink.model.services.AllServiceTests;

@RunWith(Suite.class)
@SuiteClasses({AllDomainTests.class, AllServiceTests.class})
public class ApplicationTestSuite {

}
