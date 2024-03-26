package com.zzmin.jwtdemo22.config;

import com.zzmin.jwtdemo22.jwt.JwtAccessDeniedHandler;
import com.zzmin.jwtdemo22.jwt.JwtAuthenticationEntryPoint;
import com.zzmin.jwtdemo22.jwt.JwtSecurityConfig;
import com.zzmin.jwtdemo22.jwt.TokenProvider;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final CorsFilter corsFilter;

    public SecurityConfig(
            TokenProvider tokenProvider,
            JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint,
            JwtAccessDeniedHandler jwtAccessDeniedHandler,
            CorsFilter corsFilter
            ) {
        this.tokenProvider = tokenProvider;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtAccessDeniedHandler = jwtAccessDeniedHandler;
        this.corsFilter = corsFilter;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
                .csrf(AbstractHttpConfigurer::disable)

                // CORS 설정
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)

                // 예외페이지 설정
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .accessDeniedHandler(jwtAccessDeniedHandler)//필요한 권한이 없이 접근하려 할때 403 에러 발생
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)// 유효한 자격증명을 제공하지 않고 접근하려 할때 401 에러 발생
                )

                // 세션을 사용하지 않기 때문에 STATELESS로 설정
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 특정 엔드포인트를 인가 예외
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/api/signup").permitAll() // 회원가입
                                .requestMatchers("/api/authenticate").permitAll() // 로그인
                                .requestMatchers("/api/hello").permitAll() // test
                                .requestMatchers(PathRequest.toH2Console()).permitAll() // DB
                                .anyRequest().authenticated()
                )

                // enable h2-console
                .headers(headers ->
                        headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                )

                .with(new JwtSecurityConfig(tokenProvider), customizer -> {});

        return http.build();
    }

}
