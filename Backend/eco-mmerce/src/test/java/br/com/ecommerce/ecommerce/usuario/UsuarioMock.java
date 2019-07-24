package br.com.ecommerce.ecommerce.usuario;

import com.github.javafaker.Faker;

    public class UsuarioMock {
        public static Usuario getUsuarioMock(){
            Faker faker = new Faker();
            Usuario usuario = new Usuario();
            usuario.setLogin(faker.name().username());
            usuario.setPassword(faker.internet().password());
            return usuario;
        }
    }

