// package br.com.cancastilho;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import
// org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
// public class WebSecuityConfig extends WebSecurityConfigurerAdapter {
//
// @Autowired
// public void configureGlobal(AuthenticationManagerBuilder auth) throws
// Exception {
// auth.inMemoryAuthentication().withUser("admin").password("password").roles("ADMIN",
// "USER").and()
// .withUser("user").password("password").roles("USER");
// }
//
// }