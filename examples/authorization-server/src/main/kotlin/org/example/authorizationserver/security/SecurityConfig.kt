package org.example.authorizationserver.security

import com.nimbusds.jose.jwk.JWKSet
import com.nimbusds.jose.jwk.RSAKey
import com.nimbusds.jose.jwk.source.ImmutableJWKSet
import com.nimbusds.jose.jwk.source.JWKSource
import com.nimbusds.jose.proc.SecurityContext
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.core.AuthorizationGrantType
import org.springframework.security.oauth2.core.ClientAuthenticationMethod
import org.springframework.security.oauth2.core.oidc.OidcScopes
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.util.*

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    @Throws(
        Exception::class
    )
    fun authorizationServerSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        // @formatter:off
        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http)
        http.getConfigurer(OAuth2AuthorizationServerConfigurer::class.java).oidc(Customizer.withDefaults()) // Enable OpenID Connect 1.0
        http
        .exceptionHandling{exceptions:ExceptionHandlingConfigurer<HttpSecurity?> -> exceptions
        .authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/login"))}
        .oauth2ResourceServer{resourceServer:OAuth2ResourceServerConfigurer<HttpSecurity?> -> resourceServer.jwt(Customizer.withDefaults())}
                // @formatter:on
        return http.build()
    }

    @Bean
    @Order(2)
    @Throws(Exception::class)
    fun standardSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        // @formatter:off
        http
        .authorizeHttpRequests(Customizer {authorize:AuthorizationManagerRequestMatcherRegistry -> authorize
        .anyRequest().authenticated()}
        )
        .formLogin(Customizer.withDefaults())
        
                // @formatter:on
        return http.build()
    }

    @Bean
    fun registeredClientRepository(): RegisteredClientRepository {
        // @formatter:off
         val loginClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("login-client")
        .clientSecret("{noop}openid-connect")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
        .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
        .redirectUri("http://127.0.0.1:8080/login/oauth2/code/login-client")
        .redirectUri("http://127.0.0.1:8080/authorized")
        .scope(OidcScopes.OPENID)
        .scope(OidcScopes.PROFILE)
        .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
        .build()
         val registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
        .clientId("messaging-client")
        .clientSecret("{noop}secret")
        .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
        .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
        .scope("message:read")
        .scope("message:write")
        .build()
        
                // @formatter:on
        return InMemoryRegisteredClientRepository(loginClient, registeredClient)
    }

    @Bean
    fun jwkSource(keyPair: KeyPair): JWKSource<SecurityContext> {
        val publicKey = keyPair.public as RSAPublicKey
        val privateKey = keyPair.private as RSAPrivateKey
        // @formatter:off
         val rsaKey = RSAKey.Builder(publicKey)
        .privateKey(privateKey)
        .keyID(UUID.randomUUID().toString())
        .build()
                // @formatter:on
        val jwkSet = JWKSet(rsaKey)
        return ImmutableJWKSet(jwkSet)
    }

    @Bean
    fun jwtDecoder(keyPair: KeyPair): JwtDecoder {
        return NimbusJwtDecoder.withPublicKey(keyPair.public as RSAPublicKey).build()
    }

    @Bean
    fun providerSettings(): AuthorizationServerSettings {
        return AuthorizationServerSettings.builder().issuer("http://localhost:9000").build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        // @formatter:off
         val userDetails = User.withDefaultPasswordEncoder()
        .username("user")
        .password("password")
        .roles("USER")
        .build()
        
                // @formatter:on
        return InMemoryUserDetailsManager(userDetails)
    }

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun generateRsaKey(): KeyPair {
        val keyPair: KeyPair
        try {
            val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
            keyPairGenerator.initialize(2048)
            keyPair = keyPairGenerator.generateKeyPair()
        } catch (ex: Exception) {
            throw IllegalStateException(ex)
        }
        return keyPair
    }
}
