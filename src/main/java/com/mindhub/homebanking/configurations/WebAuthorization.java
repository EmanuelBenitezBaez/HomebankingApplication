package com.mindhub.homebanking.configurations;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity

@Configuration

class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                /*
                .antMatchers("/api/login","/api/logout").permitAll()
                .antMatchers("/web/index.html", "/web/css/**","/web/js/**", "/web/img/**").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .antMatchers(HttpMethod.GET, "/api/clients").hasAnyAuthority("ADMIN")
               // .antMatchers("/web/admin/**","/rest/**", "/h2-console/**").hasAuthority("ADMIN")
                .antMatchers("/web/accounts.html").hasAnyAuthority("ADMIN", "CLIENT")
                .antMatchers("/web/**").hasAnyAuthority("ADMIN", "CLIENT");
               */
                // Permitir acceso público a rutas de inicio de sesión y cierre de sesión
                .antMatchers("/api/login", "/api/logout").permitAll()
                // Permitir acceso público a recursos estáticos (CSS, JS, imágenes)
                .antMatchers("/web/index.html", "/web/css/**", "/web/js/**", "/web/img/**").permitAll()
                // Permitir registro de nuevos clientes
                .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
                // Requiere rol de ADMIN para ver la lista de clientes
                .antMatchers(HttpMethod.GET, "/api/clients").hasAnyAuthority("ADMIN")
                // Páginas de cuentas accesibles para ADMIN y CLIENT
                .antMatchers("/web/accounts.html").permitAll()
                // Acceso general a rutas web para ADMIN y CLIENT
                .antMatchers("/web/**").hasAnyAuthority("ADMIN", "CLIENT");
                // Cualquier otra solicitud requiere autenticación
                //.anyRequest().authenticated();



        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");



        http.logout().logoutUrl("/api/logout");




        // turn off checking for CSRF tokens

        http.csrf().disable();



        //disabling frameOptions so h2-console can be accessed

        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }



}
